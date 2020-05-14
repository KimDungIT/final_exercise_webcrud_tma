package com.tma.apa.training.device.mgmt.impl.dao;

import com.tma.apa.training.device.mgmt.dao.DeviceDAO;
import com.tma.apa.training.device.mgmt.entity.Device;
import com.tma.apa.training.device.mgmt.entity.DeviceHolder;
import com.tma.apa.training.device.mgmt.impl.persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class DeviceDAOImpl implements DeviceDAO {

    private EntityManager m_entityManager;

    public DeviceDAOImpl(PersistenceUtil persistenceUtil) {
        m_entityManager = persistenceUtil.getEntityManager();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Device> getAll() {
        TypedQuery<Device> query = m_entityManager.createQuery("SELECT d FROM Device d", Device.class);
        return query.getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Device> getByDeviceHolder(DeviceHolder deviceHolder, int page, int limit) {
        // TODO: only use device-holder name
        TypedQuery<Device> query = m_entityManager.createQuery("SELECT d FROM Device d WHERE "
                + "d.deviceHolder = :deviceHolder", Device.class);
        query.setParameter("deviceHolder", deviceHolder);
        query.setFirstResult((page - 1) * limit);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Device getByName(String name) {
        TypedQuery<Device> query = m_entityManager.createQuery("SELECT d FROM Device d WHERE "
                + "d.name = :name", Device.class);
        query.setParameter("name", name);
        return query.getResultList().isEmpty() ? null : query.getSingleResult();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void add(Device device) {
        m_entityManager.persist(device);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void update(Device device, Device deviceDB) {
        device.setHardwareType(deviceDB.getHardwareType());
        device.setInterfaceVersion(deviceDB.getInterfaceVersion());
        device.setConnectionMechanism(deviceDB.getConnectionMechanism());
        m_entityManager.merge(device);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void delete(String name) {
        Device device = getByName(name);
        // TODO: device can be null
        m_entityManager.remove(device);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteByDeviceHolder(DeviceHolder deviceHolder) {
        // TODO: only use device-holder name
        m_entityManager.createQuery("DELETE FROM Device d WHERE d.deviceHolder = :deviceHolder")
                .setParameter("deviceHolder", deviceHolder).executeUpdate();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteAll() {
        m_entityManager.createQuery("DELETE FROM Device").executeUpdate();
    }

    @Override
    public Long countRowsByDeviceHolder(DeviceHolder deviceHolder) {
        Query query = m_entityManager.createQuery("SELECT count(d) FROM Device d WHERE "
                + "d.deviceHolder = :deviceHolder");
        query.setParameter("deviceHolder", deviceHolder);
        return (Long)query.getSingleResult();
    }


}