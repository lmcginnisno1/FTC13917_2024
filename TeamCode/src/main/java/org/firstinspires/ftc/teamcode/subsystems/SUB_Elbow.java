package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.TrapezoidMotionProfile;

import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_Elbow extends SubsystemBase{
     DcMotorEx m_elbowmotor;
     TrapezoidMotionProfile m_profile = new TrapezoidMotionProfile();
     ElapsedTime m_elapsedTime = new ElapsedTime();
     public SUB_Elbow(OpMode p_opMode, final String p_elbowmotorname) {
          m_elbowmotor = p_opMode.hardwareMap.get(DcMotorEx.class, p_elbowmotorname);
          m_elbowmotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
     }
     public void setPower(double p_power){
          m_elbowmotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
          m_elbowmotor.setPower(p_power);
     }

     public double getPosition(){
          return m_elbowmotor.getCurrentPosition();
     }

     public void setTargetPosition(int p_position){
          m_elbowmotor.setTargetPosition(p_position);
     }
}
