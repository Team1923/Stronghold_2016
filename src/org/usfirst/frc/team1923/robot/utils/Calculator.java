package org.usfirst.frc.team1923.robot.utils;

import org.usfirst.frc.team1923.robot.RobotMap;

/**
 * A class that contains various mathematic methods
 * @author Chris
 *
 */
public class Calculator {

	public static double ease(double current, double old, double easeIncrement) {
		double newVal;
		if (current < old - easeIncrement) {
			newVal = old - easeIncrement;
		} else if (current > old + easeIncrement) {
			newVal = old + easeIncrement;
		} else {
			newVal = current;
		}
		return newVal;
	}
	
	public static double ease(double current, double old){
		return ease(current, old, RobotMap.EASE_INCREMENT);
	}
	
	
}
