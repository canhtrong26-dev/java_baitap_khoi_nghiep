public class HoaDon {
    private String maHD;
    private String maSach;
    private String maNV;
    private int soLuong;
    private double giaBan;
    private String ngayBan;

    public HoaDon() {
    }

    public HoaDon(String maHD, String maSach, String maNV, int soLuong, double giaBan, String ngayBan) {
        this.maHD = maHD;
        this.maSach = maSach;
        this.maNV = maNV;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngayBan = ngayBan;
    }

    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getGiaBan() { return giaBan; }
    public void setGiaBan(double giaBan) { this.giaBan = giaBan; }

    public String getNgayBan() { return ngayBan; }
    public void setNgayBan(String ngayBan) { this.ngayBan = ngayBan; }

    public double getThanhTien() {
        return soLuong * giaBan;
    }

    @Override
    public String toString() {
        return String.format("| %-6s | %-6s | %-6s | %6d | %10.0f | %12.0f | %-12s |",
                maHD, maSach, maNV, soLuong, giaBan, getThanhTien(), ngayBan);
    }
}
