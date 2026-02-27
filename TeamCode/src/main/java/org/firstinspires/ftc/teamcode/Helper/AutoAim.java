package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@Configurable
public class AutoAim {
    public static double xOffset;
    public static double yOffset;
    public static GoBildaPinpointDriver.EncoderDirection xDir = GoBildaPinpointDriver.EncoderDirection.FORWARD;
    public static GoBildaPinpointDriver.EncoderDirection yDir = GoBildaPinpointDriver.EncoderDirection.FORWARD;
    public static GoBildaPinpointDriver.GoBildaOdometryPods resolution = GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD;
    public static DistanceUnit unit = DistanceUnit.INCH;
    public static GoBildaPinpointDriver.EncoderDirection forwardDirection = GoBildaPinpointDriver.EncoderDirection.FORWARD;
    public static GoBildaPinpointDriver.EncoderDirection strafeDirection = GoBildaPinpointDriver.EncoderDirection.FORWARD;
    public Pose2D pos;
    Turret t;
    Pose2D target;

    public AutoAim(Pose2D startingPos, Pose2D target, Turret turret) {
        Hardware.pinpoint.setEncoderDirections(xDir, yDir);
        Hardware.pinpoint.setEncoderResolution(resolution);
        Hardware.pinpoint.setEncoderDirections(forwardDirection, strafeDirection);
        Hardware.pinpoint.setOffsets(xOffset, yOffset, DistanceUnit.INCH);
        Hardware.pinpoint.resetPosAndIMU();
        Hardware.pinpoint.setPosition(startingPos);
        t = turret;
        pos = startingPos;
        this.target = target;
    }

    public void Update() {
        Hardware.pinpoint.update();
        pos = Hardware.pinpoint.getPosition();
        double angle = Math.atan2(target.getY(DistanceUnit.INCH) - pos.getY(DistanceUnit.INCH), target.getX(DistanceUnit.INCH) - pos.getX(DistanceUnit.INCH));
        double botHeading = 90 - pos.getHeading(AngleUnit.DEGREES);
        t.setTurretAngle(Math.toDegrees(angle) - botHeading);
    }
}
