package org.firstinspires.ftc.teamcode.helper

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor

@Configurable
class Spindexer {
    var position: Int = 0

    init {
        Hardware.spindexMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Hardware.spindexMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        Hardware.spindexMotor.targetPosition = 0
        Hardware.spindexMotor.mode = DcMotor.RunMode.RUN_TO_POSITION
        Hardware.spindexMotor.power = power
    }

    fun launchNextBall() {
        position += 2 * nextBallOffset - (position % nextBallOffset)
        Hardware.spindexMotor.targetPosition = position
    }

    fun pickupNextBall() {
        position += pickupOffset + nextBallOffset - (position % nextBallOffset)
        Hardware.spindexMotor.targetPosition = position
    }

    companion object {
        @JvmField
        var nextBallOffset: Int = 300

        @JvmField
        var pickupOffset: Int = 100

        @JvmField
        var power: Double = 0.20
    }
}
