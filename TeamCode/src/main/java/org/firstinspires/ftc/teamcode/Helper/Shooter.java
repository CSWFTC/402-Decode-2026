package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class Shooter {
    public static double intakePower = 1;
    public static double outtakeTopPower = 0.75;
    public static double outtakeMiddlePower = 1;
    public static double outtakeBottomPower = 1;
    public boolean intakeOn;
    public boolean outtakeOn;

    public Shooter() {
        SetIntake(false);
        SetOuttake(false);
    }

    public void SetIntake(boolean status) {
        intakeOn = status;
        Hardware.intake.setPower(status ? intakePower : 0.0);
    }

    public void SetOuttake(boolean status) {
        outtakeOn = status;
        Hardware.outtakeBottom.setPower(status ? outtakeBottomPower : 0.0);
        Hardware.outtakeMiddle.setPower(status ? outtakeMiddlePower : 0.0);
        Hardware.outtakeTop.setPower(status ? outtakeTopPower : 0.0);
    }

    public void increaseIntakePower(double power) {
        intakePower = intakePower + power;
        if (intakePower > 1) {
            intakePower = 1;
        }
        SetIntake(intakeOn);
    }

    public void decreaseIntakePowerMultiplier(double power) {
        intakePower = intakePower - power;
        if (intakePower < 0.05) {
            intakePower = 0.05;
        }
        SetIntake(intakeOn);
    }

    public void setOuttakeTopPowerMultiplier(double power) {
        outtakeTopPower = power;
        if (outtakeTopPower > 1) {
            outtakeTopPower = 1;
        } else if (outtakeTopPower < 0.05) {
            outtakeTopPower = 0.05;
        }
        SetOuttake(outtakeOn);
    }

    public void increaseTopOuttakePower(double power) {
        outtakeTopPower = outtakeTopPower + power;
        if (outtakeTopPower > 1) {
            outtakeTopPower = 1;
        }
        SetOuttake(outtakeOn);
    }

    public void decreaseTopOuttakePower(double power) {
        outtakeTopPower = outtakeTopPower - power;
        if (outtakeTopPower < 0.05) {
            outtakeTopPower = 0.05;
        }
        SetOuttake(outtakeOn);
    }

    public void ToggleIntake() {
        SetIntake(!intakeOn);
    }

    public void ToggleOuttake() {
        SetOuttake(!outtakeOn);
    }
}
