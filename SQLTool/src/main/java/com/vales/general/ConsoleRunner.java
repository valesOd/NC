package com.vales.general;

import com.vales.general.DAO.DAO;
import com.vales.general.Menu.Menu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ConsoleRunner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        //TODO do without cast
        DAO dao = (DAO) context.getBean("objectDao");

        //TODO if do - while(true) we can delete variable "more"
        while (Menu.isMore()) {

            dao.executeQuery(Menu.getQuery());

        }
    }
}