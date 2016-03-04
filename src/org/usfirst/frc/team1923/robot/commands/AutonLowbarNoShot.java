package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonLowbarNoShot extends CommandGroup {
    
    public  AutonLowbarNoShot() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	this(0.7,4);
    }
    
    public AutonLowbarNoShot(double speed, double timeOut){
    	addSequential(new IntakePistonCommand());
    	addSequential(new DriveTimeCommand(0, 1));
    	addSequential(new DriveTimeCommand(speed,timeOut));
    }
}