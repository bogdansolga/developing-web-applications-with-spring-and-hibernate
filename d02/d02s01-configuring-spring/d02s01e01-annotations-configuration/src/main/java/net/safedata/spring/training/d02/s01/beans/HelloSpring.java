package net.safedata.spring.training.d02.s01.beans;

import org.springframework.context.annotation.Bean;

/**
 * A minimal example of a simple Spring {@link Bean}
 *
 * @author bogdan.solga
 */
public class HelloSpring {

    public void displayWelcomeMessage() {
        System.out.println("Hello, Spring! [using annotations based config]");
    }
}
