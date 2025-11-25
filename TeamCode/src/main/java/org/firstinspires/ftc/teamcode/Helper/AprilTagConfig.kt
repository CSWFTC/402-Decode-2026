package org.firstinspires.ftc.teamcode.Helper

import org.firstinspires.ftc.vision.VisionPortal
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor

class AprilTagConfig {
    private val aprilTag: AprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults()
    private val visionPortal: VisionPortal =
        VisionPortal.easyCreateWithDefaults(Hardware.camera, aprilTag)
    var order: BallOrder? = null

    fun Update() {
        if (order == null) {
            for (d in aprilTag.detections) {
                when (d.id) {
                    21 -> order = BallOrder.GPP
                    22 -> order = BallOrder.PGP
                    23 -> order = BallOrder.PPG
                }
            }
        }
    }

    fun Close() {
        visionPortal.close()
    }
}
