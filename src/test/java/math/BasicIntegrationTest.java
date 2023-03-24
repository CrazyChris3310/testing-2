package math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicIntegrationTest {

    MathSystem system = new MathSystem(new MyMath());

    @ParameterizedTest
    @MethodSource("provideSinData")
    void testFunctionDependingOnSinus(double x, double sinX, double sinHalf, double expected) {
        try (MockedStatic<Basic> utils = Mockito.mockStatic(Basic.class)) {
            utils.when(() -> Basic.sin(x)).thenReturn(sinX);
            utils.when(() -> Basic.sin(x/2)).thenReturn(sinHalf);

            assertEquals(expected, system.calculateFunction(x), BasicTest.ALLOWED_PRECISION);
        }
    }

    static Stream<Arguments> provideSinData() {
        return Stream.of(
                Arguments.of(-Math.PI/6, -0.5, -0.2588, -7.80779),
                Arguments.of(-23, 0.84622, 0.87545, 12.1045),
                Arguments.of(-Math.PI/4, -0.7071, 0.3827, -7.7763)
        );
    }

    @ParameterizedTest
    @MethodSource("provideLogData")
    void testFunctionDependingOnLog(double x, double log, double expected) {
        try (MockedStatic<Basic> utils = Mockito.mockStatic(Basic.class)) {
            utils.when(() -> Basic.ln(x)).thenReturn(log);
            utils.when(() -> Basic.ln(5)).thenReturn(1.6094);
            utils.when(() -> Basic.ln(3)).thenReturn(1.0986);
            utils.when(() -> Basic.ln(10)).thenReturn(2.3026);
            utils.when(() -> Basic.ln(2)).thenReturn(0.6931);

            assertEquals(expected, system.calculateFunction(x), BasicTest.ALLOWED_PRECISION);
        }
    }

    static Stream<Arguments> provideLogData() {
        return Stream.of(
                Arguments.of(0.5, -0.693, 1.5949),
                Arguments.of(1.7, 0.5306, -1.2609),
                Arguments.of(5, 1.6094, -4.8354)
        );
    }

}
