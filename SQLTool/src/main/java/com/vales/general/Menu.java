package com.vales.general;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

class Menu {
    private Menu() {
    }

    private static String query;
    private static final Logger LOGGER = LogManager.getLogger(Menu.class);
    private static boolean more=true;
    private static final String MAIN_MENU = "\n" +
            "1) Show available tables \n" +
            "2) Select information from available table\n" +
            "3) Insert custom query\n" +
            "0) Exit\n";
    private static final String TABLE_MENU = "\n" +
            "1) Select all from objects \n" +
            "2) Select all from attrtype\n" +
            "3) Select all from objtype\n" +
            "4) Select all from attributes\n" +
            "5) Select all from objreference\n" +
            "9) Back to main menu\n" +
            "0) Exit\n" ;

    public static boolean isMore() {
        return more;
    }
    private static void expectQuery(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your query ");
        query = scanner.nextLine();
        }
    private static void showMainMenu() {
        System.out.println( MAIN_MENU +
                "Selection: ");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        switch (option) {
            case 1:
                query = "select table_name from all_tables where owner = 'JAVA'";
                break;

            case 2:
                showTableMenu();
                break;

            case 3:
                expectQuery();
                break;
            case 0:
                more = false;
                break;

            default:
                System.err.println("Wrong option " + option);
                LOGGER.warn("Wrong option " + option +
                        "from next options :" +
                      MAIN_MENU);
                break;
        }
    }
    private static void showTableMenu() {
        System.out.println(TABLE_MENU+
                "Selection: ");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        switch (option) {
            case 1:
                query = "select * from objects";
                break;
            case 2:
                query = "select * from attrtype";
                break;
            case 3:
                query = "select * from objtype";
                break;
            case 4:
                query = "select * from attributes";
                break;
            case 5:
                query = "select * from objreference";
                break;
            case 9:
                showMainMenu();
                break;
            case 0:
                more = false;
                break;
            default:
                System.err.println("Wrong option " + option);
                LOGGER.warn("Wrong option " + option +
                        "from next options :" +
                       TABLE_MENU);
                break;
        }
    }
    public static String getQueryFromCustomer() {
        showMainMenu();
        return more?query:"";
    }

}