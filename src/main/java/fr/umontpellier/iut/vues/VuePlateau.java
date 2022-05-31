package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.Route;
import fr.umontpellier.iut.rails.Ville;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import javax.swing.event.ChangeListener;
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
    private Boolean listenerSet = false;

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

        if(this.listenerSet == false){
            for(int i=0; i<jeu.getRoutes().size(); i++){
                Route r = (Route) jeu.getRoutes().get(i);
                if(r.getProprietaire() == null){
                    r.proprietaireProperty().addListener(event -> {
                        System.out.println("Route - Changement de proprietaire");
                        for (Node nRoute : routes.getChildren()) {
                            Group gRoute = (Group) nRoute;
                            //System.out.println(nRoute);
                            String stringRoute = String.valueOf(nRoute);
                            if (stringRoute.startsWith("Group")) {
                                stringRoute = stringRoute.substring(9);
                                stringRoute = stringRoute.replace(stringRoute.substring(stringRoute.length() - 1), "");
                            }
                            if(stringRoute.equalsIgnoreCase(r.getNom()) || stringRoute.equalsIgnoreCase(r.getNom())){
                                int numRect = 0;
                                for (Node nRect : gRoute.getChildren()) {
                                    Rectangle rect = (Rectangle) nRect;
                                    //rect.setFill();
                                    //rect.setFill(new Color(1,0,0,1.0));
                                    //rect.setFill(Color.web(VueDuJeu.convertFrenchColorToEnglishColor(r.getProprietaire().getCouleur().toString())));
                                    rect.setFill(new ImagePattern(new Image("images/wagons/image-wagon-" + r.getProprietaire().getCouleur().toString() + ".png")));
                                    //rect.setFill(new ImagePattern(new Image("images/wagons/image-wagon-BLEU.png")));
                                    bindRectangle(rect, DonneesPlateau.getRoute(nRoute.getId()).get(numRect).getLayoutX(), DonneesPlateau.getRoute(nRoute.getId()).get(numRect).getLayoutY());
                                    numRect++;
                                }
                            }

                        }
                    });
                }
            }
            for(int i=0; i<jeu.getVilles().size(); i++){
                Ville v = (Ville) jeu.getVilles().get(i);
                if(v.getProprietaire() == null){
                    v.proprietaireProperty().addListener(event -> {
                        System.out.println("Ville - Changement de proprietaire");
                        for (Node nVille : villes.getChildren()) {
                            if(nVille.getId().equals(v.getNom())){
                                Circle ville = (Circle) nVille;
                                //ville.setFill(new Color(1,0,0,1.0));
                                //ville.setFill(Color.web(VueDuJeu.convertFrenchColorToEnglishColor(v.getProprietaire().getCouleur().toString())));
                                ville.setFill(new ImagePattern(new Image("images/gares/gare-" + v.getProprietaire().getCouleur().toString() + ".png")));
                            }
                        }
                    });
                }
            }
            listenerSet = true;
        }
    }

    @FXML
    ImageView image;
    @FXML
    private Group villes;
    @FXML
    private Group routes;

    public void creerBindings() {

        Lighting l = new Lighting(new Light.Distant(30,30,Color.WHITE));
        l.setDiffuseConstant(100);
        image.setEffect(l);
        bindRedimensionPlateau();
    }

    private void bindRedimensionPlateau() {
        bindRoutes();
        bindVilles();
        image.setPreserveRatio(true);
        image.setFitWidth(300);
        this.getScene().widthProperty().addListener(e -> {
            this.setMinWidth(getScene().getWidth()/1.75);
            this.setWidth(getScene().getWidth()/1.75);
        });
        this.getScene().heightProperty().addListener(e -> {
            this.setMinHeight(getScene().getHeight()/1.75);
            this.setHeight(getScene().getHeight()/1.75);
        });
        image.fitHeightProperty().bind(getScene().heightProperty().divide(1.75));
        image.fitWidthProperty().bind(getScene().widthProperty().divide(1.75));
        /*BorderPane
        getScene().widthProperty().addListener(e -> {
            this.setTranslateX(((1326 - (getScene().getWidth()/2))/3)+100);
        });
        getScene().heightProperty().addListener(e -> {
            this.setTranslateY((855 - (getScene().getHeight()/2))/3);
        });*/
        /*GridPane*/
        this.setTranslateX(50);
        this.setTranslateY(50);

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
            Group gRoute = (Group) nRoute;
            int numRect = 0;
            for (Node nRect : gRoute.getChildren()) {
                Rectangle rect = (Rectangle) nRect;
                rect.setFill(new Color(0,0,0,0.0));
                //rect.setFill(new ImagePattern(new Image("images/wagons/image-wagon-BLEU.png")));
                bindRectangle(rect, DonneesPlateau.getRoute(nRoute.getId()).get(numRect).getLayoutX(), DonneesPlateau.getRoute(nRoute.getId()).get(numRect).getLayoutY());
                numRect++;
            }
        }
    }

    private void bindVilles() {
        for (Node nVille : villes.getChildren()) {
            Circle ville = (Circle) nVille;
            ville.setFill(new Color(0,0,0,0.0));
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
