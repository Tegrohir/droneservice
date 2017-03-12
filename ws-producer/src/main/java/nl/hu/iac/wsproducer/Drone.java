package nl.hu.iac.wsproducer;

import nl.hu.iac.wsinterface.CreateRequest;

import java.math.BigInteger;

/**
 * Created by Tegrohir on 12-3-2017.
 */
public class Drone {
    private BigInteger droneID;
    private String motorFrame;
    private String motor;
    private String transmitter;
    private String receiver;
    private String propellers;
    private BigInteger numberOfPropellers;
    private String batteries;

    public Drone (CreateRequest request){
        this.droneID = request.getDroneID();
        this.motorFrame = request.getMotorFrame();
        this.motor = request.getMotor();
        this.transmitter = request.getTransmitter();
        this.receiver = request.getReceiver();
        this.propellers = request.getPropellers();
        this.numberOfPropellers = request.getNumberOfPropellers();
        this.batteries = request.getBatteries();
    }

    public BigInteger getDroneID() {
        return droneID;
    }

    public void setMotorFrame(String motorFrame) {
        this.motorFrame = motorFrame;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void setTransmitter(String transmitter) {
        this.transmitter = transmitter;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setPropellers(String propellers) {
        this.propellers = propellers;
    }

    public void setNumberOfPropellers(BigInteger numberOfPropellers) {
        this.numberOfPropellers = numberOfPropellers;
    }

    public void setBatteries(String batteries) {
        this.batteries = batteries;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "droneID=" + droneID +
                ", motorFrame='" + motorFrame + '\'' +
                ", motor='" + motor + '\'' +
                ", transmitter='" + transmitter + '\'' +
                ", receiver='" + receiver + '\'' +
                ", propellers='" + propellers + '\'' +
                ", numberOfPropellers=" + numberOfPropellers +
                ", batteries='" + batteries + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drone drone = (Drone) o;

        return droneID.equals(drone.droneID);
    }

    @Override
    public int hashCode() {
        return droneID.hashCode();
    }
}
