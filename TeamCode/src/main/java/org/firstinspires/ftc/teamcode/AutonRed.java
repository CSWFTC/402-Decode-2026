package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Helper.BallTransfer;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.Helper.Spindexer;

@Autonomous(name = "RED auton (very bad)")
@Configurable
public class AutonRed extends LinearOpMode {
    public static long forwardTime = 2000;
    public static long spinTime = 250;
    public static long flapTime = 1000;
    public static long spindexTime = 2000;

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Shooter shooter = new Shooter();
        BallTransfer flap = new BallTransfer();
        Spindexer spindexer = new Spindexer();
        waitForStart();
        spindexer.nextShootingLocation();
        sleep(spindexTime);
        if (!opModeIsActive())
            return;
        Hardware.frontLeft.setPower(1);
        Hardware.frontRight.setPower(1);
        Hardware.rearLeft.setPower(1);
        Hardware.rearRight.setPower(1);
        if (!opModeIsActive())
            return;
        sleep(forwardTime);
        shooter.SetOuttake(true);
        Hardware.frontRight.setPower(0);
        Hardware.rearRight.setPower(0);
        if (!opModeIsActive())
            return;
        sleep(spinTime);
        Hardware.frontLeft.setPower(0);
        Hardware.rearLeft.setPower(0);
        for (int i = 0; i < 3; i++) {
            spindexer.nextShootingLocation();
            sleep(spindexTime);
            if (!opModeIsActive())
                return;
            flap.SetLaunching(true);
            if (!opModeIsActive())
                return;
            sleep(flapTime);
            flap.SetLaunching(false);
            if (!opModeIsActive())
                return;
            sleep(flapTime);
        }
        spindexer.nextPickupLocation();
        sleep(spindexTime);
        if (!opModeIsActive()) {
        }
    }
}
