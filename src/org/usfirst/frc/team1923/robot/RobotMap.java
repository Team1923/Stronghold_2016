package org.usfirst.frc.team1923.robot;

import edu.wpi.first.wpilibj.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
	public static Solenoid gearSolenoidLeft = new Solenoid(0); //@TODO find port
	public static Solenoid gearSolenoidRight = new Solenoid(0); //@TODO find port
}
