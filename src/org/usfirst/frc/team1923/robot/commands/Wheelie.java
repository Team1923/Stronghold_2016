package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Wheelie extends CommandGroup {
    
    public  Wheelie() {
        addSequential(new RawDriveCommand(-1, -1, 0.1));
        addSequential(new RawDriveCommand(1, 1, 0.1));
    }
}
