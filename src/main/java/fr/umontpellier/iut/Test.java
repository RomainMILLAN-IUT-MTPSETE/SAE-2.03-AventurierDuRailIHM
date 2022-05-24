package fr.umontpellier.iut;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Test extends Application {

    @Override
    public void start(Stage stage) {
        Button btn = new Button();
        btn.setText("Load PDF");
        btn.setOnAction(e -> {
            /*File file = new File("documents_Les Aventuriers du Rail - Règles.pdf");
            HostServices hostServices = getHostServices();
            hostServices.showDocument(file.getAbsolutePath());*/
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show open file dialog
            //File file = fileChooser.showOpenDialog(stage);
            //System.out.println(fileChooser.showOpenDialog(stage));
            File file = new File("https://cdn.1j1ju.com/medias/3e/81/8e-les-aventuriers-du-rail-europe-regle.pdf");
            //File file = new File("/home/romain/Bureau/documents_Les Aventuriers du Rail - Règles.pdf");
            //File file = new File("documents_Les Aventuriers du Rail - Règles.pdf");

            HostServices hostServices = getHostServices();
            hostServices.showDocument(file.getAbsolutePath());
        });
        //File file = new File("C:/Users/YourUsername/Desktop/Test.pdf");


        BorderPane pane = new BorderPane();

        pane.setCenter(btn);

        Scene scene = new Scene(pane, 300, 250);

        stage.setTitle("T");
        stage.setScene(scene);
        stage.show();

    }
}
