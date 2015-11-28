package com.vales.general.Menu;

import com.vales.general.Query.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Menu {
    static {
        mainMenu = new HashMap();
        initializationMainMenu();
    }

    private static void initializationMainMenu() {
        mainMenu.put(1, Query.SELECT_OBJECTS);
        mainMenu.put(2, Query.SELECT_ATTRTYPE);
        mainMenu.put(3, Query.SELECT_OBJTYPE);
        mainMenu.put(4, Query.SELECT_ATTRIBUTES);
        mainMenu.put(5, Query.SELECT_OBJREFERENCE);
        mainMenu.put(6, Query.CUSTOM_QUERY);
    }

    private static Map<Integer, Query> mainMenu;
    private static final Logger LOGGER = LogManager.getLogger(Menu.class);
    private static boolean more = true;
    private static final String MAIN_MENU = "\n" +
            "1) Select all from objects \n" +
            "2) Select all from attrtype\n" +
            "3) Select all from objtype\n" +
            "4) Select all from attributes\n" +
            "5) Select all from objreference\n" +
            "6) Insert custom query\n" +
            "0) Exit\n";

    public static boolean isMore() {
        return more;
    }

    public static Query getQuery() {
        return showMainMenu();
    }

    private static Query showMainMenu() {
        System.out.println(MAIN_MENU +
                "Selection: ");
        Scanner in = new Scanner(System.in);
        Integer option = in.nextInt();
        if (mainMenu.containsKey(option))
            return mainMenu.get(option);
        else if (option == 0)
                System.exit(0);
        System.err.println("Wrong option " + option);
        LOGGER.warn("Wrong option " + option +
                "from next options :" +
                MAIN_MENU);
        return showMainMenu();

    }



}