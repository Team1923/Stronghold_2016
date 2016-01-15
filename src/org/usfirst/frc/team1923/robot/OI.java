package org.usfirst.frc.team1923.robot;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

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
	
	public JoystickButton jLeft4;
	public JoystickButton jLeft2;
	public JoystickButton jLeft3;
	
//	public JoystickButton start;
//	public JoystickButton a;
//	public JoystickButton b;
//	public JoystickButton x;
//	public JoystickButton y;
//	public JoystickButton lB;
//	public JoystickButton rB;
//	public JoystickButton lT;
//	public JoystickButton rT;
//	public JoystickButton back;
//	public JoystickButton leftClick;
//	public JoystickButton rightClick;
	public XboxController xboxController;
	
	public OI() {
		leftStick = new Joystick(1);
		rightStick = new Joystick(2);
		
		xboxController = new XboxController(3);
		
//		// Create XBOX buttons
//		Deprecated by the new XboxController Class
//		a = new JoystickButton(xboxController, 1);
//		b = new JoystickButton(xboxController, 2);
//		x = new JoystickButton(xboxController, 3);
//		y = new JoystickButton(xboxController, 4);
//		lB = new JoystickButton(xboxController, 5);
//		rB = new JoystickButton(xboxController, 6);
//		back = new JoystickButton(xboxController, 7);
//		start = new JoystickButton(xboxController, 8);
//		leftClick = new JoystickButton(xboxController, 9);
//		rightClick = new JoystickButton(xboxController, 10);
//		lT = new JoystickButton(xboxController, 11);
//		rT = new JoystickButton(xboxController, 12);
		
		//Joystick buttons
		jRCenter = new JoystickButton(rightStick, 3);
		
		jLeft4 = new JoystickButton(leftStick, 4);
		jLeft2 = new JoystickButton(leftStick, 2);
		jLeft3 = new JoystickButton(leftStick, 3);
		
		//@TODO: Button mappings to the subsystems 
		
		
	
	}

}
