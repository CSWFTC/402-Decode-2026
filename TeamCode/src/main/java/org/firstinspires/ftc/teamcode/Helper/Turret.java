package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class Turret {
    public static double hoodMin = 0;
    public static double hoodMax = 0.2;

    public Turret() {
        setHoodPosition(hoodMin);
    }

    public double getHoodPosition() {
        return Hardware.hood1.getPosition();
    }

    public void setHoodPosition(double position) {
        Hardware.hood1.setPosition(position);
        Hardware.hood2.setPosition(position);
    }
}
