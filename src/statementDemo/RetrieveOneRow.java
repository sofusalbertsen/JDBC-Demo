package statementDemo;
// JDBC demo - with detailed comments
// select one row
// hau

//=== Import the JDBC classes
import java.sql.*;


public class RetrieveOneRow {

    public static void main(String[] args) throws SQLException
    {
        ResultSet rs 			= null;
        Statement statement 	= null;
        Connection connection 	= null;
        
        try 
        {  
          //=== Load the vendor specific JDBC-driver
          //	The driver file must be copied to the hard drive
          // 	The driver must be added to the project compile-driver Libraries  
          // 	The driver will be registered with the DriverManager
          Class.forName(DB.driver);

          //=== Retrieve a Connection object from the DriverManager
          //	A Connection object represents a connection to
          //	the database and has methods to retrieve Statement
          //   	objects, commit etc. 
          connection = DriverManager.getConnection(DB.URL, DB.ID, DB.PW);        

          //=== Retrieve a Statement object from the Connection object.
          //	A Statement object is used to execute 
          //	SQL commands and has different methods used for 
          //	queries and non-queries respectively.
          //	Statement objects exist in 3 types
          //	- Statement 		(used in this example)
          //	- PreparedStatement	(used in another example)
          //	- CallableStatement
          statement = connection.createStatement();
          
          //=== Build an SQL-query-statement in a plain Java String 
          String query = "SELECT * FROM persons WHERE id = 1";
          
          //=== Execute the query and retrieve the result as a ResultSet object.
          //	The ResultSet object is a list of objects
          //	where each object represents a row in answer.
          //	The ResultSet object has methods to access 
          //	each row one at a time 
          rs = statement.executeQuery(query);
          
          //===	Move cursor to 1st row in answer (if any) 
          if (rs.next())					
          {
            System.out.println(
                rs.getInt(1)+"\t" + 		// read 1st cell as an int 
                rs.getString(2)+"\t" );		// read 2nd cell as a String
          }
          else
        	  System.out.println("No rows selected");
        }
        //=== If database driver is unavailable or query fails
        catch (Exception ee) 
        { 
          System.out.println("fail");
          System.err.println(ee); 
        }        
        //=== Always close the database statement and connection
        finally
        {
          statement.close();
          connection.close(); 
        }        
    }
}
