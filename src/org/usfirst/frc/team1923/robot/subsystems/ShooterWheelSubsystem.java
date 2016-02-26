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
//    	if(Robot.driveSubsystem.isDriving() && RobotMap.mainCompressor.enabled()){
//    		RobotMap.mainCompressor.setClosedLoopControl(false);
//    		RobotMap.mainCompressor.stop();
//    	}
//    	RobotMap.shooterLeft.set(-1);
//    	RobotMap.shooterRight.set(1);
    	RobotMap.mainCompressor.setClosedLoopControl(false);
		RobotMap.mainCompressor.stop();
    	bangbangUp();
    	isSpinning = true;
    }
    
    public void bangbangUp(){//TODO fix to work with encoders
    	if(RobotMap.shooterLeft.getOutputCurrent() < 23){
    		RobotMap.shooterLeft.set(RobotMap.shooterLeft.get() - .2);
    		RobotMap.shooterRight.set(RobotMap.shooterRight.get() + .2);
    	} else if(RobotMap.shooterLeft.getOutputCurrent() > 25){
    		RobotMap.shooterLeft.set(RobotMap.shooterLeft.get() + .05);
    		RobotMap.shooterRight.set(RobotMap.shooterRight.get() - .05);
    	}
    }
    
    public void stop(){
    	RobotMap.shooterLeft.set(0);
    	RobotMap.shooterRight.set(0);
    	if(RobotMap.mainCompressor.getClosedLoopControl() == false){//start compressor loop again
    		RobotMap.mainCompressor.setClosedLoopControl(true);
    	}
    	isSpinning = false;
    }
    
    public boolean getStatus(){
    	return isSpinning;
    }
}

