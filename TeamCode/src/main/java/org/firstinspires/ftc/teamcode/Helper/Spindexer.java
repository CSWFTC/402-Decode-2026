package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;

@Configurable
public class Spindexer {
    public static int nextBallOffset = 49;
    public static int pickupOffset = 15;
    public static double power = 0.35;

    public static int recoilComp = 14;

    int position = 0;

    public Spindexer() {
        Hardware.spindex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hardware.spindex.setTargetPosition(0);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }

    public void LaunchNextBall() {
        position = Hardware.spindex.getCurrentPosition();

        int mod = position % nextBallOffset;
        if (mod < 0) mod += nextBallOffset;

        position += 2 * nextBallOffset - mod;

        Hardware.spindex.setTargetPosition(position + recoilComp);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }

    public void PickupNextBall() {
        position = Hardware.spindex.getCurrentPosition();

        int mod = position % nextBallOffset;
        if (mod < 0) mod += nextBallOffset;

        position += pickupOffset + nextBallOffset - mod;

        Hardware.spindex.setTargetPosition(position + recoilComp);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }
}
