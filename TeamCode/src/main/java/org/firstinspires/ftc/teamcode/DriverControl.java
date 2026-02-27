package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Helper.DriveTrainV2;
import org.firstinspires.ftc.teamcode.Helper.GamePad;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.Helper.Turret;

import java.util.Locale;

@TeleOp(name = "Driver Control", group = "Competition!!")
public class DriverControl extends LinearOpMode {
    private static final String version = "1.1";
    private boolean setReversed = false;
    private GamePad gpIn1;
    private GamePad gpIn2;
    private DriveTrainV2 drvTrain;

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        // Load Introduction and Wait for Start
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);
        telemetry.addLine("Driver Control");
        telemetry.addData("Version Number", version);
        telemetry.addLine();
        telemetry.addData(">", "Press Start to Launch");
        telemetry.update();

        //gpIn1 = new GamePad(PanelsGamepad.INSTANCE.getFirstManager().asCombinedFTCGamepad(gamepad1));
        gpIn1 = new GamePad(gamepad1);
        gpIn2 = new GamePad(gamepad2);
        Shooter shooter = new Shooter();
        Turret turret = new Turret();
        drvTrain = new DriveTrainV2();

        waitForStart();
        shooter.SetOuttake(true);
        if (isStopRequested()) {
            return;
        }
        telemetry.clear();

        double speedMultiplier = 0.5;
        long prevTime = System.currentTimeMillis();
        while (opModeIsActive()) {
//            atConf.Update();
            update_telemetry(speedMultiplier, Shooter.outtakePower);

            GamePad.GameplayInputType inpType1 = gpIn1.WaitForGamepadInput(30);
            switch (inpType1) {
                case BUTTON_BACK:
                    setReversed = !setReversed;
                    break;
                case DPAD_DOWN:
                    speedMultiplier = 0.25;
                    break;
                case DPAD_LEFT:
                    speedMultiplier = 0.75;
                    break;
                case DPAD_RIGHT:
                    speedMultiplier = 0.5;
                    break;
                case DPAD_UP:
                    speedMultiplier = 1;
                    break;
                case JOYSTICK:
                    drvTrain.setDriveVectorFromJoystick(gamepad1.left_stick_x * (float) speedMultiplier,
                            gamepad1.right_stick_x * (float) speedMultiplier,
                            gamepad1.left_stick_y * (float) speedMultiplier, setReversed);
                    break;
            }
            long now = System.currentTimeMillis();
            float delta = (now - prevTime) / 1e3f;
            prevTime = now;
            GamePad.GameplayInputType inpType2 = gpIn2.WaitForGamepadInput(30);
            switch (inpType2) {
                case BUTTON_A:
                    shooter.ToggleIntake();
                    break;
                case BUTTON_Y:
                    shooter.ToggleRamp();
                    break;
                case BUTTON_X:
                    shooter.ToggleOuttake();
                    break;
            }
            turret.setTurretAngle(turret.getTurretAngle() + gamepad2.left_stick_x * delta);
            turret.setHoodAngle(turret.getHoodAngle() + gamepad2.left_stick_y * delta);
        }
    }


    private void update_telemetry(double speed, double outtakePower) {
        telemetry.addLine("Gamepad #1");

        String inpTime1 = new java.text.SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.US).format(gpIn1.getTelemetry_InputLastTimestamp());
        telemetry.addLine().addData("Current Speed Multiplier", speed);
        telemetry.addLine();
        telemetry.addLine().addData("GP1 Time", inpTime1);
        telemetry.addLine().addData("GP1 Cnt", gpIn1.getTelemetry_InputCount());
        telemetry.addLine().addData("GP1 Input", gpIn1.getTelemetry_InputLastType().toString());
//        telemetry.addLine().addData("Apriltag Status:", atConf.order.map(Enum::toString).orElse("Not Found Yet"));
        telemetry.addLine();
        telemetry.addLine().addData("Current Top Outtake Power Multiplier", outtakePower);
        telemetry.addLine();
        telemetry.update();
    }
}