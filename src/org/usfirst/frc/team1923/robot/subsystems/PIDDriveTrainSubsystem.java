package org.usfirst.frc.team1923.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.commands.TeleopCommand;
import org.usfirst.frc.team1923.robot.utils.Calculator;
/**
 *
 */
public class PIDDriveTrainSubsystem extends PIDSubsystem {
	
	private Timer timer;
	private double timeOut = 1.0; //Default timeout when robot starts
	private boolean manual = true;
	
	private double oldLeftSpeed=0, oldRightSpeed=0;
	private static final double 
	Pe = 0.000, Ie = 0.000, De = 0.000;
	private static final double
	ENC_DIST_TOLERANCE = 1.0, //Tolerance in distance units
	WHEEL_CIRCUMFERENCE = 6*Math.PI,
	DEFAULT_TIMEOUT = 3.0; //Default of 3 seconds
    public PIDDriveTrainSubsystem() {
    	super(Pe, Ie, De);
    	RobotMap.leftDriveEncoder.setDistancePerPulse(WHEEL_CIRCUMFERENCE/256.0);
    	RobotMap.rightDriveEncoder.setDistancePerPulse(WHEEL_CIRCUMFERENCE/256.0);
    	this.getPIDController().setPID(Pe, Ie, De);
    	this.setAbsoluteTolerance(ENC_DIST_TOLERANCE);
    	this.setOutputRange(-1.0, 1.0);
    	this.setInputRange(-200, 200);
    	this.disable();
    	
    	timer=new Timer();
    }
    
    private void setManual(boolean m)
    {
    	if(m) {
    		manual=true;
    		this.disable();
    	}
    	else {
    		manual=false;
    		this.enable();
    	}
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleopCommand());
    }
    
    public void manualDrive(double left, double right) {
    	manualDrive(left, right, DEFAULT_TIMEOUT);
    }

    public void manualDrive(double left, double right, double timeOut) {
    	setManual(true);
    	smoothDrive(left, right);
    	this.timeOut = timeOut;
		this.timer.reset();
		this.timer.start();
    }
    
    public void rawDrive(double l, double r) {
    	RobotMap.leftDriveOne.set(l);
    	RobotMap.leftDriveTwo.set(l);
    	RobotMap.leftDriveThree.set(l);

    	RobotMap.leftDriveOne.set(r);
    	RobotMap.leftDriveTwo.set(r);
    	RobotMap.leftDriveThree.set(r);
    }
    
    public void stop() {
    	rawDrive(0,0);
    }
    
    public void smoothDrive(double left, double right){
		// open loop bias based correction
		oldLeftSpeed = Calculator.ease(left, oldLeftSpeed);
		oldRightSpeed = Calculator.ease(right, oldRightSpeed);

		left = oldLeftSpeed;
		right = oldRightSpeed;
		
		rawDrive(left, right);
	}
    
    public void driveDistance(double dist, double timeOut)
    {
    	resetEncoders();
    	setSetpoint(dist);
    	setManual(false);
    	this.timeOut = timeOut;
		this.timer.reset();
		this.timer.start();
    }
    
    public void driveDistance(double dist)	//Overload
    {
    	driveDistance(dist, DEFAULT_TIMEOUT);
    }
    
    public void resetEncoders() {
    	RobotMap.leftDriveEncoder.reset();
    	RobotMap.rightDriveEncoder.reset();
    }
    public boolean reachedTarget() {
		if (timer.get() > this.timeOut || this.onTarget()) {
			this.disable();
			manual = true;
			timer.stop();
			timer.reset();
			return true;
		}
		return false;
	}
    
    
    protected double returnPIDInput() {	//Returns the average distance since last reset
    	return (RobotMap.leftDriveEncoder.getDistance()+RobotMap.rightDriveEncoder.getDistance())/2.0;
    }
    
    protected void usePIDOutput(double output) {
    	if(!manual) {
    		smoothDrive(output, output);
    	}
    }
}
