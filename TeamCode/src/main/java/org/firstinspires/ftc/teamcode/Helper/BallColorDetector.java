package org.firstinspires.ftc.teamcode.Helper;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

public class BallColorDetector {
    public static float left = -1;
    public static float right = 1;
    public static float up = 1;
    public static float down = -1;
    PredominantColorProcessor colorSensor;
    VisionPortal portal;

    public BallColorDetector() {
        colorSensor = new PredominantColorProcessor.Builder().setRoi(ImageRegion.asUnityCenterCoordinates(left, up, right, down))
                .setSwatches(PredominantColorProcessor.Swatch.ARTIFACT_GREEN, PredominantColorProcessor.Swatch.ARTIFACT_PURPLE, PredominantColorProcessor.Swatch.BLACK)
                .build();
        portal = new VisionPortal.Builder().addProcessor(colorSensor).setCamera(Hardware.camera).build();
    }

    public Ball GetBallStatus() {
        switch (colorSensor.getAnalysis().closestSwatch) {
            case ARTIFACT_GREEN:
                return Ball.GREEN;
            case ARTIFACT_PURPLE:
                return Ball.PURPLE;
            default:
                return Ball.EMPTY;
        }
    }

    public void Close() {
        portal.close();
    }

    public enum Ball {
        GREEN,
        PURPLE,
        EMPTY
    }
}
