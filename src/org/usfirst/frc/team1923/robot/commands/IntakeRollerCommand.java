package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.subsystems.IntakeRollerSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeRollerCommand extends Command {

	String direction;

	public IntakeRollerCommand(String dir) {
		// Use requires() here to declare subsystem dependencies
		requires(new IntakeRollerSubsystem());
		direction = dir;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	//TODO: Write something that will set position to a definite state instead of toggle

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//check to see limit switch 
		if (direction.equals("in") && !RobotMap.limitSwitch1.get() && !RobotMap.limitSwitch2.get())
			Robot.intakeRollerSubsystem.intake();
		else if(direction.equalsIgnoreCase("out"))
			Robot.intakeRollerSubsystem.outake();
		else
			Robot.intakeRollerSubsystem.neutral();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !(direction.equalsIgnoreCase("in") || direction.equalsIgnoreCase("out"));
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intakeRollerSubsystem.neutral();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
