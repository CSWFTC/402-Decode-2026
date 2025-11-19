package org.firstinspires.ftc.teamcode.Simulation;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public abstract class OpModeBase extends LinearOpMode {
    public static OpModeBase Init() {
        return null;
    }

    @Override
    public void runOpMode(){
        Run(Constants.createFollower(hardwareMap));
    }
    public abstract void Run(Follower follower);
    public static void main(String[] args){
        Init().Run(new Follower(new FollowerConstants(), new SimLocalizer(), new SimDrivetrain()));
    }
}
