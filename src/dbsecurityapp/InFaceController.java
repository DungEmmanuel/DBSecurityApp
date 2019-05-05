/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbsecurityapp;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author Dungs
 */
public class InFaceController implements Initializable {

     @FXML
     private Button seleteButt;
     
     @FXML
     private Button insertButt;
     
     @FXML
      private Button updateButt;
     
     @FXML
     private Button deleteButt;
     
     @FXML
     private Button executetheshit;
     
     @FXML
     private Text writeText1;
     
     
     @FXML
     private TextArea area1;
     
     @FXML
     private TextArea area2;
     
     @FXML
     private String selectQuery, insertQuery, queryString, vetQuery;
     
     DBConnector dbconnect=new DBConnector();
     ResultSet rs=null;
     
     Random ranGen=new SecureRandom();
    /**
     * Initializes the controller class.
     */
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     @FXML
     public void selectFromDB(ActionEvent event)
     {
      
         selectQuery="SELECT `[col[1]`, `col[2]`, `col[3]` FROM `[table]`";
         area1.setEditable(true);
         area1.clear();
         area1.setText(selectQuery);
     }
     
     @FXML
     public void insertIntoDB(ActionEvent event)
     {
         int k3Value=ranGen.nextInt(20);
         insertQuery="INSERT INTO `table`(`[col1]`, `[col2]`, `[col3]`,`k3`) VALUES ([value-1],[value-2],[value-3]," + k3Value + " )";
         area1.setEditable( true);
         area1.clear();
         area1.setText(insertQuery);
     }
     
      @FXML
     public void updateDB(ActionEvent event)
     {
         
     }
     
     @FXML
     public void executetheshit(ActionEvent event)
     {   
          String errorChecker= "";
        // System.out.println("Debugging!!");
         queryString=area1.getText();
         System.out.println(queryString);
         vetQuery=CommandParser.cParser(queryString);
         //System.out.println(vetQuery);
         if(vetQuery.equalsIgnoreCase("select"))
         {
             try 
             {
                 rs=dbconnect.selectRecordsFromTable(queryString);
             } 
             catch (SQLException ex) 
             {
                 Logger.getLogger(InFaceController.class.getName()).log(Level.SEVERE, null, ex);
             }
             catch (IOException ex) 
             {
                 Logger.getLogger(InFaceController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             try 
             {
                 String record=" ";
                 String record1=" ";
                 String colType;
                 ResultSetMetaData metaData=rs.getMetaData();
                 int columnCount=metaData.getColumnCount();
                 //System.out.println(columnCount);
                 //metaData.getColumnLabel(columnCount);
                 Object object = null;
                 while(rs.next())
                 {
                    for(int columnIndex=1; columnIndex<=columnCount-1; columnIndex++)
                    {
                        object=rs.getObject(columnIndex);
                        record=record + metaData.getColumnName(columnIndex)+ " : " + object.toString() + "\t";
                        //record=record + 
                        colType=metaData.getColumnTypeName(columnIndex);
                        //System.out.println(colType);
                        if(colType.equalsIgnoreCase("varchar"))
                        {
                            String decryptedValue="";
                            String encryptedValue;
                            String colName=metaData.getColumnName(columnIndex);
                            encryptedValue=rs.getObject(columnIndex).toString();
                            for(int i=0; i<encryptedValue.length();i++)
                            {
                                decryptedValue=decryptedValue + Mobat.decryptNonumeric(BlackBox.k2_Col2, encryptedValue.charAt(i));
                            }
                            
                            record1=record1 + colName + " : " + decryptedValue + "\t";
                        }
                        else
                        {
                            String k33=rs.getObject(columnCount).toString();
                            int k3=Integer.parseInt(k33);
                            String colName=metaData.getColumnName(columnIndex);
                            int decryptedValue, encryptedValue;
                            String encryptV=rs.getObject(columnIndex).toString();
                            encryptedValue=Integer.parseInt(encryptV);
                            decryptedValue=Mobat.decrypt(encryptedValue, BlackBox.k1, BlackBox.k2_Col3, k3);
                            record1=record1 + colName + " : " + decryptedValue + "\t";
                        }
                    }
                    
                    area2.setText("");
                    area2.setText("ENCRYPTED RECORD: " + record + "\n" + "DECRYPTED RECORD: " + record1);
                 }
             } 
             catch (SQLException ex) 
             {
                 Logger.getLogger(InFaceController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         
         if(vetQuery.equalsIgnoreCase("insert"))
         {
             
                String delimss= "[ INSERTINTO(`,VALUES)]+";
                String[] splittedArray=queryString.split("VALUES");
                
                String colPart=splittedArray[0];
                String valPart=splittedArray[1];
                
                String[] firstArr=colPart.split(delimss);
                String[] secArr=valPart.split(delimss);
                
                String tableName=firstArr[1]; 
                System.out.println(tableName);
                String col2=firstArr[2];
                String col3=firstArr[3];
                String col4=firstArr[4];
                
                /*for(int i=0; i<secArr.length; i++)
                {
                   System.out.println(secArr[i]);
                }*/
                
                String productName=secArr[1];
                //System.out.println(tableName);
                System.out.println(secArr.length);
                int weigh=Integer.parseInt(secArr[2]);
                int k3=Integer.parseInt(secArr[3]);
                
                String newPName="";
                int newWeight=Mobat.encrypt(weigh, BlackBox.k1,BlackBox.k2_Col3, k3);
                
                for(int i=0; i<productName.length();i++)
                {
                    char holdChar=Mobat.encryptNonumeric(BlackBox.k2_Col2, productName.charAt(i));
                    newPName=newPName + holdChar ;
                }
                
             try 
             {  
                String qString="INSERT INTO`" + tableName + "`(`product_name`, `weight`,`k3`) VALUES ('" + newPName + "'," + newWeight + "," +  k3 + " )";
                        
                
                 dbconnect.insertRecordIntoTable(qString);
             } catch (SQLException ex) 
             {
                 Logger.getLogger(InFaceController.class.getName()).log(Level.SEVERE, null, ex);
                 errorChecker=ex.toString();
             }
             
             if(errorChecker.equalsIgnoreCase(""))
             {
               area2.setText("");
               area2.setText("Original Values: " + " Product Name: " + productName + "," + "Weight: "  + weigh + "\n" + "Encrypted Values: " + " Product Name: " + newPName + "," + "Weight: "  + newWeight   +  "\n" + "Insertion Operation Successful!!");
               
             }
             else 
             {
                 area2.setText("");
                 area2.setText(errorChecker);
             }
         }
     }
     
     @FXML
     public void deleteFromDB(ActionEvent event)
     {
         
     }
     
}
