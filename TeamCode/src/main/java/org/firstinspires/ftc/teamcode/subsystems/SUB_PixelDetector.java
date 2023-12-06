package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.ftclib.command.button.DigitalPort;

public class SUB_PixelDetector extends SubsystemBase {
     DigitalPort m_intakeSensorOne;
     DigitalPort m_intakeSensorTwo;
     DigitalPort m_intakeSensorThree;
     OpMode m_opMode;
     boolean m_pixelOne, m_pixelTwo, m_pixelThree;
     public SUB_PixelDetector(DigitalPort p_intakeSensorOne, DigitalPort p_intakeSensorTwo,
                              DigitalPort p_intakeSensorThree, OpMode p_opMode){
          m_intakeSensorOne = p_intakeSensorOne;
          m_intakeSensorTwo = p_intakeSensorTwo;
          m_intakeSensorThree = p_intakeSensorThree;
          m_opMode = p_opMode;

          reset();
     }

     public void reset(){
          m_pixelOne = false;
          m_pixelTwo = false;
          m_pixelThree = false;
     }

     public boolean getIntakeSensor(int p_sensor){
          if(p_sensor == 1){
               return m_intakeSensorOne.get();
          }else if(p_sensor == 2){
               return m_intakeSensorTwo.get();
          }else if(p_sensor == 3){
               return m_intakeSensorThree.get();
          }else{
               return false;
          }
     }

     public boolean getPixel(int p_pixel){
          if(p_pixel == 1){
               return m_pixelOne;
          }else if(p_pixel == 2){
               return m_pixelTwo;
          }else if(p_pixel == 3){
               return m_pixelThree;
          }else{
               return false;
          }
     }

     public void updatePixel(int p_pixel){
          if(p_pixel == 1){
               m_pixelOne = getIntakeSensor(1);
          }else if(p_pixel == 2){
               m_pixelOne = getIntakeSensor(2);
          }else if(p_pixel == 3){
               m_pixelOne = getIntakeSensor(3);
          }
     }

     @Override
     public void periodic(){
          m_opMode.telemetry.addData("intake sensors","1: %b, 2: %b, 3: %b",
                  getIntakeSensor(1), getIntakeSensor(2), getIntakeSensor(3));
     }
}
