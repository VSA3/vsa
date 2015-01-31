/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.GUI;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vsa.domain.Administratie;
import vsa.domain.Artikelgroep;
import vsa.domain.BTW;

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
    
    @FXML private TableView<Artikelgroep> artikelgroep_tableview_overzicht;
    
    @FXML private TableColumn artikelgroep_column_nummer;
    @FXML private TableColumn artikelgroep_column_naam;
    @FXML private TableColumn artikelgroep_column_zoekcode;
    
    @FXML private TextField artikelgroep_textfield_nummer;
    @FXML private TextField artikelgroep_textfield_naam;
    @FXML private TextField artikelgroep_textfield_zoekcode;
    
    @FXML private TextField textfield_week;
    @FXML private TextField textfield_dag;
    @FXML private TextField textfield_maand;
    @FXML private TextField textfield_systeemtijd;
    
    private Parent root;
    private Scene scene;
    private Stage stage;
    private Scene currentscene;
    private Stage currentstage;
    
    private Administratie administratie;
    
    private Timer timer;
    
    /**
     * Attributen voor het toevoegen van het artikel
     */
    private int artikelnr;
    private String omschrijving;
    private String korteomschrijving;
    private Artikelgroep artikelgroep;
    private LocalDate ingangsdatum;
    private LocalDate einddatum;
    private double verkoopprijsincl;
    private double verkoopprijsexcl;
    private BTW btw;
    private String zoekcode;
    private String notitie;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        
    }
    
    public void setData() {
        //INSTANTIEREN VAN DE ADMINISTRATIE KLASSEN
        this.administratie = Administratie.getInstance();
        
        //VOORGESTELD ARTIKEL NUMMER AUTOMATISCH IN DE BOX ZETTEN
        //ALS DE FORM OPENT
        artikelgroep_textfield_nummer.setText(Integer.toString(this.voorGesteldArtikelgroepNummer()));
        
        //BINDEN VAN TABLEVIEW -> MET ARTIKELGROEP OBJECTEN
        artikelgroep_column_nummer.setCellValueFactory(new PropertyValueFactory<Artikelgroep, String>("nummer"));
        artikelgroep_column_naam.setCellValueFactory(new PropertyValueFactory<Artikelgroep, String>("naam"));
        artikelgroep_column_zoekcode.setCellValueFactory(new PropertyValueFactory<Artikelgroep, String>("zoekcode"));

        artikelgroep_tableview_overzicht.setItems(this.administratie.getArtikelgroepen()); 

        //CHECKT ELKE SECONDE NAAR DE SYSTEEMTIJD EN PAST DEZE AAN
        TimerTask task = new TimerTask() {

            @Override
            public void run() 
            {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        setSystemTimeBoxes();
                    }
                ;
            }
        
        );
                }
            };
        timer = new Timer();
        timer.schedule(task, 0, 1000);
    }
    
    public int voorGesteldArtikelgroepNummer()
    {
        int voorgesteldnummer = this.administratie.getArtikelgroepen().size() + 1;
        return voorgesteldnummer;
    }
    
    public void getArtikelgroepData()
    {
        Artikelgroep ophalen = (Artikelgroep)artikelgroep_tableview_overzicht.getSelectionModel().getSelectedItem();
        artikelgroep_textfield_nummer.setText(Integer.toString(ophalen.getNummer()));
        artikelgroep_textfield_naam.setText(ophalen.getNaam());
        artikelgroep_textfield_zoekcode.setText(ophalen.getZoekcode());
    }
    
    public void bindBoxes()
    {
        artikelgroep_tableview_overzicht.getColumns().clear();
        artikelgroep_tableview_overzicht.getColumns().add(artikelgroep_column_nummer);
        artikelgroep_tableview_overzicht.getColumns().add(artikelgroep_column_naam);
        artikelgroep_tableview_overzicht.getColumns().add(artikelgroep_column_zoekcode);
        artikelgroep_tableview_overzicht.setItems(this.administratie.getArtikelgroepen());
    }
    
    public void openArtikelGroepOverzicht() throws IOException
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
    
    public void openArtikelMenu() throws IOException
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
        stage.showAndWait();
    }
    
    /**
    * Sluit het huidige form netjes af en beindigd de Thread.
    */
    public void sluitenButtonAction(ActionEvent event)
    {
        // get a handle to the stage
        Stage stage = (Stage) textfield_dag.getScene().getWindow();
        // do what you have to do
        stage.close();
        
        System.out.println("Test");
    }
          
    public void artikelGroepToevoegen()
    {
        
    }
    
    /**
     * Zet de tijden van het systeem, week, maand en systeemtijd
     */
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
