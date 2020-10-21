package pl.coderslab;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class warsztat {

    public static void main(String[] args) {

        System.out.println(ConsoleColors.BLUE_BOLD + "WELCOME" + ConsoleColors.RESET);
        choice();
        new ConsoleColors();
        int count = 0;
    }

    public static void choice() {

        System.out.println(ConsoleColors.BLUE + "Pleace select number: " + ConsoleColors.RESET);
        String menu[] = {"1. to add", "2. to remove", "3. to list", /*"4. to exit"*/ };
        for (int i = 0; i < menu.length; i++) {
            String men = menu[i];
            System.out.println(men);
        }

        Scanner choice1 = new Scanner(System.in);
        System.out.print(ConsoleColors.BLUE + "====> :" + ConsoleColors.RESET);
        String choice = choice1.nextLine();
        switch (choice.toLowerCase()) {
            case "1":
                getIn();
                break;
            case "2":
                remove();
                break;
            case "3":
                list();
                break;
            case "4":
                exit();
                break;
            case "5":
//                listy Array;
            default:
                System.out.println(ConsoleColors.BLUE + "Make the correct selection from the list");
                System.out.println("----------------------------------------" + ConsoleColors.RESET);
                choice();
        }
    }

    public static void list() {
        System.out.println(ConsoleColors.BLUE + "TASKS" + ConsoleColors.RESET);
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
        choice();

    }

    public static void exit() {
//        try {
//            FileWriter list = new FileWriter("warsztat/Files/Task.csv", true);
//            list.append(add());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public static void getIn() {

//        int counter = 0;
        try {
            Scanner scannerAdd = new Scanner(System.in);
            System.out.println(ConsoleColors.BLUE + "Please, add task description");
            String description = scannerAdd.nextLine();
            System.out.println("Please,add task due date");
            String data = scannerAdd.nextLine();
            System.out.println("Is your task is important: true/false" + ConsoleColors.RESET);
            String tOrF = scannerAdd.nextLine();

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
                if ((b) < 3) {
                    docelowa[a][b] = tymczasowa[i];
                    b++;
                } else {
                    a++;
                    b = 0;
                }
            }
            for (int i = 0; i < docelowa.length; i++) {
                for (int j = 0; j < 3; j++) {
//                    System.out.println(docelowa[i][j] + "|");
                }
//                System.out.println("####################");
            }
            String add[][] = new String[docelowa.length+1][3];
            add[0][0] = description;
            add[0][1] = data;
            add[0][2] = tOrF;
            System.arraycopy(docelowa, 0, add, 1, docelowa.length);


            for (String[] s : add) {
//                System.out.println(Arrays.toString(s));
            }
            FileWriter fileWriter = new FileWriter("warsztat/Files/Task.csv");
            for (int i = 0; i < add.length; i++) {
                    fileWriter.write(add[i][0] + "," + add[i][1] + "," + add[i][2] + "," + '\n');
            }
            fileWriter.close();
            choice();

        } catch (
                NoSuchElementException e) {
            System.out.println("Task is empty");
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }


    public static void remove() {
        System.out.print(ConsoleColors.BLUE + "REMOVE " + ConsoleColors.RESET);
        System.out.println();
        int counter2 = 0;
        File fil = new File("warsztat/Files/Task.csv");
        try {
            Scanner scanner2 = new Scanner(fil);
            while (scanner2.hasNextLine()) {
                counter2++;
                System.out.println(counter2 + "." + " " + scanner2.nextLine());
            }
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
                int counter = (int) Files.lines(Paths.get("warsztat/Files/Task.csv")).count();
                String old[] = new String[counter];
                String yong[] = new String[old.length];
                Scanner scanner = new Scanner(files);
                while (scanner.hasNextLine()) {

                    for (int i = 0; i < old.length; i++) {
                        old[i] = scanner.nextLine();
                        FileWriter fileWriter = new FileWriter("warsztat/Files/Task.csv");
                        for (int j = 0; j < old.length; j++) {
                            if (j != (number - 1)) {
                                yong[j] = old[i];
                                fileWriter.write(old[j] + '\n');
                            } else {
                                yong[j] = ConsoleColors.RED + "delete" + ConsoleColors.RESET;
                            }


                        }
//                        System.out.println(yong[i]);
                        fileWriter.close();
                    }list();
                }


            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();

            } catch (NoSuchElementException e) {
                System.out.println();
                System.out.println(ConsoleColors.BLUE + "All line read, task delete. " + ConsoleColors.RESET);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }choice();
    }
}
