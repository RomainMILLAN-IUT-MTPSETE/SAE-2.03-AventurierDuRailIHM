package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.CouleurWagon;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe présente les éléments appartenant au joueur courant.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueJoueurCourant extends BorderPane {
    private IJeu jeu;

    private Image imagePlayer;
    private SimpleStringProperty namePlayer;
    private Label pseudo;

    //TOP
    VBox top;
    //Avatar & Pseudo
    HBox nameandavatarimg;
    ImageView imgViewAvatar;
    //Destination, Gare & Wagons
    Label titledestination;
    HBox destigarewagon;
    VBox destinations;
    VBox allDestinations;
    VBox gareEtWagon;
    HBox gare;
    HBox wagon;
    ImageView gareImgView;
    Label gareLabel;
    ImageView wagonImgView;
    Label wagonLabel;

    //CENTER
    VBox center;
    Label cardTitle;
    HBox cardAll;

    //BOTTOM
    VBox bottom;
    Label cardSelectTitle;
    HBox allCardSelected;


    public VueJoueurCourant(IJeu jeu){
        this.jeu = jeu;
        this.setMaxHeight(500);
        this.setMinSize(200, 200);
        this.setPrefHeight(500);
        this.setPrefWidth(250);
        this.setStyle("-fx-background-color: green");
        this.setTranslateY(50);
        this.setTranslateX(5);

        /*
        TOP
        */
        top = new VBox();
        //NAME PLAYER & AVATAR
        nameandavatarimg = new HBox();
        String str = this.jeu.joueurCourantProperty().getName();
        if(str.equalsIgnoreCase("")){
            str = "Avatar";
        }
        pseudo = new Label(str);
        pseudo.setTextFill(Color.WHITE);
        pseudo.setFont(Font.font("Cabin", FontWeight.BOLD, 22));
        pseudo.setTranslateX(5);
        pseudo.setTranslateY(5);
        imgViewAvatar = new ImageView(new Image("images/avatars/avatar-ROSE.png"));
        imgViewAvatar.setFitHeight(55);
        imgViewAvatar.setFitWidth(45);
        imgViewAvatar.setTranslateX(5);
        nameandavatarimg.getChildren().addAll(imgViewAvatar, pseudo);


        //DESTINATION & GARE & WAGON
        destigarewagon = new HBox();

        //Destinations
        destinations = new VBox();
        //title destination
        titledestination = new Label("Destinations:");
        titledestination.setTextFill(Color.WHITE);
        titledestination.setFont(Font.font("QuickSand", FontWeight.MEDIUM, 17));
        titledestination.setStyle("-fx-underline: true;");
        //all destination
        allDestinations = new VBox();
        allDestinations.setTranslateX(10);

        destinations.getChildren().addAll(titledestination, allDestinations);
        destinations.setTranslateX(10);
        destinations.setTranslateY(15);


        //GARE & WAGON
        gareEtWagon = new VBox();
        //gare
        gare = new HBox();
        gareImgView = new ImageView(new Image("images/gares/gare-ROSE.png"));
        gareImgView.setFitWidth(25);
        gareImgView.setFitHeight(25);
        gareLabel = new Label("4");
        gareLabel.setFont(Font.font("QuickSand", FontWeight.MEDIUM, 20));
        gareLabel.setTextFill(Color.WHITE);
        gareLabel.setTranslateX(15);
        gare.getChildren().addAll(gareImgView, gareLabel);
        //wagon
        wagon = new HBox();
        wagonImgView = new ImageView(new Image("images/wagons/image-wagon-ROSE.png"));
        wagonImgView.setFitWidth(40);
        wagonImgView.setFitHeight(40);
        wagonLabel = new Label("4");
        wagonLabel.setFont(Font.font("QuickSand", FontWeight.MEDIUM, 20));
        wagonLabel.setTextFill(Color.WHITE);
        wagonLabel.setTranslateX(5);
        wagon.getChildren().addAll(wagonImgView, wagonLabel);
        //FINAL
        gareEtWagon.getChildren().addAll(gare, wagon);
        gareEtWagon.setTranslateX(75);
        destigarewagon.getChildren().addAll(destinations, gareEtWagon);

        //TOP
        top.getChildren().addAll(nameandavatarimg, destigarewagon);
        this.setTop(top);



        /*
        MIDDLE
         */
        center = new VBox();
        cardTitle = new Label("Vos Cartes:");
        cardTitle.setFont(Font.font("QuickSand", FontPosture.REGULAR, 18));
        cardTitle.setStyle("-fx-underline: true");
        cardTitle.setTextFill(Color.WHITE);
        cardAll = new HBox();
        cardAll.setMaxWidth(this.getWidth());
        cardAll.setPrefHeight(this.getWidth());
        cardAll.setMinWidth(this.getWidth());

        center.setTranslateY(20);
        center.setTranslateX(10);
        center.getChildren().addAll(cardTitle, cardAll);
        this.setCenter(center);


        /*
        BOTTOM
         */
        bottom = new VBox();
        cardSelectTitle = new Label("Cartes sélectionné:");
        cardSelectTitle.setFont(Font.font("QuickSand", FontWeight.MEDIUM, 18));
        cardSelectTitle.setStyle("-fx-underline: true");
        cardSelectTitle.setTextFill(Color.WHITE);
        allCardSelected = new HBox();
        allCardSelected.setTranslateX(5);
        /*for(int i=0; i<5; i++){
            ImageView imgv = new ImageView(new Image("images/cartesWagons/carte-wagon-BLANC.png"));
            imgv.setFitHeight(45);
            imgv.setFitWidth(80);
            allCardSelected.getChildren().add(imgv);
        }
        allCardSelected.setAlignment(Pos.BOTTOM_LEFT);*/

        bottom.getChildren().addAll(cardSelectTitle, allCardSelected);
        bottom.setTranslateY(-10);
        bottom.setTranslateX(10);
        this.setBottom(bottom);

        this.createBinding();
    }

    public String getNamePlayer() {
        return namePlayer.get();
    }

    public SimpleStringProperty namePlayerProperty() {
        return namePlayer;
    }

    private void createBinding(){
        this.widthProperty().addListener(e -> {
            this.gareEtWagon.setTranslateX(this.getWidth()/7.5);
        });

        this.heightProperty().addListener(e -> {
            this.setPrefHeight(this.getHeight()/1.5);
        });

        this.jeu.joueurCourantProperty().addListener(e -> {
            this.pseudo.setText(this.jeu.joueurCourantProperty().getValue().getNom());
            this.imgViewAvatar.setImage(new Image("images/avatars/avatar-" + this.jeu.joueurCourantProperty().getValue().getCouleur().toString() + ".png"));
            this.setStyle("-fx-background-color: " + this.convertFrenchColorToEnglishColor(this.jeu.joueurCourantProperty().getValue().getCouleur().toString()));
            this.wagonImgView.setImage(new Image("images/wagons/image-wagon-" + this.jeu.joueurCourantProperty().getValue().getCouleur().toString() + ".png"));
            this.gareImgView.setImage(new Image("images/gares/gare-" + this.jeu.joueurCourantProperty().getValue().getCouleur().toString() + ".png"));

            this.allDestinations.getChildren().clear();
            for(int i=0; i<this.jeu.joueurCourantProperty().getValue().getDestinations().size(); i++){
                Label destinationLabel = new Label(this.jeu.joueurCourantProperty().getValue().getDestinations().get(i).getNom());
                destinationLabel.setFont(Font.font("QuickSand", FontWeight.NORMAL, 16));
                destinationLabel.setTextFill(Color.WHITE);
                this.allDestinations.getChildren().add(destinationLabel);
            }

            Map<CouleurWagon, Integer> cartesJoueur = CouleurWagon.compteur(this.jeu.joueurCourantProperty().getValue().getCartesWagon());
            //DEBUG
            System.out.println(cartesJoueur.toString());
            //DEBUG
            if(cartesJoueur.size() > 0){
                HBox cardAllTemp = new HBox();
                for(CouleurWagon cw : CouleurWagon.getCouleursSimples()){
                    if(cartesJoueur.get(cw) > 0 && !cw.equals(CouleurWagon.GRIS) && !cw.equals(CouleurWagon.BLANC)){
                        VBox card = new VBox();
                        card.setTranslateX(25);
                        ImageView carte = new ImageView(new Image("images/cartesWagons/carte-wagon-" + cw.toString() + ".png"));
                        carte.setFitWidth(80);
                        carte.setFitHeight(45);
                        Circle backNumber = new Circle();
                        backNumber.setFill(Color.web("#4B4B4B"));
                        backNumber.setRadius(10);
                        Text number = new Text(cartesJoueur.get(cw).toString());
                        number.setBoundsType(TextBoundsType.VISUAL);
                        number.setFont(Font.font("QuickSand", FontPosture.REGULAR, 16));
                        number.setStyle("-fx-background-color: #8C8C8C;");
                        number.setFill(Color.WHITE);
                        StackPane stackCardNumberCircle = new StackPane();

                        stackCardNumberCircle.getChildren().addAll(backNumber, number);
                        card.getChildren().addAll(carte,stackCardNumberCircle);
                        stackCardNumberCircle.setTranslateX(carte.getFitWidth()/2);
                        stackCardNumberCircle.setTranslateY(-10);
                        cardAllTemp.getChildren().addAll(card);
                    }
                }
                this.cardAll.getChildren().addAll(cardAllTemp);
                System.out.println("Test !");
            }
        });
    }

    private String convertFrenchColorToEnglishColor(String fc){
        String res = "white";
        if(fc.equalsIgnoreCase("rouge")){
            res = "#FF5C5C";
        }else if(fc.equalsIgnoreCase("vert")){
            res = "#00993D";
        }else if(fc.equalsIgnoreCase("rose")){
            res = "#7941B1";
        }else if(fc.equalsIgnoreCase("bleu")){
            res = "#605CFF";
        }else if(fc.equalsIgnoreCase("jaune")){
            res = "#E4CC09";
        }

        return res;
    }
}
