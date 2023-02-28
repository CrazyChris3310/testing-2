package math;

public class MyMath {

    public static double sin(double x) {
        return Basic.sin(x);
    }

    public static double cos(double x) {
        return 1 - 2 * sin(x / 2)* sin(x / 2);
    }

    public static double tan(double x) {
        return sin(x) / cos(x);
    }

    public static double sec(double x) {
        return 1 / cos(x);
    }

    public static double cosec(double x) {
        return 1 / sin(x);
    }

    public static double cotan(double x) {
        return cos(x) / sin(x);
    }

    public static double ln(double x) {
        return Basic.ln(x);
    }

    public static double log(int base, double x) {
        return ln(x) / ln(base);
    }
}
