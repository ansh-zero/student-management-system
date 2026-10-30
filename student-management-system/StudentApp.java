import java.sql.*;
import java.util.Scanner;

public class StudentApp {

    
    static final String URL = "jdbc:mysql://localhost:3306/student_db";
    static final String USER = "root";
    static final String PASSWORD = "your_password";
    

    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("MySQL se connect ho gaya!");
            createTable();

            while (true) {
                System.out.println("\n===== Student Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Search Student");
                System.out.println("6. Exit");
                System.out.print("Choice: ");
                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1": addStudent(); break;
                    case "2": viewStudents(); break;
                    case "3": updateStudent(); break;
                    case "4": deleteStudent(); break;
                    case "5": searchStudent(); break;
                    case "6":
                        conn.close();
                        System.out.println("Bye!");
                        return;
                    default: System.out.println("Galat choice!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS students ("
                   + "roll_no INT PRIMARY KEY, "
                   + "name VARCHAR(100) NOT NULL, "
                   + "course VARCHAR(50), "
                   + "phone VARCHAR(15))";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    static int readRoll(String msg) {
        System.out.print(msg);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Roll No number hona chahiye!");
            return -1;
        }
    }

    // CREATE
    static void addStudent() {
        int roll = readRoll("Roll No: ");
        if (roll < 0) return;
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Course: ");
        String course = sc.nextLine().trim();
        System.out.print("Phone: ");
        String phone = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name khali nahi ho sakta!");
            return;
        }

        String sql = "INSERT INTO students (roll_no, name, course, phone) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setString(3, course);
            ps.setString(4, phone);
            ps.executeUpdate();
            System.out.println("Student add ho gaya!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Ye Roll No pehle se hai!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // READ
    static void viewStudents() {
        String sql = "SELECT * FROM students ORDER BY roll_no";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            boolean found = false;
            System.out.printf("%-8s %-20s %-15s %-12s%n", "Roll", "Name", "Course", "Phone");
            System.out.println("-------------------------------------------------------");
            while (rs.next()) {
                found = true;
                System.out.printf("%-8d %-20s %-15s %-12s%n",
                        rs.getInt("roll_no"), rs.getString("name"),
                        rs.getString("course"), rs.getString("phone"));
            }
            if (!found) System.out.println("Koi record nahi hai.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // UPDATE
    static void updateStudent() {
        int roll = readRoll("Kis Roll No ko update karna hai? ");
        if (roll < 0) return;
        System.out.print("Naya Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Naya Course: ");
        String course = sc.nextLine().trim();
        System.out.print("Naya Phone: ");
        String phone = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name khali nahi ho sakta!");
            return;
        }

        String sql = "UPDATE students SET name=?, course=?, phone=? WHERE roll_no=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, course);
            ps.setString(3, phone);
            ps.setInt(4, roll);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Update ho gaya!" : "Roll No nahi mila.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // DELETE
    static void deleteStudent() {
        int roll = readRoll("Kis Roll No ko delete karna hai? ");
        if (roll < 0) return;
        String sql = "DELETE FROM students WHERE roll_no=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roll);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Delete ho gaya!" : "Roll No nahi mila.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // SEARCH
    static void searchStudent() {
        System.out.print("Naam likho (ya uska part): ");
        String key = sc.nextLine().trim();
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + key + "%");
            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.printf("Roll: %d | Name: %s | Course: %s | Phone: %s%n",
                            rs.getInt("roll_no"), rs.getString("name"),
                            rs.getString("course"), rs.getString("phone"));
                }
                if (!found) System.out.println("Koi student nahi mila.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}