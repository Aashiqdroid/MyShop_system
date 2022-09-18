package com.seekerscloud.posSystem.Modal;

public class Item {

    private String code;
    private String description;
    private String unit;

    private Double QtyOnHand;


    public Item() {
    }

    public Item(String code, String description, String unit, Double qtyOnHand) {
        this.code = code;
        this.description = description;
        this.unit = unit;
        QtyOnHand = qtyOnHand;
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



}
