package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Helper.Hardware;

@Autonomous(name = "Auton JUST MOVES VERY LITTLE")
@Configurable
public class MoveAuton extends LinearOpMode {
    public static long forwardTime = 250;

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        waitForStart();
        Hardware.frontLeft.setPower(1);
        Hardware.frontRight.setPower(1);
        Hardware.rearLeft.setPower(1);
        Hardware.rearRight.setPower(1);
        sleep(forwardTime);
        Hardware.frontRight.setPower(0);
        Hardware.rearRight.setPower(0);
        Hardware.frontLeft.setPower(0);
        Hardware.rearLeft.setPower(0);
    }
}
