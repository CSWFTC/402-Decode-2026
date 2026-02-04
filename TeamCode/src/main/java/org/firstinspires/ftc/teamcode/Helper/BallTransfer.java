package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class BallTransfer {
    public static double waiting = 1;
    public static double launch = 0.73;
    public static long outtakeDelay = 2000;
    public boolean isLaunching = false;
    Shooter shooter;

    long flapUpTime;

    public BallTransfer(Shooter shooter) {
        this.shooter = shooter;
        SetLaunching(false);
    }

    public void Update() {
        Hardware.flapServo.setPosition(isLaunching && System.currentTimeMillis() >= flapUpTime ? launch : waiting);
    }

    public void SetLaunching(boolean toLaunch) {
        shooter.SetOuttake(toLaunch);
        isLaunching = toLaunch;
        if (toLaunch) {
            shooter.SetIntake(false);
            flapUpTime = System.currentTimeMillis() + outtakeDelay;
        } else if (System.currentTimeMillis() >= flapUpTime) {
            shooter.SetOuttake(false);
        }
    }

    public void ToggleLaunch() {
        SetLaunching(!isLaunching);
    }

}
