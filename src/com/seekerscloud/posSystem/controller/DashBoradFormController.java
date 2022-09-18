package com.seekerscloud.posSystem.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DashBoradFormController {
    public AnchorPane dashBoardContext;
    public Label lblTime;


    public void initialize() {
        setTime();
    }


    public void openCstmrFormOnAction(ActionEvent actionEvent) throws IOException {
        setUI("constmrForm");
    }

    public void openItemFormOnAction(ActionEvent actionEvent) throws IOException {

        setUI("itemForm");

    }

    public void openOrderDetailsFormOnAction(ActionEvent actionEvent) {
    }

    public void openPlaceOrderFormOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String ui) throws IOException {

        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load
                (getClass().getResource("../view/" + ui + ".fxml"))));
        stage.centerOnScreen();

    }


    private void setTime() {
        //setting time
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
