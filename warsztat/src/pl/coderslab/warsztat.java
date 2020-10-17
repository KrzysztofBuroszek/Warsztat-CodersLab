package pl.coderslab;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class warsztat {

    public static void main(String[] args) {
//        remove();
        getIn();
        System.out.println(ConsoleColors.BLUE_BOLD + "WELCOME" + ConsoleColors.RESET);
        choice();
        new ConsoleColors();
    }

    public static void choice() {

        System.out.println(ConsoleColors.BLUE + "Pleace select an option: " + ConsoleColors.RESET);
        String menu[] = {"add", "remove", "list", "exit"};
        for (int i = 0; i < menu.length; i++) {
            String men = menu[i];
            System.out.println(men);
        }

        Scanner choice1 = new Scanner(System.in);
        System.out.print(ConsoleColors.BLUE + "====> :" + ConsoleColors.RESET);
        String choice = choice1.nextLine();
        switch (choice) {
            case "add":
                add();
                break;
            case "remove":
                remove();
                break;
            case "list":
                list();
                break;
            case "exit":
                exit();
            default:
                System.out.println(ConsoleColors.BLUE + "Make the correct selection from the list");
                System.out.println("----------------------------------" + ConsoleColors.RESET);
                choice();
        }
    }

    public static void list() {
        int counter = 0;
        File files = new File("warsztat/Files/Task.csv");
        try {
            Scanner scanner = new Scanner(files);
            while (scanner.hasNextLine()) {
                counter++;
                System.out.println(counter + "." + " " + scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
            e.printStackTrace();
        }
//        choice();
    }

    public static void exit() {
//        try {
//            FileWriter list = new FileWriter("warsztat/Files/Task.csv", true);
//            list.append(add());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void add() {
        getIn();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task due date");
        String dueDate = scanner.nextLine();
        System.out.println("Is your task is important: true/false");
        String isImportant = scanner.nextLine();





        choice();
    }

    public static void getIn() {
        int counter = 0;
        try {
            File files = new File("warsztat/Files/Task.csv");
            Scanner scanner = new Scanner(files);
            String inputString = "";
            while (scanner.hasNextLine()) {
                inputString = scanner.nextLine() + "," + inputString;
            }
            String[] tymczasowa = inputString.split(",");
            for (String s : tymczasowa) {
//                System.out.println(s);
            }
            int a = 0;
            int b = 0;
            int rows = (tymczasowa.length / 3);
            String[][] docelowa = new String[rows][3];
            for (int i = 0; i < tymczasowa.length; i++) {
                if ((b) < 2) {
                    docelowa[a][b] = tymczasowa[i];
                    b++;
                } else {
                    a++;
                    b = 0;
                }
            }
            for (int i = 0; i < docelowa.length; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(docelowa[i][j] + "|");
                }
                System.out.println();
            }
        } catch (
                NoSuchElementException e) {
            System.out.println("Task is empty");
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
//        int counter = 0;
//
//        try {
//            File files = new File("warsztat/Files/Task.csv");
//            Scanner scanner = new Scanner(files);
//            while (scanner.hasNextLine()) {
//                String tym = scanner.nextLine();
//                counter++;
//            }
//            String[] tymczasowa = scanner.nextLine().split(",");
//            int a = 0;
//            int b = 0;
//            String tabW[][] = new String[counter][tymczasowa.length];
//            for (int i = 0; i < counter; i++) {
//                if (b < 2) {
//                    tabW[a][b] = tymczasowa[i];
//                    b++;
//                } else {
//                    a++;
//                    b = 0;
//                }
//            }
//
//            for (int i = 0; i < tabW.length; i++) {
//                for (int j = 0; j < 3; j++) {
//                    System.out.println(tabW[i][j]);
//                }
//            }
//
//        } catch (NoSuchElementException e) {
//            System.out.println("Lista jest pusta");
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void remove() {
        System.out.println(ConsoleColors.BLUE + "Remove list:" + ConsoleColors.RESET);
        list();
        try {
            System.out.println(ConsoleColors.BLUE + "Which task number to delete?");
            System.out.print("====> :" + ConsoleColors.RESET);
            Scanner choice1 = new Scanner(System.in);
            while (!choice1.hasNextInt()) {
                choice1.next();
                System.out.print(ConsoleColors.BLUE + "Incorrect data enter, please choose a number");
                System.out.print("====> :" + ConsoleColors.RESET);

            }
            int number = choice1.nextInt();
            File files = new File("warsztat/Files/Task.csv");
            String old[] = new String[4]; // dopisać czytanie ilości wierszy

            Scanner scanner = new Scanner(files);
            while (scanner.hasNextLine()) {
                for (int i = 0; i < old.length; i++) {
                    old[i] = scanner.nextLine();
                    String yong[] = new String[old.length];
                    for (int j = 0; j < old.length; j++) {
                        if (j != (number - 1)) {
                            yong[j] = old[i];
                        } else {
                            yong[j] = ConsoleColors.RED +"delete" + ConsoleColors.RESET;
                        }


                    }
//                    System.out.println(yong[i]);
                    Arrays.sort(yong);
                    System.out.println(yong[i]);
                }

            }
        } catch (NoSuchElementException e) {
            System.out.println();
            System.out.println("(Wyjątek brak lini 191");
        }catch (IOException e) {
            e.printStackTrace();
        }


    }
}
