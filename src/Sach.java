public class Sach {
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String nhaXuatBan;
    private String theLoai;
    private double giaNhap;
    private double giaBan;
    private int soLuongTon;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, String tacGia, String nhaXuatBan,
                String theLoai, double giaNhap, double giaBan, int soLuongTon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.theLoai = theLoai;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
    }

    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }

    public String getTenSach() { return tenSach; }
    public void setTenSach(String tenSach) { this.tenSach = tenSach; }

    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }

    public String getNhaXuatBan() { return nhaXuatBan; }
    public void setNhaXuatBan(String nhaXuatBan) { this.nhaXuatBan = nhaXuatBan; }

    public String getTheLoai() { return theLoai; }
    public void setTheLoai(String theLoai) { this.theLoai = theLoai; }

    public double getGiaNhap() { return giaNhap; }
    public void setGiaNhap(double giaNhap) { this.giaNhap = giaNhap; }

    public double getGiaBan() { return giaBan; }
    public void setGiaBan(double giaBan) { this.giaBan = giaBan; }

    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }

    @Override
    public String toString() {
        return String.format("| %-6s | %-28s | %-18s | %-14s | %-12s | %10.0f | %10.0f | %6d |",
                maSach, tenSach, tacGia, nhaXuatBan, theLoai, giaNhap, giaBan, soLuongTon);
    }
}
