package calculator;

import java.io.IOException;
import java.util.Random;

public class Calculator {

    public static final Random RANDOM = new Random();

    public int generateRandomA() {
        return RANDOM.nextInt();
    }

    public int generateRandomB() {
        return RANDOM.nextInt();
    }

    public static int generateStaticRandomA() {
        Random random = new Random();
        return random.nextInt();
    }

    public static int generateStaticRandomB() {
        Random random = new Random();
        return random.nextInt();
    }

    private int generatePrivateRandomA() {
        Random random = new Random();
        return random.nextInt(20);
    }

    private int generatePrivateRandomB() {
        Random random = new Random();
        return random.nextInt(20);
    }

    public int multiplyPublic() {
        return generateRandomA() * generateRandomB();
    }

    public double devidePublic() {
        return generateRandomA() / generateRandomB();
    }

    public int addPublic() {
        return generateRandomA() + generateRandomB();
    }

    public int subtractPublic() {
        return generateRandomA() - generateRandomB();
    }

    public int restPublic() {
        return generateRandomA() % generateRandomB();
    }

    public int multiplyPrivate() {
        return generatePrivateRandomA() * generatePrivateRandomB();
    }

    public double devidePrivate() {
        return generatePrivateRandomA() / generatePrivateRandomB();
    }

    public int addPrivate() {
        return generatePrivateRandomA() + generatePrivateRandomB();
    }

    public int subtractPrivate() {
        return generatePrivateRandomA() - generatePrivateRandomB();
    }

    public int restPrivate() {
        return generatePrivateRandomA() % generatePrivateRandomB();
    }

    public int multiplyStatic() {
        return generateStaticRandomA() * generateStaticRandomB();
    }

    public double devideStatic() {
        return generateStaticRandomA() / generateStaticRandomB();
    }

    public static int addStatic() {
        return generateStaticRandomA() + generateStaticRandomB();
    }

    public int subtractStatic() {
        return generateStaticRandomA() - generateStaticRandomB();
    }

    public int restStatic() {
        return generateStaticRandomA() % generateStaticRandomB();
    }

    public void print() {
        System.out.println("Hello, Donat");
    }

    public void throwException() throws IOException {
        throw new IOException("Don't do it");
    }

}
