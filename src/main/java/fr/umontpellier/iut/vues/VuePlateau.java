package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.Route;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Cette classe présente les routes et les villes sur le plateau.
 *
 * On y définit le listener à exécuter lorsque qu'un élément du plateau a été choisi par l'utilisateur
 * ainsi que les bindings qui mettront ?à jour le plateau après la prise d'une route ou d'une ville par un joueur
 */
public class VuePlateau extends Pane {

    public VuePlateau() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/plateau.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void choixRouteOuVille(MouseEvent e) {
        IJeu jeu = ((VueDuJeu) getScene().getRoot()).getJeu();
        String source = String.valueOf(e.getSource());
        if (source.startsWith("Group")) {
            source = source.substring(9);
            source = source.replace(source.substring(source.length() - 1), "");
            jeu.uneVilleOuUneRouteAEteChoisie(source);
        } else {
            jeu.uneVilleOuUneRouteAEteChoisie(e.getPickResult().getIntersectedNode().getId());
        }
    }

    @FXML
    ImageView image;
    @FXML
    private Group villes;
    @FXML
    private Group routes;

    public void creerBindings() {
        bindRedimensionPlateau();
    }

    private void bindRedimensionPlateau() {
        bindRoutes();
        bindVilles();
        image.setPreserveRatio(true);
        image.fitHeightProperty().bind(getScene().heightProperty().divide(2));
        image.fitWidthProperty().bind(getScene().widthProperty().divide(2));
        getScene().widthProperty().addListener(e -> {
            this.setTranslateX(((1326 - (getScene().getWidth()/2))/2)+100);
        });
        getScene().heightProperty().addListener(e -> {
            this.setTranslateY((855 - (getScene().getHeight()/2))/2);
        });
        /*image.fitWidthProperty().addListener(e ->{
            /*double X = (1326-image.getFitWidth())/ 2;
            double Y = (855-image.getFitHeight())/ 2;
            double X = 0;
            double Y = 0;
            System.out.println(X + " | " + Y);
            this.setTranslateX(X);
            this.setTranslateY(Y);
        });*/
    }

    private void bindRectangle(Rectangle rect, double layoutX, double layoutY) {
        rect.layoutXProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return layoutX * image.getLayoutBounds().getWidth()/ DonneesPlateau.largeurInitialePlateau;
            }
        });
        rect.layoutYProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return layoutY * image.getLayoutBounds().getHeight()/ DonneesPlateau.hauteurInitialePlateau;
            }
        });
        rect.widthProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return DonneesPlateau.largeurRectangle * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
        rect.heightProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return DonneesPlateau.hauteurRectangle * image.getLayoutBounds().getHeight() / DonneesPlateau.hauteurInitialePlateau;
            }
        });
        rect.xProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return DonneesPlateau.xInitial * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
        rect.yProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return DonneesPlateau.yInitial * image.getLayoutBounds().getHeight() / DonneesPlateau.hauteurInitialePlateau;
            }
        });
    }

    private void bindRoutes() {
        for (Node nRoute : routes.getChildren()) {
            /*Image wagonImg = new Image("images/wagons/image-wagon-BLEU.png");
            ImagePattern wagonImgPattern = new ImagePattern(wagonImg);
            rect.setFill(wagonImgPattern);*/

            Group gRoute = (Group) nRoute;
            int numRect = 0;
            for (Node nRect : gRoute.getChildren()) {
                Rectangle rect = (Rectangle) nRect;
                bindRectangle(rect, DonneesPlateau.getRoute(nRoute.getId()).get(numRect).getLayoutX(), DonneesPlateau.getRoute(nRoute.getId()).get(numRect).getLayoutY());
                numRect++;
            }
        }
    }

    private void bindVilles() {
        for (Node nVille : villes.getChildren()) {
            Circle ville = (Circle) nVille;
            bindVille(ville, DonneesPlateau.getVille(ville.getId()).getLayoutX(), DonneesPlateau.getVille(ville.getId()).getLayoutY());
        }
    }

    private void bindVille(Circle ville, double layoutX, double layoutY) {
        ville.layoutXProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return layoutX * image.getLayoutBounds().getWidth()/ DonneesPlateau.largeurInitialePlateau;
            }
        });
        ville.layoutYProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return layoutY * image.getLayoutBounds().getHeight()/ DonneesPlateau.hauteurInitialePlateau;
            }
        });
        ville.radiusProperty().bind(new DoubleBinding() {
            { super.bind(image.fitWidthProperty(), image.fitHeightProperty());}
            @Override
            protected double computeValue() {
                return DonneesPlateau.rayonInitial * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
    }



}
