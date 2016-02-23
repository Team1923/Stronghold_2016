package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterWheelSubsystem extends Subsystem {
	
	private boolean isSpinning;
	    

    public void initDefaultCommand() {

    }
    
    public void spinUp(){
    	//stop compressor if driving and shooting
    	if(Robot.driveSubsystem.isDriving() && RobotMap.mainCompressor.enabled()){
    		RobotMap.mainCompressor.setClosedLoopControl(false);
    		RobotMap.mainCompressor.stop();
    	}
    	bangbangUp();
    	isSpinning = true;
    }
    
    public void bangbangUp(){//TODO fix to work with encoders
    	if(RobotMap.shooter.getOutputCurrent() < 25){
    		RobotMap.shooter.set(RobotMap.shooter.get() + .05);
    	} else if(RobotMap.shooter.getOutputCurrent() > 28){
    		RobotMap.shooter.set(RobotMap.shooter.get() - .05);
    	}
    }
   
    
    public void stop(){
    	RobotMap.shooter.set(0); 
    	if(RobotMap.mainCompressor.getClosedLoopControl() == false){//start compressor loop again
    		RobotMap.mainCompressor.setClosedLoopControl(true);
    	}
    	isSpinning = false;
    }
    
    public boolean getStatus(){
    	return isSpinning;
    }
}

