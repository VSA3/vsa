/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import vsa.domain.Administratie;

/**
 *
 * @author Michael
 */
public class MainFXController implements Initializable
{
    @FXML private MenuBar main_menubar;
    @FXML private Menu artikelen_menu;
    @FXML private Menu bestand_menu;
    @FXML private Menu namen_menu;
    @FXML private Menu orders_menu;
    @FXML private Menu printen_menu;
    @FXML private Menu help_menu;
    
    @FXML private MenuItem bestand_instellingen;
    @FXML private MenuItem bestand_administratie_laden;
    @FXML private MenuItem bestand_administratie_opslaan;
    @FXML private MenuItem bestand_backup_maken;
    @FXML private MenuItem bestand_backup_maken_mailen;
    @FXML private MenuItem bestand_printerinstellingen;
    @FXML private MenuItem bestand_afsluiten;
    
    @FXML private MenuItem artikelen_overzicht;
    @FXML private MenuItem artikelen_toevoegen;
    @FXML private MenuItem artikelen_verwijderen;
    @FXML private MenuItem artikelen_artikelgroepen;
    @FXML private MenuItem artikelen_btwoverzicht;
    
    @FXML private MenuItem namen_afleveradressen;
    @FXML private MenuItem namen_debiteuren;
    
    @FXML private MenuItem orders_orders;

    private Parent root;
    private Scene scene;
    private Stage stage;
    private Scene currentscene;
    private Stage currentstage;
    
    private Administratie administratie;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        
    }
    
    public void setData()
    {
        this.administratie = Administratie.getInstance();
    }
    
    public void openArtikelMenu(ActionEvent event) throws IOException
    {
        URL location1 = ArtikelOverzichtFXController.class.getResource("ArtikelOverzicht.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location1);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        root = (Parent)(Node)fxmlLoader.load(location1.openStream());

        ArtikelOverzichtFXController ctrl1 = (ArtikelOverzichtFXController) fxmlLoader.getController();
        
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
                     
        //show the stage
        stage.show();
    }
    
    public void openArtikelToevoegenMenu(ActionEvent event) throws IOException
    {
        URL location1 = ArtikelToevoegenFXController.class.getResource("ArtikelToevoegen.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location1);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        root = (Parent)(Node)fxmlLoader.load(location1.openStream());

        ArtikelToevoegenFXController ctrl1 = (ArtikelToevoegenFXController) fxmlLoader.getController();  

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
               
        ctrl1.setData();
                
        //show the stage
        stage.show();
    }
    
    public void openArtikelGroepMenu(ActionEvent event) throws IOException
    {
        URL location1 = ArtikelGroepOverzichtFXController.class.getResource("ArtikelGroepOverzicht.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location1);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        root = (Parent)(Node)fxmlLoader.load(location1.openStream());

        ArtikelGroepOverzichtFXController ctrl1 = (ArtikelGroepOverzichtFXController) fxmlLoader.getController();  

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
               
        ctrl1.setData();
                
        //show the stage
        stage.show();
    }
    
    public void openDebiteurenOverzichtMenu(ActionEvent event) throws IOException
    {
        URL location1 = DebiteurenOverzichtFXController.class.getResource("DebiteurenOverzicht.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location1);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        root = (Parent)(Node)fxmlLoader.load(location1.openStream());

        DebiteurenOverzichtFXController ctrl1 = (DebiteurenOverzichtFXController) fxmlLoader.getController();  

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
                
        //show the stage
        stage.show();
    }
    
    public void openBTWOverzichtMenu(ActionEvent event)
    {
        URL location1 = BTWOverzichtFXController.class.getResource("BTWOverzicht.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location1);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        try {
            root = (Parent)(Node)fxmlLoader.load(location1.openStream());
        } catch (IOException ex) {
            Logger.getLogger(MainFXController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        BTWOverzichtFXController ctrl1 = (BTWOverzichtFXController) fxmlLoader.getController();  

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
                
        //show the stage
        stage.show();
    }
    
    public Administratie getAdministratie()
    {
        return this.administratie;
    }
}
