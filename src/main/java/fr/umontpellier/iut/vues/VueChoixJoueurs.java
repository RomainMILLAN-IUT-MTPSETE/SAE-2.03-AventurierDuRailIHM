package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.RailsIHM;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe correspond à une nouvelle fenêtre permettant de choisir le nombre et les noms des joueurs de la partie.
 *
 * Sa présentation graphique peut automatiquement être actualisée chaque fois que le nombre de joueurs change.
 * Lorsque l'utilisateur a fini de saisir les noms de joueurs, il demandera à démarrer la partie.
 */
public class VueChoixJoueurs extends GridPane {

    private ObservableList<String> nomsJoueurs;
    private RailsIHM railsIHM;
    public ObservableList<String> nomsJoueursProperty() {
        return nomsJoueurs;
    }

    public List<String> getNomsJoueurs() {
        return nomsJoueurs;
    }

    HBox titleBox;
    ImageView imageTitle;
    Label selectJoueurTitle;

    ImageView imgPlus;
    ImageView imgMoins;
    SimpleIntegerProperty nbJoueur;
    Label nbLabelJoueur;
    HBox nbJoueurBox;
    Button btnMoins;
    Button btnPlus;

    HBox joueurBox;
    VBox j1Box;
    ImageView j1img;
    TextField j1tf;
    VBox j2Box;
    ImageView j2img;
    TextField j2tf;
    VBox j3Box;
    ImageView j3img;
    TextField j3tf;
    VBox j4Box;
    ImageView j4img;
    TextField j4tf;
    VBox j5Box;
    ImageView j5img;
    TextField j5tf;
    Button play;
    HBox playButtonBox;


    public VueChoixJoueurs(RailsIHM r) {

        this.railsIHM = r;
        nomsJoueurs = FXCollections.observableArrayList();


        this.setPrefWidth(1600);
        this.setPrefHeight(900);
        this.setStyle("-fx-background-color: #F3DEC4");

        titleBox = new HBox();
        titleBox.setPrefWidth(this.getPrefWidth());
        imageTitle = new ImageView(new Image("images/titre aventuriers du rail.png"));
        imageTitle.setFitHeight(130);
        imageTitle.setFitWidth(800);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(imageTitle);
        titleBox.setTranslateY(35);

        selectJoueurTitle = new Label("Selection des joueurs:");
        selectJoueurTitle.setFont(Font.font("Cabin", FontWeight.BLACK, 22));
        selectJoueurTitle.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        selectJoueurTitle.setTranslateY(75);
        selectJoueurTitle.setTranslateX(45);

        imgPlus = new ImageView(new Image("images/plus.png"));
        imgPlus.setFitWidth(45);
        imgPlus.setFitHeight(45);
        btnPlus = new Button();
        btnPlus.setGraphic(imgPlus);
        btnPlus.setPrefWidth(40);
        btnPlus.setPrefHeight(40);
        btnPlus.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        btnPlus.setOnMouseClicked(e -> {
            if(this.nbJoueur.getValue() < 5){
                this.nbJoueur.set(this.nbJoueur.getValue()+1);
            }
        });
        imgMoins = new ImageView(new Image("images/signe-moins.png"));
        imgMoins.setFitHeight(50);
        imgMoins.setFitWidth(40);
        btnMoins = new Button();
        btnMoins.setGraphic(imgMoins);
        btnMoins.setPrefWidth(40);
        btnMoins.setPrefHeight(50);
        btnMoins.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        btnMoins.setOnMouseClicked(e -> {
            if(this.nbJoueur.getValue() > 2){
                this.nbJoueur.set(this.nbJoueur.getValue()-1);
            }
        });

        nbJoueur = new SimpleIntegerProperty(2);
        nbLabelJoueur = new Label(nbJoueur.getValue() + " / 5");
        nbLabelJoueur.setTranslateX(5);
        nbLabelJoueur.setFont(Font.font("Cabin", FontWeight.MEDIUM, 22));
        nbJoueurBox = new HBox();
        nbJoueurBox.getChildren().addAll(btnMoins, nbLabelJoueur, btnPlus);
        nbJoueurBox.setAlignment(Pos.CENTER);
        nbJoueurBox.setPrefWidth(this.getPrefWidth());
        nbJoueurBox.setSpacing(25);
        nbJoueurBox.setTranslateY(110);


        joueurBox = new HBox();

        VBox j1Box = new VBox();
        j1img = new ImageView(new Image("images/avatars/avatar-BLEU.png"));
        j1img.setFitWidth(150);
        j1img.setFitHeight(175);
        j1img.setTranslateX(22.5);
        j1tf = new TextField();
        j1tf.setTranslateY(10);
        j1tf.setPromptText("Joueur 1");
        j1tf.setFont(Font.font("Cabin", FontPosture.REGULAR, 15));
        j1tf.setStyle("-fx-background-color: #E5E5E5; -fx-background-radius: 5px; -fx-border-color: #B9B9B9; -fx-border-radius: 5px");
        j1Box.getChildren().addAll(j1img, j1tf);
        j1Box.setTranslateX(5);

        VBox j2Box = new VBox();
        j2img = new ImageView(new Image("images/avatars/avatar-JAUNE.png"));
        j2img.setFitWidth(150);
        j2img.setFitHeight(175);
        j2img.setTranslateX(22.5);
        j2tf = new TextField();
        j2tf.setTranslateY(10);
        j2tf.setPromptText("Joueur 2");
        j2tf.setFont(Font.font("Cabin", FontPosture.REGULAR, 15));
        j2tf.setStyle("-fx-background-color: #E5E5E5; -fx-background-radius: 5px; -fx-border-color: #B9B9B9; -fx-border-radius: 5px");
        j2Box.getChildren().addAll(j2img, j2tf);
        j2Box.setTranslateX(5);

        VBox j3Box = new VBox();
        j3img = new ImageView(new Image("images/avatars/avatar-ROSE.png"));
        j3img.setFitWidth(150);
        j3img.setFitHeight(175);
        j3img.setTranslateX(22.5);
        j3tf = new TextField();
        j3tf.setTranslateY(10);
        j3tf.setPromptText("Joueur 3");
        j3tf.setFont(Font.font("Cabin", FontPosture.REGULAR, 15));
        j3tf.setStyle("-fx-background-color: #E5E5E5; -fx-background-radius: 5px; -fx-border-color: #B9B9B9; -fx-border-radius: 5px");
        j3Box.getChildren().addAll(j3img, j3tf);
        j3Box.setTranslateX(5);

        VBox j4Box = new VBox();
        j4img = new ImageView(new Image("images/avatars/avatar-ROUGE.png"));
        j4img.setFitWidth(150);
        j4img.setFitHeight(175);
        j4img.setTranslateX(22.5);
        j4tf = new TextField();
        j4tf.setTranslateY(10);
        j4tf.setPromptText("Joueur 4");
        j4tf.setFont(Font.font("Cabin", FontPosture.REGULAR, 15));
        j4tf.setStyle("-fx-background-color: #E5E5E5; -fx-background-radius: 5px; -fx-border-color: #B9B9B9; -fx-border-radius: 5px");
        j4Box.getChildren().addAll(j4img, j4tf);
        j4Box.setTranslateX(5);

        VBox j5Box = new VBox();
        j5img = new ImageView(new Image("images/avatars/avatar-VERT.png"));
        j5img.setFitWidth(150);
        j5img.setFitHeight(175);
        j1img.setTranslateX(22.5);
        j5tf = new TextField();
        j5tf.setTranslateY(10);
        j5tf.setPromptText("Joueur 5");
        j5tf.setFont(Font.font("Cabin", FontPosture.REGULAR, 15));
        j5tf.setStyle("-fx-background-color: #E5E5E5; -fx-background-radius: 5px; -fx-border-color: #B9B9B9; -fx-border-radius: 5px");
        j5Box.getChildren().addAll(j5img, j5tf);
        j5Box.setTranslateX(5);

        joueurBox.getChildren().addAll(j1Box,j2Box);
        joueurBox.setSpacing(10.0);
        joueurBox.setAlignment(Pos.CENTER);
        joueurBox.setTranslateY(150);

        playButtonBox = new HBox();
        play = new Button("Joueur");
        play.setPrefHeight(50);
        play.setPrefWidth(350);
        play.setStyle("-fx-background-color: #F6E7D4; -fx-background-radius: 25px");
        play.setFont(Font.font("Cabin", FontWeight.MEDIUM, 22));
        play.setOnMouseEntered(e -> {
            play.setStyle("-fx-background-color: #FFE6C7; -fx-background-radius: 25px");
        });
        play.setOnMouseExited(e -> {
            play.setStyle("-fx-background-color: #F6E7D4; -fx-background-radius: 25px");
        });
        play.setAlignment(Pos.CENTER);

        DropShadow ds = new DropShadow();
        ds.setRadius(10);
        ds.setColor(Color.BLACK);
        ds.setOffsetX(3);
        ds.setOffsetY(3);
        play.setEffect(ds);
        play.setOnAction(e -> {
            this.enterNamePlayer();
            this.railsIHM.demarrerPartie();
        });
        playButtonBox.getChildren().addAll(play);
        playButtonBox.setMaxWidth(this.getMaxWidth());
        playButtonBox.setTranslateY(400);
        playButtonBox.setAlignment(Pos.BOTTOM_CENTER);


        this.addRow(0, titleBox);
        this.addRow(1, selectJoueurTitle);
        this.addRow(2, nbJoueurBox);
        this.addRow(3, joueurBox);
        this.addRow(4, playButtonBox);

        this.createBindings();
    }

    public void createBindings(){
        this.nbJoueur.addListener(e -> {
            Platform.runLater(() -> {
                this.nbLabelJoueur.setText(this.nbJoueur.getValue() + " / 5");

                this.joueurBox.getChildren().clear();

                VBox j1Box = new VBox();
                j1img = new ImageView(new Image("images/avatars/avatar-BLEU.png"));
                j1img.setFitWidth(150);
                j1img.setFitHeight(175);
                j1img.setTranslateX(22.5);
                j1tf = new TextField();
                j1tf.setTranslateY(10);
                j1tf.setPromptText("Joueur 1");
                j1tf.setFont(Font.font("Cabin", FontPosture.REGULAR, 15));
                j1tf.setStyle("-fx-background-color: #E5E5E5; -fx-background-radius: 5px; -fx-border-color: #B9B9B9; -fx-border-radius: 5px");
                j1Box.getChildren().addAll(j1img, j1tf);
                j1Box.setTranslateX(5);

                VBox j2Box = new VBox();
                j2img = new ImageView(new Image("images/avatars/avatar-JAUNE.png"));
                j2img.setFitWidth(150);
                j2img.setFitHeight(175);
                j2img.setTranslateX(22.5);
                j2tf = new TextField();
                j2tf.setTranslateY(10);
                j2tf.setPromptText("Joueur 2");
                j2tf.setFont(Font.font("Cabin", FontPosture.REGULAR, 15));
                j2tf.setStyle("-fx-background-color: #E5E5E5; -fx-background-radius: 5px; -fx-border-color: #B9B9B9; -fx-border-radius: 5px");
                j2Box.getChildren().addAll(j2img, j2tf);
                j2Box.setTranslateX(5);

                VBox j3Box = new VBox();
                j3img = new ImageView(new Image("images/avatars/avatar-ROSE.png"));
                j3img.setFitWidth(150);
                j3img.setFitHeight(175);
                j3img.setTranslateX(22.5);
                j3tf = new TextField();
                j3tf.setTranslateY(10);
                j3tf.setPromptText("Joueur 3");
                j3tf.setFont(Font.font("Cabin", FontPosture.REGULAR, 15));
                j3tf.setStyle("-fx-background-color: #E5E5E5; -fx-background-radius: 5px; -fx-border-color: #B9B9B9; -fx-border-radius: 5px");
                j3Box.getChildren().addAll(j3img, j3tf);
                j3Box.setTranslateX(5);

                VBox j4Box = new VBox();
                j4img = new ImageView(new Image("images/avatars/avatar-ROUGE.png"));
                j4img.setFitWidth(150);
                j4img.setFitHeight(175);
                j4img.setTranslateX(22.5);
                j4tf = new TextField();
                j4tf.setTranslateY(10);
                j4tf.setPromptText("Joueur 4");
                j4tf.setFont(Font.font("Cabin", FontPosture.REGULAR, 15));
                j4tf.setStyle("-fx-background-color: #E5E5E5; -fx-background-radius: 5px; -fx-border-color: #B9B9B9; -fx-border-radius: 5px");
                j4Box.getChildren().addAll(j4img, j4tf);
                j4Box.setTranslateX(5);

                VBox j5Box = new VBox();
                j5img = new ImageView(new Image("images/avatars/avatar-VERT.png"));
                j5img.setFitWidth(150);
                j5img.setFitHeight(175);
                j1img.setTranslateX(22.5);
                j5tf = new TextField();
                j5tf.setTranslateY(10);
                j5tf.setPromptText("Joueur 5");
                j5tf.setFont(Font.font("Cabin", FontPosture.REGULAR, 15));
                j5tf.setStyle("-fx-background-color: #E5E5E5; -fx-background-radius: 5px; -fx-border-color: #B9B9B9; -fx-border-radius: 5px");
                j5Box.getChildren().addAll(j5img, j5tf);
                j5Box.setTranslateX(5);

                if(this.nbJoueur.getValue() == 2){
                    joueurBox.getChildren().addAll(j1Box,j2Box);
                }else if(this.nbJoueur.getValue() == 3){
                    joueurBox.getChildren().addAll(j1Box,j2Box,j3Box);
                }else if(this.nbJoueur.getValue() == 4){
                    joueurBox.getChildren().addAll(j1Box,j2Box,j3Box,j4Box);
                }else if(this.nbJoueur.getValue() == 5){
                    joueurBox.getChildren().addAll(j1Box,j2Box,j3Box,j4Box,j5Box);
                }
            });
        });
    }

    public void enterNamePlayer(){
        if(this.nbJoueur.getValue() == 2){
            this.nomsJoueurs.add(0, this.j1tf.getText());
            this.nomsJoueurs.add(1, this.j2tf.getText());
        }else if(this.nbJoueur.getValue() == 3){
            this.nomsJoueurs.add(0, this.j1tf.getText());
            this.nomsJoueurs.add(1, this.j2tf.getText());
            this.nomsJoueurs.add(2, this.j3tf.getText());
        }else if(this.nbJoueur.getValue() == 4){
            this.nomsJoueurs.add(0, this.j1tf.getText());
            this.nomsJoueurs.add(1, this.j2tf.getText());
            this.nomsJoueurs.add(2, this.j3tf.getText());
            this.nomsJoueurs.add(3, this.j4tf.getText());
        }else if(this.nbJoueur.getValue() == 5){
            this.nomsJoueurs.add(0, this.j1tf.getText());
            this.nomsJoueurs.add(1, this.j2tf.getText());
            this.nomsJoueurs.add(2, this.j3tf.getText());
            this.nomsJoueurs.add(3, this.j4tf.getText());
            this.nomsJoueurs.add(4, this.j5tf.getText());
        }
    }

    /**
     * Définit l'action à exécuter lorsque la liste des participants est correctement initialisée
     */
    public void setNomsDesJoueursDefinisListener(ListChangeListener<String> quandLesNomsDesJoueursSontDefinis) {
        
    }

    /**
     * Définit l'action à exécuter lorsque le nombre de participants change
     */
    protected void setChangementDuNombreDeJoueursListener(ChangeListener<Integer> quandLeNombreDeJoueursChange) {

    }

    /**
     * Vérifie que tous les noms des participants sont renseignés
     * et affecte la liste définitive des participants
     */
    protected void setListeDesNomsDeJoueurs() {
        ArrayList<String> tempNamesList = new ArrayList<>();
        for (int i = 1; i <= getNombreDeJoueurs() ; i++) {
            String name = getJoueurParNumero(i);
            if (name == null || name.equals("")) {
                tempNamesList.clear();
                break;
            }
            else
                tempNamesList.add(name);
        }
        if (!tempNamesList.isEmpty()) {
            //hide();
            nomsJoueurs.clear();
            nomsJoueurs.addAll(tempNamesList);
        }
    }

    /**
     * Retourne le nombre de participants à la partie que l'utilisateur a renseigné
     */
    protected int getNombreDeJoueurs() {
        //throw new RuntimeException("Methode à implémenter");
        return this.nomsJoueurs.size();
    }

    /**
     * Retourne le nom que l'utilisateur a renseigné pour le ième participant à la partie
     * @param playerNumber : le numéro du participant
     */
    protected String getJoueurParNumero(int playerNumber) {
        //throw new RuntimeException("Methode à implémenter");
        return this.nomsJoueurs.get(playerNumber);
    }




}
