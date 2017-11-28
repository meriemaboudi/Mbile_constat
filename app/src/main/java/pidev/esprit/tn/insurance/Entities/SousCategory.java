package com.example.oualhaomar.insurancepi.Entities;

/**
 * Created by oualhaomar on 23/11/2017.
 */

public class SousCategory {

    private int idSousCategory;
    private String Name;


    public SousCategory() {
    }

    public SousCategory(int idSousCategory, String name) {
        this.idSousCategory = idSousCategory;
        Name = name;
    }

    public int getIdSousCategory() {
        return idSousCategory;
    }

    public void setIdSousCategory(int idSousCategory) {
        this.idSousCategory = idSousCategory;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
