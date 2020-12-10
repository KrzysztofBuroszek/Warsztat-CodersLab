package pl.coderslab.Warsztat_1;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funcions extends New_Warsztat {

    private List<Task> list = new ArrayList<>();

    public int userChoice() {
        int result = 0;
        try {
            Scanner scanner = new Scanner(System.in);
            while (!scanner.hasNextInt()) {
                System.out.println("WRONG! GIVE THE NUMBER: ");
                scanner.next();
            }
            result = scanner.nextInt();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void startIn() {
        System.out.println();

        try {
            File files = new File("/Users/krzysztofburoszek/Desktop/Github CL/WARSZTAT/Warsztat-CodersLab/warsztat/Files/Zadania");
            Scanner scanner = new Scanner(files);
            while (scanner.hasNextLine()) {
                Task tasks = new Task(scanner.next(), scanner.next(), scanner.nextLine());
                list.add(tasks);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
            e.printStackTrace();
        }
        System.out.println();
    }

    public void showList() {
        System.out.println(ConsoleColors.BLUE + "LIST" + ConsoleColors.RESET);
        int counter = 0;
        for (Task list2 : list) {
            counter++;
            System.out.println(counter + "." + list2);

        }
    }

    public void removeAtList() {
        System.out.println();
        showList();
        System.out.print(ConsoleColors.BLUE + "PODAJ NUMER Z LISTY DO " + ConsoleColors.RED + "USUNIĘCIA:" + "\n" + ConsoleColors.RESET);
        Task number = list.get(userChoice() - 1);
        System.out.println(ConsoleColors.RED + "DELETED: " + ConsoleColors.RESET + number);
        list.remove(number);
    }

    public void save() {
        try {
            PrintWriter save = new PrintWriter("warsztat/Files/Zadania");
            for (int i = 0; i < list.size(); i++) {
                save.println(list.get(i));
            }
            save.close();
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
        }
        System.out.println(ConsoleColors.GREEN_BOLD + "LISTA ZAPISANA" + ConsoleColors.RESET);
    }

    public void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę zadania");
        String task = scanner.nextLine();

        System.out.println("Podaj datę wykonania");
        String date = scanner.nextLine();

        System.out.println("Czy zadanie jest ważne(T)");
        String importants = scanner.nextLine();

        String important;
        if (importants.equals("T")) {
            important = "WAŻNE";
        } else {
            important = " ";
        }

        Task task1 = new Task(task, date, important);
        showList();
        list.add(task1);
        System.out.println(ConsoleColors.BLUE + "Task add: " + ConsoleColors.RESET + task1);
        save();
    }

    public void calendar() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();
        Month nameMonth = date.getMonth();

        date = date.minusDays(today - 1);
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue();

        System.out.println(ConsoleColors.BLUE + " " + date.getMonth() + " " + date.getYear());
        System.out.println(" PN,WT,ŚR,CZ,PT,SO,ND" + ConsoleColors.RESET);
        for (int i = 1; i < value; i++) {
            System.out.print("   ");
        }
        while (date.getMonthValue() == month) {
            if (date.getDayOfMonth() != today) {
                System.out.printf("%3d", date.getDayOfMonth());
            } else {
                System.out.printf(ConsoleColors.BLUE + "%3d" + ConsoleColors.RESET, date.getDayOfMonth());
            }
            date = date.plusDays(1);

            if (date.getDayOfWeek().getValue() == 1)
                System.out.println();
        }
        if (date.getDayOfWeek().getValue() != 1)
            System.out.println();
        System.out.println();
    }






    public static void firstTape(Funcions funcions, User user, UserDao userDao) {
        funcions.startIn();
        String[] menu = {
                "LIST",
                "ADD",
                "REMOVE",
                "SAVE",
                "CALENDAR",
                "EXIT"};


        while (true) {
            choice(menu, funcions, user, userDao);
            System.out.println();
        }
    }

    protected static void choice(String[] menu, Funcions funcions, User user, UserDao userDao) {

        System.out.println(ConsoleColors.BLUE + "MENU WYBORU" + ConsoleColors.RESET);
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + 1 + "." + menu[i]);
        }
        System.out.println();

        System.out.print(ConsoleColors.BLUE + "\"ENTER THE ACTIVITY NUMBER: " + ConsoleColors.RESET);

        int userChoice = funcions.userChoice();
        switch (userChoice) {
            case 1 -> funcions.showList();
            case 2 -> funcions.add();
            case 3 -> funcions.removeAtList();
            case 4 -> funcions.save();
            case 5 -> funcions.calendar();
            case 6 -> start(funcions, user, userDao);
            default -> System.out.println(ConsoleColors.RED + "WRONG! GIVE THE NUMBER FROM 1 TO" + " " + menu.length + ConsoleColors.RESET);
        }
    }

    public static void readAllLines() { //testowe
        try{
            int counter = 0;
            ArrayList<String>zadania = (ArrayList<String>) Files.readAllLines(Paths.get("warsztat/Files/Zadania"), Charset.forName("UTF-8"));
            for (String s: zadania) {
                counter++;
                System.out.println(counter + " " + s);
            }
        }catch (IOException e) {
            System.out.println("uu");
        }

    }
}

