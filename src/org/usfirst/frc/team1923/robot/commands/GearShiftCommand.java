package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command that shifts the gearbox up or down
 * @author Christopher Cushman
 *
 */
public class GearShiftCommand extends Command {
	

	boolean shiftUp;
	/**
	 * Shifts the robot's gearbox up or down
	 * @param direction The direction in which to shift, either up or down
	 */
	public GearShiftCommand(String direction) {
//		requires(Robot.gearSubsystem);
//		shiftUp = direction.equals("up");
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
//		if(shiftUp)
//			Robot.gearSubsystem.shiftUp();
//		else
//			Robot.gearSubsystem.shiftDown();	
////		else if(Robot.gearSubsystem.safeToShift())
////			Robot.gearSubsystem.shiftDown();
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
