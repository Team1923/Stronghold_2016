package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @modified Tim
 * @modified Xavier
 */
public class ShooterSpinCommand extends Command {
	private double speed;

	public ShooterSpinCommand(double speed) {
		this.speed = speed;
		requires(Robot.shooterSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.shooterEncoder.reset();
		Robot.shooterSubsystem.startUp(speed);// TODO find the speed of the
												// motor
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooterSubsystem.update();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Math.abs(Robot.shooterSubsystem.getRate() - Robot.shooterSubsystem.getTarget()) < 10);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.shooterSubsystem.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
