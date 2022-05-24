package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Cette classe présente les éléments des joueurs autres que le joueur courant,
 * en cachant ceux que le joueur courant n'a pas à connaitre.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueAutresJoueurs extends GridPane {

    private IJoueur joueur;
    private GridPane contentPane;

    private Label namePlayer;
    private ImageView imagePlayer;

    //CONTENT
    private HBox wagonBox;
    private HBox gareBox;
    private ImageView gareImg;
    private ImageView wagonImg;
    private Label gareLabel;
    private Label wagonLabel;
    private Label scoreLabel;

    public VueAutresJoueurs(IJoueur joueur){
        this.contentPane = new GridPane();
        this.joueur = joueur;
        this.setMaxHeight(115);
        this.setMaxWidth(350);
        this.setPrefHeight(115);
        this.setPrefWidth(350);
        this.setStyle("-fx-background-color: " + VueDuJeu.convertFrenchColorToEnglishColor(this.joueur.getCouleur().toString()));
        this.setTranslateY(50);
        this.setTranslateX(-5);

        //Image player
        imagePlayer = new ImageView(new Image("images/avatars/avatar-" + this.joueur.getCouleur().toString().toUpperCase() + ".png"));
        imagePlayer.setFitWidth(100);
        imagePlayer.setFitHeight(115);
        //Name Player
        namePlayer = new Label(this.joueur.getNom());
        this.namePlayer.setTextFill(Color.WHITE);
        this.namePlayer.setFont(Font.font("Cabin", FontWeight.BOLD, 22));
        this.namePlayer.setTranslateY(10);
        //Content
        this.gareBox = new HBox();
        this.wagonBox = new HBox();
        this.gareImg = new ImageView(new Image("images/gares/gare-" + this.joueur.getCouleur().toString().toUpperCase() + ".png"));
        this.wagonImg = new ImageView(new Image("images/wagons/image-wagon-" + this.joueur.getCouleur().toString().toUpperCase() + ".png"));
        this.gareLabel = new Label(String.valueOf(this.joueur.getNbGares()));
        this.gareLabel.setFont(Font.font("QuickSand", FontWeight.MEDIUM, 18));
        this.gareLabel.setTextFill(Color.WHITE);
        this.gareLabel.setTranslateX(15);
        this.wagonLabel = new Label(String.valueOf(this.joueur.getNbWagons()));
        this.wagonLabel.setFont(Font.font("QuickSand", FontWeight.MEDIUM, 18));
        this.wagonLabel.setTextFill(Color.WHITE);
        this.wagonLabel.setTranslateX(5);
        this.gareImg.setFitHeight(25);
        this.gareImg.setFitWidth(25);
        this.wagonImg.setFitWidth(40);
        this.wagonImg.setFitHeight(40);
        gareBox.getChildren().addAll(gareImg, gareLabel);
        wagonBox.getChildren().addAll(wagonImg, wagonLabel);
        this.gareBox.setTranslateY(20);
        this.wagonBox.setTranslateY(20);
        //SCORe
        //scoreLabel = new Label("Score: " + String.valueOf(this.joueur.getScore()));
        scoreLabel = new Label("Score: 10");
        this.scoreLabel.setTextFill(Color.WHITE);
        this.scoreLabel.setFont(Font.font("Cabin", FontWeight.NORMAL, 15));
        this.scoreLabel.setTranslateY((this.getPrefHeight()/2)-10);
        if(this.joueur.getScore() > 99){
            this.scoreLabel.setTranslateX(this.getPrefWidth()/2-80);
        }else if(this.joueur.getScore() > 999){
            this.scoreLabel.setTranslateX(this.getPrefWidth()/2-95);
        }else {
            this.scoreLabel.setTranslateX(this.getPrefWidth()/2-70);
        }
        scoreLabel.setAlignment(Pos.BOTTOM_RIGHT);


        this.contentPane.addRow(0, namePlayer);
        this.contentPane.addRow(1, gareBox);
        this.contentPane.addRow(2, wagonBox);


        this.addColumn(0, imagePlayer);
        this.addColumn(1, contentPane);
        this.addColumn(2, scoreLabel);
        this.setAlignment(Pos.CENTER_LEFT);

    }

    public void setJoueur(IJoueur joueur) {
        this.joueur = joueur;

        this.setStyle("-fx-background-color: " + VueDuJeu.convertFrenchColorToEnglishColor(joueur.getCouleur().toString()));
        this.namePlayer.setText(joueur.getNom());
        imagePlayer = new ImageView(new Image("images/avatars/avatar-" + joueur.getCouleur().toString().toUpperCase() + ".png"));
        this.gareImg = new ImageView(new Image("images/gares/gare-" + joueur.getCouleur().toString().toUpperCase() + ".png"));
        this.wagonImg = new ImageView(new Image("images/wagons/image-wagon-" + joueur.getCouleur().toString().toUpperCase() + ".png"));
        this.gareLabel.setText(String.valueOf(joueur.getNbGares()));
        this.wagonLabel.setText(String.valueOf(joueur.getNbWagons()));
        this.scoreLabel.setText("Score: " + String.valueOf(joueur.getScore()));

    }
}
