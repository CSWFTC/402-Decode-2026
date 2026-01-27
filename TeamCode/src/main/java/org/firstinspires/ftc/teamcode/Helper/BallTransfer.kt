package org.firstinspires.ftc.teamcode.Helper

import com.bylazar.configurables.annotations.Configurable

@Configurable
class BallTransfer {
    var isLaunching: Boolean
        get() {
            return Hardware.flapServo.position == launch
        }
        set(value) {
            Hardware.flapServo.position = if (value) launch else waiting
        }

    init {
        isLaunching = false
    }

    fun ToggleLaunch() {
        isLaunching = !isLaunching
    }

    companion object {
        @JvmField
        var waiting: Double = 1.0

        @JvmField
        var launch: Double = 0.0
    }
}
