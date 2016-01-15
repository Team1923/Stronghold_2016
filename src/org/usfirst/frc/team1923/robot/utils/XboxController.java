package org.usfirst.frc.team1923.robot.utils; // Change to whatever package you would like

/* Imports */
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/*
 * Original Authors: AJ Granowski & 4624 Owatonna Robotics
 * Link to original: https://github.com/owatonnarobotics/XboxController
 * Modified by: Aravind 
 * Date: 2016-01-15
 */

public class XboxController extends Joystick {

	/* Default Values */
	private static final double DEFAULT_TRIGGER_DEADZONE = 0.01; // Jiggle room
																	// for the
																	// triggers
	private static final double DEFAULT_TRIGGER_SENSITIVITY = 0.6; // If the
																	// trigger
																	// is beyond
																	// this
																	// limit,
																	// say it
																	// has been
																	// pressed

	/* Button Mappings */
	private static final int A_BUTTON_ID = 1;
	private static final int B_BUTTON_ID = 2;
	private static final int X_BUTTON_ID = 3;
	private static final int Y_BUTTON_ID = 4;
	private static final int LB_BUTTON_ID = 5;
	private static final int RB_BUTTON_ID = 6;
	private static final int BACK_BUTTON_ID = 7;
	private static final int START_BUTTON_ID = 8;
	private static final int LEFT_CLICK_ID = 9;
	private static final int RIGHT_CLICK_ID = 10;

	/* Axis Mappings */
	private static final int LEFT_TRIGGER_AXIS_ID = 2;
	private static final int RIGHT_TRIGGER_AXIS_ID = 3;

	/* Instance Values */
	private final int port;
	private final Joystick controller;

	public final Trigger lt;
	public final Trigger rt;
	public final DirectionalPad dPad;
	public final Button a;
	public final Button b;
	public final Button x;
	public final Button y;
	public final Button lb;
	public final Button rb;
	public final Button back;
	public final Button start;
	public final Button rightClick;
	public final Button leftClick;

	/*
	 * @param: port
	 */
	public XboxController(final int port) {
		super(port); // Extends Joystick...

		/* Initialize */
		this.port = port;
		this.controller = new Joystick(this.port); // Joystick referenced by
													// everything
		this.dPad = new DirectionalPad(this.controller);
		this.lt = new Trigger(this.controller, HAND.LEFT);
		this.rt = new Trigger(this.controller, HAND.RIGHT);
		this.a = new JoystickButton(this.controller, A_BUTTON_ID);
		this.b = new JoystickButton(this.controller, B_BUTTON_ID);
		this.x = new JoystickButton(this.controller, X_BUTTON_ID);
		this.y = new JoystickButton(this.controller, Y_BUTTON_ID);
		this.lb = new JoystickButton(this.controller, LB_BUTTON_ID);
		this.rb = new JoystickButton(this.controller, RB_BUTTON_ID);
		this.back = new JoystickButton(this.controller, BACK_BUTTON_ID);
		this.start = new JoystickButton(this.controller, START_BUTTON_ID);
		this.rightClick = new JoystickButton(this.controller, RIGHT_CLICK_ID);
		this.leftClick = new JoystickButton(this.controller, LEFT_CLICK_ID);
	}

	/**
	 * Rather than use an integer (which might not be what we expect) we use an
	 * enum which has a set amount of possibilities.
	 */
	public static enum HAND {
		LEFT, RIGHT
	}

	/**
	 * This is the relation of direction and number for .getPOV() used in the
	 * DirectionalPad class.
	 */
	public static enum DPAD {
		UP(0), UP_RIGHT(45), RIGHT(90), DOWN_RIGHT(135), DOWN(180), DOWN_LEFT(225), LEFT(270), UP_LEFT(315);

		/* Instance Value */
		private int value;

		/**
		 * Constructor
		 * 
		 * @param value
		 */
		DPAD(final int value) {
			this.value = value;
		}

		/**
		 * Convert integers to DPAD values
		 * 
		 * @param value
		 * @return DPAD with matching angle
		 */
		public static DPAD getEnum(int angle) {
			angle = Math.abs(angle);
			angle %= 360;
			angle = Math.round(angle / 45) * 45; // May have rounding errors.
													// Due to rounding errors.

			DPAD[] all = DPAD.values();

			for (int i = 0; i < all.length; i++) {
				if (all[i].value == angle) {
					return all[i];
				}
			}
			// I don't know what to do here
			// throw new UnsupportedOperationException("Integer supplied (" +
			// angle + ") is not a possible value of this enum.");
			System.out.println(
					"[XboxController.DPAD.getEnum()] Angle supplied (" + angle + ") has no related DPad direction");
			return DPAD.UP;
		}
	}

	/**
	 * This class is used to represent one of the two Triggers on an Xbox360
	 * controller.
	 */
	public static class Trigger extends Button {

		/* Instance Values */
		private final Joystick parent;
		private final HAND hand;

		private double deadZone;
		private double sensitivity;

		/**
		 * Constructor
		 * 
		 * @param joystick
		 * @param hand
		 */
		Trigger(final Joystick joystick, final HAND hand) {

			/* Initialize */
			this.parent = joystick;
			this.hand = hand;
			this.deadZone = DEFAULT_TRIGGER_DEADZONE;
			this.sensitivity = DEFAULT_TRIGGER_SENSITIVITY;
		}

		/* Extended Methods */
		@Override
		public boolean get() {
			return getX() > sensitivity;
		}

		/* Get Methods */
		/**
		 * getHand
		 * 
		 * @return Trigger hand
		 * 
		 *         See which side of the controller this trigger is
		 */
		public HAND getHand() {
			return hand;
		}

		/**
		 * 0 = Not pressed 1 = Completely pressed
		 * 
		 * @return How far its pressed
		 */
		public double getX() {
			final double rawInput;

			if (hand == HAND.LEFT) {
				rawInput = parent.getRawAxis(LEFT_TRIGGER_AXIS_ID);
			} else {
				rawInput = parent.getRawAxis(RIGHT_TRIGGER_AXIS_ID);
			}

			return createDeadZone(rawInput, deadZone);
		}

		/* Set Methods */
		/**
		 * Set the deadzone of this trigger
		 * 
		 * @param number
		 */
		public void setTriggerDeadZone(double number) {
			this.deadZone = number;
		}

		/**
		 * How far you need to press this trigger to activate a button press
		 * 
		 * @param number
		 */
		public void setTriggerSensitivity(double number) {
			this.sensitivity = number;
		}
	}

	/**
	 * This is a weird object which is essentially just 8 buttons.
	 */
	public static class DirectionalPad extends Button {

		/* Instance Values */
		private final Joystick parent;

		public final Button up;
		public final Button upRight;
		public final Button right;
		public final Button downRight;
		public final Button down;
		public final Button downLeft;
		public final Button left;
		public final Button upLeft;

		/**
		 * Constructor
		 * 
		 * @param parent
		 */
		DirectionalPad(final Joystick parent) {

			/* Initialize */
			this.parent = parent;
			this.up = new DPadButton(this, DPAD.UP);
			this.upRight = new DPadButton(this, DPAD.UP_RIGHT);
			this.right = new DPadButton(this, DPAD.RIGHT);
			this.downRight = new DPadButton(this, DPAD.DOWN_RIGHT);
			this.down = new DPadButton(this, DPAD.DOWN);
			this.downLeft = new DPadButton(this, DPAD.DOWN_LEFT);
			this.left = new DPadButton(this, DPAD.LEFT);
			this.upLeft = new DPadButton(this, DPAD.UP_LEFT);
		}

		/**
		 * This class is used to represent each of the 8 values a dPad has as a
		 * button.
		 */
		public static class DPadButton extends Button {

			/* Instance Values */
			private final DPAD direction;
			private final DirectionalPad parent;

			/**
			 * Constructor
			 * 
			 * @param parent
			 * @param dPad
			 */
			DPadButton(final DirectionalPad parent, final DPAD dPadDirection) {

				/* Initialize */
				this.direction = dPadDirection;
				this.parent = parent;
			}

			/* Extended Methods */
			@Override
			public boolean get() {
				return parent.getAngle() == direction.value;
			}
		}

		private int angle() {
			return parent.getPOV();
		}

		/* Extended Methods */
		@Override
		public boolean get() {
			return angle() != -1;
		}

		/* Get Methods */
		/**
		 * UP 0; UP_RIGHT 45; RIGHT 90; DOWN_RIGHT 135; DOWN 180; DOWN_LEFT 225;
		 * LEFT 270; UP_LEFT 315;
		 * 
		 * @return A number between 0 and 315 indicating direction
		 */
		public int getAngle() {
			return angle();
		}

		/**
		 * Just like getAngle, but returns a direction instead of an angle
		 * 
		 * @return A DPAD direction
		 */
		public DPAD getDirection() {
			return DPAD.getEnum(angle());
		}
	}

	/**
	 * Creates a deadzone, but without clipping the lower values. turns this
	 * |--1--2--3--4--5--| into this ______|-1-2-3-4-5-|
	 * 
	 * @param input
	 * @param deadZoneSize
	 * @return adjusted_input
	 */
	private static double createDeadZone(double input, double deadZoneSize) {
		final double negative;
		double deadZoneSizeClamp = deadZoneSize;
		double adjusted;

		if (deadZoneSizeClamp < 0 || deadZoneSizeClamp >= 1) {
			deadZoneSizeClamp = 0; // Prevent any weird errors
		}

		negative = input < 0 ? -1 : 1;

		adjusted = Math.abs(input) - deadZoneSizeClamp; // Subtract the deadzone
														// from the magnitude
		adjusted = adjusted < 0 ? 0 : adjusted; // if the new input is negative,
												// make it zero
		adjusted = adjusted / (1 - deadZoneSizeClamp); // Adjust the adjustment
														// so it can max at 1

		return negative * adjusted;
	}

	/* Get Methods */
	/**
	 * @return The port of this XboxController
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return The Joystick of this XboxController
	 */
	public Joystick getJoystick() {
		return controller;
	}

	/* Set Methods */
	/**
	 * Make the controller vibrate
	 * 
	 * @param hand
	 *            The side of the controller to rumble
	 * @param intensity
	 *            How strong the rumble is
	 */
	public void setRumble(HAND hand, double intensity) {
		final float amount = new Float(intensity);

		if (hand == HAND.LEFT) {
			controller.setRumble(RumbleType.kLeftRumble, amount);
		} else {
			controller.setRumble(RumbleType.kRightRumble, amount);
		}
	}

	/**
	 * Make the controller vibrate
	 * 
	 * @param intensity
	 *            How strong the rumble is
	 */
	public void setRumble(double intensity) {
		final float amount = new Float(intensity);

		controller.setRumble(RumbleType.kLeftRumble, amount);
		controller.setRumble(RumbleType.kRightRumble, amount);
	}
}

// Old Implementation below

// package org.usfirst.frc.team1923.robot.utils;
//
// import edu.wpi.first.wpilibj.*;
//
// public class XboxController extends Joystick {
//
// public XboxController(int port) {
// super(port);
// // TODO Auto-generated constructor stub
// }
//
// public boolean getButton(XboxController.Button input) {
// return this.getRawButton(input.value);
// }
//
// public double DPad() {
// return this.getRawAxis(6);
// }
//
// public double getRightTrigger(){
// return this.getRawAxis(3) ;
// }
// public double getLeftTrigger(){
// return this.getRawAxis(2);
// }
//
// public int getSharpTriggerDiff(){
// double temp = getRightTrigger() - getLeftTrigger();
//
// if (temp > .1 ) {
// return 1;
// } else if (temp < -.1) {
// return -1;
// } else {
// return 0;
// }
// }
//
// public static class Button {
//
// public final int value;
// public static final int kA_val = 1;
// public static final int kB_val = 2;
// public static final int kX_val = 3;
// public static final int kY_val = 4;
// public static final int kLB_val = 5;
// public static final int kRB_val = 6;
// public static final int kStart_val = 8;
// public static final int kBack_val = 7;
// public static final int kLeftClick_val = 9;
// public static final int kRightClick_val = 10;
// public static final int kLT_val = 11;
// public static final int kRT_val = 12;
// public static final Button A = new Button(kA_val);
// public static final Button B = new Button(kB_val);
// public static final Button X = new Button(kX_val);
// public static final Button Y = new Button(kY_val);
// public static final Button LB = new Button(kLB_val);
// public static final Button RB = new Button(kRB_val);
// public static final Button LT = new Button(kLT_val);
// public static final Button RT = new Button(kRT_val);
// public static final Button Start = new Button(kStart_val);
// public static final Button Back = new Button(kBack_val);
// public static final Button LeftClick = new Button(kLeftClick_val);
// public static final Button RightClick = new Button(kRightClick_val);
//
// private Button(int value) { // Value inputed is equal to value in Button
// // class
// this.value = value;
// }
//
// }
// }
