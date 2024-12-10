package server;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class DieuKhienServer {
    @FXML
    private TextArea textAreaThongBao;

    private static DieuKhienServer instance;

    public DieuKhienServer() {
        instance = this;
    }

    public static DieuKhienServer getInstance() {
        return instance;
    }

    public void themThongBao(String thongBao) {
        textAreaThongBao.appendText(thongBao + "\n");
    }
}
