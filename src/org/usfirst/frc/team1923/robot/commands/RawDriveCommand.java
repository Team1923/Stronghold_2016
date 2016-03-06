package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *This command is the MOST low level command in this project.
 *
 *This directly controls the output of the talons controlling drive.
 *This does not have any scaling, colescing, or protection timeouts.
 */
public class RawDriveCommand extends Command {

	double left, right, timeOut = 0;
	Timer t = new Timer();
    public RawDriveCommand(double left, double right, double timeOut) {
    	requires(Robot.driveSubsystem);
    	this.left=left;
    	this.right=right;
    	this.timeOut=timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(left>1)
    		left = 1;
    	else if(left<-1)
    		left=-1;
    	
    	if(right>1)
    		right = 1;
    	else if(right<-1)
    		right=-1;
    	
    	t.reset();
    	t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.rawDrive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (t.get()>timeOut);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
