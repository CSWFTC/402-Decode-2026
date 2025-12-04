package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class Shooter {
    public static double intakePower = 1;
    public static double outtakeTopPower = 0.75;
    public static double outtakeMiddlePower = 1;
    public static double outtakeBottomPower = 1;
    public double outtakePowerMultiplier = 1;
    public double intakePowerMultiplier = 1;
    public boolean intakeOn;
    public boolean outtakeOn;
    public Shooter(){
        SetIntake(false);
        SetOuttake(false);
    }

    private void SetIntake(boolean status) {
        intakeOn = status;
        Hardware.intake.setPower(status ? intakePower * intakePowerMultiplier : 0.0);
    }
    private void SetOuttake(boolean status) {
        outtakeOn = status;
        Hardware.outtakeBottom.setPower(status ? outtakeBottomPower * outtakePowerMultiplier : 0.0);
        Hardware.outtakeMiddle.setPower(status ? outtakeMiddlePower * outtakePowerMultiplier : 0.0);
        Hardware.outtakeTop.setPower(status ? outtakeTopPower * outtakePowerMultiplier : 0.0);
    }

    public void increaseIntakePower(double power) {
        intakePowerMultiplier = intakePowerMultiplier + power;
        if (intakePowerMultiplier > 1) {
            intakePowerMultiplier = 1;
        }
        SetIntake(intakeOn);
    }

    public void decreaseIntakePower(double power) {
        intakePowerMultiplier = intakePowerMultiplier - power;
        if (intakePowerMultiplier < 0.05) {
            intakePower = 0.05;
        }
        SetIntake(intakeOn);
    }

    public void setOuttakePowerMultiplier(double power) {
        outtakePowerMultiplier = power;
        if (outtakePowerMultiplier > 1) {
            outtakePowerMultiplier = 1;
        } else if (outtakePowerMultiplier < 0.05) {
            outtakePowerMultiplier = 0.05;
        }
    }

    public void increaseOuttakePower(double power) {
        outtakePowerMultiplier = outtakePowerMultiplier + power;
        if (outtakePowerMultiplier > 1) {
            outtakePowerMultiplier = 1;
        }
        SetOuttake(outtakeOn);
    }

    public void decreaseOuttakePower(double power) {
        outtakePowerMultiplier = outtakePowerMultiplier - power;
        if (outtakePowerMultiplier < 0.05) {
            outtakePowerMultiplier = 0.05;
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
