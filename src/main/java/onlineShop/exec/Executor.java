package onlineShop.exec;

import onlineShop.WareInterface;
import onlineShop.entities.User;
import onlineShop.entities.Ware;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Executor {

    private Map<Ware, Integer> shop;
    private Set<User> userBase;


    public Executor() {
        shop = new HashMap<>();
        userBase = new HashSet<>();
    }

    public void addWare(String name, String manufacturer, Double price, String category, String subCategory) {
        Ware ware = new Ware(name, manufacturer, LocalDate.now(), price, category, subCategory);
        shop.putIfAbsent(ware, 0);
        shop.put(ware, shop.get(ware) + 1);//Map's merge
    }


    public List<Ware> findComplimentaryWares(Ware ware) {
        return shop.keySet().stream().filter(key -> key.hasCharacteristics(ware) &&
                !key.equals(ware)).collect(Collectors.toList());


    }

    public List<Ware> findComplimentaryWaresWithParams(Ware ware, String... args) {
        List<Ware> res = new ArrayList<>();
        shop.forEach((key, value) -> {
            if (key.hasCharacteristics(ware, args) && !key.equals(ware)) {
                res.add(key);
            }
        });
        return res;
    }


    public void createUser(String login, String pswd, String name) {
        User newUser = new User(login, pswd, name);

        userBase.add(newUser);
    }

    public void delWare(Ware ware) {
        shop.put(ware, shop.get(ware) - 1);//merge

        shop.entrySet().removeIf(entry -> entry.getValue() <= 0);

    }

    public void loadCart(User user, Ware ware) {
        user.getCart().add(ware);
    }

    public Double cartCashout(User user) {

        WareInterface counter = userArg ->
                user.getCart().stream().mapToDouble(Ware::getPrice).sum();

        return counter.count(user);
    }

    public Ware searchWare(String... args) {
        Ware[] foundWare = {new Ware()};

        shop.forEach((Ware ware, Integer value) -> {

            boolean present = Arrays.stream(args)
                    .anyMatch(searcWord -> searcWord
                            .equals(ware.getName()) ||
                            searcWord.equals(ware.getManufacturer()) ||
                            searcWord.equals(String.valueOf(ware.getPrice())));
            if (present) {
                foundWare[0] = ware;
            }
        });

        return foundWare[0];
    }
}
