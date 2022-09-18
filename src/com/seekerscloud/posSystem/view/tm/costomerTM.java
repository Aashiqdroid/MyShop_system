package com.seekerscloud.posSystem.view.tm;

import javafx.scene.control.Button;

public class costomerTM {

    private String id;
    private String name;
    private String address;
    private Double salary;
    private Button btn;

    public costomerTM() {


    }

    public costomerTM(String id, String name, String address, Double salary, Button btn) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
