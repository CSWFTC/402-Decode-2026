package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="SpindexSlowSpinCalibrate")
public class SpindexSlowSpinCalibrate extends LinearOpMode {

    double SPIN_POWER = 0.15;
    double GEAR_RATIO = 1.0;

    @Override
    public void runOpMode() {

        DcMotorEx spindex = hardwareMap.get(DcMotorEx.class, "spindex");

        spindex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spindex.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double motorTicksPerRev = spindex.getMotorType().getTicksPerRev();
        double ticksPerSpindexRev = motorTicksPerRev * GEAR_RATIO;
        double ticksPerPocket = ticksPerSpindexRev / 3.0;

        telemetry.addData("motorTicksPerRev", motorTicksPerRev);
        telemetry.addData("GEAR_RATIO", GEAR_RATIO);
        telemetry.addData("ticksPerSpindexRev", ticksPerSpindexRev);
        telemetry.addData("ticksPerPocket", ticksPerPocket);
        telemetry.addLine("START = slow spin. Watch CurrentTicks and write values when pockets are centered.");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spindex.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        spindex.setPower(SPIN_POWER);

        int last = 0;
        int turns = 0;

        while (opModeIsActive()) {

            int current = spindex.getCurrentPosition();

            if (current < last) turns++;
            last = current;

            telemetry.addData("SpinPower", SPIN_POWER);
            telemetry.addData("CurrentTicks", current);
            telemetry.addData("ApproxTurns", turns);
            telemetry.update();
        }

        spindex.setPower(0);
    }
}
