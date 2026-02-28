package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Helper.Hardware;

@Autonomous(name = "AUTON Starting Red -> Shooting Blue")
@Configurable
public class MoveAutonRedToBlue extends LinearOpMode {
    //TODO: Adjust these times! in both simple auton files
    public static long forwardTime = 650;
    public static long waitTime = 500;
    public static long turn45Time = 150;
    public static long forwardToGoalTime = 50;
    public static long shootingTime = 3000;

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
        sleep(waitTime);
        Hardware.frontLeft.setPower(-1);
        Hardware.frontRight.setPower(1);
        Hardware.rearLeft.setPower(-1);
        Hardware.rearRight.setPower(1);
        sleep(turn45Time);
        Hardware.frontRight.setPower(0);
        Hardware.rearRight.setPower(0);
        Hardware.frontLeft.setPower(0);
        Hardware.rearLeft.setPower(0);
        sleep(waitTime);
        Hardware.frontLeft.setPower(1);
        Hardware.frontRight.setPower(1);
        Hardware.rearLeft.setPower(1);
        Hardware.rearRight.setPower(1);
        sleep(forwardToGoalTime);
        Hardware.frontRight.setPower(0);
        Hardware.rearRight.setPower(0);
        Hardware.frontLeft.setPower(0);
        Hardware.rearLeft.setPower(0);
        sleep(waitTime);
        Hardware.intake.setPower(1);
        Hardware.ramp.setPower(1);
        Hardware.shooter1.setPower(1);
        Hardware.shooter2.setPower(1);
        sleep(shootingTime);
        Hardware.intake.setPower(0);
        Hardware.ramp.setPower(0);
        Hardware.shooter1.setPower(0);
        Hardware.shooter2.setPower(0);
    }
}
