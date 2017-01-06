package com.wizecore.tomcat.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

/**
 * @author Sebastian Hennebrueder
 */
@ApplicationScoped
public class EntityManagerDelegate implements EntityManager {

    @Inject
    private EntityManagerStore store;

    @Override
    public void persist(Object entity) {
        store.get().persist(entity);
    }

    @Override
    public <T> T merge(T entity) {
        return store.get().merge(entity);
    }

    @Override
    public void remove(Object entity) {
        store.get().remove(entity);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return store.get().find(entityClass, primaryKey);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
        return store.get().find(entityClass, primaryKey, properties);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
        return store.get().find(entityClass, primaryKey, lockMode);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
        return store.get().find(entityClass, primaryKey, lockMode, properties);
    }

    @Override
    public <T> T getReference(Class<T> entityClass, Object primaryKey) {
        return store.get().getReference(entityClass, primaryKey);
    }

    @Override
    public void flush() {
        store.get().flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushMode) {
        store.get().setFlushMode(flushMode);
    }

    @Override
    public FlushModeType getFlushMode() {
        return store.get().getFlushMode();
    }

    @Override
    public void lock(Object entity, LockModeType lockMode) {
        store.get().lock(entity, lockMode);
    }

    @Override
    public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        store.get().lock(entity, lockMode, properties);
    }

    @Override
    public void refresh(Object entity) {
        store.get().refresh(entity);
    }

    @Override
    public void refresh(Object entity, Map<String, Object> properties) {
        store.get().refresh(entity, properties);
    }

    @Override
    public void refresh(Object entity, LockModeType lockMode) {
        store.get().refresh(entity, lockMode);
    }

    @Override
    public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        store.get().refresh(entity, lockMode, properties);
    }

    @Override
    public void clear() {
        store.get().clear();
    }

    @Override
    public void detach(Object entity) {
        store.get().detach(entity);
    }

    @Override
    public boolean contains(Object entity) {
        return store.get().contains(entity);
    }

    @Override
    public LockModeType getLockMode(Object entity) {
        return store.get().getLockMode(entity);
    }

    @Override
    public void setProperty(String propertyName, Object value) {
        store.get().setProperty(propertyName, value);
    }

    @Override
    public Map<String, Object> getProperties() {
        return store.get().getProperties();
    }

    @Override
    public Query createQuery(String qlString) {
        return store.get().createQuery(qlString);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return store.get().createQuery(criteriaQuery);
    }

    @Override
    public Query createQuery(CriteriaUpdate updateQuery) {
        return store.get().createQuery(updateQuery);
    }

    @Override
    public Query createQuery(CriteriaDelete deleteQuery) {
        return store.get().createQuery(deleteQuery);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
        return store.get().createQuery(qlString, resultClass);
    }

    @Override
    public Query createNamedQuery(String name) {
        return store.get().createNamedQuery(name);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
        return store.get().createNamedQuery(name, resultClass);
    }

    @Override
    public Query createNativeQuery(String sqlString) {
        return store.get().createNativeQuery(sqlString);
    }

    @Override
    public Query createNativeQuery(String sqlString, Class resultClass) {
        return store.get().createNativeQuery(sqlString, resultClass);
    }

    @Override
    public Query createNativeQuery(String sqlString, String resultSetMapping) {
        return store.get().createNativeQuery(sqlString, resultSetMapping);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
        return store.get().createNamedStoredProcedureQuery(name);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
        return store.get().createStoredProcedureQuery(procedureName);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class[] resultClasses) {
        return store.get().createStoredProcedureQuery(procedureName, resultClasses);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
        return store.get().createStoredProcedureQuery(procedureName, resultSetMappings);
    }

    @Override
    public void joinTransaction() {
        store.get().joinTransaction();
    }

    @Override
    public boolean isJoinedToTransaction() {
        return store.get().isJoinedToTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> cls) {
        return store.get().unwrap(cls);
    }

    @Override
    public Object getDelegate() {
        return store.get().getDelegate();
    }

    @Override
    public void close() {
        store.get().close();
    }

    @Override
    public boolean isOpen() {
        return store.get().isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return store.get().getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return store.get().getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return store.get().getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return store.get().getMetamodel();
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
        return store.get().createEntityGraph(rootType);
    }

    @Override
    public EntityGraph<?> createEntityGraph(String graphName) {
        return store.get().createEntityGraph(graphName);
    }

    @Override
    public EntityGraph<?> getEntityGraph(String graphName) {
        return store.get().getEntityGraph(graphName);
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
        return store.get().getEntityGraphs(entityClass);
    }
}