package org.firstinspires.ftc.teamcode.Helper

class Shooter {
    var intakeOn: Boolean = false
    var outtakeOn: Boolean = false

    init {
        SetIntake(false)
        SetOuttake(false)
    }

    fun SetIntake(status: Boolean) {
        intakeOn = status
        Hardware.intake.power = if (status) 1.0 else 0.0
    }

    fun SetOuttake(status: Boolean) {
        outtakeOn = status
        Hardware.outtakeBottom.power = if (status) 1.0 else 0.0
        Hardware.outtakeMiddle.power = if (status) 1.0 else 0.0
        Hardware.outtakeTop.power = if (status) 1.0 else 0.0
    }

    fun ToggleIntake() {
        SetIntake(!intakeOn)
    }

    fun ToggleOuttake() {
        SetOuttake(!outtakeOn)
    }
}
