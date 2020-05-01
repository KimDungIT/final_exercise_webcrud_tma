package com.tma.apa.training.device.mgmt.impl.dao;

import com.tma.apa.training.device.mgmt.dao.DeviceHolderDAO;
import com.tma.apa.training.device.mgmt.entity.DeviceHolder;
import com.tma.apa.training.device.mgmt.impl.persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class DeviceHolderDAOImpl implements DeviceHolderDAO {

    private EntityManager m_entityManager;

    public DeviceHolderDAOImpl(PersistenceUtil persistenceUtil) {
        this.m_entityManager = persistenceUtil.getEntityManager();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DeviceHolder> getAll(int page, int limit) {
        Query query = m_entityManager.createQuery("From DeviceHolder");
        query.setFirstResult((page-1) * limit);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public DeviceHolder getByName(String name) {
        return m_entityManager.find(DeviceHolder.class, name);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void add(DeviceHolder deviceHolder) {
        m_entityManager.persist(deviceHolder);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteAll() {
        Query query = m_entityManager.createQuery("DELETE DeviceHolder");
        query.executeUpdate();
    }

    @Override
    public Long countRows() {
        Query query = m_entityManager.createQuery("Select count(h) From DeviceHolder h");
        return (Long)query.getSingleResult();
    }

}