package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.commands.DriveJoystickCommand;
import org.usfirst.frc.team1923.robot.utils.Calculator;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem handles drive CANTalons.
 * @author Saikiran Nakka
 * @modified: Aravind (1/21/2016)
 *
 */
public class DriveTrainSubsytem extends Subsystem{
	
	final double SCALE_CONSTANT = 3;
	public double oldLeftSpeed = 0,
			oldRightSpeed = 0;

	protected void initDefaultCommand() {
		setDefaultCommand(new DriveJoystickCommand());
	}

	public void stop(){
		drive(0, 0);
	}
	
	public void drive(double left, double right){
		RobotMap.leftDriveOne.set(left);
		RobotMap.leftDriveTwo.set(left);
		RobotMap.leftDriveThree.set(left);

		RobotMap.rightDriveOne.set(right);
		RobotMap.rightDriveTwo.set(right);
		RobotMap.rightDriveThree.set(right);
	}

	public void cubicDrive(double left, double right){
		left = Math.pow(left, SCALE_CONSTANT);
		right = Math.pow(right, SCALE_CONSTANT);
		
		drive(left, right);
	}
	
	public void smoothDrive(double left, double right){
		// open loop bias based correction
		oldLeftSpeed = Calculator.ease(left, oldLeftSpeed);
		oldRightSpeed = Calculator.ease(right, oldRightSpeed);

		left = oldLeftSpeed;
		right = oldRightSpeed;
		
		drive(left, right);

	}


}
