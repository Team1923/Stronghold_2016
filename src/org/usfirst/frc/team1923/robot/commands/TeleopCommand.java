package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TeleopCommand extends CommandGroup {
    
    public  TeleopCommand() {
    	addParallel(new DriveJoystickCommand());
    	//addParallel(new HoodManagementCommand());
    }
}
