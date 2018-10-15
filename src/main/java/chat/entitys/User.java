package chat.entitys;

import java.time.LocalDate;

public class User {
    private String login;
    private String password;
    private String username;
    private LocalDate dateOfregistration;

    private Status status;

    public User(String login, String password, String username) {
        this.login = login;
        this.password = password;
        this.username = username;
        dateOfregistration = LocalDate.now();
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDateOfregistration() {
        return dateOfregistration;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
