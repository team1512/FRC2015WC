package org.usfirst.frc.team1512.robot.commands;

import org.usfirst.frc.team1512.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1512.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class cmd extends Command {

	public static OI oi;
	DriveTrain drive = new DriveTrain();
	
    public static void init(){
    	oi = new OI();
    }
    
    public cmd(String name){
    	super(name);
    }
    
    public cmd(){
    	super();
    }
}
