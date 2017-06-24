package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import DBconection.*;
import Checker.*;

import java.sql.*;

public class Main extends Application {
    Database baza = Database.getInstance();
    Stage window;
    Scene logIn;
    Scene Loged;

    ObservableList<Ispit> ispiti = FXCollections.observableArrayList();
    TableView<Ispit> table;
    Ispit ispitforUpdate;
    ResultSet rs = null;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Odabir predmeta i indeksa");
        primaryStage.setScene(new Scene(root, 600, 400));
        window = primaryStage;
        //-------------------------------------LogIn---------------------------------------------//
        Label lerror = new Label("");
        lerror.setStyle("-fx-color: red");

        Label lmesage = new Label("Unesite indeks studenta i sifru predmeta koji je polagao:");

        Label lindeks = new Label("Indeks:");
        TextField txtIndex = new TextField();

        Label lIdPredmeta = new Label("Sifra predmeta: ");
        TextField txtIdPredmeta = new TextField();

        Button goButton = new Button("Gotovo");
        goButton.setMinWidth(175);

        Button exitButton = new Button("Izadji");
        exitButton.setMinWidth(175);

        VBox grid = new VBox();
        grid.setPadding(new Insets(100,10,200,10));
        grid.setSpacing(10);

        GridPane.setConstraints(lindeks,0,0);
        GridPane.setConstraints(txtIndex,1,0);
        GridPane.setConstraints(lIdPredmeta,0,1);
        GridPane.setConstraints(txtIdPredmeta,1,1);
        GridPane.setConstraints(goButton,1,2);
        GridPane.setConstraints(exitButton,0,2);
        GridPane.setConstraints(lerror, 0,1);

        logIn = new Scene(grid, 600,400);

        HBox buttonGrid =new HBox();
        buttonGrid.setPadding(new Insets(5,10,5,10));
        buttonGrid.setSpacing(200);
        buttonGrid.getChildren().addAll(goButton,exitButton);
        grid.getChildren().addAll(lmesage,lindeks,txtIndex,lIdPredmeta,txtIdPredmeta,buttonGrid,lerror);
        //--------------------------------------------------------------------------------------//

        //-------------------------------Loged---------------------------------------------------//
        Button backButton = new Button("Nazad");
        backButton.setMinWidth(175);
        Button selectButton = new Button("Odaberi");
        selectButton.setMinWidth(175);
        Button updateButton = new Button("Azuriraj");
        updateButton.setMinWidth(175);

        // top side---------------------------------------------------------------------------
        Label lerror2 = new Label("");
        Label lDatumPrijave = new Label("Datum Prijave:");
        TextField txtDatumPrijave = new TextField();
        Label lNacinPrijave = new Label("Nacin prijave:");
        TextField txtNacinPrijave = new TextField();
        Label lStatusPrijave = new Label("Status prijave:");
        TextField txtStatusPrijave = new TextField();
        Label lDatumPismenog = new Label("Datum pismenog:");
        TextField txtDatumPismenog = new TextField();
        Label lBodoviPismenog = new Label("Bodovi pismenog:");
        TextField txtBodoviPismenog = new TextField();
        Label lDatumUsmenog = new Label("Datum usmenog:");
        TextField txtDatumUsmenog = new TextField();
        Label lBodoviUsmenog = new Label("Bodovi usmenog:");
        TextField txtBodoviUsmenog = new TextField();
        Label lBodovi = new Label("Bodovi:");
        TextField txtBodovi = new TextField();
        Label lOcena = new Label("Ocena:");
        TextField txtOcena = new TextField();
        Label lNastavnik = new Label("Nastavnik:");
        TextField txtNastavnik = new TextField();
        Label lNapomena = new Label("Napomena:");
        TextField txtNapomena = new TextField();
        TextArea taIzvestaj =new TextArea();
        Alert alert1 =new Alert(Alert.AlertType.ERROR, "OVDe ce ici izvestaj!\nOVDe ce ici izvestaj!\nOVDe ce ici izvestaj!\n" +
                "                                                  OVDe ce ici izvestaj!\nOVDe ce ici izvestaj!\nOVDe ce ici izvestaj!\n");
    //---------------------------------------------------------------------------------
        // Columns for table------------------------------------------------------------
        TableColumn<Ispit,String> indeks = new TableColumn<>("indeks");
        TableColumn<Ispit,String> idPredmeta = new TableColumn<>("idPredmeta");
        TableColumn<Ispit,String> godinaRoka = new TableColumn<>("godinaRoka");
        TableColumn<Ispit,String> oznakaRoka = new TableColumn<>("oznakaRoka");
        TableColumn<Ispit,String> godina = new TableColumn<>("godina");
        TableColumn<Ispit,String> semestar = new TableColumn<>("semestar");
        TableColumn<Ispit,String> datumPrijave = new TableColumn<>("datumPrijave");
        TableColumn<Ispit,String> nacinPrijave = new TableColumn<>("nacinPrijave");
        TableColumn<Ispit,String> brojPolaganja = new TableColumn<>("brojPolaganja");;
        TableColumn<Ispit,String> statusPrijave = new TableColumn<>("statusPrijave");
        TableColumn<Ispit,String> datumPismenog = new TableColumn<>("datumPismenog");
        TableColumn<Ispit,String> bodoviPismenog= new TableColumn<>("bodoviPismenog");
        TableColumn<Ispit,String> datumUsmenog = new TableColumn<>("datumUsmenog");
        TableColumn<Ispit,String> bodoviUsmenog= new TableColumn<>("bodoviUsmenog");
        TableColumn<Ispit,String> bodovi = new TableColumn<>("bodovi");
        TableColumn<Ispit,String> ocena = new TableColumn<>("ocena");
        TableColumn<Ispit,String> nastavnik = new TableColumn<>("nastavnik");
        TableColumn<Ispit,String> napomena = new TableColumn<>("napomena");

        indeks.setCellValueFactory(new PropertyValueFactory<>("indeks"));
        idPredmeta.setCellValueFactory(new PropertyValueFactory<>("idPredmeta"));
        godinaRoka.setCellValueFactory(new PropertyValueFactory<>("godinaRoka"));
        oznakaRoka.setCellValueFactory(new PropertyValueFactory<>("oznakaRoka"));
        godina.setCellValueFactory(new PropertyValueFactory<>("godina"));
        semestar.setCellValueFactory(new PropertyValueFactory<>("semestar"));
        datumPrijave.setCellValueFactory(new PropertyValueFactory<>("datumPrijave"));
        nacinPrijave.setCellValueFactory(new PropertyValueFactory<>("nacinPrijave"));
        brojPolaganja.setCellValueFactory(new PropertyValueFactory<>("brojPolaganja"));
        statusPrijave.setCellValueFactory(new PropertyValueFactory<>("statusPrijave"));
        datumPismenog.setCellValueFactory(new PropertyValueFactory<>("datumPismenog"));
        bodoviPismenog.setCellValueFactory(new PropertyValueFactory<>("bodoviPismenog"));
        datumUsmenog.setCellValueFactory(new PropertyValueFactory<>("datumUsmenog"));
        bodoviUsmenog.setCellValueFactory(new PropertyValueFactory<>("bodoviUsmenog"));
        bodovi.setCellValueFactory(new PropertyValueFactory<>("bodovi"));
        ocena.setCellValueFactory(new PropertyValueFactory<>("ocena"));
        nastavnik.setCellValueFactory(new PropertyValueFactory<>("nastavnik"));
        napomena.setCellValueFactory(new PropertyValueFactory<>("napomena"));
        //-------------------------------------------------------------------------------------------------------------

        table = new TableView<>();
        table.setItems(ispiti);
        table.getColumns().addAll(
                indeks,idPredmeta,godinaRoka,oznakaRoka,godina,semestar,datumPrijave,
                nacinPrijave,brojPolaganja,statusPrijave,datumPismenog,bodoviPismenog,
                datumUsmenog,bodoviUsmenog,bodovi,ocena,nastavnik,napomena
        );
        VBox labelgrid = new VBox();
        labelgrid.getChildren().addAll(lDatumPrijave,lNacinPrijave,lStatusPrijave,lDatumPismenog,
                                        lBodoviPismenog,lDatumUsmenog,lBodoviUsmenog,lBodovi,lOcena,lNastavnik,lNapomena);
        labelgrid.setSpacing(8);
        VBox txtgrid = new VBox();
        txtgrid.getChildren().addAll(txtDatumPrijave,txtNacinPrijave,txtStatusPrijave,txtDatumPismenog,
                                     txtBodoviPismenog,txtDatumUsmenog,txtBodoviUsmenog,txtBodovi,txtOcena,txtNastavnik,txtNapomena);
        VBox buttonGrid2 = new VBox();
        buttonGrid2.setSpacing(30);
        buttonGrid2.getChildren().addAll(backButton,selectButton,updateButton);

        HBox workGrid = new HBox();
        workGrid.setPadding(new Insets(10,10,10,10));
        workGrid.setSpacing(20);
        workGrid.getChildren().addAll(labelgrid,txtgrid,buttonGrid2,taIzvestaj);

        VBox loggrid = new VBox();
        loggrid.setPadding(new Insets(10,10,10,10));
        loggrid.setSpacing(10);
        loggrid.getChildren().addAll(workGrid,table);


        GridPane.setConstraints(backButton,1,2);

        Loged = new Scene(loggrid,1200,800);

        //---------------------------------------------------------------------------------//

        goButton.setOnAction(e -> {
            String s = Assert.CheckForm1(txtIndex.getText(),txtIdPredmeta.getText());
            lerror.setText(s);

            if(s.length() != 0)
                return;

            //rs=baza.getCursor(txtIndex.getText(),txtIdPredmeta.getText());

            ResultSet rs = null;
            PreparedStatement ps = null;
            String sql = "select indeks,i.id_predmeta,godina_roka,oznaka_roka, " +
                    "godina,semestar,datum_prijave,nacin_prijave, broj_polaganja,status_prijave, " +
                    "datum_pismenog, bodovi_pismenog,datum_usmenog,bodovi_usmenog, " +
                    "i.bodovi, ocena, nastavnik,napomena " +
                    "from ispit i join predmet p on i.id_predmeta=p.id_predmeta" +
                    " where i.indeks=" + txtIndex.getText() +
                    " and p.sifra= '" + txtIdPredmeta.getText() + "'";


            try {
                baza.konektujSe();
                Connection con = baza.getConnection();
                ps = con.prepareStatement(sql, rs.HOLD_CURSORS_OVER_COMMIT);
                rs = Database.otvoriKursor(ps);
                    if (!rs.next()) {
                        lerror.setText("Nema podatka u bazi!");
                        rs.close();
                        ps.close();
                        baza.diskonektujSe();
                        return;
                    }
                    do{
                        Ispit i = new Ispit(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getString(7),
                                rs.getString(8),
                                rs.getString(9),
                                rs.getString(10),
                                rs.getString(11),
                                rs.getString(12),
                                rs.getString(13),
                                rs.getString(14),
                                rs.getString(15),
                                rs.getString(16),
                                rs.getString(17),
                                rs.getString(18)
                        );
                        ispiti.add(i);
                    }while(rs.next());
            } catch (SQLException sqle) {
                System.out.println("SQL error: SQLCODE" + sqle.getErrorCode());
                System.out.println(sqle.getMessage());
                if(sqle.getErrorCode() == -911 || sqle.getErrorCode() == -913)
                    try{rs = Database.obradiCekanje(rs, baza.getConnection(), ps);}
                    catch (SQLException sqle1){System.out.println("SQLException : error with obradiCekanje!");}
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            primaryStage.setTitle("Azuriranje");
            window.setScene(Loged);
            primaryStage.show();

        });
        backButton.setOnAction(e ->{
            primaryStage.setTitle("Odabir predmeta i indeksa");
            window.setScene(logIn);
            ispiti.clear();
            txtDatumPrijave.clear();
            txtNacinPrijave.clear();
            txtStatusPrijave.clear();
            txtDatumPismenog.clear();
            txtBodoviPismenog.clear();
            txtDatumUsmenog.clear();
            txtBodoviUsmenog.clear();
            txtBodovi.clear();
            txtOcena.clear();
            txtNastavnik.clear();
            txtNapomena.clear();
            primaryStage.show();
            baza.diskonektujSe();
        });
        exitButton.setOnAction(e->{
            primaryStage.close();
        });
        window.setScene(logIn);
        primaryStage.show();

        updateButton.setDisable(true);
        updateButton.setOnAction(e->{
            String sqlupdate = "update ispit " +
                    " set datum_prijave =" + (txtDatumPrijave.getLength() == 0 ? "NULL" : "'" + txtDatumPrijave.getText() + "'") +
                    " , nacin_prijave = " + (txtNacinPrijave.getLength() == 0 ? "''" : "'" + txtNacinPrijave.getText() + "'") +
                    " , status_prijave = " + (txtStatusPrijave.getLength() == 0 ? "''" : "'" + txtStatusPrijave.getText() + "'") +
                    " , datum_pismenog = " + (txtDatumPismenog.getLength() == 0 ? "NULL" : "'" + txtDatumPismenog.getText() + "'") +
                    " , bodovi_pismenog = " + (txtBodoviPismenog.getLength() == 0 ? "NULL" : txtBodoviPismenog.getText()) +
                    " , datum_usmenog = " + (txtDatumUsmenog.getLength() == 0 ? "NULL" : "'" + txtDatumUsmenog.getText() + "'") +
                    " , bodovi_usmenog = " + (txtBodoviUsmenog.getLength() == 0 ? "NULL" : txtBodoviUsmenog.getText()) +
                    " , bodovi = " + (txtBodovi.getLength() == 0 ? "NULL" : txtBodovi.getText()) +
                    " , ocena = " + (txtOcena.getLength() == 0 ? "NULL" : txtOcena.getText()) +
                    " , nastavnik = " + (txtNastavnik.getLength() == 0 ? "''" : "'" +txtNastavnik.getText() + "'") +
                    " , napomena = " + (txtNapomena.getLength() == 0 ? "''" : "'" + txtNapomena.getText() + "'") +

                    " where indeks =" + ispitforUpdate.indeks +
                    " and id_predmeta =" + ispitforUpdate.idPredmeta +
                    " and godina_roka =" + ispitforUpdate.godinaRoka +
                    " and oznaka_roka = '" + ispitforUpdate.oznakaRoka + "'";
            //System.out.println(update)
            Statement updateS=null;
            /* Azuriranje */
            try{
                updateS=baza.getConnection().createStatement();
                updateS.executeUpdate(sqlupdate );
                System.out.println("Updated!");
                baza.getConnection().commit();
                ObservableList<Ispit> ispits =table.getItems();

                //Update table na prozoru
                for(Ispit i : ispits){
                    if(ispitforUpdate.indeks.equals(i.indeks) && ispitforUpdate.idPredmeta.equals(i.idPredmeta)
                            && ispitforUpdate.godinaRoka.equals(i.godinaRoka) && ispitforUpdate.oznakaRoka.equals(i.oznakaRoka)){
                        i.datumPrijave = txtDatumPrijave.getText();
                        i.nacinPrijave = txtNacinPrijave.getText();
                        i.statusPrijave = txtStatusPrijave.getText();
                        i.datumPismenog = txtDatumPismenog.getText();
                        i.bodoviPismenog = txtBodoviPismenog.getText();
                        i.datumUsmenog = txtDatumUsmenog.getText();
                        i.bodoviUsmenog = txtBodoviUsmenog.getText();
                        i.bodovi = txtBodovi.getText();
                        i.ocena = txtOcena.getText();
                        i.nastavnik = txtNastavnik.getText();
                        i.napomena = txtNapomena.getText();
                        table.refresh();
                    }
                }
                // IZRADA IZVESTAJA------------------------------------------------------------------------------------------
                taIzvestaj.clear();
                String izvestajBuffer="";
                for(int GODINA=2007 ; GODINA < 2017 ; GODINA++){
                    izvestajBuffer = izvestajBuffer + "\n--------------|" +  GODINA  + "|---------------------------\n";
                    String sqlQuery = "select ir.naziv,p.naziv,count(*) as prijavilo," +
                            " sum(case when ocena > 5 then 1 else 0 end) as polozilo," +
                            " sum(case when ocena > 5 then 1 else 0 end)*1.0*100.0/count(*)*1.0 as procenat," +
                            " case when sum(case when ocena > 5 then 1 else 0 end)= 0 then 0 else avg(ocena*1.0) end as prosek" +
                            " from ispitni_rok ir left outer join ispit i  on ir.oznaka=i.oznaka_roka and i.godina_roka=ir.godina" +
                            " join predmet p on p.id_predmeta = i.id_predmeta" +
                            " where ir.godina =" +  GODINA  +
                            " group by ir.naziv,p.naziv";
                    ResultSet rsI = null;
                    PreparedStatement ps = null;
                    try{
                        ps = baza.getConnection().prepareStatement(sqlQuery, rs.HOLD_CURSORS_OVER_COMMIT);
                        rsI = Database.otvoriKursor(ps);
                        if(!rsI.next())
                            continue;
                        do{
                            izvestajBuffer = izvestajBuffer + rsI.getString(1).trim() + "\t"
                                                + rsI.getString(2).trim() + "\t"
                                                + rsI.getString(3).trim() + "\t"
                                                + rsI.getString(4).trim() + "\t"
                                                + rsI.getString(5).trim().substring(0,4) + "%\t"
                                                + rsI.getString(6).trim().substring(0,4) + "\n";

                        }while(rsI.next());
                        rsI.close();
                        ps.close();
                        taIzvestaj.setText(izvestajBuffer);
                    }catch(SQLException sqle) {
                        if (sqle.getErrorCode() == -911 || sqle.getErrorCode() == -913) {
                            try {
                                Database.obradiCekanje(rsI, baza.getConnection(), ps);
                            } catch (SQLException sqle2) {
                            }
                            ;
                        }
                        System.out.println("sql error:" + sqle.getMessage());
                    }
                    }

                }catch(SQLException sqle){

                    if(sqle.getErrorCode() == -911 || sqle.getErrorCode() == -913){
                        try{Database.obradiCekanjeUpdate(updateS,sqlupdate);}catch(SQLException sqle2){};
                    }
                    else if(sqle.getErrorCode() == -545 || sqle.getErrorCode()== -180){

                        Alert alert = new Alert(Alert.AlertType.ERROR, "Greska: Pokusavas da promenis podatke na nekonzistentan nacin! (" + sqle.getMessage() + ")!");
                        alert.show();
                    }
                System.out.println("sql error:" + sqle.getMessage());
            }


        });
        selectButton.setOnAction(e->{
            Ispit i=table.getSelectionModel().getSelectedItem();
            txtDatumPrijave.setText(i.datumPrijave);
            txtNacinPrijave.setText(i.nacinPrijave);
            txtStatusPrijave.setText(i.statusPrijave);
            txtDatumPismenog.setText(i.datumPismenog);
            txtBodoviPismenog.setText(i.bodoviPismenog);
            txtDatumUsmenog.setText(i.datumUsmenog);
            txtBodoviUsmenog.setText(i.bodoviUsmenog);
            txtBodovi.setText(i.bodovi);
            txtOcena.setText(i.ocena);
            txtNastavnik.setText(i.nastavnik);
            txtNapomena.setText(i.napomena);
            updateButton.setDisable(false);
            ispitforUpdate = i;
        });
    }



    public static void main(String[] args) {
        Database baza = Database.getInstance();

        launch(args);
    }
}
