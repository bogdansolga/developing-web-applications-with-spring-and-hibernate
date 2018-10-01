package net.safedata.spring.training.d02.s01;

import net.safedata.spring.training.d02.s01.beans.HelloSpring;
import net.safedata.spring.training.d02.s01.config.DemoConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A first demo for the usage of a few simple Spring {@link org.springframework.context.annotation.Bean}s, using
 * annotations based configuration
 *
 * @author bogdan.solga
 */
@SuppressWarnings("unused")
public class AnnotationConfigSpringDemo {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoConfig.class);

        retrievingBeansByType(applicationContext);

        //retrievingBeansByTypeAndID(applicationContext);
    }

    private static void retrievingBeansByType(final ApplicationContext applicationContext) {
        // 'give me the bean (object) of type HelloSpring from the application context'
        final HelloSpring helloSpring = applicationContext.getBean(HelloSpring.class);
        helloSpring.displayWelcomeMessage();

        // 'give me the bean (object) of type String from the application context'
        //final String stringBean = applicationContext.getBean(String.class);
        //System.out.println(stringBean);

        //Boolean theBoolean = applicationContext.getBean(Boolean.class);
        //System.out.println("The value is " + theBoolean);
    }

    private static void retrievingBeansByTypeAndID(final ApplicationContext applicationContext) {
        // retrieving a bean by it's type and ID
        final HelloSpring helloSpring = applicationContext.getBean("helloSpring", HelloSpring.class);
        helloSpring.displayWelcomeMessage();
    }
}
