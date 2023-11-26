package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class GlobalVariables{

     public static Pose2d m_autonomousEndPose = new Pose2d();

     public enum RobotState{
          Home
          ,ReadyToIntake
          ,Stow
          ,ReadyToDeploy
          ,Climb
          ,ReadyToLaunch
          ,Transitioning
     }

     private int m_currentScoringLevel = 2;
     private final int m_maxScoringLevel = 8;
     private int m_currentIntakeLevel = 2;

     RobotState m_robotState = RobotState.Home;

     public void setRobotState(RobotState p_robotState){
          m_robotState = p_robotState;
     }

     public RobotState getRobotState(){
          return m_robotState;
     }

     public boolean isRobotState(RobotState p_robotState){
          return p_robotState == m_robotState;
     }


     public void setScoringLevel(int p_scoringLevel){
          m_currentScoringLevel = p_scoringLevel;
     }

     public int getScoringLevel(){
          return m_currentScoringLevel;
     }

     public boolean isScoringLevel(int p_scoringLevel){
          return m_currentScoringLevel == p_scoringLevel;
     }

     public void decreaseScoringLevel() {
          m_currentScoringLevel-= 1;
          if (m_currentScoringLevel < 1) m_currentScoringLevel = 1;
     }

     public void increaseScoringLevel() {
          m_currentScoringLevel += 1;
          if (m_currentScoringLevel > m_maxScoringLevel) m_currentScoringLevel = m_maxScoringLevel;
     }

     public int getIntakeLevel(){
          return m_currentIntakeLevel;
     }

     public void setIntakeLevel(int p_intakeLevel) { m_currentIntakeLevel = p_intakeLevel;}

}
