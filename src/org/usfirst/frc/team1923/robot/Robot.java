
package org.usfirst.frc.team1923.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team1923.robot.commands.*;
import org.usfirst.frc.team1923.robot.subsystems.*;
import org.usfirst.frc.team1923.robot.utils.*;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Robot extends IterativeRobot {
	
	public static GearSubsystem gearSubsystem = new GearSubsystem();
	public static DriveTrainSubsytem driveSubsystem = new DriveTrainSubsytem();
	public static IntakeRollerSubsystem intakeRollerSubsystem = new IntakeRollerSubsystem();
	public static IntakePistonSubsystem intakePistonSubsystem = new IntakePistonSubsystem();
    public static ShooterPistonSubsystem shooterPistonSubsystem = new ShooterPistonSubsystem();
    public static ShooterWheelSubsystem shooterWheelSubsystem = new ShooterWheelSubsystem();

	public static OI oi;

    public Command autonomousCommand;
    public Command teleopCommand;
    public SendableChooser chooser; //TODO make a chooser 
    public CameraServer server;
    
    public USBCamera camera;
    
    
    public void robotInit() {
		oi = new OI(); 
				
		chooser = new SendableChooser();
		teleopCommand = new TeleopCommand();
		
		gearSubsystem.shiftDown(); //forces start in low gear
		intakePistonSubsystem.intakeUp(); //force intake to go up
		shooterPistonSubsystem.shooterDown(); //force shooter down
		RobotMap.mainCompressor.setClosedLoopControl(true); 
		
		initCamera();//start camera feed
    }
    
    public void initCamera(){
    	camera = new USBCamera("cam0");//cam1 for knightmare
    	camera.setFPS(20);
    	camera.updateSettings();
    	
    	server = CameraServer.getInstance();
    	server.setQuality(50);
    	server.startAutomaticCapture(camera);	
    }


    public void disabledInit(){
    	if (autonomousCommand != null)
			autonomousCommand.cancel();
		if (teleopCommand != null)
			teleopCommand.cancel();
		
		//Disabled shifting to low, talons to break
		
		Robot.gearSubsystem.shiftDown();
		RobotMap.leftDriveOne.enableBrakeMode(true);
		RobotMap.leftDriveTwo.enableBrakeMode(true);
		RobotMap.leftDriveThree.enableBrakeMode(true);
		RobotMap.rightDriveOne.enableBrakeMode(true);
		RobotMap.rightDriveTwo.enableBrakeMode(true);
		RobotMap.rightDriveThree.enableBrakeMode(true);
		
//		camera.stopCapture();
//		camera.closeCamera();
		
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        autonomousCommand = (Command) new DefenseAuton();
        autonomousCommand.start();
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
    	
    	SmartDashboard.putNumber("Shooter Encoder Rate: ", RobotMap.shooterEncoder.getRate());
    	//SmartDashboard.putNumber("Shooter Left Amp: ", RobotMap.shooterLeft.getOutputCurrent());
    	//SmartDashboard.putNumber("Shooter Right Amp: " , RobotMap.shooterRight.getOutputCurrent());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
}
