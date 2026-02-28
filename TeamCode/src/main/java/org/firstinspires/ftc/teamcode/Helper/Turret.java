package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class Turret {
    public static double hoodTicksPerDeg = 0.002;
    public static double turretTicksPerDeg = 0.002;
    public static double hoodMinAngle = 0;
    public static double hoodMaxAngle = 90;
    public static double turretMinAngle = 0;
    public static double turretMaxAngle = 180;

    public double getHoodAngle() {
        return Hardware.hood1.getPosition() / hoodTicksPerDeg;
    }

    public void setHoodAngle(double angle) {
//        double position = hoodTicksPerDeg * Math.max(hoodMinAngle, Math.min(angle, hoodMaxAngle));
//        Hardware.hood1.setPosition(position);
//        Hardware.hood2.setPosition(position);
    }

    public double getTurretAngle() {
        return Hardware.turret1.getPosition() / turretTicksPerDeg;
    }

    public void setTurretAngle(double angle) {
        double position = turretTicksPerDeg * Math.max(turretMinAngle, Math.min(angle, turretMaxAngle));
        Hardware.turret1.setPosition(position);
        Hardware.turret2.setPosition(position);
    }
}
