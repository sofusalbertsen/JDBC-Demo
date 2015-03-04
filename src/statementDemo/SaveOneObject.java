package statementDemo;

//JDBC demo
//save one object
//hau
import java.sql.*;
import java.util.Random;

import javax.swing.JOptionPane;


public class SaveOneObject {

    public static void main(String[] args) throws SQLException 
    {

        //== create a Person object
        int newId      = Integer.parseInt(JOptionPane.showInputDialog("Enter a number"));
        String newName = JOptionPane.showInputDialog("Enter a name");

        Person p = new Person(newId, newName);

        Statement statement 	= null;
        Connection connection 	= null;

        try 
        {  
           Class.forName(DB.driver);

           connection = DriverManager.getConnection(DB.URL, DB.ID, DB.PW);

           statement = connection.createStatement();

           String insertSQL = 	"INSERT INTO persons VALUES (" + 
                                   + p.getId() + ",'" + p.getName() + "')";
           //=== Execute the statement and retrieve 
           //	a count of how many rows was inserted      
           int rows = statement.executeUpdate(insertSQL);

           //=== Validate the result
           if (rows == 1) 
              System.out.println("One row inserted!");
           else
              System.out.println("No row inserted (fail)");
        }
        catch (Exception ee) 
        { 
          System.out.println("fail");
          System.err.println(ee); 
        }        
        finally
        {
          statement.close();
          connection.close(); 
        }        
    }
}

