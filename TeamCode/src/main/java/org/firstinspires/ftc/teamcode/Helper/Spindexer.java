package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Arrays;

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
    Shooter shooter;
    BallColorDetector color;
    BallTransfer flap;

    BallColorDetector.Ball[] status = new BallColorDetector.Ball[3];
    int currentSlot = 0;
    boolean autoLaunch = false;

    public Spindexer(Shooter shooter, BallColorDetector color, BallTransfer flap) {
        this.shooter = shooter;
        this.color = color;
        this.flap = flap;
        Arrays.fill(status, BallColorDetector.Ball.EMPTY);
        Hardware.spindex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.spindex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hardware.spindex.setTargetPosition(0);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }

    public void nextPickupLocation() {
        shooter.SetIntake(true);
        position = Hardware.spindex.getTargetPosition();

        if (mode == SpindexerMode.PICKUP) {
            position += 49;
            currentSlot = (currentSlot + 1) % 3;
        } else {
            position += 37;
            mode = SpindexerMode.PICKUP;
        }
        Hardware.spindex.setTargetPosition(position);
        Hardware.spindex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.spindex.setPower(power);
    }

    public void Update() {
        if (mode == SpindexerMode.PICKUP) {
            BallColorDetector.Ball ballStatus = color.GetBallStatus();
            if (ballStatus != BallColorDetector.Ball.EMPTY) {
                shooter.SetIntake(false);
                status[currentSlot] = ballStatus;
                nextShootingLocation();
            }
        }
        if (autoLaunch && Hardware.spindex.getTargetPosition() == Hardware.spindex.getCurrentPosition()) {
            autoLaunch = false;
            flap.SetLaunching(true);
        }
    }

    public void Pickup() {
        if (Arrays.asList(status).contains(BallColorDetector.Ball.EMPTY))
            while (mode != SpindexerMode.PICKUP && status[currentSlot] != BallColorDetector.Ball.EMPTY)
                nextPickupLocation();
    }

    public void Launch(BallColorDetector.Ball type) {
        if (Arrays.asList(status).contains(type)) {
            while (mode != SpindexerMode.SHOOTING && status[currentSlot] != type)
                nextShootingLocation();
            autoLaunch = true;
        }
    }

    public void nextShootingLocation() {
        position = Hardware.spindex.getTargetPosition();

        if (mode == SpindexerMode.SHOOTING) {
            position += 49;
        } else {
            position += 12;
            mode = SpindexerMode.SHOOTING;
        }
        currentSlot = (currentSlot + 1) % 3;
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