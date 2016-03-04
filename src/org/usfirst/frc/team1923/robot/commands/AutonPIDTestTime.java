//package org.usfirst.frc.team1923.robot.commands;
//
//import org.usfirst.frc.team1923.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class AutonPIDTestTime extends Command {
//
//    public AutonPIDTestTime() {
//    	requires(Robot.driveSubsystem);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	Robot.driveSubsystem.driveDistance(40,3);	//3 second timeout for timed driving
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return Robot.driveSubsystem.reachedTarget();
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	Robot.driveSubsystem.stop();
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    	end();
//    }
//}
