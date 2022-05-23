package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Cette classe présente les éléments des joueurs autres que le joueur courant,
 * en cachant ceux que le joueur courant n'a pas à connaitre.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueAutresJoueurs extends BorderPane {

    private IJoueur joueur;

    private Label namePlayer;
    private ImageView imagePlayer;

    public VueAutresJoueurs(IJoueur joueur){

        this.joueur = joueur;
        this.setMaxHeight(115);
        this.setMaxWidth(350);
        this.setPrefHeight(115);
        this.setPrefWidth(350);
        this.setStyle("-fx-background-color: " + VueDuJeu.convertFrenchColorToEnglishColor(this.joueur.getCouleur().toString()));
        this.setTranslateY(50);
        this.setTranslateX(-5);

        //Image player
        imagePlayer = new ImageView(new Image("images/avatars/avatar-BLEU.png"));
        imagePlayer.setFitWidth(100);
        imagePlayer.setFitHeight(115);
        namePlayer = new Label("Avatar");

        this.setLeft(imagePlayer);
        this.setCenter(namePlayer);

    }

}
