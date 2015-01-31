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
import javafx.application.Platform;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vsa.domain.Administratie;
import vsa.domain.Artikel;
import vsa.domain.BTW;

/**
 *
 * @author Michael
 */
public class BTWOverzichtFXController implements Initializable
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
    
    @FXML private Text btw_text_gegevens;
    @FXML private Text btw_text_naam;
    @FXML private Text btw_text_percentage;
    @FXML private Text btw_text_omschrijving;
    
    @FXML private TextField btw_textfield_omschrijving;
    @FXML private TextField btw_textfield_percentage;
    @FXML private TextField btw_textfield_naam;
    
    @FXML private TextField textfield_dag;
    @FXML private TextField textfield_maand;
    @FXML private TextField textfield_week;
    @FXML private TextField textfield_systeemtijd;
    
    @FXML private TableView<BTW> btw_tableview_overzicht;
    
    @FXML private TableColumn btw_tablecolumn_naam;
    @FXML private TableColumn btw_tablecolumn_percentage;
    @FXML private TableColumn btw_tablecolumn_omschrijving;
    
    @FXML private Button btw_button_bewerken;
    @FXML private Button btw_button_verwijderen;
    @FXML private Button btw_button_sluiten;

    private Parent root;
    private Scene scene;
    private Stage stage;
    private Scene currentscene;
    private Stage currentstage;
    
    private Administratie administratie;
    
    private Timer timer;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        administratie = Administratie.getInstance();  
        
        btw_tablecolumn_naam.setCellValueFactory(new PropertyValueFactory<BTW, String>("omschrijving"));
        btw_tablecolumn_percentage.setCellValueFactory(new PropertyValueFactory<BTW, String>("percentage"));
        btw_tablecolumn_omschrijving.setCellValueFactory(new PropertyValueFactory<BTW, String>("zoekterm"));

        btw_tableview_overzicht.setItems(this.administratie.getBTW());
        
        //CHECKT ELKE SECONDE NAAR DE SYSTEEMTIJD EN PAST DEZE AAN
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                Platform.runLater(new Runnable() 
                {
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
    
    public void getBTWData()
    {
        BTW ophalen = (BTW)btw_tableview_overzicht.getSelectionModel().getSelectedItem();
        btw_textfield_naam.setText(ophalen.getNaam());
        btw_textfield_percentage.setText(Integer.toString(ophalen.getPercentage()));
        btw_textfield_omschrijving.setText(ophalen.getOmschrijving());
    }
    
    public void aanpassenBTW()
    {
        String naam = btw_textfield_naam.getText();
        int percentage = Integer.parseInt(btw_textfield_percentage.getText());
        
        if(!btw_textfield_naam.equals("") || !btw_textfield_percentage.equals("") || !btw_textfield_omschrijving.equals(""))
        {
            try
            {
                administratie.aanpassenBTW(naam, percentage);
                this.bindBoxes();
            }
            catch(Exception e)
            {
                        
            }
        }  
    }
                
    public void bindBoxes()
    {
        btw_tableview_overzicht.getColumns().clear();
        btw_tableview_overzicht.getColumns().add(btw_tablecolumn_naam);
        btw_tableview_overzicht.getColumns().add(btw_tablecolumn_percentage);
        btw_tableview_overzicht.getColumns().add(btw_tablecolumn_omschrijving);
        btw_tableview_overzicht.setItems(this.administratie.getBTW());
    }
    
    public void sluitenButtonAction(ActionEvent event)
    {
        // get a handle to the stage
        Stage stage = (Stage) btw_button_sluiten.getScene().getWindow();
        // do what you have to do
        stage.close();
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
