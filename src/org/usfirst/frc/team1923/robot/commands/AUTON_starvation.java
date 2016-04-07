package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTON_starvation extends CommandGroup {
    
    public  AUTON_starvation() {
    	//get there
    	addSequential(new IntakePistonCommand());
    	addSequential(new DriveTimeCommand(0, 1));
    	addSequential(new DriveTimeCommand(.6,4));
    	
    	//drop ball
    	addSequential(new IntakeRollerCommand("out"));
    	addSequential(new DriveTimeCommand(-.6,4));
    	
    }
}
