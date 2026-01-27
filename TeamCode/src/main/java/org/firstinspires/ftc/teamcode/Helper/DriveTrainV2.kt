package org.firstinspires.ftc.teamcode.Helper

import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.concurrent.Volatile
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.sign

class DriveTrainV2 {
    @Volatile
    protected var brakingOn: Boolean = false

    init {
        Hardware.frontLeft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Hardware.rearLeft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Hardware.frontRight.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Hardware.rearRight.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    fun setDriveVector(forward: Double, strafe: Double, rotate: Double) {
        if (brakingOn) return

        val denominator = max(abs(forward) + abs(strafe) + abs(rotate), 1.0)

        val pwrfrontLeft = (forward + strafe + rotate) / denominator
        val pwrrearLeft = (forward - strafe + rotate) / denominator
        val pwrfrontRight = (forward - strafe - rotate) / denominator
        val pwrrearRight = (forward + strafe - rotate) / denominator

        Hardware.frontLeft.power = pwrfrontLeft
        Hardware.rearLeft.power = pwrrearLeft
        Hardware.frontRight.power = pwrfrontRight
        Hardware.rearRight.power = pwrrearRight
    }

    /** ONLY USE FOR TESTING */
    fun setMotorsManually(
        frontLeft: Boolean,
        frontRight: Boolean,
        backLeft: Boolean,
        backRight: Boolean
    ) {
        Hardware.rearLeft.power = if (backLeft) 1.0 else 0.0
        Hardware.rearRight.power = if (backRight) 1.0 else 0.0
        Hardware.frontLeft.power = if (frontLeft) 1.0 else 0.0
        Hardware.frontRight.power = if (frontRight) 1.0 else 0.0
    }

    fun setDriveVectorFromJoystick(
        stickLeftX: Float,
        stickRightX: Float,
        stickLeftY: Float,
        setReversed: Boolean
    ) {
        if (brakingOn) return

        val rotate = stickRightX.toDouble()
        var forward: Double = stickLeftY * PARAMS.joystickYInputAdjustment
        var strafe: Double = stickLeftX * PARAMS.strafingAdjustment

        if (setReversed) {
            forward = stickLeftY * PARAMS.joystickYInputAdjustment * -1
            strafe = stickLeftX * PARAMS.strafingAdjustment * -1
        }

        setDriveVector(forward, strafe, rotate)
    }

    fun setBrakeStatus(braking: Boolean) {
        brakingOn = braking
        if (braking) {
            var allStop = false
            var timerExpired = false
            val brakeStart = System.currentTimeMillis()


            while (!allStop && !timerExpired) {
                val flStop = coasterBrakeMotor(Hardware.frontLeft)
                val blStop = coasterBrakeMotor(Hardware.rearLeft)
                val frStop = coasterBrakeMotor(Hardware.frontRight)
                val brStop = coasterBrakeMotor(Hardware.rearRight)


                allStop = flStop && blStop && frStop && brStop
                timerExpired =
                    (System.currentTimeMillis() >= (brakeStart + PARAMS.brakingMaximumTime))


                if (!allStop && !timerExpired) {
                    try {
                        Thread.sleep(PARAMS.brakingInterval)
                    } catch (e: InterruptedException) {
                        throw RuntimeException(e)
                    }
                }
            }
        }
    }

    private fun coasterBrakeMotor(motor: DcMotor): Boolean {
        val curPower = motor.power
        val stopped = (curPower == 0.0)

        if (!stopped) {
            var newPower: Double = curPower - (sign(curPower) * PARAMS.brakingInterval)
            if (abs(newPower) < PARAMS.brakingStopThreshold) newPower = 0.0
            motor.power = newPower
        }

        return stopped
    }

    class Params {
        var strafingAdjustment: Double = 1.08
        var joystickYInputAdjustment: Double = -1.0
        var brakingStopThreshold: Double = 0.25
        var brakingGain: Double = 0.15
        var brakingInterval: Long = 100
        var brakingMaximumTime: Double =
            (ceil(1 / brakingGain).toLong() * brakingInterval).toDouble()
    }

    companion object {
        var PARAMS: Params = Params()
    }
}