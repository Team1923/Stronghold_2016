package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterPistonSubsystem extends Subsystem {
	
	private boolean isLow;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void toggle(){
    	if(isLow){
    		hoodUp();
    	} else if(!isLow){
    		hoodDown();
    	}
    	
    }
    
    public void hoodDown(){
    	isLow = true;
    	RobotMap.shooterSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void hoodUp(){
    	isLow = false;
    	RobotMap.shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}

