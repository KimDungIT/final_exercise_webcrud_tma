package impl.service;

import com.tma.apa.training.device.mgmt.dao.DeviceHolderDAO;
import com.tma.apa.training.device.mgmt.entity.Device;
import com.tma.apa.training.device.mgmt.entity.DeviceHolder;
import com.tma.apa.training.device.mgmt.exception.DeviceHolderException;
import com.tma.apa.training.device.mgmt.impl.service.DeviceHolderServiceImpl;
import com.tma.apa.training.device.mgmt.vo.DeviceHolderPagingVO;
import com.tma.apa.training.device.mgmt.vo.DeviceHolderVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

public class DeviceHolderServiceImplTest {

    public static final String HOLDER_1 = "holder1";
    public static final String HOLDER_2 = "holder2";
    private DeviceHolderDAO m_deviceHolderDAO;
    private DeviceHolderServiceImpl m_deviceHolderServiceImpl;

    @Before
    public void setUp() {
        m_deviceHolderDAO = Mockito.mock(DeviceHolderDAO.class);
        m_deviceHolderServiceImpl = new DeviceHolderServiceImpl(m_deviceHolderDAO);
    }

//    @Test
//    public void testGetAll_InvalidPagination() {
//        m_deviceHolderServiceImpl.getAll(-1, -1);
//        verify(m_deviceHolderDAO).getAll(1, 20);
//    }

    @Test
    public void testGetAll_InvalidInput() {
        List<DeviceHolder> deviceHolders = new ArrayList<DeviceHolder>() {{
            add(generateDeviceHolder(HOLDER_1));
            add(generateDeviceHolder(HOLDER_2));
        }};

        Mockito.when(m_deviceHolderDAO.getAll(1, 20)).thenReturn(deviceHolders);
        Mockito.when(m_deviceHolderDAO.countRows()).thenReturn(2L);

        DeviceHolderPagingVO response = m_deviceHolderServiceImpl.getAll(-1, -1);
        Assert.assertEquals(2, response.getTotal().longValue());

        Assert.assertEquals(2, response.getDeviceHolderVOS().size());
        Assert.assertEquals(HOLDER_1, response.getDeviceHolderVOS().get(0).getDeviceHolderName());
        Assert.assertEquals(2, response.getDeviceHolderVOS().get(0).getNoOfDevices());

        Assert.assertEquals(HOLDER_2, response.getDeviceHolderVOS().get(1).getDeviceHolderName());
        Assert.assertEquals(2, response.getDeviceHolderVOS().get(1).getNoOfDevices());
    }

    @Test
    public void testGetAll_ValidInput() {

        List<DeviceHolder> deviceHolders = new ArrayList<DeviceHolder>() {{
            add(generateDeviceHolder(HOLDER_1));
            add(generateDeviceHolder(HOLDER_2));
        }};

        Mockito.when(m_deviceHolderDAO.getAll(1, 5)).thenReturn(deviceHolders);
        Mockito.when(m_deviceHolderDAO.countRows()).thenReturn(2L);

        DeviceHolderPagingVO response = m_deviceHolderServiceImpl.getAll(1, 5);
        Assert.assertEquals(2, response.getTotal().longValue());

        Assert.assertEquals(2, response.getDeviceHolderVOS().size());
        Assert.assertEquals(HOLDER_1, response.getDeviceHolderVOS().get(0).getDeviceHolderName());
        Assert.assertEquals(2, response.getDeviceHolderVOS().get(0).getNoOfDevices());

        Assert.assertEquals(HOLDER_2, response.getDeviceHolderVOS().get(1).getDeviceHolderName());
        Assert.assertEquals(2, response.getDeviceHolderVOS().get(1).getNoOfDevices());
    }

    @Test
    public void testGetByName() {
        DeviceHolder deviceHolder = generateDeviceHolder(HOLDER_1);
        Mockito.when(m_deviceHolderDAO.getByName(HOLDER_1)).thenReturn(deviceHolder);

        DeviceHolderVO deviceHolderVO = m_deviceHolderServiceImpl.getByName(HOLDER_1);
        Assert.assertEquals(2, deviceHolderVO.getNoOfDevices());
    }

    @Test
    public void testGetByName_DeviceHolderNotFound() {
        DeviceHolderVO deviceHolderVO = m_deviceHolderServiceImpl.getByName(HOLDER_1);
        Assert.assertNull(deviceHolderVO);
    }

    @Test
    public void testDeleteAll() {
        m_deviceHolderServiceImpl.deleteAll();
        verify(m_deviceHolderDAO).deleteAll();
    }

    @Test
    public void testAdd_NullDeviceHolderVO() {
        try {
            m_deviceHolderServiceImpl.add(null);
            fail();
        } catch (DeviceHolderException e) {
            Assert.assertEquals("Device holder is empty", e.getMessage());
        }
    }

    @Test
    public void testAdd_DeviceHolderIsExisting() {
        try {
            DeviceHolder existingDeviceHolder = Mockito.mock(DeviceHolder.class);
            Mockito.when(m_deviceHolderDAO.getByName(HOLDER_1)).thenReturn(existingDeviceHolder);
            m_deviceHolderServiceImpl.add(new DeviceHolderVO(new DeviceHolder(HOLDER_1)));
            fail();
        } catch (DeviceHolderException e) {
            Assert.assertEquals("Device holder holder1 already exists", e.getMessage());
        }
    }

    @Test
    public void testAdd_DeviceHolderIsEmpty() {
        try {
            m_deviceHolderServiceImpl.add(new DeviceHolderVO(new DeviceHolder("")));
            fail();
        } catch (DeviceHolderException e) {
            Assert.assertEquals("Device holder is empty", e.getMessage());
        }
    }

    @Test
    public void testAdd_SuccessCase() {
        DeviceHolderVO deviceHolderVO = new DeviceHolderVO();
        deviceHolderVO.setDeviceHolderName(HOLDER_1);
        m_deviceHolderServiceImpl.add(deviceHolderVO);
        verify(m_deviceHolderDAO).add(new DeviceHolder(HOLDER_1));
    }

    private DeviceHolder generateDeviceHolder(String deviceHolderName) {
        DeviceHolder deviceHolder = new DeviceHolder(deviceHolderName);
        deviceHolder.setDevices(Arrays.asList(Mockito.mock(Device.class), Mockito.mock(Device.class)));
        return deviceHolder;
    }
}
