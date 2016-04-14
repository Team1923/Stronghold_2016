package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.subsystems.ShooterWheelSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterWheelToggleCommand extends Command {

	private boolean spinup = false;

	public ShooterWheelToggleCommand(boolean up) {
		spinup = up;// Distinguish between spin up and spin down
		requires(Robot.shooterWheelSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.shooterPistonSubsystem.shooterUp();
		RobotMap.mainCompressor.stop();
		RobotMap.mainCompressor.setClosedLoopControl(false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (spinup)
			Robot.shooterWheelSubsystem.spinUp();
		else
			Robot.shooterWheelSubsystem.stopShooter();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		if (spinup)
//			return Math.abs(RobotMap.shooterEncoder.getRate() - Robot.shooterWheelSubsystem.CONSTANT_RATE) <50;
		if(!spinup)
			return RobotMap.shooterEncoder.getRate() < 25;
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.shooterWheelSubsystem.stopShooter();
		RobotMap.mainCompressor.start();
		RobotMap.mainCompressor.setClosedLoopControl(true);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
