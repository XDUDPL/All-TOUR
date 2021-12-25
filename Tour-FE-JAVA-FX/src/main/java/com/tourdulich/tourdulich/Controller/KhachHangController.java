package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Common.ConfirmAlert;
import com.tourdulich.tourdulich.Common.SuccessNoti;
import com.tourdulich.tourdulich.Entity.KhachHangEntity;
import com.tourdulich.tourdulich.Entity.LoaiTourEntity;
import com.tourdulich.tourdulich.Service.KhachHangService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class KhachHangController implements Initializable, Controller {

    public Button addButton;

    public Button updateButton;

    public Button deleteButton;
    public TextField SearchText;

    @FXML
    private TableView<KhachHangEntity> table;

    @FXML
    private TableColumn<KhachHangEntity, Integer> idColunm;

    @FXML
    private TableColumn<KhachHangEntity, String> tenColunm;

    @FXML
    private TableColumn<KhachHangEntity, String> sdtColunm;

    @FXML
    private TableColumn<KhachHangEntity, Date> ngaySinhColunm;

    @FXML
    private TableColumn<KhachHangEntity, String> emailColumn;

    @FXML
    private TableColumn<KhachHangEntity, String> cmndColunm;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField tenTextField;

    @FXML
    private TextField sdtTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField cmndTextFeild;

    private ObservableList<KhachHangEntity> khachHangList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        khachHangList = FXCollections.observableArrayList(KhachHangService.getData());
        idColunm.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColunm.setCellValueFactory(new PropertyValueFactory<>("khTen"));
        sdtColunm.setCellValueFactory(new PropertyValueFactory<>("khSdt"));
        ngaySinhColunm.setCellValueFactory(new PropertyValueFactory<>("khNgaysinh"));
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
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("khEmail"));
        cmndColunm.setCellValueFactory(new PropertyValueFactory<>("khCmnd"));
        table.setItems(khachHangList);
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


    public void onTableClick() {
        KhachHangEntity kh = table.getSelectionModel().getSelectedItem();
        if (kh != null) {
            idTextField.setText(String.valueOf(kh.getId()));
            tenTextField.setText(kh.getKhTen());
            sdtTextField.setText(kh.getKhSdt());
            datePicker.setValue(new java.sql.Date(kh.getKhNgaysinh().getTime()).toLocalDate());
            emailTextField.setText(kh.getKhEmail());
            cmndTextFeild.setText(kh.getKhCmnd());
        }
        valid();
    }

    public boolean isValidAddData() {
        String data = idTextField.getText();
        return !data.isEmpty();
    }

    public boolean isValidUpadte() {
        String data = idTextField.getText();
        return data.isEmpty();
    }

    public void valid() {
        addButton.setDisable(isValidAddData());
        updateButton.setDisable(isValidUpadte());
        deleteButton.setDisable(isValidUpadte());
    }

    public void clearForm() {
        idTextField.setText("");
        tenTextField.setText("");
        sdtTextField.setText("");
        datePicker.setValue(null);
        emailTextField.setText("");
        cmndTextFeild.setText("");
        khachHangList = FXCollections.observableArrayList(KhachHangService.getData());
        table.setItems(khachHangList);
        valid();
    }

    public void addEntity() {
        KhachHangEntity kh = new KhachHangEntity();
        kh.setKhTen(tenTextField.getText());
        kh.setKhSdt(sdtTextField.getText());
        kh.setKhNgaysinh(datePicker.getValue());
        kh.setKhEmail(emailTextField.getText());
        kh.setKhCmnd(cmndTextFeild.getText());

        ConfirmAlert alert = new ConfirmAlert("Xác nhận thêm khách hàng");
        if (alert.getConfirm()) {
            KhachHangEntity result = KhachHangService.add(kh);
            new SuccessNoti(result != null, SuccessNoti.THEM);
            khachHangList.add(result);
        } else {
            new SuccessNoti(true, SuccessNoti.HUY);
        }
    }

    public void updateEntity() {
        KhachHangEntity khinput = new KhachHangEntity();
        khinput.setId(Integer.valueOf(idTextField.getText()));
        khinput.setKhTen(tenTextField.getText());
        khinput.setKhSdt(sdtTextField.getText());
        khinput.setKhNgaysinh(datePicker.getValue());
        khinput.setKhEmail(emailTextField.getText());
        khinput.setKhCmnd(cmndTextFeild.getText());
        KhachHangEntity kh = khachHangList.stream().filter((item) ->
                Objects.equals(item.getId(), khinput.getId())).findAny().get();

        if (kh.Compare(khinput)) {
            new SuccessNoti("Không có thay đổi");
        } else {
            ConfirmAlert alert = new ConfirmAlert("Xác nhận Sửa khách hàng");
            if (alert.getConfirm()) {
                KhachHangEntity result = KhachHangService.update(khinput, khinput.getId());
                if (result != null) {
                    new SuccessNoti(true, SuccessNoti.SUA);
                    for (int i = 0; i < khachHangList.size(); i++) {
                        if (Objects.equals(khachHangList.get(i).getId(), result.getId())) {
                            khachHangList.set(i, result);
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

    public void deleteEntity() {
        Integer id = Integer.valueOf(idTextField.getText());
        ConfirmAlert alert = new ConfirmAlert("Xác nhận xoá khách hàng");
        if (alert.getConfirm()) {
            if (KhachHangService.delete(id)) {
                new SuccessNoti(true, SuccessNoti.XOA);
                for (int i = 0; i < khachHangList.size(); i++) {
                    if (khachHangList.get(i).getId() == id) {
                        khachHangList.remove(i);
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

    public void onSearchAction() {
        String input = SearchText.getText().toLowerCase();
        if(input.isBlank()){
            table.setItems(khachHangList);
            return;
        }
        KhachHangEntity[] a = khachHangList.stream().filter(e->{
            return e.getId().toString().contains(input)||e.getKhTen().toLowerCase().contains(input)
                    ||e.getKhCmnd().toLowerCase().contains(input)||e.getKhEmail().toLowerCase().contains(input)
                    ||e.getKhSdt().toLowerCase().contains(input);
        }).toArray(KhachHangEntity[]::new);
        ObservableList<KhachHangEntity> list = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(list);
    }
}
