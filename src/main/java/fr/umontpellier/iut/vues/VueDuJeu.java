package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.CouleurWagon;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Cette classe correspond à la fenêtre principale de l'application.
 *
 * Elle est initialisée avec une référence sur la partie en cours (Jeu).
 *
 * On y définit les bindings sur les éléments internes qui peuvent changer
 * (le joueur courant, les 5 cartes Wagons visibles, les destinations lors de l'étape d'initialisation de la partie, ...)
 * ainsi que les listeners à exécuter lorsque ces éléments changent
 */
public class VueDuJeu extends BorderPane {

    private IJeu jeu;
    //private VuePlateau plateau;
    private Image plateau;
    private ImageView plateauView;
    private VueJoueurCourant joueurCourant;
    private VueAutresJoueurs autresJoueurs;

    private Label titlePage;

    public VueDuJeu(IJeu jeu) {
        this.jeu = jeu;
        this.setPrefHeight(720);
        this.setPrefWidth(1280);
        this.setMinHeight(720);
        this.setMinWidth(1280);
        this.setStyle("-fx-background-color: #F3DEC4");

        //HAUT
        BorderPane haut = new BorderPane();

        titlePage = new Label("Les Aventuriers du Rail - Europe");
        titlePage.setPrefWidth(750);
        titlePage.setAlignment(Pos.CENTER);
        titlePage.setFont(Font.font("../resources/fonts/Dancing_Script/DancingScript-VariableFont_wght.ttf", FontWeight.NORMAL, 35));
        haut.setCenter(titlePage);
        haut.setAlignment(titlePage, Pos.CENTER);

        Button rulesButton = new Button("Règles");
        rulesButton.setText("Règles");
        rulesButton.setPrefWidth(100);
        rulesButton.setPrefHeight(30);
        rulesButton.setTranslateX(10);
        rulesButton.setTranslateY(10);
        rulesButton.setStyle("-fx-background-color: #E8D9C7; -fx-border-radius: 10px; -fx-border-color: #000;");
        rulesButton.setOnMouseClicked(e -> {
            System.out.println("Règles");
        });
        haut.setLeft(rulesButton);

        //Joueur Courant
        joueurCourant = new VueJoueurCourant(this.jeu);

        //Joueur Autre
        autresJoueurs = new VueAutresJoueurs(this.jeu);

        //Plateau
        //plateau = new VuePlateau();
        plateau = new Image("images/euMap.jpg");
        plateauView = new ImageView(plateau);
        //ORIGINAL:
        //Width: 3402
        //Heigt: 2194
        plateauView.setFitWidth(850.5);
        plateauView.setFitHeight(548.5);
        //
        //this.plateauView.setTranslateX(-100);
        //this.plateauView.setTranslateY(-75);

        /*//Card
        BorderPane bas = new BorderPane();
        HBox cartesVisibles = new HBox();
        for(int i=0; i<5; i++){
            cartesVisibles.getChildren().add(new VueCarteWagon(CouleurWagon.LOCOMOTIVE));
        }
        cartesVisibles.setSpacing(10);
        bas.setAlignment(cartesVisibles, Pos.CENTER);
        bas.setTop(cartesVisibles);
        */

        this.setLeft(joueurCourant);
        this.setRight(autresJoueurs);
        this.setCenter(plateauView);
        this.setTop(haut);
    }

    public IJeu getJeu() {
        return jeu;
    }

    public void creerBindings() {
        /**
         * this.heightProperty().addListener(e -> {
         *             System.out.println("toto");
         *         });
         *
         *         this.widthProperty().addListener(e -> {
         *             System.out.println("tata");
         *         });
         */
        this.heightProperty().addListener(e -> {
            this.plateauView.setFitHeight(this.getHeight()/1.75);
            //this.joueurCourant.setPrefHeight(this.getHeight()/500);
        });
        this.widthProperty().addListener(e -> {
            this.plateauView.setFitWidth(this.getWidth()/1.75);
            this.joueurCourant.setPrefWidth(this.getWidth()/5);
        });
    }

}