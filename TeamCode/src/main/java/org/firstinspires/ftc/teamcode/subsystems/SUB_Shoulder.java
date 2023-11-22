package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.TrapezoidProfileSubsystem;
import org.firstinspires.ftc.teamcode.ftclib.first.math.trajectory.TrapezoidProfile;
import org.firstinspires.ftc.teamcode.Constants.ShoulderConstants;

public class SUB_Shoulder extends TrapezoidProfileSubsystem {
     DcMotorEx m_rightMotor, m_leftMotor;
     OpMode m_opMode;

     public SUB_Shoulder(OpMode p_opMode, final String p_rightMotorName, final String p_leftMotorName) {
          super(
                  new TrapezoidProfile.Constraints(
                          ShoulderConstants.kMaxVelocityDegreesPerSecond,
                          ShoulderConstants.kMaxAccelerationDegreesPerSecond),
                  ShoulderConstants.kOffsetDegrees
          );

          m_opMode = p_opMode;
          m_rightMotor = p_opMode.hardwareMap.get(DcMotorEx.class, p_rightMotorName);
          m_leftMotor = p_opMode.hardwareMap.get(DcMotorEx.class, p_leftMotorName);
          m_rightMotor.setVelocityPIDFCoefficients(12.5, 7.5, 0.0, 13.57);//good but slow
          m_leftMotor.setVelocityPIDFCoefficients(12.5, 7.5, 0.0, 13.57);//good but slow
          m_rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
          m_leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

          m_leftMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(15, 3, .5, 0));
          m_rightMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(15, 3, .5, 0));

          m_rightMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION,
                  new PIDFCoefficients(
                          ShoulderConstants.kP,
                          ShoulderConstants.kI,
                          ShoulderConstants.kD,
                          ShoulderConstants.kF)
          );
          m_leftMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION,
                  new PIDFCoefficients(
                          ShoulderConstants.kP,
                          ShoulderConstants.kI,
                          ShoulderConstants.kD,
                          ShoulderConstants.kF)
          );
          m_leftMotor.setTargetPosition(m_leftMotor.getCurrentPosition());
          m_rightMotor.setTargetPosition(m_rightMotor.getCurrentPosition());

          m_rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          m_leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

          m_rightMotor.setPower(ShoulderConstants.kMaxPower);
          m_leftMotor.setPower(ShoulderConstants.kMaxPower);
     }

     @Override
     protected void useState(TrapezoidProfile.State setpoint) {
          int tickPosition = (int)(setpoint.position * ShoulderConstants.kTicksToDegrees);
          m_rightMotor.setTargetPosition(tickPosition);
          m_leftMotor.setTargetPosition(tickPosition);
     }

     // Return the leader motor's position in degrees
     public double getAngle() {
          return m_rightMotor.getCurrentPosition() / ShoulderConstants.kTicksToDegrees;
     }

     public int getLeftMotorTicks(){
          return m_leftMotor.getCurrentPosition();
     }

     public int getRightMotorTicks(){
          return m_rightMotor.getCurrentPosition();
     }

     // Set motor's angle
     public void setTargetAngle(double degree) {
          setGoal(degree);
     }

     public void resetAngle() {
          m_rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
          m_leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
          m_rightMotor.setTargetPosition(0);
          m_leftMotor.setTargetPosition(0);
          m_rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          m_leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     }
}
