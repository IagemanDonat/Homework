package chat.executor;

import chat.entitys.Message;
import chat.entitys.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class ChatExecutor {

    private List<Message> messages;
    private Set<User> users;

    public void sendMessages(String text) {
        User user1 = new User("user1", "password1", "Josh");

        User user2 = new User("user2", "password2", "Bob");

        Message newMessage = new Message(user1, user2, text);

        printMessage(newMessage);
    }

    public void printLogPeriod(LocalTime from, LocalTime to) {
        messages.stream().filter(message ->
                message.getTime().isAfter(from) && message.getTime().isBefore(to))
                .forEach(this::printMessage);
    }

    private void printMessage(Message message) {
        StringBuilder messageText = new StringBuilder(message.getTime().toString())
                .append(message.getFrom())
                .append("->")
                .append(message.getTo())
                .append(":")
                .append(message.getText());
        System.out.println(messageText);
    }

    public void printLogAll() {
        messages.forEach(this::printMessage);
    }

    public void printUserInfo(User user) {
        StringBuilder userInfo = new StringBuilder(user.getUsername())
                .append(" ")
                .append(user.getDateOfregistration())
                .append(" ")
                .append(user.getStatus());
        System.out.println(userInfo);
    }

    public void sendFile(Path path, Message message) throws IOException {
        message.setFile(Files.readAllBytes(path));
    }

    public void recieveFile(Path path, Message message) throws IOException {
        FileOutputStream fileOS = new FileOutputStream(path.toFile());
        fileOS.write(message.getFile());
    }

    public void registerNewUser(String login, String password, String username) {
        users.add(new User(login, password, username));
    }

}
