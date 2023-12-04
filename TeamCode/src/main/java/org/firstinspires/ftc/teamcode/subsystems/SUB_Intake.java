package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants.IntakeConstants;
import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_Intake extends SubsystemBase {
     DcMotor m_conveyorMotor;
     CRServo m_middleServo;
     Servo m_pivotServo;
     OpMode m_opMode;
     boolean m_conveyorReversed;
     public SUB_Intake(OpMode p_opMode, final String p_middleServoName, final String p_pivotServoName,
                         final String p_conveyormotorname){
          m_opMode = p_opMode;
          m_conveyorMotor = m_opMode.hardwareMap.get(DcMotor.class, p_conveyormotorname);
          m_middleServo = m_opMode.hardwareMap.get(CRServo.class, p_middleServoName);
          m_pivotServo = m_opMode.hardwareMap.get(Servo.class, p_pivotServoName);
          m_pivotServo.setDirection(Servo.Direction.FORWARD);
          m_middleServo.setDirection(DcMotorSimple.Direction.FORWARD);
          m_conveyorMotor.setDirection(DcMotorSimple.Direction.REVERSE);
     }

     public void pivotServoHome(){
          m_pivotServo.setPosition(IntakeConstants.kPivotServoHome);
     }

     public void pivotServoOut(){
          m_pivotServo.setPosition(IntakeConstants.kPivotServoOut);
     }

     public void middleServoOn(){
          m_middleServo.setPower(IntakeConstants.kMiddleServoOn);
     }

     public void middleServoOff(){
          m_middleServo.setPower(IntakeConstants.kMiddleServoOff);
     }

     public void middleServoReverse(){
          m_middleServo.setPower(IntakeConstants.kMiddleServoReverse);
     }
     
     public void conveyorOn(){
          m_conveyorReversed = false;
          m_conveyorMotor.setPower(IntakeConstants.kConveyorOn);
     }

     public void conveyorOff(){
          m_conveyorMotor.setPower(IntakeConstants.kConveyorOff);
     }

     public void conveyorReverse(){
          m_conveyorReversed = true;
          m_conveyorMotor.setPower(IntakeConstants.kConveyorReverse);
     }

     public void setPivotPosition(double p_pos){
          m_pivotServo.setPosition(p_pos);
     }
     
     public boolean getConveyorReversed(){
          return m_conveyorReversed;
     }
}
