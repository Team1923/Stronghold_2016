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
    	//setShooterPower(1);
		bangbangUp();
    	isSpinning = true;
    }
    
    public void setShooterPower(double power){
    	RobotMap.shooterLeft.set(-power);
    	RobotMap.shooterRight.set(power);
    }
    
    public void bangbangUp(){//TODO fix to work with encoders
    	if(RobotMap.shooterLeft.getOutputCurrent() < 37){
    		RobotMap.shooterLeft.set(RobotMap.shooterLeft.get() - .1);
    		RobotMap.shooterRight.set(RobotMap.shooterRight.get() + .1);
    	} else if(RobotMap.shooterLeft.getOutputCurrent() > 38){
    		RobotMap.shooterLeft.set(RobotMap.shooterLeft.get() + .02);
    		RobotMap.shooterRight.set(RobotMap.shooterRight.get() - .02);
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

