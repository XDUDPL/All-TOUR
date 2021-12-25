package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Common.ConfirmAlert;
import com.tourdulich.tourdulich.Common.SuccessNoti;
import com.tourdulich.tourdulich.Entity.LoaiTourEntity;
import com.tourdulich.tourdulich.Service.LoaiTourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoaiTourController  implements Initializable,Controller {
    public TextField SearchText;
    @FXML
    private TableView<LoaiTourEntity> table;
    @FXML
    private Button clearButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField tenTextField;
    @FXML
    private TextField moTaTextFeild;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableColumn<LoaiTourEntity, Integer> idColumn;
    @FXML
    private TableColumn<LoaiTourEntity,String> tenColumn;
    @FXML
    private TableColumn<LoaiTourEntity,String> motaColumn;
    private ObservableList<LoaiTourEntity> loaiToutList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loaiToutList = FXCollections.observableArrayList(LoaiTourService.getData());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("loaiTen"));
        motaColumn.setCellValueFactory(new PropertyValueFactory<>("loaiMota"));
        table.setItems(loaiToutList);
        valid();
    }
    @Override
    public void onTableClick() {
        LoaiTourEntity loaiTourEntity = table.getSelectionModel().getSelectedItem();
        if(loaiTourEntity!=null){
            idTextField.setText(String.valueOf(loaiTourEntity.getId()));
            tenTextField.setText(loaiTourEntity.getLoaiTen());
            moTaTextFeild.setText(loaiTourEntity.getLoaiMota());
        }
        valid();
    }

    @Override
    public boolean isValidAddData() {
        String data = idTextField.getText();
        return !data.isEmpty();
    }

    @Override
    public boolean isValidUpadte() {
        String data = idTextField.getText();
        return data.isEmpty();
    }

    @Override
    public void valid() {
        addButton.setDisable(this.isValidAddData());
        updateButton.setDisable(this.isValidUpadte());
        deleteButton.setDisable(this.isValidUpadte());
    }

    @Override
    public void clearForm() {
        idTextField.setText("");
        tenTextField.setText("");
        moTaTextFeild.setText("");
        loaiToutList = FXCollections.observableArrayList(LoaiTourService.getData());
        table.setItems(loaiToutList);
        valid();
    }

    @Override
    public void addEntity() {
        LoaiTourEntity loai = new LoaiTourEntity();
        loai.setLoaiTen(tenTextField.getText());
        loai.setLoaiMota(moTaTextFeild.getText());

        ConfirmAlert alert = new ConfirmAlert("Xác nhận thêm loại tour");
        if(alert.getConfirm()){
            LoaiTourEntity result = LoaiTourService.add(loai);
            new SuccessNoti(result!=null,SuccessNoti.THEM);
            loaiToutList.add(result);
        }else{
            new SuccessNoti(true,SuccessNoti.HUY);
        }
    }

    @Override
    public void updateEntity() {
        LoaiTourEntity loaiInput = new LoaiTourEntity();
        loaiInput.setId(Integer.valueOf(idTextField.getText()));
        loaiInput.setLoaiTen(tenTextField.getText());
        loaiInput.setLoaiMota(moTaTextFeild.getText());

        LoaiTourEntity loai = loaiToutList.stream().filter((item)->
                Objects.equals(item.getId(), loaiInput.getId())).findAny().get();
        if(loai.Compare(loaiInput)){
            new SuccessNoti("Không có thay đổi");
        }else{
            ConfirmAlert alert = new ConfirmAlert("Xác nhận sửa loại tour");
            if(alert.getConfirm()){
                LoaiTourEntity result = LoaiTourService.update(loaiInput,loaiInput.getId());
                if(result!=null){
                    new SuccessNoti(true,SuccessNoti.SUA);
                    for(int i=0;i<loaiToutList.size();i++){
                        if(Objects.equals(loaiToutList.get(i).getId(), result.getId())){
                            loaiToutList.set(i,result);
                            break;
                        }
                    }
                }else{
                    new SuccessNoti(false,SuccessNoti.SUA);
                }
            }else{
                new SuccessNoti(true,SuccessNoti.HUY);
            }
        }
    }

    @Override
    public void deleteEntity() {
        Integer id = Integer.valueOf(idTextField.getText());
        ConfirmAlert alert = new ConfirmAlert("Xác nhận xoá loại tour");
        if(alert.getConfirm()){
            if(LoaiTourService.delete(id)) {
                new SuccessNoti(true, SuccessNoti.XOA);
                for(int i=0;i<loaiToutList.size();i++){
                    if(loaiToutList.get(i).getId()==id){
                        loaiToutList.remove(i);
                        break;
                    }
                }
            }else{
                new SuccessNoti(false, SuccessNoti.XOA);
            }

        }else{
            new SuccessNoti(true,SuccessNoti.HUY);
        }
    }

    public void onSearchAction(ActionEvent actionEvent) {
        String input = SearchText.getText();
        if(input.isBlank()){
            table.setItems(loaiToutList);
            return;
        }
        LoaiTourEntity[] a = loaiToutList.stream().filter(e->{
            return e.getId().toString().contains(input.toLowerCase())||e.getLoaiTen().toLowerCase().contains(input)
                    ||e.getLoaiMota().toLowerCase().contains(input);
        }).toArray(LoaiTourEntity[]::new);
        ObservableList<LoaiTourEntity> list = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(list);
    }
}
