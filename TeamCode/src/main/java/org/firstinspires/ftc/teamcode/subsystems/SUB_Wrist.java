package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.Constants.WristConstants;
import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.ftclib.first.math.trajectory.TrapezoidProfile;

/**
 * The Wrist subsystem comproised of three servos at the end of the arm.
 * wristservo: rotates the wrist
 * intakepivotservo: Holds the pixel on the inside of the claw
 * pincherservo: Holds the pixel towards the outside of the claw
 */
public class SUB_Wrist extends SubsystemBase{
     Servo m_wristservo, m_pincherservo;
     ServoImplEx m_intakepivotservo;
     private boolean pincherservoOpen;
     public SUB_Wrist(OpMode p_opMode, final String p_wristservoname, final String p_intakepivotservoName, final String p_pincherservoName) {
          m_wristservo = p_opMode.hardwareMap.get(Servo.class, p_wristservoname);
          m_intakepivotservo = p_opMode.hardwareMap.get(ServoImplEx.class, p_intakepivotservoName);
          m_pincherservo = p_opMode.hardwareMap.get(Servo.class, p_pincherservoName);
          m_pincherservo.setDirection(Servo.Direction.FORWARD);
          PwmControl.PwmRange pivotServoRange = new PwmControl.PwmRange(500, 2500);
          m_intakepivotservo.setPwmRange(pivotServoRange);
     }
     public void IntakePivotHome() {
          moveIntakePivot(WristConstants.kIntakePivotHome);
     }
     public void IntakePivotDeploy() {
          moveIntakePivot(WristConstants.kIntakePivotDeploy);
     }

     public void IntakePincherDoubleGrab(){
          movePincher(WristConstants.kPincherDoubleGrab);
     }

     public void movePivotServo(double p_pos){
          moveIntakePivot(p_pos);
     }

     public void openPincher() {
          pincherservoOpen = true;
          movePincher(WristConstants.kPincherOpen);
     }

     public void closePincher() {
          pincherservoOpen = false;
          movePincher(WristConstants.kPincherClose);
     }
     private void moveIntakePivot(double pos) {
          m_intakepivotservo.setPosition(pos);
     }
     private void movePincher(double pos) {
          m_pincherservo.setPosition(pos);
     }

     public boolean getIsPincherOpen() {
          return pincherservoOpen;
     }
     public void setPincherPosition(double p_pos){
          m_pincherservo.setPosition(p_pos);
     }
     public void setPosition(double p_position){
          m_wristservo.setPosition(p_position + WristConstants.kWristOffset);
     }
}
