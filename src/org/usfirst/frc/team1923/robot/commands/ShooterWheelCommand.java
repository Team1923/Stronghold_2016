package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.subsystems.ShooterPistonSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.ShooterWheelSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterWheelCommand extends Command {
		
    public ShooterWheelCommand() {
        requires(Robot.shooterPistonSubsystem);
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
    	Robot.shooterWheelSubsystem.spinUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterWheelSubsystem.setShooterPower(0);
    	RobotMap.mainCompressor.start();
    	RobotMap.mainCompressor.setClosedLoopControl(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
