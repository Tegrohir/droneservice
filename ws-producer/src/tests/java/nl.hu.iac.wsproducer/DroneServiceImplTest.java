package nl.hu.iac.wsproducer;

import nl.hu.iac.wsinterface.*;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tegrohir on 12-3-2017.
 */
public class DroneServiceImplTest {
    @org.junit.Test
    public void createDrone() throws Exception {
        System.out.println("\n\nCreate Drone test");
        CreateRequest request = new CreateRequest();
        createTestRequest(request);
        DroneServiceImpl impl = new DroneServiceImpl();
        boolean thrown = false;

        try {
            DroneResponse response = impl.createDrone(request);
            System.out.println(response.getMessage());
        } catch (Exception e) {
            System.out.println("Test Create eerst joh, stom programma :(  ");
            thrown = true;
        }
        try {
            DroneResponse response2 = impl.createDrone(request);
            System.out.println(response2.getMessage());
        } catch (Exception e) {
            System.out.println("Exception gevangen");
            thrown = true;
        }
        assertTrue(thrown);
    }


    @org.junit.Test
    public void readDrone() throws Exception {
        System.out.println("\n\nRead Drone test");
        DroneServiceImpl impl = new DroneServiceImpl();
        Drone drone = createTestDrone();
        ReadRequest request = new ReadRequest();
        request.setDroneID(drone.getDroneID());

        DroneResponse response = impl.readDrone(request);
        assertEquals(drone.toString(), response.getMessage());
    }

    @org.junit.Test
    public void updateDrone() throws Exception {
        System.out.println("\n\nUpdate Drone test");
        DroneServiceImpl impl = new DroneServiceImpl();
        Drone drone = createTestDrone();
        UpdateRequest request = new UpdateRequest();
        createTestRequest(request);
        System.out.println(request.getPropellers());
        request.setDroneID(drone.getDroneID());

        DroneResponse response = impl.updateDrone(request);
        assertEquals("Succes, nieuwe waardes van je drone: " + drone.toString(), response.getMessage());
    }

    @org.junit.Test
    public void deleteDrone() throws Exception {
        System.out.println("\n\nDelete Drone test");
        DroneServiceImpl impl = new DroneServiceImpl();
        Drone drone = createTestDrone();
        DeleteRequest request = new DeleteRequest();
        request.setDroneID(drone.getDroneID());

        DroneResponse response = impl.deleteDrone(request);
        assertEquals(drone.toString() + " has been removed.", response.getMessage());

    }

    private Drone createTestDrone() {
        CreateRequest request = new CreateRequest();
        createTestRequest(request);
        DroneServiceImpl impl = new DroneServiceImpl();
        try{
            impl.createDrone(request);
        } catch(Exception e){
            System.out.println("Drone already exists in Impl class");
        }
        Drone drone = new Drone(request);
        System.out.println(drone.toString());

        return drone;
    }

    private IRequest createTestRequest(IRequest request) {

        request.setDroneID(new BigInteger("12345"));
        request.setMotorFrame("MotorFrame 0.1");
        request.setMotor("Motor 8000");
        request.setTransmitter("Transmitter 9000");
        request.setReceiver("Receiver 7000");
        request.setPropellers("Propeller Series 5300");
        request.setNumberOfPropellers(new BigInteger("38"));
        request.setBatteries("Wes-034");
        return request;
    }
}