package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Cette classe présente les routes et les villes sur le plateau.
 *
 * On y définit le listener à exécuter lorsque qu'un élément du plateau a été choisi par l'utilisateur
 * ainsi que les bindings qui mettront ?à jour le plateau après la prise d'une route ou d'une ville par un joueur
 */
public class VuePlateau extends Pane {


    public VuePlateau() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/plateau.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void choixRouteOuVille(MouseEvent e) {
        IJeu jeu = ((VueDuJeu) getScene().getRoot()).getJeu();
        String source = String.valueOf(e.getSource());
        if (source.startsWith("Group")) {
            source = source.substring(9);
            source = source.replace(source.substring(source.length() - 1), "");
            jeu.uneVilleOuUneRouteAEteChoisie(source);
        } else {
            jeu.uneVilleOuUneRouteAEteChoisie(e.getPickResult().getIntersectedNode().getId());
        }
    }

}
