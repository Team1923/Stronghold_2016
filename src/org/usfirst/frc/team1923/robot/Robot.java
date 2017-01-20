
package org.usfirst.frc.team1923.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team1923.robot.commands.*;
import org.usfirst.frc.team1923.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static GearSubsystem gearSubsystem = new GearSubsystem();
	public static DriveTrainSubsytem driveSubsystem = new DriveTrainSubsytem();
	public static IntakeRollerSubsystem intakeRollerSubsystem = new IntakeRollerSubsystem();
	public static IntakePistonSubsystem intakePistonSubsystem = new IntakePistonSubsystem();
	public static ShooterPistonSubsystem shooterPistonSubsystem = new ShooterPistonSubsystem();
	public static ShooterWheelSubsystem shooterWheelSubsystem = new ShooterWheelSubsystem();
	public static DefensePistonSubsystem defensePistonSubsystem = new DefensePistonSubsystem();

	public static OI oi;

	public Command autonomousCommand;
	public Command teleopCommand;
	public SendableChooser chooser;
	public CameraServer server;

	public UsbCamera camera;

	public void robotInit() {
		oi = new OI();
		teleopCommand = new TeleopCommand();
		chooser = new SendableChooser();

		initializeAutons();// make auto
		defaultRobotPosition(); // force robot into match config
		initCamera();// start camera feed
	}

	public void initializeAutons() {
		chooser.addDefault("Cross Defense", new AUTON_cross_defense());
		chooser.addObject("Starvation", new AUTON_starvation());
		chooser.addObject("Take the L", new AUTON_nothing());
		log();
		// SmartDashboard.putData("Auton Chooser: ", chooser);
	}

	public void defaultRobotPosition() {
		gearSubsystem.shiftDown(); // forces start in low gear
		intakePistonSubsystem.intakeUp(); // force intake to go up
		shooterPistonSubsystem.shooterDown(); // force shooter down
		defensePistonSubsystem.up(); // bring defense pistons up
		RobotMap.mainCompressor.setClosedLoopControl(true); // turn compressor
															// on
	}

	public void initCamera() {
		// camera = new UsbCamera();/ cam1 for knightmare
		// camera.setFPS(20);
		// camera.updateSettings();

		// server = CameraServer.getInstance();
		// server.setQuality(50);
		// server.startAutomaticCapture(camera);
	}

	public void disabledInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		if (teleopCommand != null)
			teleopCommand.cancel();

		// Disabled shifting to low, talons to break

		Robot.gearSubsystem.shiftDown();
		RobotMap.leftDriveOne.enableBrakeMode(true);
		RobotMap.leftDriveTwo.enableBrakeMode(true);
		RobotMap.leftDriveThree.enableBrakeMode(true);
		RobotMap.rightDriveOne.enableBrakeMode(true);
		RobotMap.rightDriveTwo.enableBrakeMode(true);
		RobotMap.rightDriveThree.enableBrakeMode(true);

		// camera.stopCapture();
		// camera.closeCamera();

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();
		autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		// hoodManagement();
		log();
	}

	public void hoodManagement() {
		if (RobotMap.shooterEncoder.getRate() > 600)
			Robot.shooterPistonSubsystem.shooterUp();
		else if (RobotMap.shooterEncoder.getRate() < 500)
			Robot.shooterPistonSubsystem.shooterDown();
	}

	public void log() {
		SmartDashboard.putData("Auton Chooser: ", chooser);

		SmartDashboard.putNumber("left joy: ", oi.leftStick.getY());
		SmartDashboard.putNumber("right joy: ", oi.rightStick.getY());
		SmartDashboard.putBoolean("Low Gear: ", gearSubsystem.getGearPosition());

		SmartDashboard.putString("Intake Roller Status: ", intakeRollerSubsystem.getIntakeState());
		SmartDashboard.putBoolean("Intake Down?: ", intakePistonSubsystem.intakePosition());
		SmartDashboard.putBoolean("Intake Switch 1: ", RobotMap.limitSwitch1.get());
		SmartDashboard.putBoolean("Intake Switch 2: ", RobotMap.limitSwitch2.get());

		SmartDashboard.putBoolean("Shooter Hood Up?: ", !shooterPistonSubsystem.position());
		SmartDashboard.putBoolean("READY?!: ", readyToShooter());
		SmartDashboard.putNumber("Shooter Encoder Rate: ", RobotMap.shooterEncoder.getRate());

		SmartDashboard.putNumber("RIGHT:", RobotMap.shooterLeft.get());
		SmartDashboard.putNumber("LEFT: ", RobotMap.shooterRight.get());

		// SmartDashboard.putBoolean("call: ", oi.xboxController.rb.get());
		// SmartDashboard.putBoolean("response: ",
		// Robot.intakeRollerSubsystem.getOverride());

		SmartDashboard.putBoolean("Defense Piston Down: ", defensePistonSubsystem.isDown());

		// SmartDashboard.putNumber("Right Drive Encoder: ",
		// RobotMap.rightDriveEncoder.get());
		// SmartDashboard.putNumber("Left Drive Encoder: ",
		// RobotMap.leftDriveEncoder.get());
	}

	public boolean readyToShooter() {
		return RobotMap.shooterEncoder.getRate() > 675 && RobotMap.shooterEncoder.getRate() < 750
				&& !shooterPistonSubsystem.position();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}

}
