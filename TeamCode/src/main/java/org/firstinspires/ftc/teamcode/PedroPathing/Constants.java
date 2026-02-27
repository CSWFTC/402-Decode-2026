package org.firstinspires.ftc.teamcode.PedroPathing;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Helper.AutoAim;

@Configurable
// TODO: Add the actual values for everything
public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants().mass(2);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static MecanumConstants driveConstants = new MecanumConstants().maxPower(1)
            .rightFrontMotorName("frontRight")
            .rightRearMotorName("rearRight")
            .leftFrontMotorName("frontLeft")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorName("rearLeft")
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE);

    public static PinpointConstants localizerConstants = new PinpointConstants().forwardPodY(AutoAim.yOffset).strafePodX(AutoAim.xOffset).distanceUnit(AutoAim.unit).hardwareMapName("pinpoint").encoderResolution(AutoAim.resolution).forwardEncoderDirection(AutoAim.forwardDirection).strafeEncoderDirection(AutoAim.strafeDirection);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap).pathConstraints(pathConstraints).mecanumDrivetrain(driveConstants).pinpointLocalizer(localizerConstants).build();
    }
}