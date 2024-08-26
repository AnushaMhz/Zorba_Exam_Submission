package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExecutionOfPropertyApplication {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        Property rentOfOneBHkFlat = (Property) context.getBean("rentOfOneBHKFlat");
        rentOfOneBHkFlat.displayPropertyDetails();

        Property rentOfTwoBHKFlat = (Property) context.getBean("rentOfTwoBHKFlat");
        rentOfTwoBHKFlat.displayPropertyDetails();

        Property rentOfThreeBHKFlat = (Property) context.getBean("rentOfThreeBHKFlat");
        rentOfThreeBHKFlat.displayPropertyDetails();

        Property selfOwnedOneBHKFlat = (Property) context.getBean("selfOwnedOneBHKFlat");
        selfOwnedOneBHKFlat.displayPropertyDetails();

        Property selfOwnedTwoBHKFlat = (Property) context.getBean("selfOwnedTwoBHKFlat");
        selfOwnedTwoBHKFlat.displayPropertyDetails();

        Property selfOwnedThreeBHKFlat = (Property) context.getBean("selfOwnedThreeBHKFlat");
        selfOwnedThreeBHKFlat.displayPropertyDetails();


    }
}
