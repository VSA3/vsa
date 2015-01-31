/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vsa.domain.Administratie;

/**
 *
 * @author Michael
 */
public class DebiteurenToevoegenFXController implements Initializable
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
    
    @FXML private MenuItem namen_afleveradressen;
    @FXML private MenuItem namen_debiteuren;
    
    @FXML private MenuItem orders_orders;
    
    @FXML private Text text_debiteur_nummer;
    @FXML private Text text_debiteur_naam;
    @FXML private Text text_debiteur_zoekcode;
    
    @FXML private Text text_debiteur_adres;
    @FXML private Text text_debiteur_plaats;
    @FXML private Text text_debiteur_telefoon;
    
    @FXML private TextField textfield_debiteur_nummer;
    @FXML private TextField textfield_debiteur_naam;
    @FXML private TextField textfield_debiteur_zoekcode;
    @FXML private TextField textfield_debiteur_adres;
    @FXML private TextField textfield_debiteur_postcode;
    @FXML private TextField textfield_debiteur_plaats;
    @FXML private TextField textfield_debiteur_telefoon1;
    @FXML private TextField textfield_debiteur_telefoon2;

    @FXML private Button button_artikelgroepoverzicht;

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
        this.textfield_debiteur_nummer.setText(this.voorGesteldDebiteurNummer()+"");
    }
        
    public void debiteurToevoegen()
    {

    }
    
    public int voorGesteldDebiteurNummer()
    {
        int voorgesteldnummer = this.administratie.getDebiteuren().size() + 1;
        return voorgesteldnummer;
    }
    
    public void openArtikelGroepOverzicht(ActionEvent event) throws IOException
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
        stage.showAndWait();
    }
}
