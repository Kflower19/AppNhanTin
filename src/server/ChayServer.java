package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ChayServer extends Application {
    private static Server server;

    @Override
    public void start(Stage primaryStage) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewServer.fxml"));
            Scene canhVat = new Scene(loader.load());

            // Thêm stylesheet ServerView.css
            String css = this.getClass().getResource("ServerView.css").toExternalForm();
            canhVat.getStylesheets().add(css);

            primaryStage.setScene(canhVat);
            primaryStage.setTitle("Server");
            primaryStage.show();

            int port = 3333;
            server = new Server(port);
            new Thread(server::chay).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
