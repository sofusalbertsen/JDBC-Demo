package statementDemo;

//JDBC demo
//select all rows 
//hau
import java.sql.*;
import java.util.ArrayList;


public class RetrieveAllRows {

	// JDBC demo
	// select all rows in a table
    public static void main(String[] args) throws SQLException
    {
        ResultSet rs 			= null;
        Statement statement 	= null;
        Connection connection 	= null;
        
        try 
        {  
           Class.forName(DB.driver);
           
           connection = DriverManager.getConnection(DB.URL, DB.ID, DB.PW);         
      
           statement = connection.createStatement();
          
           String query = "SELECT * FROM SAL.persons order by 1";
          
           rs = statement.executeQuery(query);
          
           //=== read the result
           ArrayList<Person> pl = new ArrayList<Person>();
           //=== Move cursor one step at a time and
           //	 check for the existence of a row  
           while (rs.next()) 
           {
            pl.add(new Person (	rs.getInt(1),
            			rs.getString(2)));
           } 
           printTheList(pl);
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

	private static void printTheList(ArrayList<Person> pl) {
			System.out.println("The list");
			for (int i = 0; i < pl.size(); i++) {
				System.out.println(pl.get(i).toString());				
			}
	}

}

