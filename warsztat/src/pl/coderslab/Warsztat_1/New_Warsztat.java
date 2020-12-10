package pl.coderslab.Warsztat_1;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.util.Scanner;

public class New_Warsztat {
    public static void main(String[] args) {
        Funcions funcions = new Funcions();
        UserDao userDao = new UserDao();
        User user = new User();
        funcions.calendar();
        start(funcions, user, userDao);

    }

    protected static void start(Funcions funcions, User user, UserDao userDao) {
        System.out.print(ConsoleColors.BLUE + "MENU WYBORU:" + ConsoleColors.RESET + "\n" +
                "1. TASC" + "\n" +
                "2. USERS" + "\n" +
                ConsoleColors.BLUE + "ENTER THE NUMBER: " + ConsoleColors.RESET);


        int userChoice = funcions.userChoice();
        switch (userChoice) {
            case 1 -> funcions.firstTape(funcions, user, userDao);
            case 2 -> userDao.secendTape(funcions, user, userDao);
            default -> System.out.print(ConsoleColors.RED + "WRONG! GIVE THE NUMBER FROM 1 TO 2" + ConsoleColors.RESET);
        }
    }


}
