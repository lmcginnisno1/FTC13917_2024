package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

/**
 * The Wrist subsystem comproised of three servos at the end of the arm.
 * wristservo: rotates the wrist
 * clawa: Holds the pixel on the inside of the claw
 * clawb: Holds the pixel towards the outside of the claw
 */
public class SUB_Intake extends SubsystemBase{
     DcMotor m_intakeMotor;
     boolean m_intakeIsOn = false;
     public SUB_Intake(OpMode p_opMode, final String p_intakemotorname) {
          m_intakeMotor = p_opMode.hardwareMap.get(DcMotor.class, p_intakemotorname);
          m_intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
     }

     public void setPower(double p_power){
          m_intakeMotor.setPower(p_power);
     }

     public void intakeForward(){
          m_intakeMotor.setPower(1);
          m_intakeIsOn = true;
     }

     public void intakeOff(){
          m_intakeMotor.setPower(0);
          m_intakeIsOn = false;
     }

     public boolean getIntakeOn(){
          return m_intakeIsOn;
     }

}
