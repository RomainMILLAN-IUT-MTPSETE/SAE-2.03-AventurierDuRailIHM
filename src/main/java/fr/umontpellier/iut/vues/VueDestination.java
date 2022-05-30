package fr.umontpellier.iut.vues;


import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.event.MouseEvent;

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
        button.setPrefHeight(30);

        DropShadow ds = new DropShadow();
        ds.setRadius(10);
        ds.setColor(Color.BLACK);
        ds.setOffsetX(3);
        ds.setOffsetY(3);
        button.setEffect(ds);
        button.setStyle("-fx-background-color: #F6E7D4; -fx-background-radius: 5px;");
        button.setFont(Font.font("Cabin", FontWeight.MEDIUM, 15));
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: #FFE6C7");
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: #F6E7D4");
        });

        this.getChildren().add(button);
    }

    public IDestination getDestination() {
        return destination;
    }

}
