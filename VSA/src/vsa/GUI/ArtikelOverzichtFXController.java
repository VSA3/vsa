/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.GUI;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    
    @FXML private TableView<Artikel> tableview_artikelen_overzicht;
    
    @FXML private TableColumn tablecolumn_artikel_nummer;
    @FXML private TableColumn tablecolumn_artikel_naam;
    @FXML private TableColumn tablecolumn_artikel_prijsexcl;
    @FXML private TableColumn tablecolumn_artikel_prijsincl;
    @FXML private TableColumn tablecolumn_artikel_zoekcode;
    
    @FXML private TextField textfield_dag;
    @FXML private TextField textfield_maand;
    @FXML private TextField textfield_week;
    @FXML private TextField textfield_systeemtijd;

    @FXML private Button listview_refresh;
    
    @FXML private Button button_sluiten;
    @FXML private Button button_artikeldetails;

    private Parent root;
    private Scene scene;
    private Stage stage;
    private Scene currentscene;
    private Stage currentstage;
    
    private Timer timer;
    
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
        
        tableview_artikelen_overzicht.setOnMousePressed(new EventHandler<MouseEvent>() 
        {
        @Override 
        public void handle(MouseEvent event) 
        {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) 
            {
                Artikel ophalen = tableview_artikelen_overzicht.getSelectionModel().getSelectedItem();
                try 
                {
                    openArtikelDetailsWindowClicked(ophalen);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ArtikelOverzichtFXController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
        });
        
        //CHECKT ELKE SECONDE NAAR DE SYSTEEMTIJD EN PAST DEZE AAN
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

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
    
    public void openArtikelDetailsWindow(ActionEvent event) throws IOException 
    {
        if (tableview_artikelen_overzicht.getSelectionModel().getSelectedItem() == null) 
        {

        } 
        else 
        {
            Artikel artikel = (Artikel) tableview_artikelen_overzicht.getSelectionModel().getSelectedItem();

            URL location1 = ArtikelDetailsFXController.class.getResource("ArtikelDetails.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location1);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            try {
                root = (Parent) (Node) fxmlLoader.load(location1.openStream());
            } catch (IOException ex) {
                Logger.getLogger(MainFXController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }

            ArtikelDetailsFXController ctrl1 = (ArtikelDetailsFXController) fxmlLoader.getController();
            ctrl1.setData(artikel);

            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            
            ctrl1.bindBoxes(artikel);

            //show the stage
            stage.showAndWait();
        }
    }
    
    public void openArtikelDetailsWindowClicked(Artikel artikelmee) throws IOException {
        if (tableview_artikelen_overzicht.getSelectionModel().getSelectedItem() == null) 
        {

        } 
        else 
        {
            Artikel artikel = (Artikel) tableview_artikelen_overzicht.getSelectionModel().getSelectedItem();

            URL location1 = ArtikelDetailsFXController.class.getResource("ArtikelDetails.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location1);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            try {
                root = (Parent) (Node) fxmlLoader.load(location1.openStream());
            } catch (IOException ex) {
                Logger.getLogger(MainFXController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }

            ArtikelDetailsFXController ctrl1 = (ArtikelDetailsFXController) fxmlLoader.getController();
            ctrl1.setData(artikel);

            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            
            ctrl1.bindBoxes(artikel);

            //show the stage
            stage.showAndWait();
        }
    }
    
    public void getArtikelData()
    {
        Artikel ophalen = (Artikel)tableview_artikelen_overzicht.getSelectionModel().getSelectedItem();
        try {
            this.openArtikelDetailsWindowClicked(ophalen);
        } catch (IOException ex) {
            Logger.getLogger(ArtikelOverzichtFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
