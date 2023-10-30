package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.vision.VisionPortal;

public class VisionPipeline extends SubsystemBase {

	private final String m_usbCameraName;
	OpMode m_opMode;
	private final SampleVisionProcessor visionProcessor;
	private final VisionPortal m_visionPortal;

	public VisionPipeline(OpMode p_opMode, final String usbCameraName) {
		m_opMode = p_opMode;
		m_usbCameraName = usbCameraName;

		visionProcessor = new SampleVisionProcessor();
		m_visionPortal = VisionPortal.easyCreateWithDefaults(
				m_opMode.hardwareMap.get(WebcamName.class, m_usbCameraName), visionProcessor);
	}

	public void telemetry() {
		m_opMode.telemetry.addData("Identified", visionProcessor.getSelection());
	}

	public void periodic() {
	   telemetry();
	}

	public void stopStreaming() {
		m_visionPortal.stopStreaming();
	}
}
