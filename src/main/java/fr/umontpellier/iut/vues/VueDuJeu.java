package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.CouleurWagon;
import fr.umontpellier.iut.rails.Destination;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
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

import java.util.List;

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
    Image plateau;
    private ImageView plateauView;
    private VueJoueurCourant joueurCourant;
    private VueAutresJoueurs autresJoueurs;

    private Label titlePage;

    //BOTTOM
    BorderPane bas;
    HBox cartesVisibles;
    Button passer;
    HBox listDestinationCard;
    VBox bottomCenter;
    ImageView cardWagonNotVisible;
    ImageView cardDestination;
    VBox bottomLeftCard;

    public VueDuJeu(IJeu jeu) {
        this.jeu = jeu;
        this.setPrefHeight(900);
        this.setPrefWidth(1600);
        this.setMinHeight(900);
        this.setMinWidth(1600);
        this.setStyle("-fx-background-color: #F3DEC4");

        //HAUT
        BorderPane haut = new BorderPane();

        titlePage = new Label("Les Aventuriers du Rail - Europe");
        titlePage.setPrefWidth(750);
        titlePage.setAlignment(Pos.CENTER);
        titlePage.setFont(Font.font("../resources/fonts/Dancing_Script/DancingScript-VariableFont_wght.ttf", FontWeight.NORMAL, 35));
        haut.setCenter(titlePage);
        haut.setAlignment(titlePage, Pos.CENTER);

        //REGLES
        Button rulesButton = new Button("Règles");
        rulesButton.setText("Règles");
        rulesButton.setPrefWidth(100);
        rulesButton.setPrefHeight(30);
        rulesButton.setTranslateX(10);
        rulesButton.setTranslateY(10);
        rulesButton.setStyle("-fx-background-color: #E8D9C7; -fx-border-radius: 10px; -fx-border-color: #000;");
        rulesButton.setOnMouseClicked(e -> {
            //System.out.println("Règles");
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

        //Card
        bas = new BorderPane();
        bottomCenter = new VBox();
        //Cartes Visibles
        cartesVisibles = new HBox();
        cartesVisibles.setSpacing(10);
        bas.setAlignment(cartesVisibles, Pos.CENTER);
        //Passer
        passer = new Button("Passer");
        bottomCenter.getChildren().addAll(cartesVisibles);
        bas.setRight(passer);
        //Destinations
        listDestinationCard = new HBox();
        listDestinationCard.setTranslateY(20);
        bottomCenter.getChildren().addAll(listDestinationCard);
        bas.setCenter(bottomCenter);
        //LEFT - Card
        bottomLeftCard = new VBox();
        cardWagonNotVisible = new ImageView(new Image("images/wagons.png"));
        cardWagonNotVisible.setFitHeight(75);
        cardWagonNotVisible.setFitWidth(115);
        cardWagonNotVisible.setTranslateY(-50);
        cardDestination = new ImageView(new Image("images/destinations.png"));
        cardDestination.setFitHeight(75);
        cardDestination.setFitWidth(115);
        cardDestination.setTranslateY(-25);
        bottomLeftCard.getChildren().addAll(cardWagonNotVisible, cardDestination);
        bottomLeftCard.setTranslateX(25);
        bas.setLeft(bottomLeftCard);


        this.setLeft(joueurCourant);
        this.setRight(autresJoueurs);
        this.setCenter(plateauView);
        this.setBottom(bas);
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
        /*this.heightProperty().addListener(e -> {
            this.plateauView.setFitHeight(this.getHeight()/1.75);
            //this.joueurCourant.setPrefHeight(this.getHeight()/500);
        });
        this.widthProperty().addListener(e -> {
            this.plateauView.setFitWidth(this.getWidth()/1.75);
            this.joueurCourant.setPrefWidth(this.getWidth()/5);
        });*/

        this.passer.setOnAction(e -> {
            this.jeu.passerAEteChoisi();
        });

        this.jeu.destinationsInitialesProperty().addListener((ListChangeListener<? super Destination>) e -> {
            Platform.runLater(() -> {
                List<? extends Destination> destinationsListTemp = e.getList();

                this.listDestinationCard.getChildren().clear();
                for(int i=0; i<destinationsListTemp.size(); i++){
                    VueDestination vd = new VueDestination(destinationsListTemp.get(i));
                    this.listDestinationCard.getChildren().addAll(vd);

                    vd.setOnMouseClicked(event -> {
                        this.jeu.uneDestinationAEteChoisie(vd.getDestination().getNom());
                    });
                }
            });

        });

        this.jeu.cartesWagonVisiblesProperty().addListener((ListChangeListener<? super CouleurWagon>) e -> {
            Platform.runLater(() -> {
                List<? extends CouleurWagon> cardWagonVisibles = e.getList();

                this.cartesVisibles.getChildren().clear();
                for(int i=0; i<cardWagonVisibles.size(); i++){
                    VueCarteWagon vcw = new VueCarteWagon(cardWagonVisibles.get(i));
                    this.cartesVisibles.getChildren().addAll(vcw);

                    vcw.setOnMouseClicked(event -> {
                        this.jeu.uneCarteWagonAEteChoisie(vcw.getCouleurWagon());
                    });
                }
            });
        });
    }

}