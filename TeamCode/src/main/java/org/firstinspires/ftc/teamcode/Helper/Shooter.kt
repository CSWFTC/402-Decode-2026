package org.firstinspires.ftc.teamcode.Helper

import com.bylazar.configurables.annotations.Configurable

@Configurable
class Shooter {
    var intake: Boolean
        get() {
            return Hardware.intake.power > 0.0
        }
        set(value) {
            Hardware.intake.power = if (value) intakePower else 0.0
        }
    var outtake: Boolean
        get() {
            return Hardware.outtake.power > 0.0
        }
        set(value) {
            Hardware.outtake.power = if (value) outtakePower else 0.0
        }

    init {
        intake = false
        outtake = false
    }

    fun setOuttakeTopPowerMultiplier(power: Double) {
        outtakePower = power
        if (outtakePower > 1) {
            outtakePower = 1.0
        } else if (outtakePower < 0.05) {
            outtakePower = 0.05
        }
        outtake = outtake
    }

    fun increaseTopOuttakePower(power: Double) {
        outtakePower = outtakePower + power
        if (outtakePower > 1) {
            outtakePower = 1.0
        }
        outtake = outtake
    }

    fun decreaseTopOuttakePower(power: Double) {
        outtakePower = outtakePower - power
        if (outtakePower < 0.05) {
            outtakePower = 0.05
        }
        outtake = outtake
    }

    fun ToggleIntake() {
        intake = !intake
    }

    fun ToggleOuttake() {
        outtake = !outtake
    }

    companion object {
        @JvmField
        var intakePower: Double = 1.0

        @JvmField
        var outtakePower: Double = 1.0
    }
}
