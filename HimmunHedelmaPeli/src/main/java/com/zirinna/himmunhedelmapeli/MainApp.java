package com.zirinna.himmunhedelmapeli;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(640, 480);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        stage.setTitle("Himmun hedelmäpeli");
        stage.setScene(scene);
        stage.show();
        
        final GameLogic game = new GameLogic();
        //game.printBoard();
        
        //main loop
        
        new AnimationTimer() {
            @Override
            public void handle(long time) {
                drawThings(gc, game);
                doThings(game);
            }
        }.start();
    }
    
    private void drawThings(GraphicsContext gc, GameLogic game) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        game.drawEverything(gc, game);
    }
    
    private void doThings(GameLogic game) {
        //TODO later
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
