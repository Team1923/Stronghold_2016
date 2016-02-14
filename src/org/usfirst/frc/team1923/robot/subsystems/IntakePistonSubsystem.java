package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakePistonSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private boolean isDown;

    public void initDefaultCommand() {
        
    }
    
    public void toggle(){
    	if(isDown){
    		intakeUp();
    	} else{
    		intakeDown();
    	}
    }
    
    public void intakeDown(){
    	RobotMap.intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    	isDown = true;
    }
    
    public void intakeUp(){
    	RobotMap.intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    	isDown = false;
    }
    
    public boolean intakePosition(){
    	return isDown;
    }
    
    
}

