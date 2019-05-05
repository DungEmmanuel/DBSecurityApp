package dbsecurityapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dungs
 */
public class DBConnector 
{
    
	private  final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private  final String DB_CONNECTION = "jdbc:mysql://localhost:8889/Sales";
	private  final String DB_USER = "root";
	private  final String DB_PASSWORD = "root";
	

        
        
       
        private ResultSet rs=null;
        private Connection dbConnection = null;
	private PreparedStatement preparedStatement = null;
        
       
	

	private  Connection getDBConnection() 
        {
		Connection dbConnection = null;
		try 
                {
                    Class.forName(DB_DRIVER);
                } 
		catch (ClassNotFoundException e) 
                {
                    System.out.println(e.getMessage());
                }
		try {
                    dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
                    System.out.println("Connection Successful");
		    return dbConnection;
                    }
		catch (SQLException e) 
                {
                    System.out.println(e.getMessage());
                }
		return dbConnection;
	}
	
	public void insertRecordIntoTable(String query) throws SQLException 
        {

		dbConnection=getDBConnection();
                preparedStatement=dbConnection.prepareStatement(query);
               // preparedStatement.execute(query);
                preparedStatement.executeUpdate();
	}
	


	public ResultSet  selectRecordsFromTable(String sqlstmt) throws SQLException, IOException 
        {
                
		try 
                {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sqlstmt);
			rs = preparedStatement.executeQuery();	

		}
                catch (SQLException e) 
                {

			System.out.println(e.getMessage());

		} 
                
                
           return  rs;
	}
        
        
	

        
         //Close the database connection
    public void closeConnection()
    {
        try
        {
            if (preparedStatement != null) 
            {
	        preparedStatement.close();
	    }

	    if (dbConnection != null) 
            {
				
                dbConnection.close();
	    }
        }

        catch(SQLException error)
        {
            System.out.println(error.getMessage());
        }
    }

	

	private static java.sql.Timestamp getCurrentTimeStamp() 
        {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

}
