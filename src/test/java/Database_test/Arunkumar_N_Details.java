package Database_test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Arunkumar_N_Details {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private HashMap<Long, String[]> employeeMap;

    @BeforeClass  // ✅ Runs before all test methods
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/acharya_erp", "root", "arun1234");
        Assert.assertNotNull(connection, "❌ Connection to the database failed.");
        System.out.println("✅ Successfully connected to the database.");

        statement = connection.createStatement();
        employeeMap = new HashMap<>();
    }

    @Test  // ✅ Actual test case
    public void testFetchAndValidateEmployeeDetails() throws SQLException {
        
        resultSet = statement.executeQuery("SELECT * FROM arunkumar_n.arunkumardetails;");

        System.out.println("\n📌 Fetching Employee Details from Database...\n");

        while (resultSet.next()) {
            // ✅ Declare inside loop to prevent overwriting across multiple rows
            String name = resultSet.getString("name");
            long phone = resultSet.getLong("phone");
            String skills = resultSet.getString("skills");
            int experience = resultSet.getInt("experience");
            String email = resultSet.getString("email");
            int age = resultSet.getInt("age");

            // ✅ Print employee details to console
            System.out.println("------------------------------------------------");
            System.out.println("👤 Name      : " + name);
            System.out.println("📞 Phone     : " + phone);
            System.out.println("💼 Skills    : " + skills);
            System.out.println("📅 Experience: " + experience + " years");
            System.out.println("📧 Email     : " + email);
            System.out.println("🎂 Age       : " + age);
            System.out.println("------------------------------------------------");

            // ✅ Store in HashMap
            employeeMap.put(phone, new String[]{name, String.valueOf(phone), skills, String.valueOf(experience), email, String.valueOf(age)});
        }

        // ✅ Ensure data was retrieved
        Assert.assertFalse(employeeMap.isEmpty(), "❌ No data fetched from database.");

        // ✅ Validate fetched data against stored data
        for (Map.Entry<Long, String[]> entry : employeeMap.entrySet()) {
            String[] actualData = entry.getValue();

            Assert.assertEquals(actualData[0], actualData[0], "❌ Name mismatch.");
            Assert.assertEquals(actualData[1], String.valueOf(entry.getKey()), "❌ Phone mismatch.");
            Assert.assertEquals(actualData[2], actualData[2], "❌ Skills mismatch.");
            Assert.assertEquals(actualData[3], actualData[3], "❌ Experience mismatch.");
            Assert.assertEquals(actualData[4], actualData[4], "❌ Email mismatch.");
            Assert.assertEquals(actualData[5], actualData[5], "❌ Age mismatch.");
        }

        System.out.println("\n✅ All assertions passed! Employee data validated successfully.\n");
    }

    @AfterClass  // ✅ Runs after all test methods
    public void tearDown() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (connection != null) connection.close();
        System.out.println("✅ Database connection closed.");
    }

    // ✅ Email Validation Method
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}
