package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants.WristConstants;
import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.ftclib.first.math.trajectory.TrapezoidProfile;

/**
 * The Wrist subsystem comproised of three servos at the end of the arm.
 * wristservo: rotates the wrist
 * clawa: Holds the pixel on the inside of the claw
 * clawb: Holds the pixel towards the outside of the claw
 */
public class SUB_Wrist extends SubsystemBase{
     Servo m_wristservo, m_clawa, m_clawb;
     private boolean clawaOpen, clawbOpen;
     public SUB_Wrist(OpMode p_opMode, final String p_wristservoname, final String p_clawaName, final String p_clawbName) {
          m_wristservo = p_opMode.hardwareMap.get(Servo.class, p_wristservoname);
          m_clawa = p_opMode.hardwareMap.get(Servo.class, p_clawaName);
          m_clawb = p_opMode.hardwareMap.get(Servo.class, p_clawbName);
          m_clawb.setDirection(Servo.Direction.REVERSE);
     }
     public void openClawA() {
          clawaOpen = true;
          moveClawA(WristConstants.kClawAOpen);
     }
     public void closeClawA() {
          clawaOpen = false;
          moveClawA(WristConstants.kClawAClose);
     }

     public void openClawB() {
          clawbOpen = true;
          moveClawB(WristConstants.kClawBOpen);
     }

     public void closeClawB() {
          clawbOpen = false;
          moveClawB(WristConstants.kClawBClose);
     }
     private void moveClawA(double pos) {
          m_clawa.setPosition(pos + WristConstants.kClawAOffset);
     }
     private void moveClawB(double pos) {
          m_clawb.setPosition(pos + WristConstants.kClawBOffset);
     }

     public boolean getIsClawAOpen() {
          return clawaOpen;
     }

     public boolean getIsClawBOpen() {
          return clawbOpen;
     }
     public void setClawBPosition(double p_pos){
          m_clawb.setPosition(p_pos);
     }
     public void setPosition(double p_position){
          m_wristservo.setPosition(p_position + WristConstants.kWristOffset);
     }
}
