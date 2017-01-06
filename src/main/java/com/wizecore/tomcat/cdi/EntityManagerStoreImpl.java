package com.wizecore.tomcat.cdi;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * A store for entity managers. It is basically a ThreadLocal which stores the entity manager.
 * The {@link TransactionInterceptor} is expected to register entity manager. The application code
 * can get the current entity manager either by injecting the store or the {@link EntityManagerDelegate}.
 *
 * @author Sebastian Hennebrueder
 */
@ApplicationScoped
public class EntityManagerStoreImpl implements EntityManagerStore {

   final Logger logger = Logger.getLogger(EntityManagerStoreImpl.class.getName());

   private EntityManagerFactory emf;

   private ThreadLocal<Stack<EntityManager>> emStackThreadLocal = new ThreadLocal<Stack<EntityManager>>();

   @PostConstruct
   public void init() {
      emf = Persistence.createEntityManagerFactory("default");
   }

   @Override
   public EntityManager get() {
      logger.fine("Getting the current entity manager");
      final Stack<EntityManager> entityManagerStack = emStackThreadLocal.get();
      if (entityManagerStack == null || entityManagerStack.isEmpty()) {
         /* if nothing is found, we return null to cause a NullPointer exception in the business code.
         This leeds to a nicer stack trace starting with client code.
          */

         logger.warning("No entity manager was found. Did you forget to mark your method " +
               "as transactional?");

         return null;
      } else
         return entityManagerStack.peek();
   }

   /**
    * Creates an entity manager and stores it in a stack. The use of a stack allows to implement
    * transaction with a 'requires new' behaviour.
    *
    * @return the created entity manager
    */
   @Override
   public EntityManager createAndRegister() {
      logger.fine("Creating and registering an entity manager");
      Stack<EntityManager> entityManagerStack = emStackThreadLocal.get();
      if (entityManagerStack == null) {
         entityManagerStack = new Stack<EntityManager>();
         emStackThreadLocal.set(entityManagerStack);
      }

      final EntityManager entityManager = emf.createEntityManager();
      entityManagerStack.push(entityManager);
      return entityManager;
   }

   /**
    * Removes an entity manager from the thread local stack. It needs to be created using the
    * {@link #createAndRegister()} method.
    *
    * @param entityManager - the entity manager to remove
    * @throws IllegalStateException in case the entity manager was not found on the stack
    */
   @Override
   public void unregister(EntityManager entityManager) {
      logger.fine("Unregistering an entity manager");
      final Stack<EntityManager> entityManagerStack = emStackThreadLocal.get();
      if (entityManagerStack == null || entityManagerStack.isEmpty())
         throw new IllegalStateException("Removing of entity manager failed. Your entity manager was not found.");

      if (entityManagerStack.peek() != entityManager)
         throw new IllegalStateException("Removing of entity manager failed. Your entity manager was not found.");
      entityManagerStack.pop();
   }
}