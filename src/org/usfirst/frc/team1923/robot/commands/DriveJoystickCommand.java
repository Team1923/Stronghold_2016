package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command takes joystick input and runs tankDrive.
 * @author Saikiran Nakka
 *
 */
public class DriveJoystickCommand extends Command{

	public DriveJoystickCommand(){
		requires(Robot.driveSubsystem);
	}
	
	protected void initialize() {	
//		System.out.println("drive with joysticks was init");
	}

	protected void execute() {
		// @TODO implement cubicDrive()
//		System.out.println("Joystick values");
//		System.out.println("RIGHT: " + Robot.oi.rightStick.getY());
//		System.out.println("LEFT: " + Robot.oi.leftStick.getY());

		Robot.driveSubsystem.smoothDrive(Robot.oi.leftStick.getY(), Robot.oi.rightStick.getY());
		//Robot.driveSubsystem.drive(Robot.oi.leftStick.getY(), Robot.oi.rightStick.getY());

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.driveSubsystem.stop();
		
	}

	protected void interrupted() {
		end();
	}

}
