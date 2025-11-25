package org.firstinspires.ftc.teamcode.Helper

import org.firstinspires.ftc.vision.VisionPortal
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor

class AprilTagConfig {
    private val aprilTag: AprilTagProcessor
    private val visionPortal: VisionPortal

    var order: BallOrder? = null

    init {
        aprilTag = AprilTagProcessor.easyCreateWithDefaults()
        visionPortal = VisionPortal.easyCreateWithDefaults(Hardware.camera, aprilTag)
    }

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
