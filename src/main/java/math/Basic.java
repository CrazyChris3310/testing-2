package math;

public class Basic {

    public static final double PRECISION = 0.00001;

    interface FunctionMemberFinder {
        double calculate(double x, int n);
    }

    private static double sinTaylorMember(double x, int n) {
        int sign = n % 2 == 0 ? 1 : -1;
        double res = sign * Math.pow(x, 2 * n + 1);
        return divideByFactorial(res, 2*n + 1);
    }

    private static double divideByFactorial(double res, int n) {
        for (int i = 1; i <= n; ++i) {
            res /= i;
        }
        return res;
    }

    public static double sin(double value) {
        return findSum(value, Basic::sinTaylorMember);
    }

    private static double lnTaylorMember(double x, int n) {
        int sign = n % 2 == 0 ? -1 : 1;
        return sign * Math.pow(x - 1, n) / n;
    }

    public static double ln(double value) {
        return findSum(value, Basic::lnTaylorMember);
    }

    private static double findSum(double value, FunctionMemberFinder finder) {
        double result = 0;
        double prev = 0;
        double current = Double.MAX_VALUE;
        int n = 0;
        while (Math.abs(current - prev) >= PRECISION) {
            prev = current;
            current = finder.calculate(value, n);
            result += current;
            n += 1;
        }
        return result;
    }
}
