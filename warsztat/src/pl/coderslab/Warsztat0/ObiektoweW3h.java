package pl.coderslab.Warsztat0;

import pl.coderslab.Warsztat_OPP_SQL.DbUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class ObiektoweW3h {


    public static void main(String[] args) {
        int n = getInt();
        creatIteams();
    }

    public static int getInt() {
        System.out.println("podaj liczbę");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("zła wartość");
            scanner.next();
        }
        int getInd = scanner.nextInt();
        scanner.close();
        return getInd;
    }

    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        String getString = scanner.nextLine();
        scanner.close();
        return getString;
    }

    protected static final String CREATE_ITEAMS_SQL ="INSERT INTO users (email, username, password) VALUES (?, ?, ?);";

    public static void creatIteams() {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_ITEAMS_SQL);
            System.out.println("mail");
            statement.setString(1, getString());
            System.out.println("name");
            statement.setString(2, getString());
            System.out.println("password");
            statement.setString(3, getString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void fileWrit() throws IOException {
        FileWriter fileWriter = new FileWriter("sobota.txt");

    }

}
