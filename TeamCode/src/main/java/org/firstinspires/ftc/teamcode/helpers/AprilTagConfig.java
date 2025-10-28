package org.firstinspires.ftc.teamcode.helpers;

import static java.lang.Thread.sleep;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.Optional;

public class AprilTagConfig {
    private final AprilTagProcessor aprilTag;
    private final VisionPortal visionPortal;
    public AprilTagConfig(){
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(Hardware.camera, aprilTag);
    }
    public Optional<BallOrder> Check(){
        for(AprilTagDetection d: aprilTag.getDetections()){
            switch (d.id){
                case 21:
                    return Optional.of(BallOrder.GPP);
                case 22:
                    return Optional.of(BallOrder.PGP);
                case 23:
                    return Optional.of(BallOrder.PPG);
            }
        }
        return Optional.empty();
    }
    public BallOrder WaitUntilOrderFound(){ // not the best name, but makes the blocking nature clear
        Optional<BallOrder> order;
        while(!(order = Check()).isPresent()) {
            try {
                sleep(20);
            }
            catch(InterruptedException ignored){}
        }
        return order.get();
    }
    public void Close(){
        visionPortal.close();
    }
}
