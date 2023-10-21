package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class SUB_sensor_PixelGuide  extends TriggerSensorBase{
     DigitalChannel m_irSensor;

     public SUB_sensor_PixelGuide(OpMode p_opMode, final String p_irSensor) {

          m_irSensor = p_opMode.hardwareMap.get(DigitalChannel.class, p_irSensor);    //  Use generic form of device mapping
          m_irSensor.setMode(DigitalChannel.Mode.INPUT);// Set the direction of each channel

     }

     public boolean getState() {
          return m_irSensor.getState();
     }
}