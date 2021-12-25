package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Entity.LoaiChiPhiEntity;
import com.tourdulich.tourdulich.Entity.LoaiTourEntity;
import com.tourdulich.tourdulich.Service.LoaiChiPhiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class AddChiPhiController implements Initializable {
    public TextArea commentText;
    public TextField chiPhiTextInput;
    public ComboBox<LoaiChiPhiEntity> loaiChiPhiComboBox;
    public ObservableList<LoaiChiPhiEntity> list;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = FXCollections.observableArrayList(LoaiChiPhiService.getData());
        loaiChiPhiComboBox.setItems(list);
        loaiChiPhiComboBox.getSelectionModel().selectFirst();
        loaiChiPhiComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(LoaiChiPhiEntity loaiTourEntity) {
                return loaiTourEntity.getId()+": "+loaiTourEntity.getCpTen();
            }

            @Override
            public LoaiChiPhiEntity fromString(String s) {
                String id = s.substring(0,s.indexOf(":"));
                return list.stream().filter(loai ->id.equals(String.valueOf(loai.getId()))).findFirst().get();
            }
        });
    }
}
