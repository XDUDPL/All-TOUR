package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Entity.LoaiTourEntity;
import com.tourdulich.tourdulich.Entity.TourEntity;
import com.tourdulich.tourdulich.Service.LoaiTourService;
import com.tourdulich.tourdulich.Service.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddTourController implements Initializable {
    public TableView<TourEntity> table;

    public TableColumn<TourEntity,Integer> idColumn;

    public TableColumn<TourEntity,String> tenColumn;

    public TableColumn<TourEntity,String> motaColumn;

    public TableColumn<TourEntity, LoaiTourEntity> loaiCollumn;

    public ObservableList<TourEntity> toutList;
    public TextField SearchText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toutList = FXCollections.observableArrayList(TourService.getData());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("tourTen"));
        motaColumn.setCellValueFactory(new PropertyValueFactory<>("tourMota"));
        loaiCollumn.setCellValueFactory(new PropertyValueFactory<>("loai"));
        loaiCollumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LoaiTourEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getLoaiTen());
                }
            }
        });
        table.setItems(toutList);
    }

    public void onSearchAction(ActionEvent actionEvent) {
        String input = SearchText.getText().toLowerCase();
        if(input.isBlank()){
            table.setItems(toutList);
            return;
        }
        TourEntity[] a = toutList.stream().filter(e->{
            return e.getId().toString().contains(input)||e.getTourTen().toLowerCase().contains(input)
                    ||e.getTourMota().toLowerCase().contains(input)||e.getLoai().getLoaiTen().contains(input);
        }).toArray(TourEntity[]::new);
        ObservableList<TourEntity> list = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(list);
    }
}
