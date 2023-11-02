package org.firstinspires.ftc.teamcode.commands;

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
     GamepadEx m_driverOp;
     double m_timer;
     public CMD_AutoDriveIn(MecanumDriveSubsystem p_drivetrain, DigitalPort p_trigger, GamepadEx p_driverOp){
          m_drivetrain = p_drivetrain;
          m_trigger = p_trigger;
          m_driverOp = p_driverOp;
          addRequirements(p_drivetrain);
     }

     @Override
     public void initialize(){
          m_isFinished = false;
          m_timer = 0;
     }

     @Override
     public void execute(){
          m_drivetrain.drive(-.15, 0, 0);
          if (m_trigger.get() || Math.abs(m_driverOp.getLeftY()) > .1){
               m_isFinished = true;
               m_timer += 0.1;
          }
     }

     @Override
     public boolean isFinished(){
          return m_isFinished && m_timer > 2 || Math.abs(m_driverOp.getLeftY()) > .1;
     }
}
