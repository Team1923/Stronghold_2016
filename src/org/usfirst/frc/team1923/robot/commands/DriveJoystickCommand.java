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
	}

	protected void execute() {
		 //@TODO implement cubicDrive()

		Robot.driveSubsystem.smoothDrive(Robot.oi.leftStick.getY(), Robot.oi.rightStick.getY());

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
