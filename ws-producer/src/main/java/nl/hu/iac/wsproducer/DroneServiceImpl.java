package nl.hu.iac.wsproducer;

import javax.jws.WebService;

import nl.hu.iac.wsinterface.Fault;
import nl.hu.iac.wsinterface.ObjectFactory;
import nl.hu.iac.wsinterface.DroneFault;
import nl.hu.iac.wsinterface.ReadRequest;
import nl.hu.iac.wsinterface.CreateRequest;
import nl.hu.iac.wsinterface.DroneResponse;
import nl.hu.iac.wsinterface.DroneServiceInterface;
import nl.hu.iac.wsinterface.UpdateRequest;
import nl.hu.iac.wsinterface.DeleteRequest;

import java.math.BigInteger;
import java.util.ArrayList;

@WebService(endpointInterface = "nl.hu.iac.wsinterface.DroneServiceInterface")
public class DroneServiceImpl implements DroneServiceInterface {

	private static ArrayList<Drone> drones;

	@Override
	public DroneResponse createDrone(CreateRequest request) throws Fault {
		ObjectFactory factory = new ObjectFactory();
		DroneResponse response = factory.createDroneResponse();

		try {
			Drone drone = new Drone(request);

			if (drones == null) {
				drones = new ArrayList<>();
				drones.add(drone);
			} else if (!drones.contains(drone)){
				drones.add(drone);
			} else {
				throw new Exception();
			}
			response.setMessage("De drone is met succes geregistreerd");
		} catch (Exception e) {
			DroneFault x = factory.createDroneFault();
			x.setErrorCode((short) 1);
			x.setMessage("We konden de drone niet registreren");
			Fault fault = new Fault(
					"Er ging iets mis met het registreren van de Drone", x);
			throw fault;
		}

		return response;
	}

	public DroneResponse readDrone(ReadRequest request) throws Fault {
		ObjectFactory factory = new ObjectFactory();
		DroneResponse response = factory.createDroneResponse();

		try {
			Drone drone = SearchDrones(request.getDroneID());

			response.setMessage(drone.toString());
		} catch (Exception e) {
			DroneFault x = factory.createDroneFault();
			x.setErrorCode((short) 1);
			x.setMessage("We konden geen informatie vinden van de drone met het ID " + request.getDroneID());
			Fault fault = new Fault(
					"Er ging iets mis met het vinden van de Drone", x);
			throw fault;
		}

		return response;
	}

	public DroneResponse updateDrone(UpdateRequest request) throws Fault {
		ObjectFactory factory = new ObjectFactory();
		DroneResponse response = factory.createDroneResponse();

		try {
			Drone drone = SearchDrones(request.getDroneID());

			drone.setMotorFrame(request.getMotorFrame());
			drone.setMotor(request.getMotor());
			drone.setTransmitter(request.getTransmitter());
			drone.setReceiver(request.getReceiver());
			drone.setPropellers(request.getPropellers());
			drone.setNumberOfPropellers(request.getNumberOfPropellers());
			drone.setBatteries(request.getBatteries());

			response.setMessage("Succes, nieuwe waardes van je drone: " + drone.toString());
		} catch (Exception e) {
			DroneFault x = factory.createDroneFault();
			x.setErrorCode((short) 1);
			x.setMessage("We konden geen informatie vinden van de drone met het ID " + request.getDroneID());
			Fault fault = new Fault(
					"Er ging iets mis met het updaten van de Drone", x);
			throw fault;
		}

		return response;
	}

	public DroneResponse deleteDrone(DeleteRequest request) throws Fault {
		ObjectFactory factory = new ObjectFactory();
		DroneResponse response = factory.createDroneResponse();

		try {
			Drone drone = SearchDrones(request.getDroneID());

			drones.remove(drone);
			response.setMessage(drone.toString() + " has been removed.");
		} catch (Exception e) {
			DroneFault x = factory.createDroneFault();
			x.setErrorCode((short) 1);
			x.setMessage("We konden geen informatie vinden van de drone met het ID " + request.getDroneID());
			Fault fault = new Fault(
					"Er ging iets mis met het deleten van de Drone", x);
			throw fault;
		}

		return response;
	}

	private Drone SearchDrones(BigInteger droneID){
		for (Drone drone:drones){
			if (drone.getDroneID().compareTo(droneID) == 0){

				return drone;
			}
		}
		return null;
	}
}
