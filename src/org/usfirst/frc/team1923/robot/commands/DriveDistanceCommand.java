package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Xavier(1/18/16)
 *
 */

public class DriveDistanceCommand extends Command{

	private double distance;
	
	public DriveDistanceCommand(double distance){
		requires(Robot.driveSubsystem);
		this.distance = distance;
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		
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
