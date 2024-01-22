package PerfectReservations;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AirlineFX extends Application {

    private TextField textField;
    private PasswordField passwordField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MYLOGIN");

        VBox mainLayout = new VBox(10);
        mainLayout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(255, 153, 204), CornerRadii.EMPTY, Insets.EMPTY)));
        mainLayout.setPadding(new Insets(10));

        Label loginLabel = new Label("LOGIN");
        loginLabel.setFont(new Font("Tahoma", 19));

        Label passwordLabel = new Label("PASSWORD");
        passwordLabel.setFont(new Font("Tahoma", 19));

        Button okButton = new Button("OK");
        okButton.setFont(new Font("Tahoma", 19));

        textField = new TextField();
        textField.setMinWidth(189);

        passwordField = new PasswordField();
        passwordField.setMinWidth(189);

        Label titleLabel = new Label("LOGIN FORM");
        titleLabel.setFont(new Font("Dialog", 44));

       
        HBox buttonBox = new HBox(okButton);
        buttonBox.setSpacing(10);

        mainLayout.getChildren().addAll(loginLabel, textField, passwordLabel, passwordField, buttonBox, titleLabel);

        okButton.setOnAction(e -> {
            primaryStage.close();
            new MainframeFX();
        });

        Scene scene = new Scene(mainLayout, 960, 586);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
