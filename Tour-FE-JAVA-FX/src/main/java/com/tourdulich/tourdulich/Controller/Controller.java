package com.tourdulich.tourdulich.Controller;

public interface Controller {
    void onTableClick();
    boolean isValidAddData();
    boolean isValidUpadte();
    void valid();
    void clearForm();
    void addEntity();
    void updateEntity();
    void deleteEntity();
}
