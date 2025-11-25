package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.helpers.AprilTagConfig;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "RED Auton")
@Configurable
public class AutonRed extends LinearOpMode {
    public static Pose start = new Pose(56, 8, 90);
    public static Pose pickupGPP = new Pose(40, 36, 180);
    public static Pose nextSetOffset = new Pose(0, 24);
    public static Pose lastBallOffset = new Pose(-16, 0);
    public static Pose launch = new Pose(72, 90, 40);
    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Follower f = Constants.createFollower(hardwareMap);
        f.setStartingPose(start);
        AprilTagConfig atConf = new AprilTagConfig();
        Shooter shooter = new Shooter();
        while (!atConf.order.isPresent()){
            atConf.Update();
            sleep(20);
        }
        Pose pickup = pickupGPP;
        switch (atConf.order.get()){
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
        while(opModeIsActive()){
            switch (s){
                case GOING_TO_PICKUP:
                    if(!f.isBusy()){
                        shooter.SetIntake(true);
                        f.followPath(finishPickup);
                        s = State.PICKING_UP;
                    }
                    break;
                case PICKING_UP:
                    if(!f.isBusy()){
                        shooter.SetIntake(false);
                        f.followPath(toLaunch, true);
                        s = State.GOING_TO_LAUNCH;
                    }
                    break;
                case GOING_TO_LAUNCH:
                    if(!f.isBusy()){
                        shooter.SetOuttake(true);
                        s = State.DONE;
                    }
                    break;
            }
        }
    }
    PathChain between(Follower f, Pose begin, Pose end){
       return f.pathBuilder().addPath(new BezierLine(begin, end)).setLinearHeadingInterpolation(begin.getHeading(), end.getHeading()).build();
    }
}
enum State{
    GOING_TO_PICKUP,
    PICKING_UP,
    GOING_TO_LAUNCH,
    DONE
}