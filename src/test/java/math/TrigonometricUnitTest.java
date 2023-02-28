package math;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static math.BasicTest.ALLOWED_PRECISION;

public class TrigonometricUnitTest {

    @Nested
    class Cosine {
        @ParameterizedTest
        @MethodSource("cosArgsProvider")
        void testCosPositiveArgs(double x, double expected) {
            assertEquals(expected, MyMath.cos(x), ALLOWED_PRECISION);
        }

        @ParameterizedTest
        @MethodSource("cosArgsProvider")
        void testCosNegativeArgs(double x, double expected) {
            assertEquals(expected, MyMath.cos(-x), ALLOWED_PRECISION);
        }

        static Stream<Arguments> cosArgsProvider() {
            return Stream.of(
                    Arguments.of(0, 1),
                    Arguments.of(PI, -1),
                    Arguments.of(PI/2, 0),
                    Arguments.of(PI/3, 0.5),
                    Arguments.of(PI/4, sqrt(2)/2),
                    Arguments.of(PI/6, sqrt(3)/2),
                    Arguments.of(2*PI/3, -0.5),
                    Arguments.of(3*PI/4, -sqrt(2)/2),
                    Arguments.of(5*PI/6, -sqrt(3)/2),
                    Arguments.of(2*PI, 1),
                    Arguments.of(3*PI, -1),
                    Arguments.of(3*PI/2, 0),
                    Arguments.of(16*PI/3, -0.5)
            );
        }
    }

}
