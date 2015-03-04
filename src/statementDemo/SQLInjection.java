/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statementDemo;

//=== Import the JDBC classes
import java.sql.*;
import java.util.Scanner;

// JDBC demo
// Demonstrate SQL injection
// "log on" entering id and pw as follows:
// 		id: a valid id   2(ex: 1)
// 		pw: ' or 1=1 --   (including the apostrophe and the two hyphens)
//
// hau
public class SQLInjection {


  public static void main(String[] args) throws SQLException
  {
      ResultSet rs 		= null;
      Statement statement 	= null;
      Connection connection 	= null;
      
      try 
      {  

        Class.forName(DB.driver);
         connection = DriverManager.getConnection(DB.URL, DB.ID, DB.PW);      
        statement = connection.createStatement();
        
        //=== prompt the user for id and pw
        System.out.println("Enter id: ");
        int id = new Scanner(System.in).nextInt();
        System.out.println("Enter name: ");
        String pw = new Scanner(System.in).nextLine();
        
        
        //=== Build an SQL-query-statement
        //    that searches for matching rows in the persons table
        String query = "SELECT * FROM persons " +
        			   "WHERE id = " + id + " AND name = '" + pw + "'";
        System.out.println("The complete SQL command:    " + query);
        
        //=== Execute the query and receive the result
        rs = statement.executeQuery(query);
        
        //=== read the result
        if (rs.next()) 
        	System.out.println("logged on!");
        else
        	System.out.println("Wrong id or password");
      }
      catch (Exception ee) 
      { 
        System.out.println("fail");
        System.err.println(ee); 
      }        
      finally
      {
        rs.close();
        statement.close();
        connection.close(); 
      }        
  }

}


