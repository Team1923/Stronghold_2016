package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.*;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *  A Subsystem for the gear box solenoids
 * @author Christopher Cushman
 *
 */
public class GearSubsystem extends Subsystem {
    
    private boolean isShiftedDown = true;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Shifts the gear box into high gear
     */
    public void shiftUp() {
    	RobotMap.gearSolenoidLeft.set(true); //@TODO get these values
    	RobotMap.gearSolenoidLeft.set(true);
    }
    
    /**
     * Shifts the gear box into low gear
     */
    public void shiftDown() {
    	RobotMap.gearSolenoidLeft.set(false);
    	RobotMap.gearSolenoidLeft.set(false);
    }
    
    public boolean safeToShift(){
    	double leftRate = RobotMap.leftEncoder.getRate();
    	double rightRate = RobotMap.rightEncoder.getRate();
    	return leftRate < 0 || rightRate < 0; //@TODO find threshold 
    }
    
}

