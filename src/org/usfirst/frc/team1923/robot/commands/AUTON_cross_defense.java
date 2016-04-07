package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTON_cross_defense extends CommandGroup {
    
    public  AUTON_cross_defense() {
    	this(0.7,4);
    }
    
    public AUTON_cross_defense(double speed, double timeOut){
    	addSequential(new IntakePistonCommand());
    	addSequential(new DriveTimeCommand(0, 1));
    	addSequential(new DriveTimeCommand(speed,timeOut));
    }
}