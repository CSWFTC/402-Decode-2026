package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.helpers.AprilTagConfig;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

enum State {
    GOING_TO_PICKUP,
    PICKING_UP,
    GOING_TO_LAUNCH,
    DONE
}

@Configurable
public abstract class Auton extends LinearOpMode {
    // these poses are for the red side; blue will mirror them
    public static Pose start = new Pose(56, 8, 90);
    public static Pose pickupGPP = new Pose(40, 36, 180);
    public static Pose nextSetOffset = new Pose(0, 24);
    public static Pose lastBallOffset = new Pose(-16, 0);
    public static Pose launch = new Pose(56, 104, 35);

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Follower f = Constants.createFollower(hardwareMap);
        f.setStartingPose(start);
        AprilTagConfig atConf = new AprilTagConfig();
        Shooter shooter = new Shooter();
        while (!atConf.order.isPresent()) {
            atConf.Update();
            sleep(20);
        }
        Pose pickup = pickupGPP;
        switch (atConf.order.get()) {
            case PPG:
                pickup = pickup.plus(nextSetOffset);
            case PGP:
                pickup = pickup.plus(nextSetOffset);
        }
        telemetry.addData("Ball Order", atConf.order.get());
        telemetry.addLine();
        telemetry.update();
        waitForStart();
        PathChain toPickup = between(f, start, pickup);
        PathChain finishPickup = between(f, pickup, pickup.plus(lastBallOffset));
        PathChain toLaunch = between(f, pickup.plus(lastBallOffset), launch);
        State s = State.GOING_TO_PICKUP;
        f.followPath(toPickup);
        while (opModeIsActive()) {
            switch (s) {
                case GOING_TO_PICKUP:
                    if (!f.isBusy()) {
                        shooter.SetIntake(true);
                        f.followPath(finishPickup);
                        s = State.PICKING_UP;
                    }
                    break;
                case PICKING_UP:
                    if (!f.isBusy()) {
                        shooter.SetIntake(false);
                        f.followPath(toLaunch, true);
                        s = State.GOING_TO_LAUNCH;
                    }
                    break;
                case GOING_TO_LAUNCH:
                    if (!f.isBusy()) {
                        shooter.SetOuttake(true);
                        s = State.DONE;
                    }
                    break;
            }
        }
    }

    // red should just return p, blue should mirror p
    abstract Pose transform(Pose p);

    PathChain between(Follower f, Pose begin, Pose end) {
        begin = transform(begin);
        end = transform(end);
        return f.pathBuilder().addPath(new BezierLine(begin, end)).setLinearHeadingInterpolation(begin.getHeading(), end.getHeading()).build();
    }
}