package preparedStatement;

//JDBC demo
//save all object in a list
//using prepared statement object
//hau

import java.sql.*;
import java.util.*;

import statementDemo.DB;
import statementDemo.Person;


public class SaveAllObjectsPrepared {

	public static void main(String[] args) throws SQLException {
		
		ArrayList<Person> pl = fillListWithObjects();
		
        PreparedStatement preparedStatement	= null;
        Connection connection 				= null;
        
        try 
        {  
           Class.forName(DB.driver);
           connection = DriverManager.getConnection(DB.URL, DB.ID, DB.PW);
           
           //=== Build the fixed part of an SQL command 
           //	 The variable parts are represented by question marks
           String insertSQL = "INSERT INTO persons VALUES ( ? , ? )";
           
           //=== Retrieve a PreparedStatement object from the Connection object.
           //	The PreparedStatement object is used to execute 
           //	the statement many times with different values
           // 	inserted in stead of the question marks 
           preparedStatement = connection.prepareStatement(insertSQL); 
          
           int rowsInserted = 0;
           
           for (int i = 0; i < pl.size(); i++) {
        	   Person p = pl.get(i);
        	   preparedStatement.setInt(1,p.getId());				// exchange '?' for values 
        	   preparedStatement.setString(2, p.getName());
        	   rowsInserted += preparedStatement.executeUpdate();	// execute the statement	
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
           preparedStatement.close();
           connection.close(); 
        }        
	}

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

