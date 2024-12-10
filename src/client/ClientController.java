package client;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class ClientController implements Initializable {
    @FXML
    private Label labelBan;
    @FXML
    private TextArea textAreaTrucTuyen;
    @FXML
    private TextArea textAreaNoiDung;
    @FXML
    private TextField textFieldSoanThao;
    @FXML
    private ComboBox<String> comboBoxChonNguoiNhan;

    private String serverName = "localhost";
    private int port = 3333;
    private ClientModel t;

    @Override
    public void initialize(URL url, ResourceBundle res) {
        ketNoiMayChu();
    }

    public void ketNoiMayChu() {
        try {
            t = new ClientModel(serverName, port, labelBan, textAreaTrucTuyen, textAreaNoiDung, textFieldSoanThao, comboBoxChonNguoiNhan);
            t.start();
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
            return;
        }
    }

    public void hangDongGui(ActionEvent event) {
        t.gui();
    }
    
    @FXML
    private void chonVaGuiAnh() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn ảnh để gửi");
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) {
            try {
                ClientModel client = new ClientModel("localhost", 3333);
                client.guiAnh(file.getAbsolutePath());
                textAreaNoiDung.appendText("Đã gửi ảnh: " + file.getName() + "\n");
            } catch (IOException e) {
                textAreaNoiDung.appendText("Lỗi khi gửi ảnh: " + e.getMessage() + "\n");
            }
        }
    }
}
