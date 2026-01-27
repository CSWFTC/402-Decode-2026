package org.firstinspires.ftc.teamcode.helper

import org.firstinspires.ftc.vision.VisionPortal
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor

class AprilTagConfig {
    private val aprilTag: AprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults()

    private val visionPortal: VisionPortal =
        VisionPortal.easyCreateWithDefaults(Hardware.camera, aprilTag)
    var order: BallOrder? = null

    fun update() {
        if (order == null) {
            for (d in aprilTag.detections) {
                order = when (d.id) {
                    21 -> BallOrder.GPP
                    22 -> BallOrder.PGP
                    23 -> BallOrder.PPG
                    else -> null
                }
            }
        }
    }

    fun close() {
        visionPortal.close()
    }
}
