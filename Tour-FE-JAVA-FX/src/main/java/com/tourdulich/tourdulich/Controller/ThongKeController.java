package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Entity.DoanEntity;
import com.tourdulich.tourdulich.Entity.TourChiPhiEntity;
import com.tourdulich.tourdulich.Entity.TourEntity;
import com.tourdulich.tourdulich.Service.TourChiPhiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {

    public TableView<TourChiPhiEntity> table;
    public TableColumn<TourChiPhiEntity, DoanEntity> IDColumn;
    public TableColumn<TourChiPhiEntity, DoanEntity> doanNameColumn;
    public TableColumn<TourChiPhiEntity, DoanEntity> ngayDiColumn;
    public TableColumn<TourChiPhiEntity, DoanEntity> ngayVeColumn;
    public TableColumn<TourChiPhiEntity, BigDecimal> chiColumn;
    public TableColumn<TourChiPhiEntity, DoanEntity> thuColumn;
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    static final NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
    public TableColumn<TourChiPhiEntity, DoanEntity> loiNhuanColumn;
    ObservableList<TourChiPhiEntity> list;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = FXCollections.observableArrayList(TourChiPhiService.getData());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("doan"));
        IDColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(DoanEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(item.getId()));
                }
            }
        });
        doanNameColumn.setCellValueFactory(new PropertyValueFactory<>("doan"));
        doanNameColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(DoanEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getDoanName());
                }
            }
        });
        ngayDiColumn.setCellValueFactory(new PropertyValueFactory<>("doan"));
        ngayDiColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(DoanEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(item.getDoanNgaydi()));
                }
            }
        });
        ngayVeColumn.setCellValueFactory(new PropertyValueFactory<>("doan"));
        ngayVeColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(DoanEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(item.getDoanNgayve()));
                }
            }
        });
        chiColumn.setCellValueFactory(new PropertyValueFactory<>("chiphiTotal"));
        chiColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(defaultFormat.format(item));
                }
            }
        });
        thuColumn.setCellValueFactory(new PropertyValueFactory<>("doan"));
        thuColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(DoanEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(defaultFormat.format(item.getGia().getGiaSotien()));
                }
            }
        });
        loiNhuanColumn.setCellValueFactory(new PropertyValueFactory<>("doan"));
        loiNhuanColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(DoanEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    TourChiPhiEntity entity = TourChiPhiService.getByDoanId(item.getId());
                    setText(defaultFormat.format(item.getGia().getGiaSotien().subtract(entity.getChiphiTotal())));
                }
            }
        });
        table.setItems(list);
    }
}
