package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Constants.intakeConstants;
import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_Intake extends SubsystemBase {

     CRServo m_leftintakeservo;
     CRServo m_rightintakeservo;
     CRServo m_conveyorservo;
     OpMode m_opMode;
     public SUB_Intake(OpMode p_opMode, final String p_leftintakeservoname,
                       final String p_rightintakeservoname, final String p_conveyorservoname){
          m_opMode = p_opMode;
          m_leftintakeservo = m_opMode.hardwareMap.get(CRServo.class, p_leftintakeservoname);
          m_rightintakeservo = m_opMode.hardwareMap.get(CRServo.class, p_rightintakeservoname);
          m_conveyorservo = m_opMode.hardwareMap.get(CRServo.class, p_conveyorservoname);
          m_leftintakeservo.setDirection(DcMotorSimple.Direction.REVERSE);
          m_rightintakeservo.setDirection(DcMotorSimple.Direction.FORWARD);
          m_conveyorservo.setDirection(DcMotorSimple.Direction.FORWARD);
     }

     public void intakeOn(){
         m_leftintakeservo.setPower(intakeConstants.kIntakeOn);
         m_rightintakeservo.setPower(intakeConstants.kIntakeOn);
     }

     public void intakeOff(){
          m_leftintakeservo.setPower(intakeConstants.kIntakeOff);
          m_rightintakeservo.setPower(intakeConstants.kIntakeOff);
     }

     public void intakeReverse(){
          m_leftintakeservo.setPower(intakeConstants.kIntakeReverse);
          m_rightintakeservo.setPower(intakeConstants.kIntakeReverse);
     }

     public void conveyorOn(){
          m_conveyorservo.setPower(intakeConstants.kIntakeOn);
     }

     public void conveyorOff(){
          m_conveyorservo.setPower(intakeConstants.kIntakeOff);
     }

     public void conveyorReverse(){
          m_conveyorservo.setPower(intakeConstants.kIntakeReverse);
     }
}
