package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterPistonSubsystem extends Subsystem {
    
    private boolean down;

    public void initDefaultCommand() {
       
    }
    
    public void toggle(){
    	if(down){
    		shooterUp();
    	} else{
    		shooterDown();
    	}
    }
    
    public void shooterUp(){
    	down = false;
    	RobotMap.shooterSolenoid.set(Value.kForward);
    }
    
    public void shooterDown(){
    	down = true;
    	RobotMap.shooterSolenoid.set(Value.kReverse);
    }
}

