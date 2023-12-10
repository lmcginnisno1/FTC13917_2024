package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class GlobalVariables{

     public static Pose2d m_autonomousEndPose = new Pose2d(0, 0, Math.toRadians(-180));
     public static int closestTagID = -1;
     public static int randomization = 1;
     public static boolean firstCycle = true;

     public enum RobotState{
          Home
          ,ReadyToIntake
          ,Stow
          ,ReadyToDeploy
          ,Deploying
          ,Climb
          ,ReadyToLaunch
          ,TransitioningToIntake
          ,TransitioningToHome
          ,TransitioningToDeploy
     }

     private int m_currentScoringLevel = 2;
     private final int m_maxScoringLevel = 7;

     private int m_currentRotation = 1;
     private final int m_maxRotation = 6;

     private final int m_maxStackHeight = 5;
     private int m_currentStackHeight = 1;


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

     public void setRotation(int p_scoringLevel){
          m_currentRotation = p_scoringLevel;
     }

     public int getRotation(){
          return m_currentRotation;
     }

     public boolean isRotation(int p_scoringLevel){
          return m_currentRotation == p_scoringLevel;
     }

     public void decreaseRotation() {
          m_currentRotation -= 1;
          if (m_currentRotation < 0) m_currentRotation = 0;
     }

     public void increaseRotation() {
          m_currentRotation += 1;
          if (m_currentRotation > m_maxRotation) m_currentRotation = 0;
     }

     public void setStackHeight(int p_stackHeight){
          m_currentStackHeight = p_stackHeight;
     }

     public int getStackHeight(){
          return m_currentStackHeight;
     }

     public boolean isStackHeight(int p_scoringLevel){
          return m_currentStackHeight == p_scoringLevel;
     }

     public void decreaseStackHeight() {
          m_currentStackHeight -= 1;
          if (m_currentStackHeight < 1) m_currentStackHeight = 5;
     }

     public void increaseStackHeight() {
          m_currentStackHeight += 1;
          if (m_currentStackHeight > m_maxStackHeight) m_currentStackHeight = 1;
     }

     public int getIntakeLevel(){
          return m_currentIntakeLevel;
     }

     public void setIntakeLevel(int p_intakeLevel) { m_currentIntakeLevel = p_intakeLevel;}

}
