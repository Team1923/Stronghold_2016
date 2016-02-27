
package org.usfirst.frc.team1923.robot;

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
 * @modified Xavier (1/23/2016)
 */
public class Robot extends IterativeRobot {
	
	public static GearSubsystem gearSubsystem = new GearSubsystem();
	public static DriveTrainSubsytem driveSubsystem = new DriveTrainSubsytem();
	public static IntakeRollerSubsystem intakeRollerSubsystem = new IntakeRollerSubsystem();
	public static IntakePistonSubsystem intakePistonSubsystem = new IntakePistonSubsystem();
	public static ShooterWheelSubsystem shooterWheelSubsystem = new ShooterWheelSubsystem();
	public static ShooterPistonSubsystem shooterPistonSubsystem = new ShooterPistonSubsystem();
	
	public static OI oi;

    public Command autonomousCommand;
    public Command teleopCommand;
    public SendableChooser chooser; //TODO make a chooser 

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
		shooterWheelSubsystem.stop(); //forces shooter to stop
		RobotMap.mainCompressor.setClosedLoopControl(true); //forces compressor loop		
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

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
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
    	
    	SmartDashboard.putBoolean("Is spinning? ", shooterWheelSubsystem.getStatus());
    	
    	SmartDashboard.putNumber("left spin: ", RobotMap.shooterLeft.get());
    	SmartDashboard.putNumber("right spin: " , RobotMap.shooterRight.get());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
}
