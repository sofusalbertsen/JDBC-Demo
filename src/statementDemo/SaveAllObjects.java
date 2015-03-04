package statementDemo;

//JDBC demo
//save all object in a list
//using simple statement object
//hau
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


public class SaveAllObjects {

    public static void main(String[] args) throws SQLException {
		
        //=== make a list of test objects
        ArrayList<Person> pl = fillListWithObjects();

        Statement statement 	= null;
        Connection connection 	= null;
        
        try 
        {  
           Class.forName(DB.driver);
           connection = DriverManager.getConnection(DB.URL, DB.ID, DB.PW); 
           statement  = connection.createStatement();
          
           int rowsInserted = 0;
           
           for (int i = 0; i < pl.size(); i++) {
        	   Person p = pl.get(i);
        	   // Build an SQL command from fixed and variable parts.
        	   // Strings must be surrounded by apostrophes
        	   String insertSQL = "INSERT INTO persons VALUES (" + 
			 				+ p.getId() + ",'" + p.getName() + "')";
               //=== Execute the statement and retrieve 
               //	a count of how many rows was inserted    
        	   rowsInserted += statement.executeUpdate(insertSQL);
           }
           //=== Validate the result
           if (rowsInserted == pl.size())
        	   System.out.println("All objects inserted!");
           else
        	   System.out.println("Insertion failed");
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
	
    //=== Create and fill a list with dummy Person objects
    private static ArrayList<Person> fillListWithObjects() {
            ArrayList<Person> newList = new ArrayList<Person>();
            for (int i = 0; i < 10; i++) {
                    int newId = new Random().nextInt(10000);
                    Person p = new Person(newId, "agent" + newId);
                    newList.add(p);
            }
            return newList;
    }

}

