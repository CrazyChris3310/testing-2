package math;

import static java.lang.Math.pow;

public class MathSystem {

    private final MyMath math;

    public MathSystem(MyMath math) {
        this.math = math;
    }

    public double trigonometricFunction(double x) {
        return pow(pow((pow(math.cos(x) / math.tan(x) + math.sec(x), 2) *
                (math.cosec(x) + math.tan(x))) - math.tan(x), 2), 2) -
                ((math.sec(x) * pow(math.cotan(x), 2) / pow((math.cos(x) * math.cos(x)), 2))
                        + (math.sec(x) - math.sin(x)));
    }

    public double logarithmicFunction(double x) {
        return (((math.log(5, x) - math.log(5, x)) / math.log(2, x)) - math.log(3, x)) -
                pow(math.log(10, x) * math.log(3, x), 2) - math.log(2, x);
    }

    public double calculateFunction(double x) {
        if (x <= 0) {
            return trigonometricFunction(x);
        } else {
            return logarithmicFunction(x);
        }
    }

}
