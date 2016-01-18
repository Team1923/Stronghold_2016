package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Xavier (1/18/2016)
 *
 */

public class AutonReachOuterWork extends CommandGroup{

	public AutonReachOuterWork(double speed, double timeOut){
		addSequential(new DriveTimeCommand(speed, timeOut));
	}
	
	public AutonReachOuterWork(double distance){
		addSequential(new DriveDistanceCommand(distance));
	}
	
}
