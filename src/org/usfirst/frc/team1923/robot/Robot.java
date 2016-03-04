
package org.usfirst.frc.team1923.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team1923.robot.commands.*;
import org.usfirst.frc.team1923.robot.subsystems.*;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 */
public class Robot extends IterativeRobot {
	
	public static GearSubsystem gearSubsystem = new GearSubsystem();
	public static PIDDriveTrainSubsystem driveSubsystem = new PIDDriveTrainSubsystem();
	public static IntakeRollerSubsystem intakeRollerSubsystem = new IntakeRollerSubsystem();
	public static IntakePistonSubsystem intakePistonSubsystem = new IntakePistonSubsystem();

	
	public static OI oi;

    public Command autonomousCommand;
    public Command teleopCommand;
    public SendableChooser chooser; //TODO make a chooser 
    public CameraServer server;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI(); 
				
		chooser = new SendableChooser();
		teleopCommand = new TeleopCommand();
		
		gearSubsystem.shiftDown(); //forces start in low gear
		intakePistonSubsystem.intakeUp(); //force intake to go up
		RobotMap.mainCompressor.setClosedLoopControl(true); 
		
		initCamera();//start camera feed
		
		chooser = new SendableChooser();
		chooser.addDefault("Do nothing", new AutonNothing());
		chooser.addObject("low bar no shot", new AutonLowbarNoShot());
		SmartDashboard.putData("Auto Mode", chooser);
		
    }
    
    public void initCamera(){
    	server = CameraServer.getInstance();
    	server.setQuality(100);
    	server.startAutomaticCapture("cam1");	
    }

	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	if (autonomousCommand != null)
			autonomousCommand.cancel();
		if (teleopCommand != null)
			teleopCommand.cancel();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        log();
    }
    
    
    public void log(){
    	SmartDashboard.putNumber("left joy: ", oi.leftStick.getY());
    	SmartDashboard.putNumber("right joy: ", oi.rightStick.getY());
    	
    	SmartDashboard.putString("Intake Roller Status: ", intakeRollerSubsystem.getIntakeState());
    	SmartDashboard.putBoolean("Intake Down?: ", intakePistonSubsystem.intakePosition());
    	
    	SmartDashboard.putBoolean("Low Gear: ", gearSubsystem.getGearPosition());
    	
    	SmartDashboard.putNumber("Left Drive Encoder: ", RobotMap.leftDriveEncoder.get());
    	SmartDashboard.putNumber("RIght Drive Encoder: ", RobotMap.rightDriveEncoder.get());	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
}
