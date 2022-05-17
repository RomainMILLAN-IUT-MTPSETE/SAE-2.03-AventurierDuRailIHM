package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Cette classe présente les éléments des joueurs autres que le joueur courant,
 * en cachant ceux que le joueur courant n'a pas à connaitre.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueAutresJoueurs extends GridPane {
    private IJeu jeu;
    private GridPane pane;

    private Label namePlayer;
    private Image imagePlayer;

    public VueAutresJoueurs(IJeu jeu){
        pane = new GridPane();

        this.jeu = jeu;
        this.setMaxHeight(500);
        this.setMaxWidth(250);
        this.setPrefHeight(500);
        this.setPrefWidth(250);
        this.setStyle("-fx-background-color: red;");
        this.setTranslateY(50);
        this.setTranslateX(-5);

        pane.setGridLinesVisible(true);

        namePlayer = new Label();
        namePlayer.setText("Avatar Bleu");

        pane.addRow(1, namePlayer);

    }

}
