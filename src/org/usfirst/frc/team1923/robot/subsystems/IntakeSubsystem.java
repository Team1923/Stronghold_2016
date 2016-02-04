package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	String intakeState = "Neutral";

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void intake(){
    	RobotMap.intake.set(1); //TODO Test for direction and speed
    	intakeState="In";
    }
    
    public void outake(){
    	RobotMap.intake.set(-1); //TODO Test for direction and speed
    	intakeState="Out";
    }
    
    public void neutral(){
    	RobotMap.intake.set(0);
    	intakeState="Neutral";
    }
    
    public boolean isIntake(){
    	return intakeState.equalsIgnoreCase("In");
    }
    
    public boolean isOutake(){
    	return intakeState.equalsIgnoreCase("Out");
    }
    public boolean isNeutral(){
    	return intakeState.equalsIgnoreCase("Neutral");
    }
}

