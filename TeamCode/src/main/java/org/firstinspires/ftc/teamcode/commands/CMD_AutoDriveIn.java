package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.button.DigitalPort;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.concurrent.TimeUnit;

public class CMD_AutoDriveIn extends CommandBase{
     MecanumDriveSubsystem m_drivetrain;
     DigitalPort m_trigger;
     boolean m_isFinished;
     boolean m_triggered;
     GamepadEx m_driverOp;
     ElapsedTime m_timer = new ElapsedTime();
     public CMD_AutoDriveIn(MecanumDriveSubsystem p_drivetrain, DigitalPort p_trigger, GamepadEx p_driverOp){
          m_drivetrain = p_drivetrain;
          m_trigger = p_trigger;
          m_driverOp = p_driverOp;
          addRequirements(p_drivetrain);
     }

     @Override
     public void initialize(){
          m_drivetrain.setMotorPowers(.25);
          m_isFinished = false;
          m_triggered= false;
          m_timer.reset();
     }

     @Override
     public void execute(){
          if (m_trigger.get() && !m_triggered){
               m_triggered = true;
               m_timer.reset();
          }
     }

     @Override
     public boolean isFinished(){
          m_isFinished = m_triggered && m_timer.milliseconds() > 500;
          return m_isFinished || Math.abs(m_driverOp.getLeftY()) > .1;
     }
}
