package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;

@Configurable
public class Spindexer {
    public static int nextBallOffset = 300;
    public static double power = 0.20;
    int position = 0;
    int pos = 0;

    public Spindexer() {
        Hardware.spindex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SetPosition(0);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }

    public void SetPosition(int newPos) {
        position += nextBallOffset * Math.floorMod((newPos - pos), 3);
        Hardware.spindex.setTargetPosition(position);
        pos = newPos;
    }
}
