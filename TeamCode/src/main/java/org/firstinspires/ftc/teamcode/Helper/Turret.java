package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class Turret {
    public static double hoodMin = 0;
    public static double hoodMax = 0.2;
    public static double turretMin = 0;
    public static double turretMax = 0.2;

    public Turret() {
        setHoodPosition(hoodMin);
        setTurretPosition(turretMin);
    }

    public double getHoodPosition() {
        return Hardware.hood1.getPosition();
    }

    public void setHoodPosition(double position) {
        Hardware.hood1.setPosition(position);
        Hardware.hood2.setPosition(position);
    }

    public double getTurretPosition() {
        return Hardware.turret1.getPosition();
    }

    public void setTurretPosition(double position) {
        Hardware.turret1.setPosition(position);
        Hardware.turret2.setPosition(position);
    }
}
