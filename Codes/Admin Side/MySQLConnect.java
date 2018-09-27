package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnect
{
  private Connection connection;

  public MySQLConnect(String host,String dbname,String username,String password)
  {
    String url="jdbc:mysql://" + host + ":3306/" + dbname;
    try
    {
      Class.forName("com.mysql.jdbc.Driver");
      this.connection=DriverManager.getConnection(url,username,password);
    }
    catch(Exception e)
    {
      System.out.println("Exception in OracleConnect: " + e);
    }
  }
  
  public int updateDB(String query) throws Exception
  {
    Statement statment = this.connection.createStatement();
    return statment.executeUpdate(query);
  }
  
  public ResultSet searchDB(String query) throws Exception
  {
    Statement statement = this.connection.createStatement();
    return statement.executeQuery(query);
  }
  
  public void close()
  {
    try
    {
      this.connection.close();
    }
    catch(Exception e)
    {
      System.out.println("Exception in close: " + e);
    }
  }
}
