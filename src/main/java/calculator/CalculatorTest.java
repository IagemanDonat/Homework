package calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.runner.Request.method;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;


//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(Calculator.class)
public class CalculatorTest {

    //        @Spy
//    @Mock
    private Calculator calculator;

    @Rule
    public final SystemOutRule outRule = new SystemOutRule().enableLog();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
//        calculator = new Calculator();
//        calculator = mock(Calculator.class);
        //calculator = PowerMockito.spy(new Calculator());

    }

    @Test
    public void testSumPublic() throws Exception {

        when(calculator.generateRandomA()).thenReturn(4);

        when(calculator.generateRandomB()).thenReturn(3);

//        when(calculator.addPublic()).thenCallRealMethod();

/*        doReturn(5).when(calculator, method(Calculator.class, "generatePrivateRandomA"));
        doReturn(6).when(calculator, method(Calculator.class, "generatePrivateRandomB"));
        doReturn(5).when(calculator, method(Calculator.class, "generateStaticRandomA"));
        doReturn(6).when(calculator, method(Calculator.class, "generateStaticRandomB"));*/


        int res = calculator.addPublic();

        assertEquals(7, res);
    }

    @Test
    public void testSumPrivate() throws Exception {

        Calculator calculator = new Calculator();

        Calculator spy = PowerMockito.spy(calculator);

        doReturn(5).when(spy, "generatePrivateRandomA");

        doReturn(6).when(spy, "generatePrivateRandomB");

        int res = spy.addPrivate();

        assertEquals(11, res);
    }

    @Test
    public void testSumPublicStatic() throws Exception {

        PowerMockito.mockStatic(Calculator.class);

        when(Calculator.generateStaticRandomA()).thenReturn(5);

        when(Calculator.generateStaticRandomB()).thenReturn(6);

        when(Calculator.addStatic()).thenCallRealMethod();

        int res = Calculator.addStatic();

        assertEquals(11, res);
    }

    @Test
    public void testDevide() {

    }

    @Test
    public void testAdd() {

    }


    @Test
    public void testPrint() {
        new Calculator().print();

        String log = outRule.getLog();

        Assert.assertTrue(log.contains("Hello, Donat"));
    }

    @Test(expected = IOException.class)
    public void testException() throws IOException {
        new Calculator().throwException();
    }

    @Test
    public void testExceptionWithRule() throws IOException {

        exception.expectMessage("Don't do it");
        exception.expect(IOException.class);

        new Calculator().throwException();
    }

}
