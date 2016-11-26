package javax.xml.bind;

import org.astro.World;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.sun.xml.internal.bind.v2.ContextFactory");
        System.out.format("Greetings %s!\n", World.name());
    }
}
