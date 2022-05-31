package fr.umontpellier.iut;

import fr.umontpellier.iut.rails.ServiceDuJeu;
import fr.umontpellier.iut.vues.VueChoixJoueurs;
import fr.umontpellier.iut.vues.VueDuJeu;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RailsIHM extends Application {

    private VueDuJeu vueDuJeu;
    private VueChoixJoueurs vueChoixJoueurs;
    private Stage primaryStage;
    private ServiceDuJeu serviceDuJeu;

    private boolean avecVueChoixJoueurs = false;

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        if (avecVueChoixJoueurs) {
            vueChoixJoueurs = new VueChoixJoueurs(this);
            vueChoixJoueurs.setNomsDesJoueursDefinisListener(quandLesNomsJoueursSontDefinis);
            stage = new Stage();
            stage.setScene(new Scene(vueChoixJoueurs));
            stage.setTitle("Les Aventuriers du Rails - Sélection des joueurs");
            stage.show();
        } else {
            demarrerPartie();
        }
    }

    public void demarrerPartie() {
        List<String> nomsJoueurs;
        if (avecVueChoixJoueurs){
            nomsJoueurs = vueChoixJoueurs.getNomsJoueurs();
            System.out.println(nomsJoueurs.toString());
            stage.close();
        }else {
            nomsJoueurs = new ArrayList<>();
            nomsJoueurs.add("Guybrush");
            nomsJoueurs.add("Largo");
            nomsJoueurs.add("LeChuck");
            nomsJoueurs.add("Elaine");
            /*nomsJoueurs.add("Nadal");*/
        }

        serviceDuJeu = new ServiceDuJeu(nomsJoueurs.toArray(new String[0]));
        vueDuJeu = new VueDuJeu(serviceDuJeu.getJeu());
        Scene scene = new Scene(vueDuJeu); // la scene doit être créée avant de mettre en place les bindings
        vueDuJeu.creerBindings();
        demarrerServiceJeu(); // le service doit être démarré après que les bindings ont été mis en place

        primaryStage.setScene(scene);
        primaryStage.setTitle("Les Aventuriers du Rails");
        primaryStage.centerOnScreen();
        primaryStage.setOnCloseRequest(event -> {
            this.onStopGame();
            event.consume();
        });
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private void demarrerServiceJeu() {
        if (serviceDuJeu.getState() == Worker.State.READY) {
            serviceDuJeu.start();
        }
        primaryStage.show();
    }

    private final ListChangeListener<String> quandLesNomsJoueursSontDefinis = change -> {
        if (!vueChoixJoueurs.getNomsJoueurs().isEmpty())
            demarrerPartie();
    };

    public void onStopGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Arrêt du jeu - Confirmation");
        alert.setContentText("Voulez-vous arrêter de jouer ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            serviceDuJeu.getJeu().cancel();
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void openRules(){
        HostServices hs = getHostServices();
        hs.showDocument("src/main/resources/rules.pdf");
    }

    public void closeSelectPlayer(){
        stage.close();
    }

}