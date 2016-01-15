package org.usfirst.frc.team1923.robot.utils;

import edu.wpi.first.wpilibj.*;

public class XboxController extends Joystick {

	public XboxController(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	public boolean getButton(XboxController.Button input) {
		return this.getRawButton(input.value);
	}

	public double DPad() {
		return this.getRawAxis(6);
	}

	public double getRightTrigger(){
		return this.getRawAxis(3) ;
	} 
	public double getLeftTrigger(){
		return  this.getRawAxis(2);
	} 
	
	public int getSharpTriggerDiff(){
		double temp = getRightTrigger() - getLeftTrigger();
		
		if (temp > .1 ) {
			return 1;
		} else if (temp < -.1) {
			return -1;
		} else {
			return 0;
		}
	}

	public static class Button {

		public final int value;
		public static final int kA_val = 1;
		public static final int kB_val = 2;
		public static final int kX_val = 3;
		public static final int kY_val = 4;
		public static final int kLB_val = 5;
		public static final int kRB_val = 6;
		public static final int kStart_val = 8;
		public static final int kBack_val = 7;
		public static final int kLeftClick_val = 9;
		public static final int kRightClick_val = 10;
		public static final int kLT_val = 11;
		public static final int kRT_val = 12;
		public static final Button A = new Button(kA_val);
		public static final Button B = new Button(kB_val);
		public static final Button X = new Button(kX_val);
		public static final Button Y = new Button(kY_val);
		public static final Button LB = new Button(kLB_val);
		public static final Button RB = new Button(kRB_val);
		public static final Button LT = new Button(kLT_val);
		public static final Button RT = new Button(kRT_val);
		public static final Button Start = new Button(kStart_val);
		public static final Button Back = new Button(kBack_val);
		public static final Button LeftClick = new Button(kLeftClick_val);
		public static final Button RightClick = new Button(kRightClick_val);

		private Button(int value) { // Value inputed is equal to value in Button
									// class
			this.value = value;
		}

	}
}
