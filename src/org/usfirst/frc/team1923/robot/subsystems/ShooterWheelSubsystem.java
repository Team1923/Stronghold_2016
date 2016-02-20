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
    	RobotMap.shooter.set(.5); //todo check for power + direction
    	isSpinning = true;
    }
    
    public void stop(){
    	RobotMap.shooter.set(0); 
    	if(!RobotMap.mainCompressor.getClosedLoopControl()){//start compressor loop again
    		RobotMap.mainCompressor.setClosedLoopControl(true);
    	}
    	isSpinning = false;
    }
    
    public boolean getStatus(){
    	return isSpinning;
    }
}

