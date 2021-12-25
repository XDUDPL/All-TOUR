package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Common.ConfirmAlert;
import com.tourdulich.tourdulich.Common.SuccessNoti;
import com.tourdulich.tourdulich.Entity.KhachHangEntity;
import com.tourdulich.tourdulich.Entity.NhanVienEntity;
import com.tourdulich.tourdulich.Service.KhachHangService;
import com.tourdulich.tourdulich.Service.NhanVienService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class NhanVienController implements Controller, Initializable {
    public TableView<NhanVienEntity> table;
    public TableColumn<NhanVienEntity,Integer> idColunm;
    public TableColumn<NhanVienEntity,String> tenColunm;
    public TableColumn<NhanVienEntity,String> sdtColunm;
    public TableColumn<NhanVienEntity, Date> ngaySinhColunm;
    public TableColumn<NhanVienEntity,String> emailColumn;
    public TableColumn<NhanVienEntity,String> nhienVuColumn;
    public DatePicker datePicker;
    public TextField idTextField;
    public TextField tenTextField;
    public TextField sdtTextField;
    public TextField emailTextField;
    public TextField nhiemVuTextField;
    public TextField SearchText;
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    private ObservableList<NhanVienEntity> nhanVienEntities;
    @Override
    public void onTableClick() {
        NhanVienEntity item = table.getSelectionModel().getSelectedItem();
        if(item==null) return;
        datePicker.setValue(new java.sql.Date(item.getNvNgaysinh().getTime()).toLocalDate());
        idTextField.setText(item.getId()+"");
        tenTextField.setText(item.getNvTen());
        sdtTextField.setText(item.getNvSdt());
        emailTextField.setText(item.getNvEmail());
        nhiemVuTextField.setText(item.getNvNhiemvu());
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
        addButton.setDisable(isValidAddData());
        updateButton.setDisable(isValidUpadte());
        deleteButton.setDisable(isValidUpadte());
    }

    @Override
    public void clearForm() {
        datePicker.setValue(null);
        idTextField.setText("");
        tenTextField.setText("");
        sdtTextField.setText("");
        emailTextField.setText("");
        nhiemVuTextField.setText("");
        table.setItems(nhanVienEntities);
        valid();
    }

    @Override
    public void addEntity() {
        NhanVienEntity entity = new NhanVienEntity();
        entity.setNvTen(tenTextField.getText());
        entity.setNvSdt(sdtTextField.getText());
        entity.setNvEmail(emailTextField.getText());
        entity.setNvNhiemvu(nhiemVuTextField.getText());
        entity.setNvNgaysinh(datePicker.getValue());
        ConfirmAlert alert = new ConfirmAlert("Xác nhận thêm nhân viên");
        if (alert.getConfirm()) {
            NhanVienEntity result = NhanVienService.add(entity);
            new SuccessNoti(result != null, SuccessNoti.THEM);
            nhanVienEntities.add(result);
        } else {
            new SuccessNoti(true, SuccessNoti.HUY);
        }
    }

    @Override
    public void updateEntity() {
        NhanVienEntity entity = new NhanVienEntity();
        entity.setId(Integer.valueOf(idTextField.getText()));
        entity.setNvTen(tenTextField.getText());
        entity.setNvSdt(sdtTextField.getText());
        entity.setNvEmail(emailTextField.getText());
        entity.setNvNhiemvu(nhiemVuTextField.getText());
        entity.setNvNgaysinh(datePicker.getValue());
        NhanVienEntity nv = nhanVienEntities.stream().filter(item->{
            return Objects.equals(item.getId(), entity.getId());
        }).findAny().get();
        if(entity.Compare(nv)){
            new SuccessNoti("Không có thay đổi");
        }else{
            ConfirmAlert alert = new ConfirmAlert("Xác nhận Sửa khách hàng");
            if (alert.getConfirm()) {
                NhanVienEntity result = NhanVienService.update(entity, entity.getId());
                if (result != null) {
                    new SuccessNoti(true, SuccessNoti.SUA);
                    for (int i = 0; i < nhanVienEntities.size(); i++) {
                        if (Objects.equals(nhanVienEntities.get(i).getId(), result.getId())) {
                            nhanVienEntities.set(i, result);
                            break;
                        }
                    }
                } else {
                    new SuccessNoti(false, SuccessNoti.SUA);
                }
            } else {
                new SuccessNoti(true, SuccessNoti.HUY);
            }
        }
    }

    @Override
    public void deleteEntity() {
        Integer id = Integer.valueOf(idTextField.getText());
        ConfirmAlert alert = new ConfirmAlert("Xác nhận xoá khách hàng");
        if (alert.getConfirm()) {
            if (NhanVienService.delete(id)) {
                new SuccessNoti(true, SuccessNoti.XOA);
                for (int i = 0; i < nhanVienEntities.size(); i++) {
                    if (nhanVienEntities.get(i).getId() == id) {
                        nhanVienEntities.remove(i);
                        break;
                    }
                }
            } else {
                new SuccessNoti(false, SuccessNoti.XOA);
            }

        } else {
            new SuccessNoti(true, SuccessNoti.HUY);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nhanVienEntities = FXCollections.observableArrayList(NhanVienService.getData());
        idColunm.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColunm.setCellValueFactory(new PropertyValueFactory<>("nvTen"));
        sdtColunm.setCellValueFactory(new PropertyValueFactory<>("nvSdt"));
        ngaySinhColunm.setCellValueFactory(new PropertyValueFactory<>("nvNgaysinh"));
        ngaySinhColunm.setCellFactory(column -> new TableCell<>() {
            private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(item));
                }
            }
        });
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("nvEmail"));
        nhienVuColumn.setCellValueFactory(new PropertyValueFactory<>("nvNhiemvu"));
        table.setItems(nhanVienEntities);
        datePicker.setConverter(
                new StringConverter<>() {
                    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    @Override
                    public String toString(LocalDate date) {
                        return (date != null) ? dateFormatter.format(date) : "";
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        return (string != null && !string.isEmpty())
                                ? LocalDate.parse(string, dateFormatter)
                                : null;
                    }
                });
        valid();
    }

    public void onSearchAction(ActionEvent actionEvent) {
        String input = SearchText.getText().toLowerCase();
        if(input.isBlank()){
            table.setItems(nhanVienEntities);
            return;
        }
        NhanVienEntity[] a = nhanVienEntities.stream().filter(e->{
            return e.getId().toString().contains(input)||e.getNvTen().toLowerCase().contains(input)
                    ||e.getNvEmail().toLowerCase().contains(input)
                    ||e.getNvSdt().toLowerCase().contains(input)
                    ||e.getNvNhiemvu().toLowerCase().contains(input);
        }).toArray(NhanVienEntity[]::new);
        ObservableList<NhanVienEntity> list = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(list);
    }
}
