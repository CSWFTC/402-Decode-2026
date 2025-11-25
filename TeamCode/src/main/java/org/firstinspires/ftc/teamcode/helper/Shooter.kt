package org.firstinspires.ftc.teamcode.helper

import com.bylazar.configurables.annotations.Configurable

@Configurable
class Shooter {
    var intakeOn: Boolean = false
    var outtakeOn: Boolean = false

    init {
        SetIntake(false)
        SetOuttake(false)
    }

    fun SetIntake(status: Boolean) {
        intakeOn = status
        Hardware.intake.power = if (status) intakePower else 0.0
    }

    fun SetOuttake(status: Boolean) {
        outtakeOn = status
        Hardware.outtakeBottom.power = if (status) outtakeBottomPower else 0.0
        Hardware.outtakeMiddle.power = if (status) outtakeMiddlePower else 0.0
        Hardware.outtakeTop.power = if (status) outtakeTopPower else 0.0
    }

    fun ToggleIntake() {
        SetIntake(!intakeOn)
    }

    fun ToggleOuttake() {
        SetOuttake(!outtakeOn)
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
