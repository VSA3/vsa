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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vsa.domain.Administratie;
import vsa.domain.Artikel;
import vsa.enums.BTWCode;

/**
 *
 * @author Michael
 */
public class ArtikelToevoegenFXController implements Initializable
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
    
    @FXML private Text text_artikel_nummer;
    @FXML private Text text_artikel_omschrijving;
    @FXML private Text text_artikel_korteomschrijving;
    @FXML private Text text_artikel_artikelgroep;
    @FXML private Text text_artikel_ingagsdatum;
    @FXML private Text text_artikel_einddatum;
    
    @FXML private TextField textfield_artikel_nummer;
    @FXML private TextField textfield_artikel_omschrijving;
    @FXML private TextField textfield_artikel_korteomschrijving;
    @FXML private TextField textfield_artikel_artikelgroep;
    @FXML private TextField textfield_artikel_artikelgroepzoeken;
    
    @FXML private TextField textfield_artikel_verkoopprijsincl;
    @FXML private TextField textfield_artikel_verkoopprijsexcl;
    
    @FXML private TextField textfield_artikel_verkoopprijsinclPrijzen;
    @FXML private TextField textfield_artikel_verkoopprijsexclPrijzen;
    
    @FXML private DatePicker datepicker_artikel_ingangsdatum;
    @FXML private DatePicker datepicker_artikel_einddatum;
    
    @FXML private RadioButton radiobutton_artikel_nul;
    @FXML private RadioButton radiobutton_artikel_laag;
    @FXML private RadioButton radiobutton_artikel_hoog;
    
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
        
        //Als de string scherm "overzicht" equals dan komt de gebruiker voor een
        //artikel overzicht en niet voor het toevoegen van een nieuw artikel
        //artikel ophalen met meegegeven artikelnummer en eigenschappen
        //weergeven in de textvelden
//        if(scherm.equals("overzicht"))
//        {
//            Artikel artikel = this.administratie.zoekArtikelopNummer(artikelnummer);
//
//            textfield_artikel_nummer.setText(""+artikel.getArtikelnr());
//            textfield_artikel_omschrijving.setText(""+artikel.getOmschrijving());
//            textfield_artikel_korteomschrijving.setText(""+artikel.getKorteOmschrijving());
//            textfield_artikel_artikelgroep.setText(""+artikel.getArtikelgroep().getNummer());
//            textfield_artikel_artikelgroepzoeken.setText(""+artikel.getArtikelgroep().getNaam());
//            textfield_artikel_verkoopprijsincl.setText(""+artikel.getVerkoopprijsincl());
//            textfield_artikel_verkoopprijsexcl.setText(""+artikel.getVerkoopprijsexcl());
//            textfield_artikel_verkoopprijsinclPrijzen.setText(""+artikel.getVerkoopprijsincl());
//            textfield_artikel_verkoopprijsexclPrijzen.setText(""+artikel.getVerkoopprijsexcl());
//            datepicker_artikel_ingangsdatum.setValue(artikel.getIngangsdatum());
//            datepicker_artikel_einddatum.setValue(artikel.getEinddatum());
//            
//            if(artikel.getBTWCode() == BTWCode.Hoog)
//            {
////                radiobutton_artikel_nul.disarm();
////                radiobutton_artikel_laag.disarm();
////                radiobutton_artikel_hoog.arm();
//            }
//            if(artikel.getBTWCode() == BTWCode.Laag)
//            {
////                radiobutton_artikel_nul.disarm();
////                radiobutton_artikel_laag.arm();
////                radiobutton_artikel_hoog.disarm();
//            }
//            if(artikel.getBTWCode() == BTWCode.Nul)
//            {
////                radiobutton_artikel_nul.arm();
////                radiobutton_artikel_laag.disarm();
////                radiobutton_artikel_hoog.disarm();
//            }
//        }
//        else
//        {
            this.textfield_artikel_nummer.setText(this.voorGesteldArtikelNummer()+"");
//        } 
    }
        
    public void artikelToevoegen()
    {
        if(!text_artikel_nummer.getText().equals(""))
        {
            String nummer = text_artikel_nummer.getText();
        }
        if(!text_artikel_omschrijving.getText().equals(""))
        {
            String omschrijving = text_artikel_omschrijving.getText();
        }
        if(!text_artikel_korteomschrijving.getText().equals(""))
        {
            String korteomschrijving = text_artikel_korteomschrijving.getText();
        }
        if(!textfield_artikel_artikelgroep.getText().equals(""))
        {
            String artikelgroep = textfield_artikel_artikelgroep.getText();
        }
        if(!textfield_artikel_artikelgroepzoeken.getText().equals(""))
        {
            String artikelgroep2 = textfield_artikel_artikelgroepzoeken.getText();
        } 
    }
    
    public int voorGesteldArtikelNummer()
    {
        int voorgesteldnummer = this.administratie.getArtikelen().size() + 1;
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
