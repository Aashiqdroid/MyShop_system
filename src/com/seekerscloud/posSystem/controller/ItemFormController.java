package com.seekerscloud.posSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.seekerscloud.posSystem.Modal.Costomer;
import com.seekerscloud.posSystem.Modal.Item;
import com.seekerscloud.posSystem.db.Database;
import com.seekerscloud.posSystem.view.tm.costomerTM;
import com.seekerscloud.posSystem.view.tm.itemTM;
import javafx.collections.FXCollections;
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

public class ItemFormController {
    public TextField txtCode;
    public TextField txtDescription;

    public TextField txtUnit;
    public TextField txtQtyOnHand;
    public TextField txtSearch;

    public AnchorPane itemWindowContextID;
    public JFXButton btnSaveItem;



    public String searchtext = "";
    public TableView<itemTM> tblItem;
    public TableColumn colCode;
    public TableColumn colDesc;
    public TableColumn colUnit;
    public TableColumn colQty;
    public TableColumn colOption;

    public void initialize() {

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyonhand"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchItems(searchtext);

        tblItem.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

                    if (null != newValue) {
                        setData(newValue);
                    }
                });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchtext=newValue;
            searchItems(searchtext);


        });
    }

    private void setData(itemTM tm) {
        txtCode.setText(tm.getCode());
        txtDescription.setText(tm.getDescription());
        txtUnit.setText(tm.getUnit());
        txtQtyOnHand.setText(String.valueOf(tm.getQtyOnHand()));

        btnSaveItem.setText("Update");


    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage =(Stage) itemWindowContextID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashBoradForm.fxml"))));

    }

    public void newItemrOnAction(ActionEvent actionEvent) {
        btnSaveItem.setText("save");
    }

    public void saveItemOnAction(ActionEvent actionEvent) {

        Item i1 = new Item(txtCode.getText(),
                txtDescription.getText(),txtUnit.getText()
        ,Double.parseDouble(txtQtyOnHand.getText()));

        if (btnSaveItem.getText().equalsIgnoreCase("save")){
            boolean isSaved = Database.itemsTable.add(i1);
            if (isSaved){
                searchItems(searchtext);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Saved").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Error, not saved").show();

            }
        }else {
            for (int i = 0; i < Database.itemsTable.size(); i++) {
                if (txtCode.getText().equalsIgnoreCase(Database.itemsTable.get(i).getCode())) {



                    Database.itemsTable.get(i).setDescription(txtDescription.getText());


                    searchItems(searchtext);
                    new Alert(Alert.AlertType.INFORMATION, "Updated").show();
                    clearFields();
                }
            }
        }


    }

    private void clearFields() {
        txtCode.clear();
        txtDescription.clear();
        txtUnit.clear();
        txtQtyOnHand.clear();
    }

    private void searchItems(String text){
        ObservableList<itemTM> tmList = FXCollections.observableArrayList();

        for (Item i1 : Database.itemsTable
        ) {

            if (i1.getDescription().contains(text)) {

                Button btn = new Button("Delete");
                itemTM tm = new itemTM(i1.getCode(), i1.getDescription(),
                        i1.getUnit(), i1.getQtyOnHand(), btn);
                tmList.add(tm);
                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get() == ButtonType.YES) {
                        boolean isDeleted = Database.cstomerTable.remove(i1);
                        if (isDeleted) {
                            searchItems(searchtext);
                            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Error, not deleted").show();
                        }
                    }
                });

            }


        }
        tblItem.setItems(tmList);
    }


}
