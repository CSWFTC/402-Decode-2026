package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;

@Autonomous(name = "AUTON (very bad)")
@Configurable
public class SimpleAuton extends LinearOpMode {
    public static double outtakeTopMultiplier = 1;
    public static long motorRunTime = 20000;

    @Override
    public void runOpMode() {
        double old = Shooter.outtakeTopPower;
        Hardware.init(hardwareMap);
        Shooter s = new Shooter();
        waitForStart();
        s.setOuttakeTopPowerMultiplier(outtakeTopMultiplier);
        s.SetOuttake(true);
        s.SetIntake(true);
        sleep(motorRunTime);
        s.setOuttakeTopPowerMultiplier(old);
        s.SetOuttake(false);
        s.SetIntake(false);
        Hardware.frontRight.setPower(-1);
        Hardware.frontLeft.setPower(-1);
        Hardware.rearRight.setPower(-1);
        Hardware.rearLeft.setPower(-1);
        sleep(250);
        Hardware.frontRight.setPower(0);
        Hardware.frontLeft.setPower(0);
        Hardware.rearRight.setPower(0);
        Hardware.rearLeft.setPower(0);
        while (opModeIsActive())
            sleep(20);
    }
}
