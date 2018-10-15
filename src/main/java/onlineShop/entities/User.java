package onlineShop.entities;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String login;

    private String name;

    private String password;

    private List<Ware> cart;

    public User(String login, String pswd, String name) {
        this.login = login;
        this.password = pswd;
        this.name = name;
        cart = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Ware> getCart() {
        return cart;
    }


}
