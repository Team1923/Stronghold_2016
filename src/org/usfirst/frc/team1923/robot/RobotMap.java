 package org.usfirst.frc.team1923.robot;

import edu.wpi.first.wpilibj.*;

public class RobotMap {


	public static final double EASE_INCREMENT = 0.09; // TODO test ease
														// increment
	public static final double FEED_BACK_CONTROL_CONSTANT = 0.5;
	public static final double DISTANCE_PER_PULSE = 1 / 256.0;

	// Drive Motors
	public static CANTalon leftDriveOne = new CANTalon(4);
	public static CANTalon leftDriveTwo = new CANTalon(5);
	public static CANTalon leftDriveThree = new CANTalon(6);
	public static CANTalon rightDriveOne = new CANTalon(7);
	public static CANTalon rightDriveTwo = new CANTalon(8);
	public static CANTalon rightDriveThree = new CANTalon(9);

    //Shooter Motors
    public static CANTalon shooterRight = new CANTalon(0);
    public static CANTalon shooterLeft = new CANTalon(1);
    public static Encoder shooterEncoder = new Encoder(1,0);

	// Intake Motor
	public static CANTalon intake = new CANTalon(2);
	public static DigitalInput limitSwitch1 = new DigitalInput(4);
	public static DigitalInput limitSwitch2 = new DigitalInput(5);
	
	//Pneumatics 
	public static Compressor mainCompressor = new Compressor(10);
	public static DoubleSolenoid shifterSolenoid = new DoubleSolenoid(10,0,1);
	public static DoubleSolenoid intakeSolenoid = new DoubleSolenoid(10,2,3);
	public static DoubleSolenoid shooterSolenoid = new DoubleSolenoid(10,6,7);
	
	// Robot initializing
	public static void init() {
		shooterEncoder.reset();
		shooterEncoder.setDistancePerPulse(.5);
	}
}
