package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;

@Configurable
public class Spindexer {
    public static int nextBallOffset = 300;
    public static int pickupOffset = 100;
    public static double power = 0.20;
    int position = 0;

    public Spindexer() {
        Hardware.spindex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hardware.spindex.setTargetPosition(0);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }

    public void LaunchNextBall() {
        position += 2 * nextBallOffset - (position % nextBallOffset);
        Hardware.spindex.setTargetPosition(position);
    }

    public void PickupNextBall() {
        position += pickupOffset + nextBallOffset - (position % nextBallOffset);
        Hardware.spindex.setTargetPosition(position);
    }
}
