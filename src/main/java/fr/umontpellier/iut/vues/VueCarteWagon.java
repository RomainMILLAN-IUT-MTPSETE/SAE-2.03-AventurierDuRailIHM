package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Cette classe représente la vue d'une carte Wagon.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueCarteWagon extends Pane {
    private ICouleurWagon couleurWagon;

    ImageView imgviewCard;
    Image imgCard;
    String src;

    public VueCarteWagon(ICouleurWagon couleurWagon) {
        this.couleurWagon = couleurWagon;

        src = "images/cartesWagons/carte-wagon-" + String.valueOf(this.getCouleurWagon()).toUpperCase() + ".png";
        imgCard = new Image(src);
        imgviewCard = new ImageView(imgCard);

        imgviewCard.setFitWidth(124);
        imgviewCard.setFitHeight(80);
        this.getChildren().add(imgviewCard);
    }

    public ICouleurWagon getCouleurWagon() {
        return couleurWagon;
    }

}
