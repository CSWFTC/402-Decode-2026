package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Reverse {
}

// use this annotation for things that you don't want to be initialized
// because they're not plugged in
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface DoNotInitialize {
}

// singleton for easy access to hardware peripherals
public class Hardware {
    // you can add more peripherals here
    // just declare a new field
    // the field name should match the name in the robot config
    // that's it!
    @Reverse
    @DoNotInitialize
    public static DcMotor frontLeft;
    @DoNotInitialize
    public static DcMotor frontRight;
    @DoNotInitialize
    public static DcMotor rearLeft;
    @DoNotInitialize
    public static DcMotor rearRight;

    @DoNotInitialize
    public static WebcamName camera;

    @DoNotInitialize
    @Reverse
    public static DcMotor intake;
    @Reverse
    public static DcMotor shooter1;
    public static DcMotor shooter2;
    @DoNotInitialize
    public static Servo flapServo;
    @Reverse
    @DoNotInitialize
    public static DcMotor spindex;

    @DoNotInitialize
    public static Servo hood1;
    @DoNotInitialize
    public static Servo hood2;
    @DoNotInitialize
    public static Servo turret1;
    @DoNotInitialize
    public static Servo turret2;
    @DoNotInitialize
    public static GoBildaPinpointDriver pinpoint;

    // initialization code
    public static void init(HardwareMap map) {
        Field[] fields = Hardware.class.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) && field.getAnnotation(DoNotInitialize.class) == null) {
                field.setAccessible(true);
                try {
                    field.set(null, map.get(field.getType(), field.getName()));
                    Reverse r = field.getAnnotation(Reverse.class);
                    if (r != null) {
                        if (field.getType() == DcMotor.class) {
                            DcMotor m = (DcMotor) field.get(null);
                            assert m != null;
                            m.setDirection(DcMotorSimple.Direction.REVERSE);
                        } else if (field.getType() == Servo.class) {
                            Servo s = (Servo) field.get(null);
                            assert s != null;
                            s.setDirection(Servo.Direction.REVERSE);
                        }
                    }
                } catch (IllegalAccessException ignored) {

                }
            }
        }
    }
}
