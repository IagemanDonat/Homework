package chat.entitys;

import java.time.Instant;
import java.time.LocalTime;

public class Message {

    private User from;

    private User to;

    private String text;

    private LocalTime time;

    private byte[] file;

    public Message(User from, User to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
        time = LocalTime.from(Instant.now());
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public LocalTime getTime() {
        return time;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
