package server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static volatile XuLy xuly = new XuLy();
    
    private ServerSocket serverSocket = null;
    private ThreadNhapXuat threadNhapXuat;
    
    public Server(int port) throws IOException {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            DieuKhienServer.getInstance().themThongBao("Lỗi rồi (1): " + e.getMessage());
        }
    }
    
    public void chay() {
        int userID = 1;
        Socket incomingSocket;
        try {
            while (true) {
                DieuKhienServer.getInstance().themThongBao("Máy chủ: Đang chờ máy trạm đăng nhập");
                
                incomingSocket = serverSocket.accept();
                DieuKhienServer.getInstance().themThongBao("Máy chủ: Đã có máy trạm " + incomingSocket.getRemoteSocketAddress()
                        + " kết nối vào máy chủ " + incomingSocket.getLocalSocketAddress());
                
                threadNhapXuat = new ThreadNhapXuat(incomingSocket, userID++);
                threadNhapXuat.start();
                
                xuly.themVao(threadNhapXuat);
                DieuKhienServer.getInstance().themThongBao("Máy chủ: Số thread đang chạy là: " + xuly.getKichThuoc());
            }
        } catch (IOException e) {
            DieuKhienServer.getInstance().themThongBao("Lỗi rồi (2): " + e.getMessage());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                DieuKhienServer.getInstance().themThongBao("Lỗi rồi (3): " + e.getMessage());
            }
        }
    }
    
    int num = 0;
    private void nhanAnh(Socket incomingSocket) throws IOException {
        InputStream inputStream = incomingSocket.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("received_image"+(num++)+".jpg");
        
        byte[] buffer = new byte[4096];
        int bytesRead;
        
        // Đọc và lưu ảnh
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }
        
        fileOutputStream.close();
        System.out.println("Ảnh đã nhận và lưu thành công tại 'received_image"+(num)+".jpg'.");
    }
}
