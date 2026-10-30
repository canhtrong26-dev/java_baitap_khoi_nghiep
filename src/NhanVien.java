public class NhanVien {
    private String maNV;
    private String hoTen;
    private String chucVu;
    private String soDienThoai;
    private double luong;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTen, String chucVu, String soDienThoai, double luong) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.soDienThoai = soDienThoai;
        this.luong = luong;
    }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getChucVu() { return chucVu; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public double getLuong() { return luong; }
    public void setLuong(double luong) { this.luong = luong; }

    @Override
    public String toString() {
        return String.format("| %-6s | %-22s | %-14s | %-12s | %12.0f |",
                maNV, hoTen, chucVu, soDienThoai, luong);
    }
}
