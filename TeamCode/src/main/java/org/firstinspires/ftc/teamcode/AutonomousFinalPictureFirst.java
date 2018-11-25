package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "!FinalPictureFirst")
public class AutonomousFinalPictureFirst extends LinearOpMode
{
    CompRobot compRobot;
    VuforiaFunctions vuforiaFunctions;

    public void runOpMode()
    {
        compRobot = new CompRobot(hardwareMap,this);
        vuforiaFunctions = new VuforiaFunctions(this, hardwareMap);
        float yawAngle = 90;
        float sensorDepth = 5;
        float  yawAngleTurn;
        waitForStart();
        compRobot.driveStraight(10,.6f);
        compRobot.pivotenc(90, .6f);
        while (compRobot.getFrontDistSens().getDistance(DistanceUnit.INCH) > 24 && compRobot.getFrontDistSens().getDistance(DistanceUnit.INCH) > 24)
        {
            compRobot.driveMotors(.2f, .2f);
        }
        {
            if (vuforiaFunctions.hasSeenTarget())
            {
                telemetry.addData(vuforiaFunctions.getCurrentNameOfTargetSeen(), null);
                telemetry.addData("X (in): ", vuforiaFunctions.getXPosIn());
                telemetry.addData("Y (in): ", vuforiaFunctions.getYPosIn());
                telemetry.addData("X (ft): ", vuforiaFunctions.getXPosIn() / 12f);
                telemetry.addData("Y (ft): ", vuforiaFunctions.getYPosIn() / 12f);
                telemetry.addData("YAW ", vuforiaFunctions.getYawDeg());
                sleep(1000);
                yawAngle = vuforiaFunctions.getYawDeg();
                yawAngleTurn = 115 - yawAngle;
                compRobot.pivotenc(yawAngleTurn, .6f);
            }
            else
            {
                telemetry.addData("Such target is not in my sight!", null);
                compRobot.pivotenc(45, .8f);
            }

            telemetry.update();
        }

        /*compRobot.hugWall(6 + sensorDepth, 9 + sensorDepth, 18, true);
        //The hug wall code in the method is a bit different than the one that was in the original auto file
        //make sure that it still runs as intended.

        telemetry.addData("Stopped", null);
        sleep(2000); //drop team marker into depot
        telemetry.update();

        compRobot.pivotenc(90, .8f);
        compRobot.driveStraight(96, .6f);
        compRobot.stopDriveMotors();


       // compRobot.hugWall(6 + sensorDepth, 9 + sensorDepth, 18, false); */
    }
}