package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Helper.BallTransfer;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.Helper.Spindexer;

@Autonomous(name = "BLUE auton (very bad)")
@Configurable
public class AutonBlue extends LinearOpMode {
    public static long forwardTime = 2000;
    public static long spinTime = 250;
    public static long flapTime = 1000;

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Shooter shooter = new Shooter();
        BallTransfer flap = new BallTransfer();
        Spindexer spindexer = new Spindexer();
        waitForStart();
        spindexer.nextShootingLocation();
        while (Hardware.spindex.getTargetPosition() != Hardware.spindex.getCurrentPosition() && opModeIsActive())
            sleep(10);
        Hardware.frontLeft.setPower(1);
        Hardware.frontRight.setPower(1);
        Hardware.rearLeft.setPower(1);
        Hardware.rearRight.setPower(1);
        if (!opModeIsActive())
            return;
        sleep(forwardTime);
        shooter.SetOuttake(true);
        Hardware.frontLeft.setPower(0);
        Hardware.rearLeft.setPower(0);
        if (!opModeIsActive())
            return;
        sleep(spinTime);
        Hardware.frontRight.setPower(0);
        Hardware.rearRight.setPower(0);
        for (int i = 0; i < 3; i++) {
            spindexer.nextShootingLocation();
            while (Hardware.spindex.getTargetPosition() != Hardware.spindex.getCurrentPosition())
                sleep(10);
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
        while (Hardware.spindex.getTargetPosition() != Hardware.spindex.getCurrentPosition() && opModeIsActive())
            sleep(10);
    }
}
