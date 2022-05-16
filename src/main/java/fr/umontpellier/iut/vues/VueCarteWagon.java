package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Cette classe représente la vue d'une carte Wagon.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueCarteWagon extends Pane {

    private ICouleurWagon couleurWagon;

    public VueCarteWagon(ICouleurWagon couleurWagon) {
        this.couleurWagon = couleurWagon;

        Rectangle carte = new Rectangle();
        carte.setStyle("-fx-background-color: white");
        carte.setHeight(50);
        carte.setWidth(100);
    }

    public ICouleurWagon getCouleurWagon() {
        return couleurWagon;
    }

}
