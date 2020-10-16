import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class warsztat {

    public static void main(String[] args) {
        choice();
    }

    public static void choice() {
        String menu[] = {"Please select an option:", "add", "remove", "list", "exit"};
        for (int i = 0; i < menu.length; i++) {
            String men = menu[i];
            System.out.println(men);
        }

        Scanner choice1 = new Scanner(System.in);
        System.out.print("====> :");
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
                System.out.println("dokonaj popranego wyboru z listy");
        }
    }

    public static void list() {
        File files = new File("warsztat/Files/Task.csv");
        try {
            Scanner scanner = new Scanner(files);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
            e.printStackTrace();
        }
        choice();
    }

    public static void exit() {
        try {
            FileWriter exitt = new FileWriter("warsztat/Files/Task.csv", true);
            exitt.append(add());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String add() {
        System.out.print("Please add task description: ");
        Scanner add = new Scanner(System.in);
        String result = add.nextLine();
        System.out.println("You description is:" + "\"" + result + "\"");
        String[][] addd = {add.nextLine().split(",")};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println(addd[i][j]);
            }

        }


        choice();
        return result;
    }

    public static void remove() {
        int counter = 0;

        try {
            File files = new File("warsztat/Files/Task.csv");
            Scanner scanner = new Scanner(files);
            while (scanner.hasNextLine()) {
                String tymczasowa = scanner.nextLine();
                counter++;
            }
            int tymczasowa2 = scanner.nextLine().split(",").length;
            String tabW [][]= new String[counter][tymczasowa2];


        } catch (IOException e) {
            System.out.println("Lista jest pusta");
        }


    }
}
