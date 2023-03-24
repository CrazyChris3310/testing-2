package math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SystemIntegrationTest {

    public static final double STEP = 0.01;
    MathSystem system = new MathSystem(new MyMath());

    @ParameterizedTest
    @ValueSource(doubles={-5.445, -4.111, -4.051, -3.751, -2.1976, -0.8183, -0.6302, -0.4713, 0.211})
    void testSpecialPoints(double x) {
        double left = system.calculateFunction(x - STEP);
        double right = system.calculateFunction(x + STEP);
        double res = system.calculateFunction(x);
        assertTrue(res > left && res > right || res < left && res < right);
    }

    @ParameterizedTest
    @MethodSource("valuesProvider")
    void generalTest(double x, double expected) {
        assertEquals(expected, system.calculateFunction(x), BasicTest.ALLOWED_PRECISION);
    }

    static Stream<Arguments> valuesProvider() {
        return Stream.of(
                Arguments.of(2, -1.66700269542),
                Arguments.of(234, -151.243948944),
                Arguments.of(12, -11.8050899657),
                Arguments.of(-7, -7.3730523694),
                Arguments.of(0.085, 0.0296),
                Arguments.of(-0.4307, -0.0629),
                Arguments.of(-0.4307 - 2*Math.PI, -0.0629),
                Arguments.of(-0.892, 0.10539)
        );
    }

}
