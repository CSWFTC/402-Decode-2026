package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx

@TeleOp(name = "SpindexSlowSpinCalibrate")
class SpindexSlowSpinCalibrate : LinearOpMode() {
    var SPIN_POWER: Double = 0.15
    var GEAR_RATIO: Double = 1.0

    override fun runOpMode() {
        val spindex = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "spindex")

        spindex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)

        spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER)
        spindex.setMode(DcMotor.RunMode.RUN_USING_ENCODER)

        val motorTicksPerRev = spindex.getMotorType().getTicksPerRev()
        val ticksPerSpindexRev = motorTicksPerRev * GEAR_RATIO
        val ticksPerPocket = (ticksPerSpindexRev / 3.0).toInt()

        telemetry.addData("motorTicksPerRev", motorTicksPerRev)
        telemetry.addData("GEAR_RATIO", GEAR_RATIO)
        telemetry.addData("ticksPerSpindexRev", ticksPerSpindexRev)
        telemetry.addData("ticksPerPocket", ticksPerPocket)
        telemetry.addLine("START = slow spin. Watch CurrentTicks and write values when pockets are centered.")
        telemetry.update()

        waitForStart()
        if (isStopRequested()) return

        spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER)
        spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION)

        spindex.setPower(SPIN_POWER)
        spindex.setTargetPosition(ticksPerPocket)

        val last = 0
        val turns = 0

        while (opModeIsActive()) {
            //            int current = spindex.getCurrentPosition();
//
//            if (current < last) turns++;
//            last = current;
//
//            telemetry.addData("SpinPower", SPIN_POWER);
//            telemetry.addData("CurrentTicks", current);
//            telemetry.addData("ApproxTurns", turns);
//            telemetry.update();

            sleep(30)
        }

        spindex.setPower(0.0)
    }
}
