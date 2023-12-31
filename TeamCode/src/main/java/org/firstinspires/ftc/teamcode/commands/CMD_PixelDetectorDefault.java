package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

public class CMD_PixelDetectorDefault extends CommandBase {
     RobotContainer m_robot;
     int min_count = 10;
     int IRcount1, IRcount2, IRcount3;
     public CMD_PixelDetectorDefault(RobotContainer p_robot){
          m_robot = p_robot;
          addRequirements(m_robot.m_pixelDetector);
     }

     @Override
     public void initialize(){

     }

     @Override
     public void execute(){
          if(m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)){
               // increment IRcounter
               if(m_robot.m_pixelDetector.getIRSensor(1)){
                    IRcount1 += 1;
               }else{
                    IRcount1 = 0;
               }

               if(m_robot.m_pixelDetector.getIRSensor(2)){
                    IRcount2 += 1;
               }else{
                    IRcount2 = 0;
               }

               if(m_robot.m_pixelDetector.getIRSensor(3)){
                    IRcount3 += 1;
               }else{
                    IRcount3 = 0;
               }
               if (!m_robot.m_pixelDetector.getPixel(1) && (IRcount1 > min_count))  m_robot.m_pixelDetector.setPixel(1);
               if (!m_robot.m_pixelDetector.getPixel(2) && (IRcount2 > min_count))  m_robot.m_pixelDetector.setPixel(2);
               if (!m_robot.m_pixelDetector.getPixel(3) && (IRcount3 > min_count))  m_robot.m_pixelDetector.setPixel(3);

               // if we have one pixel and a new one is detected, lift the intake wheel up
               if(m_robot.m_pixelDetector.getPixel(1) && (IRcount3 > min_count || IRcount2 > min_count)){
                   m_robot.m_intake.pivotServoHome();}

               if(m_robot.m_pixelDetector.getPixel(1) && m_robot.m_pixelDetector.getPixel(2)) {
                    m_robot.m_intake.pivotServoHome();
                    m_robot.schedule(new SequentialCommandGroup(
                            new CMD_SetReadyToIntakeOff(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist,
                                    m_robot.m_blank, m_robot.m_variables, m_robot.m_intake)
                            ,new InstantCommand(() -> m_robot.m_wrist.closePincher())
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_variables.setRobotState(GlobalVariables.RobotState.Stow))
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome + 0.05))
                            ,new InstantCommand(() -> m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kLiftOffConveyor))
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorReverse())
                            ,new Sleep(1000)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOff())
                    ));
                    m_robot.m_pixelDetector.resetPixel();
               }
          }
     }

     @Override
     public boolean isFinished(){
          return false;
     }
}
