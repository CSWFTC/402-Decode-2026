package org.firstinspires.ftc.teamcode.helpers;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.Optional;

public class AprilTagConfig {
    private final AprilTagProcessor aprilTag;
    private final VisionPortal visionPortal;
    public Optional<BallOrder> order = Optional.empty();
    public AprilTagConfig(){
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(Hardware.camera, aprilTag);
    }
    public void Update(){
        if(!order.isPresent()) {
            for (AprilTagDetection d : aprilTag.getDetections()) {
                switch (d.id) {
                    case 21:
                        order = Optional.of(BallOrder.GPP);
                        break;
                    case 22:
                        order =  Optional.of(BallOrder.PGP);
                        break;
                    case 23:
                        order =  Optional.of(BallOrder.PPG);
                        break;
                }
            }
        }
    }
    public void Close(){
        visionPortal.close();
    }
}
