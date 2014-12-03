/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vsa.domain.Administratie;

/**
 *
 * @author Michael
 */
public class VSA extends Application
{
    
    private Administratie administratie;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception 
    {
        this.administratie = Administratie.getInstance();
        
        if(this.administratie.checkForSerialization() == true)
        {
            if(this.administratie.deSerializeData() == true)
            {
                //SHIT IS GOED
            }
            else
            {
                //SHIT IS FOUT GEGAAN MET SERIALIZEREN
            }
        }
        else
        {
            //SHIT GAAT FOUT ABORT DIE TOELIE
        }
                
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
        MainFXController fx = new MainFXController();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        fx.setData();
        
        stage.show();
    }
    
}
