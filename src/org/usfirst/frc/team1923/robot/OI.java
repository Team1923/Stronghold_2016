package org.usfirst.frc.team1923.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team1923.robot.commands.*;
import org.usfirst.frc.team1923.robot.utils.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Joystick leftStick;

	public Joystick rightStick;

	public JoystickButton upShifter, downShifter, leftTrigger, rightTrigger;

	public XboxController xboxController;

	public OI() {
		leftStick = new Joystick(1);
		rightStick = new Joystick(2);

		xboxController = new XboxController(3);

		// Joystick buttons
		upShifter = new JoystickButton(rightStick, 7);
		downShifter = new JoystickButton(leftStick, 7);

		leftTrigger = new JoystickButton(leftStick, 1);
		rightTrigger = new JoystickButton(rightStick, 1);
		
		//Drive Controls
		leftTrigger.whenPressed(new GearShiftCommand(true));
		rightTrigger.whenPressed(new GearShiftCommand(false));
		
		//Intake Controls
		xboxController.a.whileHeld(new IntakeRollerCommand("out"));
		xboxController.y.whileHeld(new IntakeRollerCommand("in"));
		xboxController.x.whenReleased(new IntakePistonCommand());
		
		//Shooter Controls
//		xboxController.dPad.up.whenPressed(new ShooterWheelToggleCommand(true));
//		xboxController.dPad.down.whenPressed(new ShooterWheelToggleCommand(false));
		
		xboxController.rt.whenPressed(new ShooterPistonCommand(false));
		xboxController.lt.whenPressed(new ShooterPistonCommand(true));
		xboxController.b.whileHeld(new ShooterWheelCommand());
		
		if(xboxController.rb.get()){
			Robot.intakeRollerSubsystem.setOverride(true);
		}
		
		xboxController.rb.whenReleased(new DefensePistonCommand(true));
		xboxController.lb.whenReleased(new DefensePistonCommand(false));
        
	}
}
