package pl.coderslab.entity;

import pl.coderslab.Warsztat_1.ConsoleColors;
import pl.coderslab.Warsztat_1.Funcions;
import pl.coderslab.Warsztat_OPP_SQL.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDao extends User {

    private static final String ALL_USERS_QUERY = "SELECT *FROM users;";

    private static final String CREATE_USER_QUERY = "INSERT INTO users (email, username, password) VALUES (?, ?, ?);";

    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";

    private static final String MODYFICATE_USER_QUERY = "UPDATE users SET  username = ?, email = ?, password = ? WHERE id = ?;";

    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?;";

    private static List<User> users = new ArrayList<>();


    public static void allUsers() {
        int counter = 0;

        try (Connection conn = DbUtil.getConnection();
             Statement stat = conn.createStatement()) {
            ResultSet rs = stat.executeQuery(ALL_USERS_QUERY);
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                counter++;
                System.out.println(String.format(counter + "    ID: %d" + "   " + "USERNAME: %s" + " " + "MAIL: %s: ", id, userName, email));
            }
            System.out.println("All users: " + counter);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List findAll() {

        try (Connection conn = DbUtil.getConnection();
             Statement stat = conn.createStatement()) {
            ResultSet rs = stat.executeQuery(ALL_USERS_QUERY);
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(id, email, userName, password);
                users.add(user);
//                for (User s: users) {
//                    System.out.println(s);
                return users;
//                }
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

//        for (int i = 0; i < users.size(); i++) {
//            System.out.println(users);
//        }
        return null;
    }

    public static User create(User user) {

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(2, user.getUserName());
            statement.setString(1, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword())); //hashPassword
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String scannerString() {
        Scanner scanner = new Scanner(System.in);
        String dane = scanner.nextLine();
        return dane;
    }

    public static String hashPassword(String password) {

//        String hashed = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
        String hashed = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt(12));
        if (org.mindrot.jbcrypt.BCrypt.checkpw(password, hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");


        return org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }

    public static User read(int userId) {
        User user = new User();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stat = conn.prepareStatement(READ_USER_QUERY);
            stat.setInt(1, userId);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
//                System.out.println(id + " " + email + " " + userName + " " + password);
                User userDao = new User(id, email, userName, password);
                return userDao;
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static void update() {
        Funcions funcions = new Funcions();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement prepStm = conn.prepareStatement(MODYFICATE_USER_QUERY);
//            prepStm.setInt(1, user.getId());
            System.out.println("ID TO MODYFICATION");
            prepStm.setInt(4, funcions.userChoice());
            System.out.println("NEW NAME");
            prepStm.setString(1, scannerString());
            System.out.println("NEW EMAIL");
            prepStm.setString(2, scannerString());
            System.out.println("NEW PASSWORD");
            prepStm.setString(3, hashPassword(scannerString()));
            prepStm.executeUpdate();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void delete() {
        System.out.println("ENTER THE DELETE NUMBER: ");
        Funcions funcions = new Funcions();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stat = conn.prepareStatement(DELETE_USER_QUERY);
            stat.setInt(1, funcions.userChoice());
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static User create2(User user) {

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            System.out.println("NAME: ");
            statement.setString(2, scannerString());
            System.out.println("EMAIL");
            statement.setString(1, scannerString());
            System.out.println("PASSWORD");
            statement.setString(3, hashPassword(scannerString()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void secendTape(Funcions funcions, User user, UserDao userDao) {
        String[] menu2 = {
                "USERS",
                "CREATE",
                "DELETE",
                "MODYFICATIONS",
                "EXIT",
                "USERS COPY TO TABLE -- do srobienia"
        };

        while (true) {
            choiceSQL(menu2, funcions, user, userDao);
        }
    }

    protected static void choiceSQL(String[] menu2, Funcions funcions, User user, UserDao userDao) {
        System.out.println(ConsoleColors.BLUE + "MENU WYBORU" + ConsoleColors.RESET);
        for (int i = 0; i < menu2.length; i++) {
            System.out.println(i + 1 + "." + menu2[i]);
        }

        System.out.println();

        System.out.print(ConsoleColors.BLUE + "\"ENTER THE ACTIVITY NUMBER: " + ConsoleColors.RESET);

        int userChoice = funcions.userChoice();
        switch (userChoice) {
            case 1 -> allUsers();
            case 2 -> create2(user);
            case 3 -> delete();
            case 4 -> update();
            case 5 -> start(funcions, user, userDao);
//            case 6 -> UserDao.read();
            default -> System.out.println(ConsoleColors.RED + "WRONG! GIVE THE NUMBER FROM 1 TO" + " " + menu2.length + ConsoleColors.RESET);
        }
    }
}
