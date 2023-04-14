package com.example.myfirebaseapphit.models;

import java.io.Serializable;
import java.util.List;

public class State implements Serializable {
    private String name;
    private List<String> borders = null;
    private String nativeName;
    private String flag;

    public State(String name, List<String> borders, String nativeName, String flag) {
        this.name = name;
        this.borders = borders;
        this.nativeName = nativeName;
        this.flag = flag;
    }

    public State(String name, String nativeName) {
        this.name = name;
        this.nativeName = nativeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
