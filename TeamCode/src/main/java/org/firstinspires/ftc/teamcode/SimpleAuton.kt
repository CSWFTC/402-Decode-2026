package org.firstinspires.ftc.teamcode

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.helper.Hardware
import org.firstinspires.ftc.teamcode.helper.Shooter

@Autonomous(name = "AUTON (very bad)")
@Configurable
class SimpleAuton : LinearOpMode() {
    override fun runOpMode() {
        val old = Shooter.outtakePower
        Hardware.init(hardwareMap)
        val s = Shooter()
        waitForStart()
        s.setOuttakeTopPowerMultiplier(outtakeTopMultiplier)
        s.outtake = true
        s.intake = true
        sleep(motorRunTime)
        s.setOuttakeTopPowerMultiplier(old)
        s.outtake = false
        s.intake = false
        Hardware.frontRight.power = -1.0
        Hardware.frontLeft.power = -1.0
        Hardware.rearRight.power = -1.0
        Hardware.rearLeft.power = -1.0
        sleep(250)
        Hardware.frontRight.power = 0.0
        Hardware.frontLeft.power = 0.0
        Hardware.rearRight.power = 0.0
        Hardware.rearLeft.power = 0.0
        while (opModeIsActive()) sleep(20)
    }

    companion object {
        var outtakeTopMultiplier: Double = 1.0
        var motorRunTime: Long = 20000
    }
}
