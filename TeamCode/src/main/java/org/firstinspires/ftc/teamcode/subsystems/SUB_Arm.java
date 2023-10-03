package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_Arm extends SubsystemBase{
     DcMotor m_shouldermotor;
     DcMotor m_elbowmotor;
     Servo m_wristservo;

     private int m_armLevel = 0;
     public SUB_Arm(OpMode p_opMode, final String p_shouldermotorname, final String p_elbowmotorname,
                    final String p_wristservoname) {

          m_shouldermotor = p_opMode.hardwareMap.get(DcMotor.class, p_shouldermotorname);
          m_shouldermotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

          m_elbowmotor = p_opMode.hardwareMap.get(DcMotor.class, p_elbowmotorname);
          m_elbowmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

          m_wristservo = p_opMode.hardwareMap.get(Servo.class, p_wristservoname);

     }

     public void setShoulderPower(double p_power){
          m_shouldermotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
          m_shouldermotor.setPower(p_power);
     }

     public void setShoulderPosition(int p_position){
          m_shouldermotor.setTargetPosition(p_position);
          m_shouldermotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          m_shouldermotor.setPower(0.33);
     }

     public void setElbowPower(double p_power){
          m_elbowmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
          m_elbowmotor.setPower(p_power);
     }

     public void setElbowPosition(int p_position){
          m_elbowmotor.setTargetPosition(p_position);
          m_elbowmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          m_elbowmotor.setPower(0.33);
     }

     public void setWristPosition(double p_position){
          m_wristservo.setPosition(p_position);
     }

     public int getArmLevel(){
          return m_armLevel;
     }

}
