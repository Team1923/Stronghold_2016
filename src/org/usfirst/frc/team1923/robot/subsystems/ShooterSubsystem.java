//package org.usfirst.frc.team1923.robot.subsystems;
//
//import org.usfirst.frc.team1923.robot.RobotMap;
//import org.usfirst.frc.team1923.robot.utils.Calculator;
//
//import edu.wpi.first.wpilibj.command.Subsystem;
//
///**
// * @modified Tim
// * @modified Xavier
// */
//public class ShooterSubsystem extends Subsystem {
//
//	enum ShooterState {
//		READY, STOP
//	}
//	private static final double MAX_RATE = 3600; //Unit is in rpm TODO: calculate the rpm max
//
//	private double rate = 0;
//	private double targetRate;
//	private ShooterState shooterState = ShooterState.STOP;
//
//	public void initDefaultCommand() {
//		// Set the default command for a subsystem here.
//		// setDefaultCommand(new MySpecialCommand());
//	}
//
//	/**
//	 * This method starts up the shooter
//	 * 
//	 * @param speed
//	 */
//	public void startUp(double targetRate) {
//		this.targetRate = targetRate;
//		update();
//	}
//
//	/**
//	 * This method stops the shooter
//	 */
//	public void stop() {
//		RobotMap.shooterWheel.set(0);
//	}
//
//	/**
//	 * This updates the status of the shooter based on the current angular
//	 * momentum
//	 */
//	public void update() {
//		rate = RobotMap.shooterEncoder.getRate();
//		if (rate > targetRate)
//			shooterState = ShooterState.READY;
//		else if (RobotMap.shooterEncoder.getStopped())
//			shooterState = ShooterState.STOP;
//
//		RobotMap.shooterWheel.set(Calculator.getShooterFeedbackControl(rate, targetRate));
//	}
//
//	/**
//	 * Returns the state of the shooter
//	 */
//	public ShooterState getShooterState() {
//
//		return shooterState;
//	}
//	
//	/**
//	 * Returns the target rate that the subsystem is trying to achieve
//	 */
//	
//	public double getTarget()
//	{
//		return targetRate;
//	}
//
//	/**
//	 * Returns the rate of the motor
//	 * 
//	 * @return rate;
//	 */
//	public double getRate() {
//		return rate;
//	}
//	
//	public boolean setRate(double rate) {
//		if(rate>=0&&rate<=MAX_RATE)
//			this.rate=rate;
//		else
//			return false;
//		return true;
//	}
//}
