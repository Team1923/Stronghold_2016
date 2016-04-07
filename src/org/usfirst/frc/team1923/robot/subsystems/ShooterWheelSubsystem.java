package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterWheelSubsystem extends Subsystem {
	
	private final int CONSTANT_RATE = 650;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void spinUp(){
    	if(RobotMap.shooterEncoder.getRate() > CONSTANT_RATE){
    		setShooterPower(0);
    	} else{
    		setShooterPower(-1);
    	}
//    	setShooterPower(-1);
    	
    }
    
    public void setShooterPower(double power){
    	RobotMap.shooterRight.set(-power);
    	RobotMap.shooterLeft.set(power);
    }
}

