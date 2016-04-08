package com.zirinna.himmunhedelmapeli;

import com.zirinna.himmunhedelmapeli.gamelogic.GameLogic;
import java.util.Stack;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
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
        final Stack<MouseEvent> mouseEvents = new Stack<>();
        //game.printBoard();
        
        
        //setup listeners
        scene.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    mouseEvents.push(e);
                }
            }
        );
        
        //main loop
        new AnimationTimer() {
            @Override
            public void handle(long time) {
                drawThings(gc, game);
                doThings(game, mouseEvents);
            }
        }.start();
    }
    /**
     * tells the board to draw itself
     * @param gc graphicscontext to draw things on
     * @param game current game
     */
    private void drawThings(GraphicsContext gc, GameLogic game) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        game.drawEverything(gc);
    }
    
    private void doThings(GameLogic game, Stack<MouseEvent> mouseEvents) {
        handleUserInput(game, mouseEvents);
        game.updateBoard();
    }
    
    private void handleUserInput(GameLogic game, Stack<MouseEvent> mouseEvents) {
        //empty the mouse event stack and handle user clicks on canvas
        //this while loop goes through the stack in "inverse" order from actual input, but that doesnt matter atm...
        while (!mouseEvents.isEmpty()) {
            MouseEvent e = mouseEvents.pop();
            game.mouseClickAtCoordinates(e.getSceneX(), e.getSceneY());
        }
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
