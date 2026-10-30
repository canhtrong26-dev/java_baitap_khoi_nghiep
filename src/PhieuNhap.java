public class PhieuNhap {
    private String maPhieu;
    private String maSach;
    private int soLuong;
    private double giaNhap;
    private String ngayNhap;

    public PhieuNhap() {
    }

    public PhieuNhap(String maPhieu, String maSach, int soLuong, double giaNhap, String ngayNhap) {
        this.maPhieu = maPhieu;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.ngayNhap = ngayNhap;
    }

    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }

    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getGiaNhap() { return giaNhap; }
    public void setGiaNhap(double giaNhap) { this.giaNhap = giaNhap; }

    public String getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(String ngayNhap) { this.ngayNhap = ngayNhap; }

    public double getThanhTien() {
        return soLuong * giaNhap;
    }

    @Override
    public String toString() {
        return String.format("| %-6s | %-6s | %6d | %10.0f | %12.0f | %-12s |",
                maPhieu, maSach, soLuong, giaNhap, getThanhTien(), ngayNhap);
    }
}
