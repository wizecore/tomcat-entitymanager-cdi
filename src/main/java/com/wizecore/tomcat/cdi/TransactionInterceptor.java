package com.wizecore.tomcat.cdi;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * A simple transaction interceptor which registers an entity mangager in a ThreadLocal and unregisters after the
 * method was called.
 * It does not support any kind of context propagation. If a transactional method calls another's bean transactional
 * method a new entity manager is created and added to the stack.
 *
 * @author Sebastian Hennebrueder
 */
@Interceptor
@Transactional
public class TransactionInterceptor {
    @Inject
    private EntityManagerStoreImpl entityManagerStore;
    private Logger logger = Logger.getLogger(TransactionInterceptor.class.getName());

    @AroundInvoke
    public Object runInTransaction(InvocationContext invocationContext) throws Exception {
        EntityManager em = entityManagerStore.createAndRegister();
        Object result;
        try {
            em.getTransaction().begin();
            result = invocationContext.proceed();
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                    logger.fine("Rolled back transaction");
                }
            } catch (Exception ee) {
                logger.warning("Rollback of transaction failed -> " + ee);
            }

            throw e;
        } finally {
            if (em != null) {
                entityManagerStore.unregister(em);
                em.close();
            }
        }

        return result;
    }
}