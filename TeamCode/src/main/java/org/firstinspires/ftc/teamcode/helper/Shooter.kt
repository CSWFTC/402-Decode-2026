package org.firstinspires.ftc.teamcode.helper

import com.bylazar.configurables.annotations.Configurable

@Configurable
class Shooter {
    var intake: Boolean = false
        set(value) {
            Hardware.intake.power = if (value) intakePower else 0.0
        }
    var outtake: Boolean = false
        set(value) {
            Hardware.outtakeBottom.power = if (value) outtakeBottomPower else 0.0
            Hardware.outtakeMiddle.power = if (value) outtakeMiddlePower else 0.0
            Hardware.outtakeTop.power = if (value) outtakeTopPower else 0.0
        }

    fun toggleIntake() {
        intake = !intake
    }

    fun toggleOuttake() {
        outtake = !outtake
    }

    companion object {
        @JvmField // allows the dashboard to configure these variables
        var intakePower: Double = 1.0

        @JvmField
        var outtakeTopPower: Double = 0.5

        @JvmField
        var outtakeMiddlePower: Double = 1.0

        @JvmField
        var outtakeBottomPower: Double = 1.0
    }
}
