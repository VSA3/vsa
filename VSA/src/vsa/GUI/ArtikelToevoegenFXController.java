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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import vsa.domain.Administratie;
import vsa.domain.Artikel;
import vsa.domain.Artikelgroep;
import vsa.domain.BTW;

/**
 *
 * @author Michael
 */
public class ArtikelToevoegenFXController implements Initializable {

    @FXML
    private MenuBar main_menubar;
    @FXML
    private Menu artikelen_menu;
    @FXML
    private Menu bestand_menu;
    @FXML
    private Menu namen_menu;
    @FXML
    private Menu orders_menu;
    @FXML
    private Menu printen_menu;
    @FXML
    private Menu help_menu;

    @FXML
    private MenuItem bestand_instellingen;
    @FXML
    private MenuItem bestand_administratie_laden;
    @FXML
    private MenuItem bestand_administratie_opslaan;
    @FXML
    private MenuItem bestand_backup_maken;
    @FXML
    private MenuItem bestand_backup_maken_mailen;
    @FXML
    private MenuItem bestand_printerinstellingen;
    @FXML
    private MenuItem bestand_afsluiten;

    @FXML
    private MenuItem artikelen_overzicht;
    @FXML
    private MenuItem artikelen_toevoegen;
    @FXML
    private MenuItem artikelen_verwijderen;
    @FXML
    private MenuItem artikelen_artikelgroepen;

    @FXML
    private MenuItem namen_afleveradressen;
    @FXML
    private MenuItem namen_debiteuren;

    @FXML
    private MenuItem orders_orders;

    @FXML
    private Text text_artikel_nummer;
    @FXML
    private Text text_artikel_omschrijving;
    @FXML
    private Text text_artikel_korteomschrijving;
    @FXML
    private Text text_artikel_artikelgroep;
    @FXML
    private Text text_artikel_ingagsdatum;
    @FXML
    private Text text_artikel_einddatum;

    @FXML
    private TextField textfield_artikel_nummer;
    @FXML
    private TextField textfield_artikel_omschrijving;
    @FXML
    private TextField textfield_artikel_korteomschrijving;
    @FXML
    private TextField textfield_artikel_artikelgroep;
    @FXML
    private TextField textfield_artikel_artikelgroepzoeken;

    @FXML
    private TextField textfield_artikel_verkoopprijsincl;
    @FXML
    private TextField textfield_artikel_verkoopprijsexcl;

    @FXML
    private TextField textfield_artikel_verkoopprijsinclPrijzen;
    @FXML
    private TextField textfield_artikel_verkoopprijsexclPrijzen;

    @FXML
    private TextArea textarea_artikel_notitie;

    @FXML
    private DatePicker datepicker_artikel_ingangsdatum;
    @FXML
    private DatePicker datepicker_artikel_einddatum;

    @FXML
    private RadioButton radiobutton_artikel_nul;
    @FXML
    private RadioButton radiobutton_artikel_laag;
    @FXML
    private RadioButton radiobutton_artikel_hoog;

    @FXML
    private Button button_artikelgroepoverzicht;
    @FXML
    private Button button_artikeltoevoegen;

    @FXML
    private TextField textfield_dag;
    @FXML
    private TextField textfield_maand;
    @FXML
    private TextField textfield_week;
    @FXML
    private TextField textfield_systeemtijd;

    @FXML
    private ToggleGroup radiobuttongroup;

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
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Methode die data in de GUI en objecten vult voordat het scherm geopend word om data klaar te zetten
     */
    public void setData() {
        //INSTANTIEREN VAN DE ADMINISTRATIE KLASSEN
        this.administratie = Administratie.getInstance();

        //VOORGESTELD ARTIKEL NUMMER AUTOMATISCH IN DE BOX ZETTEN
        //ALS DE FORM OPENT
        textfield_artikel_nummer.setText(Integer.toString(this.voorGesteldArtikelNummer()));

        //ONCLOSEREQUEST EVENT AANMAKEN OM HET ARTIKEL OVERZICHT
        //SCHERM TE OPENEN NA HET SLUITEN VAN DEZE WINDOW
        stage = (Stage) text_artikel_nummer.getScene().getWindow();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                try {
                    openArtikelMenu();
                } catch (IOException ex) {
                    Logger.getLogger(ArtikelToevoegenFXController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //RADIO BUTTONS TOEVOEGEN AAN EEN GROUP
        //ZODAT ER MAAR 1 GESELECTEERD KAN WORDEN
        radiobuttongroup = new ToggleGroup();
        radiobutton_artikel_nul.setToggleGroup(radiobuttongroup);
        radiobutton_artikel_laag.setToggleGroup(radiobuttongroup);
        radiobutton_artikel_hoog.setToggleGroup(radiobuttongroup);

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
     * Methode om het voorgestelde artikelnummer te berekenen. Dit doet hij door in de lijst van producten te kijken naar de laatste plek in de lijst die beschikbaar is
     * @return 
     */
    public int voorGesteldArtikelNummer() {
        int voorgesteldnummer = this.administratie.getArtikelen().size() + 1;
        return voorgesteldnummer;
    }

    /**
     * Methode voor het openen van het ArtikelGroepOverzicht menu
     * @param event
     * @throws IOException 
     */
    public void openArtikelGroepOverzicht(ActionEvent event) throws IOException {
        URL location1 = ArtikelGroepOverzichtFXController.class.getResource("ArtikelGroepOverzicht.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location1);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        root = (Parent) (Node) fxmlLoader.load(location1.openStream());

        ArtikelGroepOverzichtFXController ctrl1 = (ArtikelGroepOverzichtFXController) fxmlLoader.getController();

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);

        ctrl1.setData();

        //show the stage
        stage.showAndWait();
    }

    /**
     * Methode voor het openen van het ArtikelenOverzichtMenu
     * @throws IOException 
     */
    public void openArtikelMenu() throws IOException {
        URL location1 = ArtikelOverzichtFXController.class.getResource("ArtikelOverzicht.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location1);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        root = (Parent) (Node) fxmlLoader.load(location1.openStream());

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
    public void sluitenButtonAction(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) text_artikel_nummer.getScene().getWindow();
        // do what you have to do
        stage.close();

        System.out.println("Test");
    }

    /**
     * Methode voor het toevoegen van een artikel aan het programma. Deze word in de administratie opgeslagen en zal mee worden genomen de eerstvolgende keer als het programma opstart
     */
    public void artikelToevoegen() {
        boolean magtoevoegen = false;
        Artikel nieuwartikel = null;

        if (textfield_artikel_nummer.getText().equals("") || textfield_artikel_omschrijving.getText().equals("") || textfield_artikel_korteomschrijving.getText().equals("") || textfield_artikel_verkoopprijsinclPrijzen.getText().equals("") || textfield_artikel_verkoopprijsexclPrijzen.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Niet alle velden zijn ingevuld!", "WAARSCHUWING!", JOptionPane.WARNING_MESSAGE);
        } else {
            //BTW MAKEN
            if (radiobutton_artikel_nul.isSelected() && radiobutton_artikel_nul.getText().equals("Nul")) {
                String btwnaam = "Nul";
                BTW btw = administratie.zoekBTWOpNaam(btwnaam);
            } else if (radiobutton_artikel_laag.isSelected() && radiobutton_artikel_laag.getText().equals("Laag")) {
                String btwnaam = "Laag";
                BTW btw = administratie.zoekBTWOpNaam(btwnaam);
            } else if (radiobutton_artikel_hoog.isSelected() && radiobutton_artikel_hoog.getText().equals("Hoog")) {
                String btwnaam = "Hoog";
                BTW btw = administratie.zoekBTWOpNaam(btwnaam);
            } else {
                JOptionPane.showMessageDialog(null, "Selecteer een BTW Tarief!", "WAARSCHUWING!", JOptionPane.WARNING_MESSAGE);
            }
            if (datepicker_artikel_ingangsdatum.getValue() != null || datepicker_artikel_einddatum.getValue() != null) {
                //ARTIKELNUMMER
                artikelnr = Integer.parseInt(this.textfield_artikel_nummer.getText());
                //OMSCHRIJVING <- NAAM
                omschrijving = this.textfield_artikel_omschrijving.getText();
                //KORTE OMSCHRIJVING
                korteomschrijving = this.textfield_artikel_korteomschrijving.getText();
                //ARTIKELGROEP OP NUMMER
                if (!textfield_artikel_artikelgroep.getText().equals("")) {
                    //ARTIKELGROEP
                    artikelgroep = administratie.zoekArtikelGroepOpNummer(Integer.parseInt(textfield_artikel_artikelgroep.getText()));
                } //ARTIKELGROEP OP NAAM
                else if (!textfield_artikel_artikelgroepzoeken.getText().equals("")) {
                    artikelgroep = administratie.zoekArtikelGroepOpNaam(textfield_artikel_artikelgroepzoeken.getText());
                }
                //INGANGSDATUM
                ingangsdatum = this.datepicker_artikel_ingangsdatum.getValue();
                //EINDDATUM
                einddatum = this.datepicker_artikel_einddatum.getValue();
                //VERKOOPPRIJSINCL
                verkoopprijsincl = Double.parseDouble(this.textfield_artikel_verkoopprijsinclPrijzen.getText());
                //VERKOOPPRIJSEXCL
                verkoopprijsexcl = Double.parseDouble(this.textfield_artikel_verkoopprijsexclPrijzen.getText());
                //ZOEKCODE
                zoekcode = this.artikelgroep.getZoekcode();
                //NOTITIE
                if (!this.textarea_artikel_notitie.getText().equals("")) {
                    notitie = this.textarea_artikel_notitie.getText();

                } else {
                    notitie = "";
                }

                nieuwartikel = new Artikel(artikelnr, omschrijving, korteomschrijving, artikelgroep, ingangsdatum, einddatum, verkoopprijsincl, verkoopprijsexcl, btw, zoekcode, notitie);
                magtoevoegen = true;

            } else {
                JOptionPane.showMessageDialog(null, "Geen Ingang- en Einddatum geselecteerd!", "WAARSCHUWING!", JOptionPane.WARNING_MESSAGE);
            }

        }
        if (magtoevoegen) {
            if (this.administratie.toevoegenArtikel(nieuwartikel)) 
            {
                JOptionPane.showMessageDialog(null, "Artikel: " + omschrijving + " succesvol toegevoegd!", "SUCCESVOL!", JOptionPane.INFORMATION_MESSAGE);
                this.textfield_artikel_artikelgroep.setText("");
                this.textfield_artikel_artikelgroepzoeken.setText("");
                this.textfield_artikel_korteomschrijving.setText("");
                this.textfield_artikel_nummer.setText(Integer.toString(this.voorGesteldArtikelNummer()));
                this.textfield_artikel_omschrijving.setText("");
                this.textfield_artikel_verkoopprijsexcl.setText("");
                this.textfield_artikel_verkoopprijsexclPrijzen.setText("");
                this.textfield_artikel_verkoopprijsincl.setText("");
                this.textfield_artikel_verkoopprijsinclPrijzen.setText("");
                this.datepicker_artikel_einddatum.setValue(null);
                this.datepicker_artikel_ingangsdatum.setValue(null);
            } else {
                JOptionPane.showMessageDialog(null, "Artikel niet correct ingevoerd in het systeem!", "WAARSCHUWING!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Zet de tijden van het systeem, week, maand en systeemtijd
     */
    public void setSystemTimeBoxes() {
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
