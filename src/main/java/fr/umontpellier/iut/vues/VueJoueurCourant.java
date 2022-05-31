package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.CouleurWagon;
import fr.umontpellier.iut.rails.Destination;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;

import java.util.*;

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
    VBox cardAll;
    VBox card;
    ImageView imageCard;
    Label numberOfWagonCard;
    String stringNumberOfWagonCard;
    Circle backNumber;
    StackPane stack;
    HBox cardOneLigne;
    HBox cardTwoLigne;
    HBox cardThreeLigne;

    Label ORANGETXT = new Label("-1");
    Label BLANCTXT = new Label("-1");
    Label BLEUTXT = new Label("-1");
    Label GRISTXT = new Label("-1");
    Label ROSETXT = new Label("-1");
    Label ROUGETXT = new Label("-1");
    Label VERTTXT = new Label("-1");
    Label NOIRTXT = new Label("-1");
    Label JAUNETXT = new Label("-1");
    Label LOCOMOTIVETXT = new Label("-1");

    //BOTTOM
    VBox bottom;
    Label cardSelectTitle;
    HBox allCardSelected;


    public VueJoueurCourant(IJeu jeu){
        this.jeu = jeu;
        this.setMaxHeight(500);
        this.setPrefHeight(500);
        this.setPrefWidth(300);
        this.setStyle("-fx-background-color: green;");
        this.setTranslateY(50);
        this.setTranslateX(5);
        this.setMinWidth(300);
        this.setMinHeight(400);

        DropShadow ds = new DropShadow();
        ds.setRadius(5.0);
        ds.setColor(Color.WHITE);
        ds.setOffsetY(1);
        ds.setOffsetX(1);
        this.setEffect(ds);

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
        cardAll = new VBox();
        cardAll.setMaxWidth(this.getWidth());
        cardAll.setPrefHeight(this.getWidth());
        cardAll.setMinWidth(this.getWidth());
        //CARTES
        Map<CouleurWagon, Integer> cartesJoueur = CouleurWagon.compteur(this.jeu.getJoueurs().get(0).getCartesWagon());
        //DEBUG
        System.out.println("All Card: " + cartesJoueur.toString());
        //DEBUG
        cardOneLigne = new HBox();
        cardTwoLigne = new HBox();
        cardThreeLigne = new HBox();

        this.BLEUTXT.setTextFill(Color.WHITE);
        this.BLANCTXT.setTextFill(Color.WHITE);
        this.ORANGETXT.setTextFill(Color.WHITE);
        this.ROUGETXT.setTextFill(Color.WHITE);
        this.ROSETXT.setTextFill(Color.WHITE);
        this.NOIRTXT.setTextFill(Color.WHITE);
        this.JAUNETXT.setTextFill(Color.WHITE);
        this.VERTTXT.setTextFill(Color.WHITE);
        this.LOCOMOTIVETXT.setTextFill(Color.WHITE);

        int x=0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                card = new VBox();
                imageCard = new ImageView(new Image("images/cartesWagons/carte-wagon-" + CouleurWagon.getCouleursNoGris().get(x).toString().toUpperCase() + ".png"));
                imageCard.setFitHeight(55);
                imageCard.setFitWidth(85);
                stringNumberOfWagonCard = String.valueOf(cartesJoueur.get(CouleurWagon.getCouleursNoGris().get(x)));
                backNumber = new Circle();
                backNumber.setFill(Color.web("#4B4B4B"));
                backNumber.setRadius(10);
                stack = new StackPane();
                stack.setTranslateY(-10);
                if(x==0 || x==3 || x==6){
                    imageCard.setTranslateX(2.5);
                    stack.setTranslateX((imageCard.getFitWidth()/2));
                }else if(x==1 || x==4 || x==7){
                    imageCard.setTranslateX(12.5);
                    stack.setTranslateX((imageCard.getFitWidth()/2)+9);
                }else if(x==2 || x==5 || x==8){
                    imageCard.setTranslateX(20);
                    stack.setTranslateX((imageCard.getFitWidth()/2)+17.5);
                }

                stack.getChildren().add(backNumber);
                card.getChildren().addAll(imageCard, stack);
                if(CouleurWagon.getCouleursNoGris().get(x) == CouleurWagon.ORANGE){
                    stack.getChildren().add(this.ORANGETXT);
                }else if(CouleurWagon.getCouleursNoGris().get(x) == CouleurWagon.BLANC){
                    stack.getChildren().add(this.BLANCTXT);
                }else if(CouleurWagon.getCouleursNoGris().get(x) == CouleurWagon.BLEU){
                    stack.getChildren().add(this.BLEUTXT);
                }else if(CouleurWagon.getCouleursNoGris().get(x) == CouleurWagon.LOCOMOTIVE){
                    stack.getChildren().add(this.LOCOMOTIVETXT);
                }else if(CouleurWagon.getCouleursNoGris().get(x) == CouleurWagon.NOIR){
                    stack.getChildren().add(this.NOIRTXT);
                }else if(CouleurWagon.getCouleursNoGris().get(x) == CouleurWagon.JAUNE){
                    stack.getChildren().add(this.JAUNETXT);
                }else if(CouleurWagon.getCouleursNoGris().get(x) == CouleurWagon.ROSE){
                    stack.getChildren().add(this.ROSETXT);
                }else if(CouleurWagon.getCouleursNoGris().get(x) == CouleurWagon.VERT){
                    stack.getChildren().add(this.VERTTXT);
                }else if(CouleurWagon.getCouleursNoGris().get(x) == CouleurWagon.ROUGE){
                    stack.getChildren().add(this.ROUGETXT);
                }

                if(x>=0 && x<3){
                    cardOneLigne.getChildren().addAll(card);
                }else if(x>=3 && x<6){
                    cardTwoLigne.getChildren().addAll(card);
                }else {
                    cardThreeLigne.getChildren().addAll(card);
                }

                int finalX = x;
                card.setOnMouseClicked(event -> {
                    Platform.runLater(() -> {
                        Map<CouleurWagon, Integer> cardJoueurTemp = CouleurWagon.compteur(this.jeu.joueurCourantProperty().getValue().getCartesWagon());
                        if(cardJoueurTemp.get(CouleurWagon.getCouleursNoGris().get(finalX)) > 0){
                            this.jeu.uneCarteWagonAEteChoisie(CouleurWagon.getCouleursNoGris().get(finalX));
                        }else {
                            System.out.println("ERROR: Nombre de carte impossible à enlever dans les cartes du joueurs.");
                        }
                    });
                });
                x++;
            }
        }

        this.cardAll.getChildren().clear();
        this.cardAll.getChildren().addAll(cardOneLigne, cardTwoLigne, cardThreeLigne);

        center.setTranslateY(20);
        center.setTranslateX(10);
        center.getChildren().addAll(cardTitle, cardAll);
        this.setCenter(center);

        this.createBinding();
    }

    public String getNamePlayer() {
        return namePlayer.get();
    }

    public SimpleStringProperty namePlayerProperty() {
        return namePlayer;
    }



    private void createBinding(){
        this.jeu.joueurCourantProperty().addListener(e -> {
            Platform.runLater(() -> {
                //System.out.println(this.jeu.joueurCourantProperty().getValue().cartesWagonProperty().filtered());
                //TEXT & IMAGE
                this.pseudo.setText(this.jeu.joueurCourantProperty().getValue().getNom());
                this.imgViewAvatar.setImage(new Image("images/avatars/avatar-" + this.jeu.joueurCourantProperty().getValue().getCouleur().toString() + ".png"));
                this.setStyle("-fx-background-color: " + VueDuJeu.convertFrenchColorToEnglishColor(this.jeu.joueurCourantProperty().getValue().getCouleur().toString()) + "; -fx-background-radius: 3px;");
                this.wagonImgView.setImage(new Image("images/wagons/image-wagon-" + this.jeu.joueurCourantProperty().getValue().getCouleur().toString() + ".png"));
                this.wagonLabel.setText(String.valueOf(this.jeu.joueurCourantProperty().getValue().getNbWagons()));
                this.gareImgView.setImage(new Image("images/gares/gare-" + this.jeu.joueurCourantProperty().getValue().getCouleur().toString() + ".png"));
                this.gareLabel.setText(String.valueOf(this.jeu.joueurCourantProperty().getValue().getNbGares()));

                //DESTINATIONS
                this.allDestinations.getChildren().clear();
                for(Destination d : this.jeu.joueurCourantProperty().get().getDestinations()){
                    Label destinationLabel = new Label(d.getNom());
                    destinationLabel.setTextFill(Color.WHITE);
                    this.allDestinations.getChildren().add(destinationLabel);
                }

                if(this.jeu.joueurCourantProperty().getValue().getDestinations().size() > 0){
                    this.gareEtWagon.setTranslateX(this.allDestinations.getWidth()/5);
                }

                //CARTES
                Map<CouleurWagon, Integer> cartesPlayer = CouleurWagon.compteur(this.jeu.joueurCourantProperty().getValue().getCartesWagon());
                System.out.println("CartesJoueurs = " + cartesPlayer.toString());

                this.ORANGETXT.setText(String.valueOf(cartesPlayer.get(CouleurWagon.ORANGE)));
                this.BLANCTXT.setText(String.valueOf(cartesPlayer.get(CouleurWagon.BLANC)));
                this.BLEUTXT.setText(String.valueOf(cartesPlayer.get(CouleurWagon.BLEU)));
                this.ROSETXT.setText(String.valueOf(cartesPlayer.get(CouleurWagon.ROSE)));
                this.ROUGETXT.setText(String.valueOf(cartesPlayer.get(CouleurWagon.ROUGE)));
                this.LOCOMOTIVETXT.setText(String.valueOf(cartesPlayer.get(CouleurWagon.LOCOMOTIVE)));
                this.VERTTXT.setText(String.valueOf(cartesPlayer.get(CouleurWagon.VERT)));
                this.JAUNETXT.setText(String.valueOf(cartesPlayer.get(CouleurWagon.JAUNE)));
                this.NOIRTXT.setText(String.valueOf(cartesPlayer.get(CouleurWagon.NOIR)));

                this.jeu.joueurCourantProperty().get().cartesWagonProperty().addListener((ListChangeListener<? super CouleurWagon>) event -> {
                    Platform.runLater(() -> {
                        List<? extends CouleurWagon> listCardWagon = event.getList();
                        Map<CouleurWagon, Integer> cartesPlayerListener = CouleurWagon.compteur(this.jeu.joueurCourantProperty().getValue().getCartesWagon());

                        this.ORANGETXT.setText(String.valueOf(cartesPlayerListener.get(CouleurWagon.ORANGE)));
                        this.BLANCTXT.setText(String.valueOf(cartesPlayerListener.get(CouleurWagon.BLANC)));
                        this.BLEUTXT.setText(String.valueOf(cartesPlayerListener.get(CouleurWagon.BLEU)));
                        this.ROSETXT.setText(String.valueOf(cartesPlayerListener.get(CouleurWagon.ROSE)));
                        this.ROUGETXT.setText(String.valueOf(cartesPlayerListener.get(CouleurWagon.ROUGE)));
                        this.LOCOMOTIVETXT.setText(String.valueOf(cartesPlayerListener.get(CouleurWagon.LOCOMOTIVE)));
                        this.VERTTXT.setText(String.valueOf(cartesPlayerListener.get(CouleurWagon.VERT)));
                        this.JAUNETXT.setText(String.valueOf(cartesPlayerListener.get(CouleurWagon.JAUNE)));
                        this.NOIRTXT.setText(String.valueOf(cartesPlayerListener.get(CouleurWagon.NOIR)));
                    });
                });

                //CarteSelectioné
            });
        });

    }
}
