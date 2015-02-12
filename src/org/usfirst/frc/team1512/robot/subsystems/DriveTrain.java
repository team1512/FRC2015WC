package org.usfirst.frc.team1512.robot.subsystems;

import org.usfirst.frc.team1512.robot.OI;
import org.usfirst.frc.team1512.robot.RobotMap;
import org.usfirst.frc.team1512.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	double deadzone = 0.2;
	double expoIncre = 1;
	double upperLimit = 0.1;
	private Talon fl, fr, bl, br;
	private RobotDrive drive;
	OI oi = new OI();
	boolean MD;
	boolean TD;
	boolean flag;
	Timer time;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public DriveTrain(){
		super();
		fl = new Talon(0);
		fr = new Talon(1);
		bl = new Talon(2);
		br = new Talon(3);
		MD = true;
		TD = false;
		time = new Timer();
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void tank(double y, double rot)
    {
    	if (Math.abs(y) <= deadzone)
    		{y = 0.0;}
    	if (Math.abs(rot) <= deadzone)
    		{rot = 0.0;}
    	fl.set(y+rot);
    	fr.set(y-rot);
    	bl.set(y+rot);
    	fr.set(y-rot);
    	
    	TD = true;
    	MD = false;
    }
    
    public void mecanum(double x, double y, double rot){   	 	
    	if (Math.abs(x) <= deadzone) 
    		{x = 0.0;}
    	if (Math.abs(y) <= deadzone) 
    		{y = 0.0;}
    	if (Math.abs(rot) <= deadzone) 
    		{rot = 0.0;}
    	

    	fl.set((y+rot-x)*Math.abs(y+rot-x));
       	fr.set((y-rot+x)*Math.abs(y-rot+x));
    	bl.set((y+rot+x)*Math.abs(y+rot+x));
    	br.set((y-rot-x)*Math.abs(y-rot-x));
    	
    	MD = true;
    	TD = false;
    }
    
    public void shift(double x, double y , double rot)
    {	

			if (oi.AButton.get())
	    	{
	    		MD = !MD;
	    		TD = !TD;
	    	}
	    	
	    	if (MD&&!TD)
	    	{
	    		mecanum(x, y, rot);
	    		System.out.println("Mecanum Mode");
	    	}
	    	else if (!MD&&TD)
	    	{
	    		tank(y, rot);
	    		System.out.println("Tank Mode");
	    	}
	    	
	    

    	
}
}

