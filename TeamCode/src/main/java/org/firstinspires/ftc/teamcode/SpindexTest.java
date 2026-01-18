package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Helper.Hardware;

@TeleOp(name="Spindex Test")
public class SpindexTest extends LinearOpMode {

    int TARGET_POCKET = 0;
    double MOTOR_POWER = 0.35;
    double GEAR_RATIO = 1.0;
    int OFFSET_TICKS = 0;
    int TOLERANCE_TICKS = 15;

    @Override
    public void runOpMode() {

        DcMotor spindex = Hardware.spindexMotor;

        spindex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spindex.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double motorTicksPerRev = spindex.getMotorType().getTicksPerRev();
        double ticksPerSpindexRev = motorTicksPerRev * GEAR_RATIO;
        double ticksPerPocket = ticksPerSpindexRev / 3.0;

        telemetry.addData("motorTicksPerRev", motorTicksPerRev);
        telemetry.addData("ticksPerPocket", ticksPerPocket);
        telemetry.addData("GEAR_RATIO", GEAR_RATIO);
        telemetry.addData("OFFSET_TICKS", OFFSET_TICKS);
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spindex.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int pocket = ((TARGET_POCKET % 3) + 3) % 3;
        int targetTicks = (int) Math.round(OFFSET_TICKS + pocket * ticksPerPocket);

        spindex.setTargetPosition(targetTicks);
        spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        spindex.setPower(Math.abs(MOTOR_POWER));

        while (opModeIsActive()) {

            int current = spindex.getCurrentPosition();
            int target = spindex.getTargetPosition();
            int error = Math.abs(target - current);

            boolean atTarget = (error <= TOLERANCE_TICKS) && !spindex.isBusy();

            telemetry.addData("TARGET_POCKET", pocket);
            telemetry.addData("CurrentTicks", current);
            telemetry.addData("TargetTicks", target);
            telemetry.addData("ErrorTicks", error);
            telemetry.addData("AtTarget", atTarget);
            telemetry.update();

            if (atTarget) {
                spindex.setPower(0);
            }
        }

        spindex.setPower(0);
    }
}
