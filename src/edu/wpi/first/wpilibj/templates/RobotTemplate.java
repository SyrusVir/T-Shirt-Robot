package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class RobotTemplate extends SimpleRobot {
    RobotDrive chassis = new RobotDrive(1, 2);
    Joystick mainStick = new Joystick(1);
    Victor compressor = new Victor(3);
    Victor valve = new Victor(4);
    DoubleSolenoid tank = new DoubleSolenoid(1, 8);
    
    public void autonomous() {
        
    }
    public void operatorControl() {
        chassis.setSafetyEnabled(true);
        
        while (isOperatorControl() && isEnabled()) { //Main teleop loop
            chassis.arcadeDrive(mainStick.getY(), -1 * mainStick.getX(), true); //squared inputs, x value inverted
            Timer.delay(0.1); //Must be 0.01 because of setSafetyEnabled
            
            if(mainStick.getRawButton(2)){  //Activate compressor
                compressor.set(1.0);
            }
            
            if(mainStick.getRawButton(3)){ //Deactivate compressor
                compressor.set(0.0);
            }
            
            if(mainStick.getRawButton(1)){ //Button 3 Hold to open trigger valve
                valve.set(1.0);
            }else{
                valve.set(0.0);}
            
            
        }
    }
    public void test() {
        chassis.setSafetyEnabled(true);
        while (isTest() && isEnabled()) { //test loop
            if(mainStick.getRawButton(2)){  //Activate compressor
                    compressor.set(1.0);
            }
        
            if(mainStick.getRawButton(3)){ //Deactivate compressor
                compressor.set(0.0);
            }
            
            if(mainStick.getRawButton(1)){ //Button 3 activated open valve (sustained open)
                valve.set(1.0);
            }else{
                valve.set(0.0);}
        
            if(mainStick.getRawButton(6)){ //Button 6 activates double solenoid valve
                tank.set(DoubleSolenoid.Value.kForward);
            }else{
                tank.set(DoubleSolenoid.Value.kOff);}
        }
        
    }
}