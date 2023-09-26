package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_Elbow extends SubsystemBase{
     DcMotor m_elbowmotor;
     public SUB_Elbow(OpMode p_opMode, final String p_elbowmotorname) {
          m_elbowmotor = p_opMode.hardwareMap.get(DcMotor.class, p_elbowmotorname);
          m_elbowmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     }

     public void setPower(double p_power){
          m_elbowmotor.setPower(p_power);
     }

}
