package Database_test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class Test 
{

	public static void main(String[] args) throws SQLException 
	{
		
	  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/acharya_erp", "root", "root");
	  
	    if (connection != null && !connection.isClosed())
	    {
            System.out.println("✅ Successfully connected to the database.");
        }
	    else 
	    {
            System.out.println("❌ Failed to connect to the database.");
            return;
        }
	  
	    HashMap<Integer, String[]> employeeMap = new HashMap<>();
		        
	  Statement statement = connection.createStatement();
	  ResultSet resultSet = statement.executeQuery("SELECT created_username,emp_type,active  FROM acharya_erp.employee_type where emp_type_id =2;");
	  
	  while(resultSet.next()) {
		    int active = resultSet.getInt("active");
		   String created_username = resultSet.getString("created_username");
		   String emp_type = resultSet.getString("emp_type");
		   
		   employeeMap.put(active, new String[]{created_username, emp_type});
		}
	  
	  for (Map.Entry<Integer, String[]> entry : employeeMap.entrySet()) {
          System.out.println("active: " + entry.getKey() +  ", Created Username: " + entry.getValue()[0] + ", Employee Type: " + entry.getValue()[1]);
      }
	  
	  System.out.println(employeeMap.get(resultSet));
	 
	}
}
