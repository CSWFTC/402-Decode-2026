package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class BallTransfer {
    public static double waiting = 1;
    public static double launch = 0.73;
    public static long outtakeDelay = 2000;
    public boolean isLaunching = false;
    Shooter shooter;

    long flapUpTime = 1000000000;

    public BallTransfer(Shooter shooter) {
        this.shooter = shooter;
        SetLaunching(false);
    }

    public void Update() {
        Hardware.flapServo.setPosition(System.currentTimeMillis() >= flapUpTime ? launch : waiting);
    }

    public void SetLaunching(boolean toLaunch) {
        shooter.SetOuttake(toLaunch);
        isLaunching = toLaunch;
        if (toLaunch) {
            shooter.SetIntake(false);
            flapUpTime = System.currentTimeMillis() + outtakeDelay;
        } else {
            shooter.SetOuttake(false);
            flapUpTime = 1000000000;
        }
    }

    public void ToggleLaunch() {
        SetLaunching(!isLaunching);
    }

}
