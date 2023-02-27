package math;

import static math.MyMath.*;
import static java.lang.Math.pow;

public class MathSystem {

    public static double trigonometricFunction(double x) {
        return pow(pow((pow(cos(x) / tan(x) + sec(x), 2) * (cosec(x) + tan(x))) - tan(x), 2), 2) -
                ((sec(x) * pow(cotan(x), 2) / (cos(x) * pow(cos(x), 2))) + (sec(x) - sin(x)));
    }

    public static double logarithmicFunction(double x) {
        return (((log(5, x) - log(5, x)) / log(2, x)) - log(3, x)) -
                (pow((log(10, x) * log(3, x)), 2) - log(2, x));
    }

    public static double calculateFunction(double x) {
        if (x <= 0) {
            return trigonometricFunction(x);
        } else {
            return logarithmicFunction(x);
        }
    }

}
