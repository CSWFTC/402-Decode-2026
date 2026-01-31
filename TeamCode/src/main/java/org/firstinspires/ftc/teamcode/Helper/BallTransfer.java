package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class BallTransfer {
    public static double waiting = 1;
    public static double launch = 0.73;
    boolean isLaunching = false;

    public BallTransfer() {
        SetLaunching(false);
    }

    public void SetLaunching(boolean toLaunch) {
        isLaunching = toLaunch;
        Hardware.flapServo.setPosition(toLaunch ? launch : waiting);
    }

    public void ToggleLaunch() {
        SetLaunching(!isLaunching);
    }

}
