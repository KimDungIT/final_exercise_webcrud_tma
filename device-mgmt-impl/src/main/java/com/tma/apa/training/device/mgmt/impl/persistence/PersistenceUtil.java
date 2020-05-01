package com.tma.apa.training.device.mgmt.impl.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistenceUtil {

    @PersistenceContext(unitName = "device-hibernate")
    private EntityManager m_entityManager;

    public EntityManager getEntityManager() {
      return m_entityManager;
    };

    public void setEntityManager(EntityManager entityManager) {
        this.m_entityManager = entityManager;
    }
}