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
	private double timeOut = 3.0; //Default timeout when robot starts
	private boolean manual = true;//Boolean to indicate if PID is in use
	
	private double oldLeftSpeed=0, oldRightSpeed=0;	//Used for smoothDrive
	private static final double 
	Pe = 0.000, Ie = 0.000, De = 0.000;	//TODO: Input those values
	private static final double
	ENC_DIST_TOLERANCE = 1.0, //Tolerance in distance units
	WHEEL_CIRCUMFERENCE = 6*Math.PI,		//**** DISTANCE UNIT IN INCHES ****
	DEFAULT_TIMEOUT = 3.0; //Default of 3 seconds
    public PIDDriveTrainSubsystem() {
    	super(Pe, Ie, De);
    	RobotMap.leftDriveEncoder.setDistancePerPulse(WHEEL_CIRCUMFERENCE/256.0);
    	RobotMap.rightDriveEncoder.setDistancePerPulse(WHEEL_CIRCUMFERENCE/256.0);
    	this.getPIDController().setPID(Pe, Ie, De);	//Redundant?
    	this.setAbsoluteTolerance(ENC_DIST_TOLERANCE);
    	this.setOutputRange(-1.0, 1.0);	//Make sure it's legal for talon power setting
    	this.setInputRange(-200, 200);	//Setpoint distances???
    	this.disable();	//Starts in manual mode (user controlled)
    	
    	timer=new Timer();	//Used for timeout calculations
    }
    
    private void setManual(boolean m)	//Enables or disables PID calculations
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
    
    public void manualDrive(double left, double right) {	//Overload
    	manualDrive(left, right, DEFAULT_TIMEOUT);
    }

    public void manualDrive(double left, double right, double timeOut) {	//Teleop-ed smooth drive
    	setManual(true);
    	smoothDrive(left, right);
    	this.timeOut = timeOut;
		this.timer.reset();
		this.timer.start();
    }
    
    private void rawDrive(double l, double r) {		//Under the hood stuff
    	RobotMap.leftDriveOne.set(l);
    	RobotMap.leftDriveTwo.set(l);
    	RobotMap.leftDriveThree.set(l);

    	RobotMap.leftDriveOne.set(r);
    	RobotMap.leftDriveTwo.set(r);
    	RobotMap.leftDriveThree.set(r);
    }
    
    public void stop() {	//Hammer time
    	rawDrive(0,0);
    }
    
    private void smoothDrive(double left, double right){	//	Under the hood coalescing
		// open loop bias based correction
		oldLeftSpeed = Calculator.ease(left, oldLeftSpeed);
		oldRightSpeed = Calculator.ease(right, oldRightSpeed);

		left = oldLeftSpeed;
		right = oldRightSpeed;
		
		rawDrive(left, right);
	}
    
    public boolean isManual() {		//Getter for the private mode boolean
    	return manual;
    }
    
    public void driveDistance(double dist, double timeOut)		//Drives straight using PID
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
    
    private void resetEncoders() {		//Under the hood
    	RobotMap.leftDriveEncoder.reset();
    	RobotMap.rightDriveEncoder.reset();
    }
    public boolean reachedTarget() {	//Is it finished?
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
    
    protected void usePIDOutput(double output) {	//System calls this method to set the talons
    	if(!manual) {
    		smoothDrive(output, output);
    	}
    }
}
