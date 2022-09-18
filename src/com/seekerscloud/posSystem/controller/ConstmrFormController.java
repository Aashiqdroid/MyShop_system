package com.seekerscloud.posSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.seekerscloud.posSystem.Modal.Costomer;
import com.seekerscloud.posSystem.db.Database;
import com.seekerscloud.posSystem.view.tm.costomerTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

public class ConstmrFormController {
    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;

    public TableView<costomerTM> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;
    public JFXButton btnSaveCstmr;
    public AnchorPane costomerFormContext;
    public TextField txtSearch;
    private String searchtext="";


    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCstomers(searchtext);

        tblCustomer.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

                    if (null != newValue) {
                        setData(newValue);
                    }
                });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchtext=newValue;
            searchCstomers(searchtext);


        });
    }

    private void setData(costomerTM tm) {
        txtID.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtSalary.setText(String.valueOf(tm.getSalary()));

        btnSaveCstmr.setText("Update");


    }

    private void clearFields() {
        txtID.clear();
        txtAddress.clear();
        txtName.clear();
        txtSalary.clear();
    }

    private void searchCstomers(String text) {

        ObservableList<costomerTM> tmList = FXCollections.observableArrayList();

        for (Costomer c1 : Database.cstomerTable
        ) {

            if (c1.getName().contains(text)|| c1.getAddress().contains(text)) {

                Button btn = new Button("Delete");
                costomerTM tm = new costomerTM(c1.getId(), c1.getName(), c1.getAddress(), c1.getSalary(), btn);
                tmList.add(tm);
                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get() == ButtonType.YES) {
                        boolean isDeleted = Database.cstomerTable.remove(c1);
                        if (isDeleted) {
                            searchCstomers(searchtext);
                            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Error, not deleted").show();
                        }
                    }
                });

            }


        }
        tblCustomer.setItems(tmList);
    }


    public void saveconstomerOnAction(ActionEvent actionEvent) {

        Costomer c1 = new Costomer(txtID.getText(),
                txtName.getText(), txtAddress.getText()
                , Double.parseDouble(txtSalary.getText()));

        if (btnSaveCstmr.getText().
                equalsIgnoreCase("save")) {
            boolean isSaved = Database.cstomerTable.add(c1);
            if (isSaved) {
                searchCstomers(searchtext);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Saved").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Error, not saved").show();
            }
        } else {
            for (int i = 0; i < Database.cstomerTable.size(); i++) {
                if (txtID.getText().equalsIgnoreCase(Database.cstomerTable.get(i).getId())) {



                    Database.cstomerTable.get(i).setName(txtName.getText());

                    Database.cstomerTable.get(i).setAddress(txtAddress.getText());
                    Database.cstomerTable.get(i).setSalary(Double.parseDouble(txtSalary.getText()));

                    searchCstomers(searchtext);
                    new Alert(Alert.AlertType.INFORMATION, "Updated").show();
                    clearFields();
                }
            }
        }
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) costomerFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashBoradForm.fxml"))));
    }

    public void newCostomerOnAction(ActionEvent actionEvent) {

        btnSaveCstmr.setText("save");

    }
}
