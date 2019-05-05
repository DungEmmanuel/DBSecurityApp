/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbsecurityapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Dungs
 */
public class DBSecurityApp extends Application {
    
    static Stage theStage;
    
    @Override public void init() 
     {
        Font.loadFont(DBSecurityApp.class.getResourceAsStream("Roboto-Thin.ttf"), 10).getName();
        Font.loadFont(DBSecurityApp.class.getResourceAsStream("Roboto-Light.ttf"), 10).getName();
     }
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        theStage=primaryStage;    
        Parent root = FXMLLoader.load(getClass().getResource("InFace.fxml"));
        theStage.setScene(new Scene(root, 1000, 650));
        theStage.setTitle("DBSecurity Improved!");
        theStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
