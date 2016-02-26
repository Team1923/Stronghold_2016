package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Subsystem to handle intake talons
 */
public class IntakeRollerSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private String intakeState = "Neutral";

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void intake(){
    	//if both switches aren't pressed or the shooter is spinning
    		RobotMap.intake.set(-1);//TODO Test for direction and speed
    }
    
    public void outake(){
    	RobotMap.intake.set(1); //TODO Test for direction and speed
    	intakeState="Out";
    }
    
    public void neutral(){
    	RobotMap.intake.set(0);
    	intakeState="Neutral";
    }
    
    public String getIntakeState(){
    	return intakeState;
    }
}

