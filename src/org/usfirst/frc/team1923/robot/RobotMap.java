 package org.usfirst.frc.team1923.robot;

import edu.wpi.first.wpilibj.*;

public class RobotMap {


	public static final double EASE_INCREMENT = 0.09; // TODO test ease
	public static final double DISTANCE_PER_PULSE = 1 / 256.0; //TODO get this value

	// Drive
	public static CANTalon leftDriveOne = new CANTalon(4);
	public static CANTalon leftDriveTwo = new CANTalon(5);
	public static CANTalon leftDriveThree = new CANTalon(6);
	public static CANTalon rightDriveOne = new CANTalon(7);
	public static CANTalon rightDriveTwo = new CANTalon(8);
	public static CANTalon rightDriveThree = new CANTalon(9);
//	public static Encoder rightDriveEncoder = new Encoder(2,3);
//	public static Encoder leftDriveEncoder = new Encoder(4,5);

    //Shooter
    public static CANTalon shooterRight = new CANTalon(0);
    public static CANTalon shooterLeft = new CANTalon(1);
    public static Encoder shooterEncoder = new Encoder(2,3);

	// Intake
	public static CANTalon intake = new CANTalon(2);
	public static DigitalInput limitSwitch1 = new DigitalInput(0);
	public static DigitalInput limitSwitch2 = new DigitalInput(1);
	
	//Pneumatics 
	public static Compressor mainCompressor = new Compressor(10);
	public static DoubleSolenoid shifterSolenoid = new DoubleSolenoid(10,2,3);
	public static DoubleSolenoid intakeSolenoid = new DoubleSolenoid(10,6,7);
	public static DoubleSolenoid shooterSolenoid = new DoubleSolenoid(10,0,1);
	
	// Robot initializing
	public static void init() {
		resetEncoders();
		initializeTalons();
		
	}
	
	public static void resetEncoders(){
		shooterEncoder.reset();
		shooterEncoder.setDistancePerPulse(.5);
		
//		leftDriveEncoder.reset();
//		leftDriveEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
//		
//		rightDriveEncoder.reset();
//		rightDriveEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
	}
	
	public static void initializeTalons(){
		leftDriveOne.enableBrakeMode(true);
		leftDriveTwo.enableBrakeMode(true);
		leftDriveThree.enableBrakeMode(true);
		rightDriveOne.enableBrakeMode(true);
		rightDriveTwo.enableBrakeMode(true);
		rightDriveThree.enableBrakeMode(true);
		
		intake.enableBrakeMode(false);
		shooterLeft.enableBrakeMode(false);
		shooterRight.enableBrakeMode(false);
	}
}
