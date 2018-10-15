package calendar.test;

import calculator.Calculator;
import calendar.exec.Executor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Calculator.class)
public class CalendarTest {

    @Mock
    private Executor calendar;

    private LocalDateTime now;

    @Rule
    public final SystemOutRule outRule = new SystemOutRule().enableLog();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() throws Exception {
        calendar = new Executor();
        now = LocalDateTime.now();
        Executor spy = PowerMockito.spy(calendar);
        doCallRealMethod().when(spy).addEvent(any(String.class), any(LocalDateTime.class));
    }

    @Test
    public void printCurDateTest() throws Exception {
        Executor exec= new Executor();

        exec.printCurDate("Asia/Tokyo");

        String log = outRule.getLog();

        Assert.assertTrue(log.contains("no event found"));
    }

    public void addEventTest(String name, LocalDateTime date) {
        //events.add(new Event(name, date));
    }
    @Test
    public void printEventsTest() {
        Executor exec= new Executor();
        exec.addEvent("test event 1", now);
        exec.addEvent("test event 2", now);
        exec.printEvents();

        String log = outRule.getLog();
        Assert.assertTrue(log.contains("test event"));
        //events.forEach(event -> System.out.println(event.getName() + " date and time :" + event.getDate()));

    }

    @Test
    public void deleteEventTest() {

        Executor exec= new Executor();
        exec.addEvent("test event 1", now);
        exec.addEvent("test event 2", now);
        exec.deleteEvent("test event 1");
        exec.printEvents();

        String log = outRule.getLog();
        Assert.assertTrue(log.contains("test event 2"));
    }

    @Test
    public void printLocalTimeTest() {
        Executor exec= new Executor();
        exec.printLocalTime("Asia/Tokyo");

        String log = outRule.getLog();
        Assert.assertTrue(log.contains("Asia/Tokyo"));
    }

    @Test
    public void printDateInPeriodTest() {
        Executor exec= new Executor();
        exec.printDateInPeriod("week");

        String log = outRule.getLog();
        Assert.assertTrue(log.contains("2018-10-22"));
    }

    @Test
    public void printDatePieceTest() {

        Executor exec= new Executor();
        exec.printDatePiece("dayOfYear");

        String log = outRule.getLog();
        Assert.assertTrue(log.contains("288"));

    }
}
