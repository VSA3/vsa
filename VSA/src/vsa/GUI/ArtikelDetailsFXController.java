/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.GUI;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vsa.domain.Administratie;
import vsa.domain.Artikel;

/**
 *
 * @author Michael
 */
public class ArtikelDetailsFXController implements Initializable
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
    
    @FXML private TextField textfield_artikel_btw_naam;
    @FXML private TextField textfield_artikel_btw_percentage;
    
    @FXML private TextField textfield_dag;
    @FXML private TextField textfield_maand;
    @FXML private TextField textfield_week;
    @FXML private TextField textfield_systeemtijd;
    
    @FXML private DatePicker datepicker_artikel_ingangsdatum;
    @FXML private DatePicker datepicker_artikel_einddatum;

    
    @FXML private Button button_artikelgroepoverzicht;
    @FXML private Button button_artikeltoevoegen;
    
    @FXML private ComboBox combobox_btw_btwkiezen;

    private Parent root;
    private Scene scene;
    private Stage stage;
    private Scene currentscene;
    private Stage currentstage;
    
    private int artikelnummer;
    
    private Administratie administratie;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        this.administratie = Administratie.getInstance();
        Artikel ophalen = this.administratie.zoekArtikelopNummer(artikelnummer);
        
        setSystemTimeBoxes();
    }
    
    public void setData(Artikel artikel)
    {
        this.artikelnummer = artikel.getArtikelnr();
    }
    
    public void bindBoxes(Artikel artikel)
    {
        textfield_artikel_nummer.setText(Integer.toString(artikel.getArtikelnr()));
        textfield_artikel_omschrijving.setText(artikel.getOmschrijving());
        textfield_artikel_korteomschrijving.setText(artikel.getKorteOmschrijving());
        textfield_artikel_artikelgroep.setText(Integer.toString(artikel.getArtikelgroep().getNummer()));
        textfield_artikel_artikelgroepzoeken.setText(artikel.getArtikelgroep().getNaam());
        textfield_artikel_verkoopprijsincl.setText(Double.toString(artikel.getVerkoopprijsincl()));
        textfield_artikel_verkoopprijsexcl.setText(Double.toString(artikel.getVerkoopprijsexcl()));
        textfield_artikel_btw_naam.setText(artikel.getBTW().getNaam());
        textfield_artikel_btw_percentage.setText(Integer.toString(artikel.getBTW().getPercentage()));
        datepicker_artikel_ingangsdatum.setValue(artikel.getIngangsdatum());
        datepicker_artikel_einddatum.setValue(artikel.getEinddatum());
        textfield_artikel_verkoopprijsinclPrijzen.setText(Double.toString(artikel.getVerkoopprijsincl()));
        textfield_artikel_verkoopprijsexclPrijzen.setText(Double.toString(artikel.getVerkoopprijsexcl()));
         
        combobox_btw_btwkiezen.setItems(this.administratie.getBTW());        
    }
    
    public void aanpassenArtikel()
    {
        
    }
    
    public void setSystemTimeBoxes()
    {
        Calendar test = new GregorianCalendar();
        int weeknummer = test.get(Calendar.WEEK_OF_YEAR);
        textfield_week.setText("Week: " + Integer.toString(weeknummer));
               
        Calendar sCalendar = Calendar.getInstance();
        String dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        textfield_dag.setText("Dag: " + dayLongName);
        
        Calendar sCalendar2 = Calendar.getInstance();
        String dayLongName2 = sCalendar2.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        String dayOfMonthStr = String.valueOf(dayOfMonth);

        textfield_maand.setText("Maand: " + dayOfMonthStr + " " + dayLongName2);
        
        String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        
        textfield_systeemtijd.setText(timeStamp);
    }
}
