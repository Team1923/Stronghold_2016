package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.subsystems.IntakeRollerSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeRollerCommand extends Command {
	
	boolean direction;

    public IntakeRollerCommand(String dir) {
        // Use requires() here to declare subsystem dependencies
        requires(new IntakeRollerSubsystem());
        direction = dir.equals("in");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(direction)
    		Robot.intakeRollerSubsystem.intake();
    	else
    		Robot.intakeRollerSubsystem.outake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (RobotMap.limitSwitch1.get() || RobotMap.limitSwitch2.get());
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeRollerSubsystem.neutral();
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
