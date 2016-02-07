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
	
	public JoystickButton jRCenter;
	
	public JoystickButton jLeft4;	//upshift
	public JoystickButton jLeft2;	//downshift
	public JoystickButton jLeft3;

	public XboxController xboxController;
	
	public OI() {
		leftStick = new Joystick(1);
		rightStick = new Joystick(2);
		
		xboxController = new XboxController(3);
		//example binding: xboxController.a.whenPressed(...)
		
		//Joystick buttons
		jRCenter = new JoystickButton(rightStick, 3);
		
		jLeft4 = new JoystickButton(leftStick, 4);
		jLeft2 = new JoystickButton(leftStick, 2);
		jLeft3 = new JoystickButton(leftStick, 3);

		//TODO: Button mappings to the subsystems
		
		xboxController.a.whileHeld(new IntakeCommand("out"));
		xboxController.y.whileHeld(new IntakeCommand("in"));
	
	}
}
