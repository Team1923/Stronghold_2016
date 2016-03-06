package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WheelieCommand extends CommandGroup {
    
    public  WheelieCommand() {
    	addSequential(new GearShiftCommand(false));
        addSequential(new RawDriveCommand(-1, 1, 0.5));
        addSequential(new RawDriveCommand(1, -1, 1));
    }
}
