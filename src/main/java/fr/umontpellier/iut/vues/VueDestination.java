package fr.umontpellier.iut.vues;


import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Cette classe représente la vue d'une carte Destination.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueDestination extends Pane {

    private IDestination destination;
    Button button;
    VBox vbox;

    public VueDestination(IDestination destination) {
        this.destination = destination;
        button = new Button(destination.getNom());

        this.getChildren().add(button);
    }

    public IDestination getDestination() {
        return destination;
    }

}
