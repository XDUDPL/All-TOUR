package com.tourdulich.tourdulich.Common;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ConfirmAlert extends Alert {
    public ConfirmAlert(String header) {
        super(AlertType.CONFIRMATION);
        setTitle("Xác nhận");
        setHeaderText(header);
        setContentText("Chọn Yes để xác nhận");
        getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);
    }
    public boolean getConfirm(){
        Optional<ButtonType> result = this.showAndWait();
        if(result.get().getButtonData() == ButtonBar.ButtonData.YES) {
            return true;
        }
        return false;
    }

}
