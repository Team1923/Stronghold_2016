package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DefensePistonSubsystem extends Subsystem {
    
    boolean down;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void up(){
    	RobotMap.defenseSolenoid.set(DoubleSolenoid.Value.kReverse);
    	down = false;
    	
    }
    
    public void down(){
    	RobotMap.defenseSolenoid.set(DoubleSolenoid.Value.kForward);
    	down = true;
    }
    
    public boolean isDown(){
    	return down;
    }
}

