/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbsecurityapp;

/**
 *
 * @author Dungs
 */
public class CommandParser 
{
    public static String cParser(String checkString)
    {
        String theString=checkString;
        if(theString.contains("SELECT"))
        {
            return "select";
        }
        else if(theString.contains("INSERT"))
        {
             return "insert";       
        }
        else if(theString.contains("UPDATE"))
        {
            return "update";
        }
        else if(theString.contains("DELETE"))
        {
            return "delete";
        }
        else
        {
            return "error";
        }
    }
    
    public static void queryParser(String inspectString)
    {
        //String theString="INSERT INTO ` table `(` [col2] `,` [col3] `,` k3 `)VALUES([value-2] , [value-3], k3value )";
                String delimss= "[ INSERTINTO(`,VALUES)]+";
                String[] splittedArray=inspectString.split("VALUES");
                
                String colPart=splittedArray[0];
                String valPart=splittedArray[1];
                
                String[] firstArr=colPart.split(delimss);
                String[] secArr=valPart.split(delimss);
                
                String tableName=firstArr[0]; 
                String col2=firstArr[1];
                String col3=firstArr[2];
                String col4=firstArr[3];
                
                String val2=secArr[0];
                String val3=secArr[1];
                String val4=secArr[2];
                
        //return "";
    }
}
