package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import kotlin.math.abs

@TeleOp(name = "SpindexTest")
class SpindexTest : LinearOpMode() {
    var TARGET_POCKET: Int = 0
    var MOTOR_POWER: Double = 0.35
    var GEAR_RATIO: Double = 1.0
    var OFFSET_TICKS: Int = 0
    var TOLERANCE_TICKS: Int = 15

    override fun runOpMode() {
        val spindex = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "spindex")

        spindex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)

        spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER)
        spindex.setMode(DcMotor.RunMode.RUN_USING_ENCODER)

        val motorTicksPerRev = spindex.getMotorType().getTicksPerRev()
        val ticksPerSpindexRev = motorTicksPerRev * GEAR_RATIO
        val ticksPerPocket = ticksPerSpindexRev / 3.0

        telemetry.addData("motorTicksPerRev", motorTicksPerRev)
        telemetry.addData("ticksPerPocket", ticksPerPocket)
        telemetry.addData("GEAR_RATIO", GEAR_RATIO)
        telemetry.addData("OFFSET_TICKS", OFFSET_TICKS)
        telemetry.update()

        waitForStart()
        if (isStopRequested()) return

        spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER)
        spindex.setMode(DcMotor.RunMode.RUN_USING_ENCODER)

        val pocket = ((TARGET_POCKET % 3) + 3) % 3
        val targetTicks = Math.round(OFFSET_TICKS + pocket * ticksPerPocket).toInt()

        spindex.setTargetPosition(targetTicks)
        spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION)
        spindex.setPower(abs(MOTOR_POWER))

        while (opModeIsActive()) {
            val current = spindex.getCurrentPosition()
            val target = spindex.getTargetPosition()
            val error = abs(target - current)

            val atTarget = (error <= TOLERANCE_TICKS) && !spindex.isBusy()

            telemetry.addData("TARGET_POCKET", pocket)
            telemetry.addData("CurrentTicks", current)
            telemetry.addData("TargetTicks", target)
            telemetry.addData("ErrorTicks", error)
            telemetry.addData("AtTarget", atTarget)
            telemetry.update()

            if (atTarget) {
                spindex.setPower(0.0)
            }
        }

        spindex.setPower(0.0)
    }
}
