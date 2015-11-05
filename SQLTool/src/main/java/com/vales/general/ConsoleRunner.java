package com.vales.general;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ConsoleRunner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        DAO dao = (DAO) context.getBean("objectDao");

        while (Menu.isMore()) {

            dao.executeQuery(Menu.getQueryFromCustomer());


        }
    }
}