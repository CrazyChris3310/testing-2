package math;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.function.UnaryOperator;

public class CsvManager {
    public static void toCSV(double x, double value, File file) throws IOException {
        OutputStreamWriter wr = new OutputStreamWriter(new FileOutputStream(file, true));
        String output = x + "," + value + "\n";
        wr.write(output);
        wr.flush();
    }

    public static void functionToCSV(UnaryOperator<Double> func, String name, double lower, double upper, double step)
            throws IOException {
        File file = new File("src/main/resources/" + name + ".csv");
        file.delete();
        file.createNewFile();
        double x;
        for (int i = 0; i < (upper - lower) / step; ++i) {
            x = lower + i * step;
            toCSV(x, func.apply(x), file);
        }
//        for (double x = lower; x < upper; x += step) {
//            toCSV(x, func.apply(x), file);
//        }
    }

    public static void functionToCSV(UnaryOperator<Double> func, String name) throws IOException {
        functionToCSV(func, name, -5, 5, 0.05);
    }

    public static void main(String[] args) throws IOException {
        MyMath math = new MyMath();
        MathSystem system = new MathSystem(math);
        functionToCSV(math::sin, "sin");
        functionToCSV(system::calculateFunction, "system");
        functionToCSV(system::trigonometricFunction, "trigonometry");
        functionToCSV(system::logarithmicFunction, "logarithm");
    }
}
