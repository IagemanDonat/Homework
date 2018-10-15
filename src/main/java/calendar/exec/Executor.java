package calendar.exec;

import calendar.entities.Event;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Executor {

    private Set<Event> events = new HashSet<>();//move to constructor

    public Executor() {
    }

    private static Instant getNowInstant(){
        return Instant.now();
    }

    private static LocalDateTime getNowDate(){
        return LocalDateTime.now();
    }

    public void printCurDate(String zoneId) {
        Instant time = getNowInstant();

        if (!ZoneId.getAvailableZoneIds().contains(zoneId)) {
            System.out.println ("Invalid zone");
            return;
        }

        ZonedDateTime zonedTime = time.atZone(ZoneId.of(zoneId));

        Optional<Event> currentEvent = events.stream().filter(event -> event.getDate().equals(zonedTime)).findFirst();

        if (currentEvent.isPresent()) {
            System.out.println (zonedTime + " " + currentEvent.get().getName());
        } else {
            System.out.println ("no event found");
        }
    }

    public void addEvent(String name, LocalDateTime date) {
        events.add(new Event(name, date));
    }

    public void printEvents() {
        events.forEach(event -> System.out.println(event.getName() + " date and time :" + event.getDate()));

    }

    public void deleteEvent(String eventName) {
        Optional<Event> eventToDel = events.stream().filter(event -> event.getName().equals(eventName)).findFirst();
        eventToDel.ifPresent(event -> events.remove(event));

        events.removeIf(event -> event.getName().equals(eventName));
    }

    public void printLocalTime(String countryAndCity) {
        Instant time = getNowInstant();

        if (!ZoneId.getAvailableZoneIds().contains(countryAndCity)) {
            System.out.println("Invalid zone");
            return;
        }
        ZonedDateTime zonedTime = time.atZone(ZoneId.of(countryAndCity));

        System.out.println(ZonedDateTime.now(ZoneId.of(countryAndCity)) + " " +

         " day of the week: " + String.valueOf(zonedTime.getDayOfWeek().getValue()) +
                " time zone: " + zonedTime.getOffset().getId());
    }

    public void printDateInPeriod(String period) {
        LocalDateTime time = getNowDate();

        switch (period) {
            case "week":
                System.out.println(time.plusWeeks(1));
                break;
            case "month":
                System.out.println(time.plusMonths(1));
                break;
            case "year":
                System.out.println(time.plusYears(1));
                break;
            default:
                break;
        }
    }

    public void printDatePiece(String piece) {
        LocalDateTime time = getNowDate();
        switch (piece) {
            case "time":
                System.out.println(time.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
                break;
            case "date":
                System.out.println(time.format(DateTimeFormatter.ofPattern("DD-MM-yyyy")));
                break;
            case "dayOfWeek":
                System.out.println(time.getDayOfWeek());
                break;
            case "dayOfYear":
                System.out.println(time.getDayOfYear());
                break;
            case "daystillChristmass":
      /*          int daysTillChr = 359 - time.getDayOfYear();
                System.out.println(daysTillChr < 0 ? daysTillChr + 365 : daysTillChr);*/
                LocalDateTime lastDayOfYear = time.with(TemporalAdjusters.lastDayOfYear());
                break;
            default:
                break;
        }
    }

    public void printDatePattern(String pattern) {
        LocalDateTime time = getNowDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("");
        System.out.println(time.format(DateTimeFormatter.ofPattern(pattern)));
    }
}
