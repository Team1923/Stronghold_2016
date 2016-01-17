package org.usfirst.frc.team1923.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 * 
 * @author Xavier
 *
 */

public class LEDSubsystem extends Subsystem{

	private DriverStation.Alliance color;
	private int stationNumber;//station number TODO do we even need this
	private double time; //match time TODO do we even need this
	
	public LEDSystem(){
		super();
		color = DriverStation.getInstance().getAlliance();
		time = DriverStation.getInstance().getMatchTime();
		stationNumber = DriverStation.getInstance().getLocation();
		if(color == DriverStation.Alliance.kBlue){
			//blue lights on
			turnLEDOn(true);
		}
		else{
			//red lights on
			turnLEDOn(false);
		}
	}
	
	public void initDefaultCommand() {
		//init code here
	}
	
	public DriverStation.Alliance getAlliance(){
		return color;
	}
	
	public void turnLEDOn(boolean color){// true = blue false = red
		if(color){
			//TASK turn blue lights on
		}
		else{
			//TASK turn red lights on
		}
	}
	
	public int getStationNumber(){
		return stationNumber;
	}
	
	public double getMatchTime(){
		return time;
	}
}
