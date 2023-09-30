package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_Shoulder extends SubsystemBase{
     DcMotor m_shouldermotor;
     public SUB_Shoulder(OpMode p_opMode, final String p_shouldermotorname) {

          m_shouldermotor = p_opMode.hardwareMap.get(DcMotor.class, p_shouldermotorname);
          m_shouldermotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     }

     public void setPower(double p_power){
          m_shouldermotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
          m_shouldermotor.setPower(p_power);
     }

     public void setPosition(int p_position){
          m_shouldermotor.setTargetPosition(p_position);
          m_shouldermotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          m_shouldermotor.setPower(0.33);
     }

}
