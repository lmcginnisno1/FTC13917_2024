package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_Wrist extends SubsystemBase{
     Servo m_wristservo;

     public SUB_Wrist(OpMode p_opMode, final String p_wristservoname) {
          m_wristservo = p_opMode.hardwareMap.get(Servo.class, p_wristservoname);

     }

     public void setPosition(double p_position){
          m_wristservo.setPosition(p_position);
     }
}
