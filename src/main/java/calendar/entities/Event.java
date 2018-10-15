package calendar.entities;


import calendar.exec.Executor;

import java.time.LocalDateTime;

public class Event {

    private LocalDateTime date;

    private String name;

    public Event(String name, LocalDateTime date){
        this.name = name;
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
//        System.out.println(LocalDateTime.now());

        new Executor().printLocalTime("America/New_York");
    }
}
