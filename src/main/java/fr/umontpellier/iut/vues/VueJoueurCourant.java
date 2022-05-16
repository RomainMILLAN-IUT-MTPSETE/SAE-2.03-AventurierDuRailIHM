package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.Jeu;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Cette classe présente les éléments appartenant au joueur courant.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueJoueurCourant extends GridPane {
    private IJeu jeu;
    private GridPane pane;

    private Label namePlayer;
    private Image imagePlayer;

    public VueJoueurCourant(IJeu jeu){
        pane = new GridPane();

        this.jeu = jeu;
        this.setMaxHeight(500);
        this.setMaxWidth(250);
        this.setPrefHeight(500);
        this.setPrefWidth(250);
        this.setStyle("-fx-background-color: blue;");
        this.setTranslateY(50);
        this.setTranslateX(5);

        pane.setGridLinesVisible(true);

        namePlayer = new Label();
        namePlayer.setText("Avatar Rouge");

        pane.addRow(1, namePlayer);

    }

}
