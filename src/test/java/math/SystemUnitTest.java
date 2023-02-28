package math;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static math.BasicTest.ALLOWED_PRECISION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SystemUnitTest {

    @Nested
    class WholeSystem {

        MathSystem system = spy(new MathSystem(new MyMath()));

        @Test
        void testSystemWithNegativeArg() {
            double x = -1;
            double expected = 456.992063244;
            doReturn(expected).when(system).trigonometricFunction(x);
            doThrow(RuntimeException.class).when(system).logarithmicFunction(any(Double.class));
            assertEquals(expected, system.calculateFunction(x));
        }

        @Test
        void testSystemWithPositiveArg() {
            double x = 5;
            double expected = -4.83542139493;
            doReturn(expected).when(system).logarithmicFunction(x);
            doThrow(RuntimeException.class).when(system).trigonometricFunction(any(Double.class));
            assertEquals(expected, system.calculateFunction(x));
        }

        @Test
        void testSystemWithZero() {
            double x = 0;
            double expected = Double.NaN;
            doReturn(expected).when(system).trigonometricFunction(x);
            doThrow(RuntimeException.class).when(system).logarithmicFunction(any(Double.class));
            assertEquals(expected, system.calculateFunction(x));
        }
    }

    @Nested
    class TrigonometricFunction {
        MyMath math = mock(MyMath.class);
        MathSystem system = new MathSystem(math);

        @Test
        void testEquation() {
            double x = -1;
            when(math.sin(x)).thenReturn(-0.841470984);
            when(math.cos(x)).thenReturn(0.5403023058);
            when(math.tan(x)).thenReturn(-1.5574077246);
            when(math.cotan(x)).thenReturn(-0.64209261);
            when(math.sec(x)).thenReturn(1.85081571768);
            when(math.cosec(x)).thenReturn(-1.18839510);

            assertEquals(456.992063244, system.calculateFunction(x), ALLOWED_PRECISION);
        }

        @Test
        void testForOutOfRAngeValue() {
            double x = Math.PI / 2;
            when(math.sin(x)).thenReturn(1.0);
            when(math.cos(x)).thenReturn(0.0);
            when(math.tan(x)).thenReturn(Double.NaN);
            when(math.cotan(x)).thenReturn(0.0);
            when(math.sec(x)).thenReturn(Double.NaN);
            when(math.cosec(x)).thenReturn(1.0);

            assertEquals(Double.NaN, system.calculateFunction(x), ALLOWED_PRECISION);
        }

        @Test
        void testWithGeneralValue() {
            double x = -Math.PI / 3;
            when(math.sin(x)).thenReturn(-Math.sqrt(3)/2);
            when(math.cos(x)).thenReturn(0.5);
            when(math.tan(x)).thenReturn(-Math.sqrt(3));
            when(math.cotan(x)).thenReturn(-1/ Math.sqrt(3));
            when(math.sec(x)).thenReturn(2.0);
            when(math.cosec(x)).thenReturn(-2/Math.sqrt(3));

            assertEquals(2028.40058046, system.calculateFunction(x), ALLOWED_PRECISION);
        }
    }

    @Nested
    class LogarithmicFunction {
        MyMath math = mock(MyMath.class);
        MathSystem system = new MathSystem(math);

        @Test
        void testEquation() {
            double x = 2;
            when(math.log(5, x)).thenReturn(0.430676558073);
            when(math.log(2, x)).thenReturn(1.0);
            when(math.log(3, x)).thenReturn(0.630929753571);
            when(math.log(10, x)).thenReturn(0.301029995664);

            assertEquals(-1.66700269542, system.calculateFunction(x), ALLOWED_PRECISION);
        }

        @Test
        void testWithValueCloseToZero() {
            double x = 0.1;
            when(math.log(5, x)).thenReturn(-1.43067655807);
            when(math.log(2, x)).thenReturn(-3.32192809489);
            when(math.log(3, x)).thenReturn(-2.09590327429);
            when(math.log(10, x)).thenReturn(-1.0);

            assertEquals(1.025020834, system.calculateFunction(x), ALLOWED_PRECISION);
        }

        @Test
        void testForOutOfRAngeValue() {
            double x = 1;
            when(math.log(5, x)).thenReturn(0.0);
            when(math.log(2, x)).thenReturn(0.0);
            when(math.log(3, x)).thenReturn(0.0);
            when(math.log(10, x)).thenReturn(0.0);

            assertEquals(Double.NaN, system.calculateFunction(x), ALLOWED_PRECISION);
        }

    }

}
