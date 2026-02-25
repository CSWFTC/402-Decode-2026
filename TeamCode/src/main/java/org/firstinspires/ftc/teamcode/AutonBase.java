package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.Helper.Turret;
import org.firstinspires.ftc.teamcode.PedroPathing.Constants;

enum State {
    LAUNCHING_PRELOAD,
    GOING_TO_FIRST,
    PICKING_UP_FIRST,
    GOING_TO_LAUNCH_FIRST,
    LAUNCHING_FIRST,
    GOING_TO_SECOND,
    PICKING_UP_SECOND,
    GOING_TO_LAUNCH_SECOND,
    LAUNCHING_SECOND,
    GOING_TO_THIRD,
    PICKING_UP_THIRD,
    GOING_TO_LAUNCH_THIRD,
    LAUNCHING_THIRD,
    LEAVE
}

public class AutonBase extends LinearOpMode {
    public static Pose launchRear;
    public static Pose launchForward;
    public static Pose pickup1;
    public static Pose pickup2;
    public static Pose pickup3;
    public static Pose pickupEndOffset;
    public static Pose endPosition;

    PathChain pathBetween(Follower f, Pose start, Pose end) {
        return f.pathBuilder().addPath(new BezierLine(start, end)).setLinearHeadingInterpolation(start.getHeading(), end.getHeading()).build();
    }

    Pose offset(Pose original) {
        return new Pose(original.getX() + pickupEndOffset.getX(), original.getY() + pickupEndOffset.getY(), original.getHeading());
    }

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Turret t = new Turret();
        Shooter shooter = new Shooter();
        State s = State.LAUNCHING_PRELOAD;
        Follower f = Constants.createFollower(hardwareMap);
        f.setStartingPose(launchRear);
        waitForStart();
        PathChain goto1 = pathBetween(f, launchRear, pickup1);
        PathChain collect1 = pathBetween(f, pickup1, offset(pickup1));
        PathChain launch1 = pathBetween(f, offset(pickup1), launchRear);
        // TODO: Start Launching
        while (opModeIsActive()) {
            f.update();
            switch (s) {
                case LAUNCHING_PRELOAD:
                    // TODO: Stop Launching at Some Point
                    s = State.GOING_TO_FIRST;
                    f.followPath(goto1);
                    break;
                case GOING_TO_FIRST:
                    if (!f.isBusy()) {
                        s = State.PICKING_UP_FIRST;
                        f.followPath(collect1);
                    }
                    break;
                case PICKING_UP_FIRST:
                    if (!f.isBusy()) {
                        s = State.GOING_TO_LAUNCH_FIRST;
                        f.followPath(launch1, true);
                    }
                    break;
                case GOING_TO_LAUNCH_FIRST:
                    if (!f.isBusy()) {
                        // TODO: Start Launching
                        s = State.LAUNCHING_FIRST;
                    }
                    break;
                case LAUNCHING_FIRST:
                    // TODO: Stop Launching at Some Point
                    s = State.GOING_TO_SECOND;
                    break;
            }
        }
    }
}