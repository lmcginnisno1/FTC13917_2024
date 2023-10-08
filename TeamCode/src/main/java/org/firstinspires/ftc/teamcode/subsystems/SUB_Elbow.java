package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.ftclib.command.TrapezoidProfileSubsystem;
import org.firstinspires.ftc.teamcode.ftclib.math.trajectory.TrapezoidProfile;
import org.firstinspires.ftc.teamcode.Constants.ElbowConstants;

public class SUB_Elbow extends TrapezoidProfileSubsystem {
     DcMotorEx m_elbowmotor;
     public double p = 10;
     public SUB_Elbow(OpMode p_opMode, final String p_elbowmotorname) {
          super(
                  new TrapezoidProfile.Constraints(
                          ElbowConstants.kMaxVelocityDegreesPerSecond,
                          ElbowConstants.kMaxAccelerationDegreesPerSecond),
                  ElbowConstants.kOffsetDegrees
          );
          m_elbowmotor = p_opMode.hardwareMap.get(DcMotorEx.class, p_elbowmotorname);
          m_elbowmotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
          m_elbowmotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION,
                  new PIDFCoefficients(
                          p,
                          ElbowConstants.kI,
                          ElbowConstants.kD,
                          ElbowConstants.kF)
          );
          resetAngle();
          m_elbowmotor.setPower(ElbowConstants.kMaxPower);
     }

     @Override
     protected void useState(TrapezoidProfile.State setpoint) {
          int tickPosition = (int)(setpoint.position * ElbowConstants.kTicksToDegrees);
          m_elbowmotor.setTargetPosition(tickPosition);
     }

     public void increaseP() {
          p = p + 0.25;
          m_elbowmotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, new PIDFCoefficients(p, 0, 0, 0));
     }

     public void decreaseP() {
          p = p - 0.25;
          m_elbowmotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, new PIDFCoefficients(p, 0, 0, 0));
     }

     public double getAngle(){
          return m_elbowmotor.getCurrentPosition() / ElbowConstants.kTicksToDegrees;
     }

     public void setTargetAngle(double degree){
          setGoal(degree);
     }

     public void resetAngle() {
          m_elbowmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
          m_elbowmotor.setTargetPosition(0);
          m_elbowmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     }
}