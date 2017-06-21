package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import DBconection.*;
import Checker.*;

import java.sql.*;

public class Main extends Application {
    Database baza = Database.getInstance();
    Stage window;
    Scene logIn;
    Stage window2;
    Scene Loged;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Odabir predmeta i indeksa");
        primaryStage.setScene(new Scene(root, 600, 400));
        window = primaryStage;
        //----------------------LogIn--------------------------------//
        Label lerror = new Label("");
        lerror.setStyle("-fx-color: red");

        Label lmesage = new Label("Unesite indeks studenta i sifru predmeta koji je polagao:");

        Label lindeks = new Label("Indeks:");
        TextField txtIndex = new TextField();

        Label lIdPredmeta = new Label("Sifra predmeta: ");
        TextField txtIdPredmeta = new TextField();

        Button goButton = new Button("Gotovo");
        goButton.setMinWidth(175);

        VBox grid = new VBox();
        grid.setPadding(new Insets(100,10,200,10));
        grid.setSpacing(10);

        GridPane.setConstraints(lindeks,0,0);
        GridPane.setConstraints(txtIndex,1,0);
        GridPane.setConstraints(lIdPredmeta,0,1);
        GridPane.setConstraints(txtIdPredmeta,1,1);
        GridPane.setConstraints(goButton,1,2);
        GridPane.setConstraints(lerror, 0,1);

        logIn = new Scene(grid, 600,400);

        //-----------------------------------------------------------------------------//


        //--------------------------------Loged----------------------------------------//
        Button backButton = new Button("Nazad");
        backButton.setMinWidth(175);

        Label l = new Label("Indeks:");
        TextField txtIndex = new TextField();

        Label lIdPredmeta = new Label("Sifra predmeta: ");
        TextField txtIdPredmeta = new TextField();


        VBox updategird = new VBox();
        updategird.setPadding(new Insets(100,10,200,10));
        updategird.setSpacing(10);
        updategird.getChildren().addAll(backButton);

        GridPane.setConstraints(backButton,1,2);

        grid.getChildren().addAll(lmesage,lindeks,txtIndex,lIdPredmeta,txtIdPredmeta,goButton,lerror);


        Loged = new Scene(updategird,600,400);

        ResultSet rs = null;
        goButton.setOnAction(e -> {
            String s = Assert.CheckForm1(txtIndex.getText(),txtIdPredmeta.getText());
            lerror.setText(s);


            if(s.length() != 0)
                return;
            String sql = "select * " +
                    "from ispit i join predmet p on i.id_predmeta=p.id_predmeta" +
                    " where i.indeks=" + txtIndex.getText() +
                    " and p.sifra= '" + txtIdPredmeta.getText() + "'";
            try {
                Connection con = baza.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, rs.HOLD_CURSORS_OVER_COMMIT);
                rs = Database.otvoriKursor(ps);
                if (!rs.next()) {
                    lerror.setText("Nema podatka u bazi!");
                    return;
                }
                con.commit();
                rs.close();
                ps.close();
                primaryStage.setTitle("Azuriranje");
                window.setScene(Loged);
                primaryStage.show();
            } catch (SQLException sqle) {
                System.out.println("SQL error: SQLCODE" + sqle.getErrorCode());
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        });
        backButton.setOnAction(e ->{
            primaryStage.setTitle("Odabir predmeta i indeksa");
            window.setScene(logIn);
            primaryStage.show();
        });

        //---------------------------------------------------------------------------------//
        window.setScene(logIn);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Database baza = Database.getInstance();
        launch(args);
    }
}
