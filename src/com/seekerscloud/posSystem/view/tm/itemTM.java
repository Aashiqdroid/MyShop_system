package com.seekerscloud.posSystem.view.tm;

import javafx.scene.control.Button;

public class itemTM {

    private String code;
    private String description;
    private String unit;

    private Double QtyOnHand;
    private Button btn;

    public itemTM() {
    }

    public itemTM(String code, String description, String unit, Double qtyOnHand, Button btn) {
        this.code = code;
        this.description = description;
        this.unit = unit;
        QtyOnHand = qtyOnHand;
        this.btn = btn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(Double qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
