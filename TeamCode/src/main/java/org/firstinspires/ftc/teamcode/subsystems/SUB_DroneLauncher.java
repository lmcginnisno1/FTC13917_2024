package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants.droneLauncherConstants;
import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_DroneLauncher extends SubsystemBase {

     Servo m_DroneTriggerServo;
     public SUB_DroneLauncher(OpMode p_opMode, final String p_DroneTriggerServoName){
          m_DroneTriggerServo = p_opMode.hardwareMap.get(Servo.class, p_DroneTriggerServoName);
          m_DroneTriggerServo.setPosition(droneLauncherConstants.kClosed);
     }

     public void releaseTheDrone(){
          m_DroneTriggerServo.setPosition(droneLauncherConstants.kOpen);
     }

     public void close(){
          m_DroneTriggerServo.setPosition(droneLauncherConstants.kClosed);
     }
}
