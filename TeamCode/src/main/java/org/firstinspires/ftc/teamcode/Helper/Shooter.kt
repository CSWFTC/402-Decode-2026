package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class Shooter {
    public static double intakePower = 1;
    public static double outtakePower = 1;
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
        Hardware.outtake.setPower(status ? outtakePower : 0.0);
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
        outtakePower = power;
        if (outtakePower > 1) {
            outtakePower = 1;
        } else if (outtakePower < 0.05) {
            outtakePower = 0.05;
        }
        SetOuttake(outtakeOn);
    }

    public void increaseTopOuttakePower(double power) {
        outtakePower = outtakePower + power;
        if (outtakePower > 1) {
            outtakePower = 1;
        }
        SetOuttake(outtakeOn);
    }

    public void decreaseTopOuttakePower(double power) {
        outtakePower = outtakePower - power;
        if (outtakePower < 0.05) {
            outtakePower = 0.05;
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
