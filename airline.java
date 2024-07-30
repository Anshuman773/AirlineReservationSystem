import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Passenger {
    String passport;
    String name;
    int seatNum;
    String email;
}

public class airline{
    static final int MAX_PASSENGERS = 15;
    static Passenger[] passengers = new Passenger[MAX_PASSENGERS];
    static int num = 0;

    static Scanner scanner = new Scanner(System.in);
    static Connection conn = null;

    // JDBC connection parameters
    static final String DB_URL = "jdbc:oracle:thin:@//your_oracle_server_address:1521/XE";
    static final String DB_USER = "SYSTEM";
    static final String DB_PASSWORD = "2004";

    static void establishConnection() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    static void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void createTable() {
        try {
            establishConnection();
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE Passenger (" +
                            "passport VARCHAR2(20) PRIMARY KEY," +
                            "name VARCHAR2(100)," +
                            "email VARCHAR2(100)," +
                            "seat_num INT" +
                         ")";
            stmt.executeUpdate(sql);
            stmt.close();
            closeConnection();
            System.out.println("Passenger table created successfully.");
        } catch (SQLException e) {
            // If the table already exists, catch the exception silently
        }
    }

    static void reserve() {
        if (num >= MAX_PASSENGERS) {
            System.out.println("\n\t\t Seat Full.\n");
            return;
        }

        Passenger passenger = new Passenger();
        System.out.print("\n\t Enter your passport number: ");
        passenger.passport = scanner.next();
        System.out.print("\n\t Enter your name: ");
        passenger.name = scanner.next();
        System.out.print("\n\t Enter your email address: ");
        passenger.email = scanner.next();

        passenger.seatNum = ++num;
        passengers[num - 1] = passenger;

        // Insert passenger into the database
        try {
            establishConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO Passenger (passport, name, email, seat_num) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, passenger.passport);
            pstmt.setString(2, passenger.name);
            pstmt.setString(3, passenger.email);
            pstmt.setInt(4, passenger.seatNum);
            pstmt.executeUpdate();
            pstmt.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n\t Seat booking successful!");
        System.out.println("\n\t Your seat number is: Seat A-" + passenger.seatNum);
    }

    static void cancel() {
        System.out.print("\n\n Enter passport number to delete record: ");
        String passport = scanner.next();

        // Delete passenger from the database
        try {
            establishConnection();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Passenger WHERE passport = ?");
            pstmt.setString(1, passport);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking has been deleted.");
                num--;
            } else {
                System.out.println("Passport number is wrong. Please check your passport.");
            }
            pstmt.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void display() {
        // Retrieve and display passengers from the database
        try {
            establishConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Passenger");
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\n\n Passenger Details:\n");
            while (rs.next()) {
                Passenger passenger = new Passenger();
                passenger.passport = rs.getString("passport");
                passenger.name = rs.getString("name");
                passenger.email = rs.getString("email");
                passenger.seatNum = rs.getInt("seat_num");
                passengers[num++] = passenger;
                System.out.println("\n\n Passport Number : " + passenger.passport);
                System.out.println("         Name : " + passenger.name);
                System.out.println("      Email Address: " + passenger.email);
                System.out.println("      Seat Number: A-" + passenger.seatNum);
                System.out.println("\n\n++*=====================================================*++");
            }
            rs.close();
            pstmt.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Load the Oracle JDBC driver
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        // Create the Passenger table if it doesn't exist
        createTable();

        int choice;
        do {
            System.out.println("\n\n\t\t ********************************************************************");
            System.out.println("\n\t\t                   Welcome to Airline Reservation System                   ");
            System.out.println("\n\t\t   ********************************************************************");
            System.out.println("\n\n\n\t\t Please enter your choice from below (1-4):");
            System.out.println("\n\n\t\t 1. Reservation");
            System.out.println("\n\n\t\t 2. Cancel");
            System.out.println("\n\n\t\t 3. DISPLAY RECORDS");
            System.out.println("\n\n\t\t 4. EXIT");
            System.out.println("\n\n\t\t Feel free to ask us");
            System.out.print("\n\n\t\t Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reserve();
                    break;
                case 2:
                    cancel();
                    break;
                case 3:
                    display();
                    break;
                case 4:
                    System.out.println("\n\n\t Exiting the system.");
                    break;
                default:
                    System.out.println("\n\n\t SORRY INVALID CHOICE!");
                    System.out.println("\n\n\t PLEASE CHOOSE FROM 1-4");
                    System.out.println("\n\n\t Do not forget to choose from 1-4");
            }
        } while (choice != 4);
    }
}
