package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.TrapezoidProfileSubsystem;
import org.firstinspires.ftc.teamcode.ftclib.math.trajectory.TrapezoidProfile;
import org.firstinspires.ftc.teamcode.Constants.ShoulderConstants;

public class SUB_Shoulder extends TrapezoidProfileSubsystem {
     DcMotorEx m_shouldermotor;

     public SUB_Shoulder(OpMode p_opMode, final String p_shouldermotorname) {
          super(
                  new TrapezoidProfile.Constraints(
                          ShoulderConstants.kMaxVelocityDegreesPerSecond,
                          ShoulderConstants.kMaxAccelerationDegreesPerSecond),
                  ShoulderConstants.kOffsetDegrees
          );
          m_shouldermotor = p_opMode.hardwareMap.get(DcMotorEx.class, p_shouldermotorname);
          m_shouldermotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
          m_shouldermotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION,
                  new PIDFCoefficients(
                          ShoulderConstants.kP,
                          ShoulderConstants.kI,
                          ShoulderConstants.kD,
                          ShoulderConstants.kF)
          );
          resetAngle();
          m_shouldermotor.setPower(ShoulderConstants.kMaxPower);
     }

     @Override
     protected void useState(TrapezoidProfile.State setpoint) {
          int tickPosition = (int)(setpoint.position * ShoulderConstants.kTicksToDegrees);
          m_shouldermotor.setTargetPosition(tickPosition);
     }

     // Return motor's position in degrees
     public double getAngle() {
          return m_shouldermotor.getCurrentPosition() / ShoulderConstants.kTicksToDegrees;
     }

     // Set motor's angle
     public void setTargetAngle(double degree) {
          setGoal(degree);
     }

     public void resetAngle() {
          m_shouldermotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
          m_shouldermotor.setTargetPosition(0);
          m_shouldermotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     }
}
