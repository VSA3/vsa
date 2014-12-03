/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import vsa.domain.Administratie;
import vsa.domain.Artikel;

/**
 *
 * @author Michael
 */
public class ArtikelOverzichtFXController implements Initializable
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
    
    @FXML private TableColumn tablecolumn_artikel_nummer;
    @FXML private TableColumn tablecolumn_artikel_naam;
    @FXML private TableColumn tablecolumn_artikel_prijsexcl;
    @FXML private TableColumn tablecolumn_artikel_prijsincl;
    @FXML private TableColumn tablecolumn_artikel_zoekcode;

    @FXML private Button listview_refresh;
    
    @FXML private Button button_sluiten;

    private Parent root;
    private Scene scene;
    private Stage stage;
    private Scene currentscene;
    private Stage currentstage;
    
    private Administratie administratie;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        administratie = Administratie.getInstance();  

        tablecolumn_artikel_nummer.setCellValueFactory(new PropertyValueFactory<Artikel, String>("artikelnr"));
        tablecolumn_artikel_naam.setCellValueFactory(new PropertyValueFactory<Artikel, String>("omschrijving"));
        tablecolumn_artikel_prijsexcl.setCellValueFactory(new PropertyValueFactory<Artikel, String>("verkoopprijsexcl"));
        tablecolumn_artikel_prijsincl.setCellValueFactory(new PropertyValueFactory<Artikel, String>("verkoopprijsincl"));
        tablecolumn_artikel_zoekcode.setCellValueFactory(new PropertyValueFactory<Artikel, String>("zoekcode"));

        tableview_artikelen_overzicht.setItems(this.administratie.getArtikelen());
    }
    
    /**
    * Opent het gedetaileerd Artikel menu van het gekozen artikel
    */
    public void openArtikelMenu(ActionEvent event)
    {
        
    }
    
    /**
    * Refresht de lijst van artikelen en bind ze aan de tableview
    */
    public void bindArtikelen()
    {
        tableview_artikelen_overzicht.setItems(this.administratie.getArtikelen());
    }
    
    /**
    * Sluit het huidige form netjes af en beindigd de Thread.
    */
    public void sluitenButtonAction(ActionEvent event)
    {
        // get a handle to the stage
        Stage stage = (Stage) button_sluiten.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    /**
    * Opent een window om een artikel toe te voegen vanuit deze klasse.
    */
    public void openArtikelToevoegenWindow() throws IOException
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
        stage.showAndWait();
    }
}
