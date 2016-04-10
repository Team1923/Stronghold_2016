package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *  A Subsystem for the gear box solenoids
 *
 */
public class GearSubsystem extends Subsystem {
    
    private boolean isShiftedDown = true;

    public void initDefaultCommand() {

    }
        
    /**
     * Shifts the gear box into high gear
     */
    public void shiftUp() {
    	if(safeToShift()) {
    		RobotMap.shifterSolenoid.set(DoubleSolenoid.Value.kReverse); //TODO get these values
    		isShiftedDown = false;
    	}
    }
    
    /**
     * Shifts the gear box into low gear
     */
    public void shiftDown() {
    	if(safeToShift()){
    		RobotMap.shifterSolenoid.set(DoubleSolenoid.Value.kForward); //TODO: Get proper direction
    		isShiftedDown = true;
    	}
    }
    
    public boolean safeToShift(){
    	return true;
    }
    
    //returns true if low gear, false otherwise
    public boolean getGearPosition(){
    	return isShiftedDown;
    }
    
}
