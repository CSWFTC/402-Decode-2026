package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;

@Configurable
public class Spindexer {
    public static int nextBallOffset = 49;
    public static int pickupOffset = 15;
    public static double power = 0.10;

    public static int recoilComp = 0;
    //14 before

    //start at 0, move to 65
    int position = 0;

    SpindexerMode mode = SpindexerMode.PICKUP;

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

    public void nextPickupLocation() {
        position = Hardware.spindex.getTargetPosition();

        if (mode == SpindexerMode.PICKUP) {
            position += 49;
        } else {
            position += 37;
            mode = SpindexerMode.PICKUP;
        }
        Hardware.spindex.setTargetPosition(position);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }

    public void nextShootingLocation() {
        position = Hardware.spindex.getTargetPosition();

        if (mode == SpindexerMode.SHOOTING) {
            position += 49;
        } else {
            position += 12;
            mode = SpindexerMode.SHOOTING;
        }
        Hardware.spindex.setTargetPosition(position);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }
}
enum SpindexerMode{
    SHOOTING,
    PICKUP
}