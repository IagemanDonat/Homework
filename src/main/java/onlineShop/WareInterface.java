package onlineShop;

import onlineShop.entities.User;

@FunctionalInterface
public interface WareInterface {

    public abstract Double count(User user);
}
