package impl.service;

import com.tma.apa.training.device.mgmt.dao.DeviceDAO;
import com.tma.apa.training.device.mgmt.dao.DeviceHolderDAO;
import com.tma.apa.training.device.mgmt.entity.ConnectionMechanism;
import com.tma.apa.training.device.mgmt.entity.Device;
import com.tma.apa.training.device.mgmt.entity.DeviceHolder;
import com.tma.apa.training.device.mgmt.exception.DeviceException;
import com.tma.apa.training.device.mgmt.exception.DeviceHolderException;
import com.tma.apa.training.device.mgmt.impl.service.DeviceServiceImpl;
import com.tma.apa.training.device.mgmt.service.DeviceService;
import com.tma.apa.training.device.mgmt.vo.DevicePagingVO;
import com.tma.apa.training.device.mgmt.vo.DeviceVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class DeviceServiceImplTest {

    private DeviceDAO m_deviceDao;
    private DeviceHolderDAO m_deviceHolderDao;
    private DeviceService m_deviceService;

    @Before
    public void setUp() {
        m_deviceDao = Mockito.mock(DeviceDAO.class);
        m_deviceHolderDao = Mockito.mock(DeviceHolderDAO.class);
        m_deviceService = new DeviceServiceImpl(m_deviceDao, m_deviceHolderDao);
    }

    @Test
    public void testGetAll() {
        DeviceHolder deviceHolders = new DeviceHolder("holder1");
        List<Device> devices = new ArrayList<>();

        Device device1 = new Device();
        device1.setName("device1");
        device1.setHardwareType("G.FAST");
        device1.setInterfaceVersion("1.0");
        device1.setConnectionMechanism(ConnectionMechanism.call_home);
        device1.setDeviceHolder(deviceHolders);

        Device device2 = new Device();
        device2.setName("device2");
        device2.setHardwareType("G.FAST");
        device2.setInterfaceVersion("2.0");
        device2.setConnectionMechanism(ConnectionMechanism.non_call_home);
        device2.setDeviceHolder(deviceHolders);

        devices.add(device1);
        devices.add(device2);

        deviceHolders.getDevices().add(device1);
        deviceHolders.getDevices().add(device2);

        Mockito.when(m_deviceDao.getAll()).thenReturn(devices);

        List<DeviceVO> response = m_deviceService.getAll();
        Assert.assertEquals(2, response.size());

        DeviceVO deviceVO1 = response.get(0);
        Assert.assertEquals("device1", deviceVO1.getName());
        Assert.assertEquals("G.FAST", deviceVO1.getHardwareType());
        Assert.assertEquals("1.0", deviceVO1.getInterfaceVersion());
        Assert.assertEquals("Call home", deviceVO1.getConnectionMechanism());

        DeviceVO deviceVO2 = response.get(1);
        Assert.assertEquals("device2", deviceVO2.getName());
        Assert.assertEquals("G.FAST", deviceVO2.getHardwareType());
        Assert.assertEquals("2.0", deviceVO2.getInterfaceVersion());
        Assert.assertEquals("Non Callhome", deviceVO2.getConnectionMechanism());
    }

    @Test
    public void testGetByDeviceHolder_InvalidInput() {
        DeviceHolder deviceHolders = new DeviceHolder("holder1");
        List<Device> devices = new ArrayList<>();

        Device device1 = new Device();
        device1.setName("device1");
        device1.setHardwareType("G.FAST");
        device1.setInterfaceVersion("1.0");
        device1.setConnectionMechanism(ConnectionMechanism.call_home);
        device1.setDeviceHolder(deviceHolders);

        Device device2 = new Device();
        device2.setName("device2");
        device2.setHardwareType("G.FAST");
        device2.setInterfaceVersion("2.0");
        device2.setConnectionMechanism(ConnectionMechanism.non_call_home);
        device2.setDeviceHolder(deviceHolders);

        devices.add(device1);
        devices.add(device2);

        deviceHolders.getDevices().add(device1);
        deviceHolders.getDevices().add(device2);

        Mockito.when(m_deviceHolderDao.getByName("holder1")).thenReturn(deviceHolders);
        Mockito.when(m_deviceDao.getByDeviceHolder(deviceHolders, 1, 20)).thenReturn(devices);
        Mockito.when(m_deviceDao.countRowsByDeviceHolder(deviceHolders)).thenReturn(2L);

        DevicePagingVO response = m_deviceService.getByDeviceHolder("holder1", -1, -1);
        Assert.assertEquals(2, response.getTotal().longValue());

        DeviceVO deviceVO1 = response.getDeviceVOS().get(0);
        Assert.assertEquals("device1", deviceVO1.getName());
        Assert.assertEquals("G.FAST", deviceVO1.getHardwareType());
        Assert.assertEquals("1.0", deviceVO1.getInterfaceVersion());
        Assert.assertEquals("Call home", deviceVO1.getConnectionMechanism());

        DeviceVO deviceVO2 = response.getDeviceVOS().get(1);
        Assert.assertEquals("device2", deviceVO2.getName());
        Assert.assertEquals("G.FAST", deviceVO2.getHardwareType());
        Assert.assertEquals("2.0", deviceVO2.getInterfaceVersion());
        Assert.assertEquals("Non Callhome", deviceVO2.getConnectionMechanism());
    }

    @Test
    public void testGetByDeviceHolder_ValidInput() {
        DeviceHolder deviceHolders = new DeviceHolder("holder1");
        List<Device> devices = new ArrayList<>();

        Device device1 = new Device();
        device1.setName("device1");
        device1.setHardwareType("G.FAST");
        device1.setInterfaceVersion("1.0");
        device1.setConnectionMechanism(ConnectionMechanism.call_home);
        device1.setDeviceHolder(deviceHolders);

        Device device2 = new Device();
        device2.setName("device2");
        device2.setHardwareType("G.FAST");
        device2.setInterfaceVersion("2.0");
        device2.setConnectionMechanism(ConnectionMechanism.non_call_home);
        device2.setDeviceHolder(deviceHolders);

        devices.add(device1);
        devices.add(device2);

        deviceHolders.getDevices().add(device1);
        deviceHolders.getDevices().add(device2);

        Mockito.when(m_deviceHolderDao.getByName("holder1")).thenReturn(deviceHolders);
        Mockito.when(m_deviceDao.getByDeviceHolder(deviceHolders, 1, 5)).thenReturn(devices);
        Mockito.when(m_deviceDao.countRowsByDeviceHolder(deviceHolders)).thenReturn(2L);

        DevicePagingVO response = m_deviceService.getByDeviceHolder("holder1", 1, 5);
        Assert.assertEquals(2, response.getTotal().longValue());

        DeviceVO deviceVO1 = response.getDeviceVOS().get(0);
        Assert.assertEquals("device1", deviceVO1.getName());
        Assert.assertEquals("G.FAST", deviceVO1.getHardwareType());
        Assert.assertEquals("1.0", deviceVO1.getInterfaceVersion());
        Assert.assertEquals("Call home", deviceVO1.getConnectionMechanism());

        DeviceVO deviceVO2 = response.getDeviceVOS().get(1);
        Assert.assertEquals("device2", deviceVO2.getName());
        Assert.assertEquals("G.FAST", deviceVO2.getHardwareType());
        Assert.assertEquals("2.0", deviceVO2.getInterfaceVersion());
        Assert.assertEquals("Non Callhome", deviceVO2.getConnectionMechanism());
    }

    @Test
    public void testGetByName() {
        Device device1 = new Device();
        device1.setName("device1");
        device1.setHardwareType("G.FAST");
        device1.setInterfaceVersion("1.0");
        device1.setConnectionMechanism(ConnectionMechanism.call_home);
        device1.setDeviceHolder(Mockito.mock(DeviceHolder.class));

        Mockito.when(m_deviceDao.getByName("device1")).thenReturn(device1);

        DeviceVO deviceVO = m_deviceService.getByName("device1");

        Assert.assertEquals("device1", deviceVO.getName());
        Assert.assertEquals("G.FAST", deviceVO.getHardwareType());
        Assert.assertEquals("1.0", deviceVO.getInterfaceVersion());
        Assert.assertEquals("Call home", deviceVO.getConnectionMechanism());
    }

    @Test
    public void testGetByName_DeviceNotFound() {
        DeviceVO deviceVO = m_deviceService.getByName("device1");
        Assert.assertNull(deviceVO);
    }

    @Test
    public void testDeleteAll() {
        m_deviceService.deleteAll();
        Mockito.verify(m_deviceDao).deleteAll();
    }

    @Test
    public void testDeleteByName_DeviceDoesNotExist() {
        Mockito.when(m_deviceDao.getByName("device1")).thenReturn(null);
        try {
            m_deviceService.delete("device1");
        } catch (DeviceException e) {
            Assert.assertEquals("Device device1 does not exists", e.getMessage());
        }
    }

    @Test
    public void testDeleteByName_DeviceAlreadyExist() {
        Device device1 = new Device();
        device1.setName("device1");
        device1.setHardwareType("G.FAST");
        device1.setInterfaceVersion("1.0");
        device1.setConnectionMechanism(ConnectionMechanism.call_home);
        Mockito.when(m_deviceDao.getByName("device1")).thenReturn(device1);

        m_deviceService.delete("device1");
        Mockito.verify(m_deviceDao).delete("device1");
    }

    @Test
    public void testAdd_NullDeviceVO() {
        try {
            m_deviceService.add(null);
            fail();
        } catch (DeviceException e) {
            Assert.assertEquals("Device name is null", e.getMessage());
        }
    }

    @Test
    public void testAdd_DeviceNameIsEmpty() {
        try {
            DeviceVO deviceVO = new DeviceVO();
            deviceVO.setName("");
            deviceVO.setDeviceHolderName("holder1");
            m_deviceService.add(deviceVO);
            fail();
        } catch (DeviceException e) {
            Assert.assertEquals("Device name is null", e.getMessage());
        }
    }

    @Test
    public void testAdd_DeviceHolderDoesNotExist() {
        try {
            Mockito.when (m_deviceHolderDao.getByName("holder1")).thenReturn(null);
            DeviceVO deviceVO = new DeviceVO();
            deviceVO.setName("device1");
            deviceVO.setDeviceHolderName("holder1");
            m_deviceService.add(deviceVO);
            fail();
        } catch (DeviceHolderException e) {
            Assert.assertEquals("Device holder holder1 does not exists", e.getMessage());
        }
    }

    @Test
    public void testAdd_DeviceAlreadyExist() {
        try {
            Mockito.when (m_deviceDao.getByName("device1")).thenReturn(Mockito.mock(Device.class));
            Mockito.when (m_deviceHolderDao.getByName("holder1")).thenReturn(Mockito.mock(DeviceHolder.class));
            DeviceVO deviceVO = new DeviceVO();
            deviceVO.setName("device1");
            deviceVO.setDeviceHolderName("holder1");
            m_deviceService.add(deviceVO);
            fail();
        } catch (DeviceException e) {
            Assert.assertEquals("Device device1 already exists", e.getMessage());
        }
    }

    @Test
    public void testAdd_MandatoryValueAreMissing() {
        try {
            Mockito.when (m_deviceDao.getByName("device1")).thenReturn(null);
            Mockito.when (m_deviceHolderDao.getByName("holder1")).thenReturn(Mockito.mock(DeviceHolder.class));
            DeviceVO deviceVO = new DeviceVO();
            deviceVO.setName("device1");
            deviceVO.setDeviceHolderName("holder1");
            m_deviceService.add(deviceVO);
            fail();
        } catch (DeviceException e) {
            Assert.assertEquals("Insert device device1 failed due to hardwareType, interfaceVersion or connectionMechanism are missing", e.getMessage());
        }
    }

    @Test
    public void testAdd_SuccessCase() {
        Mockito.when (m_deviceDao.getByName("device1")).thenReturn(null);
        Mockito.when (m_deviceHolderDao.getByName("holder1")).thenReturn(Mockito.mock(DeviceHolder.class));

        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setName("device1");
        deviceVO.setDeviceHolderName("holder1");
        deviceVO.setHardwareType("G.FAST");
        deviceVO.setInterfaceVersion("1.0");
        deviceVO.setConnectionMechanism("Call home");
        m_deviceService.add(deviceVO);

        ArgumentCaptor<Device> captor = ArgumentCaptor.forClass(Device.class);
        Mockito.verify(m_deviceDao).add(captor.capture());
        Assert.assertEquals("device1", captor.getValue().getName());
    }

}
