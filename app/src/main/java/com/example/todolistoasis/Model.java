package com.example.todolistoasis;

public class Model {

    private int id;
    private String name;
    private String number;

    public Model() {}

    public Model(String name, String number, String emial) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
