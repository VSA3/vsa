/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import vsa.domain.Administratie;

/**
 *
 * @author Michael
 */
public class ArtikelGroepOverzichtFXController implements Initializable
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
    
    @FXML private TableView tableview_artikelen_overzicht;
    
    @FXML private Button listview_refresh;

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
        administratie = Administratie.getInstance();
    }
    
    public void openArtikelMenu(ActionEvent event)
    {
        
    }
    
    public void bindArtikelen()
    {
        tableview_artikelen_overzicht.setItems(this.administratie.getArtikelen());
    }
}
