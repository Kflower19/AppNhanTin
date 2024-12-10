package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ThreadNhapXuat extends Thread {
    private Socket socket;
    private int userID;
    
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    
    public ThreadNhapXuat(Socket socket, int userID) {
        this.socket = socket;
        this.userID = userID;
        DieuKhienServer.getInstance().themThongBao("Máy chủ:: Máy trạm thứ " + userID + " đã kết nối");
    }
    
    public int getUserID() {
        return userID;
    }
    
    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            bufferedReader = new BufferedReader(reader);
            
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);
            bufferedWriter = new BufferedWriter(writer);
            
            DieuKhienServer.getInstance().themThongBao("Máy chủ: Mở kênh Nhập-Xuất dữ liệu với máy trạm " + userID + " thành công");
            
            Server.xuly.guiUserIDChoClient(userID);
            Server.xuly.guiDanhSachUserDangOnline();
            Server.xuly.guiMoiNguoi("capNhatDangNhapDangXuat" + "#~" + "server" + "#~" + " ***username "
                + userID + " đã đăng nhập***");
            
            String thongDiep; 
            while (true) {
                thongDiep = nhap();
                if (thongDiep != null)
                    Server.xuly.chuyenTiepThongDiep(thongDiep, getUserID());
            }
        } catch (IOException e) {
            Server.xuly.loaiRa(userID);
            DieuKhienServer.getInstance().themThongBao("Máy chủ: máy trạm " + userID + " đã thoát: Số thread đang chạy là: " + 
                Server.xuly.getKichThuoc());
        }
        
        Server.xuly.guiDanhSachUserDangOnline();
        Server.xuly.guiMoiNguoi("capNhatDangNhapDangXuat" + "#~" + "server" + "#~" + "***username " + userID + " đã thoát***");
    }
    
    public String nhap() throws IOException {
        return bufferedReader.readLine();
    }
    
    public void xuat(String thongDiep) throws IOException {
        bufferedWriter.write(thongDiep);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}
