package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.commands.DriveJoystickCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem handles drive CANTalons.
 * @author Saikiran Nakka
 *
 */
public class DriveTrainSubsytem extends Subsystem{

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

	public void drive(double s){
		drive(s, s);
	}

	public void cubicDrive(double left, double right){
		//@TODO figure out equation and how to scale linear values to the cubic function
	}

	public void cubicDrive(double s){
		cubicDrive(s, s);
	}

}
