package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;

enum SpindexerMode {
    SHOOTING,
    PICKUP
}

@Configurable
public class Spindexer {
    public static double power = 1.0;
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

    public void ManualForward() {
        position = Hardware.spindex.getTargetPosition() + 1;
        Hardware.spindex.setTargetPosition(position);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }

    public void ManualReverse() {
        position = Hardware.spindex.getTargetPosition() - 1;
        Hardware.spindex.setTargetPosition(position);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }
}