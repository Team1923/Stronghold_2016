package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.subsystems.ShooterPistonSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterPistonCommand extends Command {

    public ShooterPistonCommand() {
        requires(new ShooterPistonSubsystem());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooterPistonSubsystem.toggle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
