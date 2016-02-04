package org.usfirst.frc.team1923.robot;

import edu.wpi.first.wpilibj.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @modified Xavier 1/17/2016
 * @modified by Aravind 1/17/2016
 * @modified by Aravind 1/31/2016
 * @modified by Aravind 2/3/2016
 */
public class RobotMap {
    
	public static final double EASE_INCREMENT = 0.09; //TODO test ease increment
	
	//Gear Shifters
	public static DoubleSolenoid gearShifter = new DoubleSolenoid(0,1);
	

	//Drive Motors
	public static CANTalon leftDriveOne = new CANTalon(1); 
	public static CANTalon leftDriveTwo = new CANTalon(2);  
	public static CANTalon leftDriveThree = new CANTalon(3);  
	public static CANTalon rightDriveOne = new CANTalon(4);  
	public static CANTalon rightDriveTwo = new CANTalon(5);  
	public static CANTalon rightDriveThree = new CANTalon(6);  
	
	//Intake Motor
	public static CANTalon intake = new CANTalon(0);
	
//	public static Encoder leftEncoder = new Encoder(0,0); //TODO: find encoder ports
//	public static Encoder rightEncoder = new Encoder(0,0);
	
	//Robot initializing
	public static void init(){
//		leftEncoder.reset();
//		rightEncoder.reset();
	}

}
