package com.example.oualhaomar.insurancepi.Entities;

/**
 * Created by oualhaomar on 22/11/2017.
 */

public class Category {

    private  int idCategory;
    private String NameCategory;


    public Category() {
    }

    public Category(int idCategory, String nameCategory) {
        this.idCategory = idCategory;
        NameCategory = nameCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return NameCategory;
    }

    public void setNameCategory(String nameCategory) {
        NameCategory = nameCategory;
    }

}
