import java.util.ArrayList;
import java.util.Comparator;

public class QuanLyNhaSach {
    private ArrayList<Sach> danhSachSach;
    private ArrayList<NhanVien> danhSachNhanVien;
    private ArrayList<PhieuNhap> danhSachPhieuNhap;
    private ArrayList<HoaDon> danhSachHoaDon;

    public QuanLyNhaSach() {
        this.danhSachSach = new ArrayList<>();
        this.danhSachNhanVien = new ArrayList<>();
        this.danhSachPhieuNhap = new ArrayList<>();
        this.danhSachHoaDon = new ArrayList<>();
    }

    public ArrayList<Sach> getDanhSachSach() { return danhSachSach; }
    public ArrayList<NhanVien> getDanhSachNhanVien() { return danhSachNhanVien; }
    public ArrayList<PhieuNhap> getDanhSachPhieuNhap() { return danhSachPhieuNhap; }
    public ArrayList<HoaDon> getDanhSachHoaDon() { return danhSachHoaDon; }

    // ===== CRUD SÁCH =====
    public void themSach(Sach s) {
        danhSachSach.add(s);
    }

    public Sach timSachTheoMa(String maSach) {
        for (Sach s : danhSachSach) {
            if (s.getMaSach().equalsIgnoreCase(maSach)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Sach> timSachTheoTen(String tenSach) {
        ArrayList<Sach> ketQua = new ArrayList<>();
        String tu = tenSach.toLowerCase();
        for (Sach s : danhSachSach) {
            if (s.getTenSach().toLowerCase().contains(tu)) {
                ketQua.add(s);
            }
        }
        return ketQua;
    }

    public boolean suaSach(String maSach, String tenMoi, String tacGiaMoi, String nxbMoi,
                           String theLoaiMoi, double giaNhapMoi, double giaBanMoi) {
        Sach s = timSachTheoMa(maSach);
        if (s == null) return false;
        s.setTenSach(tenMoi);
        s.setTacGia(tacGiaMoi);
        s.setNhaXuatBan(nxbMoi);
        s.setTheLoai(theLoaiMoi);
        s.setGiaNhap(giaNhapMoi);
        s.setGiaBan(giaBanMoi);
        return true;
    }

    public boolean xoaSach(String maSach) {
        Sach s = timSachTheoMa(maSach);
        if (s == null) return false;
        danhSachSach.remove(s);
        return true;
    }

    // ===== CRUD NHÂN VIÊN =====
    public void themNhanVien(NhanVien nv) {
        danhSachNhanVien.add(nv);
    }

    public NhanVien timNhanVienTheoMa(String maNV) {
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getMaNV().equalsIgnoreCase(maNV)) {
                return nv;
            }
        }
        return null;
    }

    public boolean suaNhanVien(String maNV, String hoTenMoi, String chucVuMoi,
                               String sdtMoi, double luongMoi) {
        NhanVien nv = timNhanVienTheoMa(maNV);
        if (nv == null) return false;
        nv.setHoTen(hoTenMoi);
        nv.setChucVu(chucVuMoi);
        nv.setSoDienThoai(sdtMoi);
        nv.setLuong(luongMoi);
        return true;
    }

    public boolean xoaNhanVien(String maNV) {
        NhanVien nv = timNhanVienTheoMa(maNV);
        if (nv == null) return false;
        danhSachNhanVien.remove(nv);
        return true;
    }

    // ===== SẮP XẾP SÁCH =====
    // Tiêu chí 1: theo giá bán tăng dần
    public void sapXepSachTheoGiaBanTang() {
        danhSachSach.sort(Comparator.comparingDouble(Sach::getGiaBan));
    }

    // Tiêu chí 2: theo số lượng tồn giảm dần
    public void sapXepSachTheoTonGiam() {
        danhSachSach.sort((a, b) -> Integer.compare(b.getSoLuongTon(), a.getSoLuongTon()));
    }

    // Tiêu chí 3: theo tên sách (A-Z)
    public void sapXepSachTheoTen() {
        danhSachSach.sort(Comparator.comparing(Sach::getTenSach));
    }

    // ===== NGHIỆP VỤ ĐẶC THÙ =====

    // Nhập hàng: tạo phiếu nhập và tăng tồn kho
    public boolean nhapHang(String maPhieu, String maSach, int soLuong, String ngayNhap) {
        Sach s = timSachTheoMa(maSach);
        if (s == null) return false;
        if (soLuong <= 0) return false;

        PhieuNhap pn = new PhieuNhap(maPhieu, maSach, soLuong, s.getGiaNhap(), ngayNhap);
        danhSachPhieuNhap.add(pn);
        s.setSoLuongTon(s.getSoLuongTon() + soLuong);
        return true;
    }

    // Bán hàng: tạo hóa đơn và giảm tồn kho
    public int banHang(String maHD, String maSach, String maNV, int soLuong, String ngayBan) {
        Sach s = timSachTheoMa(maSach);
        if (s == null) return -1;              // không có sách
        NhanVien nv = timNhanVienTheoMa(maNV);
        if (nv == null) return -2;              // không có nhân viên
        if (soLuong <= 0) return -3;            // số lượng không hợp lệ
        if (s.getSoLuongTon() < soLuong) return -4; // không đủ tồn

        HoaDon hd = new HoaDon(maHD, maSach, maNV, soLuong, s.getGiaBan(), ngayBan);
        danhSachHoaDon.add(hd);
        s.setSoLuongTon(s.getSoLuongTon() - soLuong);
        return 0;
    }

    // Tính tổng tồn kho (tổng giá trị tồn kho theo giá nhập)
    public double tinhTongGiaTriTonKho() {
        double tong = 0;
        for (Sach s : danhSachSach) {
            tong += s.getGiaNhap() * s.getSoLuongTon();
        }
        return tong;
    }

    // Tính tổng số đầu sách và tổng số lượng tồn
    public int tongSoLuongTon() {
        int tong = 0;
        for (Sach s : danhSachSach) {
            tong += s.getSoLuongTon();
        }
        return tong;
    }

    // Tính doanh thu tổng (từ tất cả hóa đơn)
    public double tinhTongDoanhThu() {
        double tong = 0;
        for (HoaDon hd : danhSachHoaDon) {
            tong += hd.getThanhTien();
        }
        return tong;
    }

    // Tính lợi nhuận ước tính (doanh thu - giá vốn hàng đã bán)
    public double tinhLoiNhuan() {
        double loiNhuan = 0;
        for (HoaDon hd : danhSachHoaDon) {
            Sach s = timSachTheoMa(hd.getMaSach());
            if (s != null) {
                loiNhuan += (hd.getGiaBan() - s.getGiaNhap()) * hd.getSoLuong();
            }
        }
        return loiNhuan;
    }

    // Thống kê doanh thu theo nhân viên
    public double doanhThuTheoNhanVien(String maNV) {
        double tong = 0;
        for (HoaDon hd : danhSachHoaDon) {
            if (hd.getMaNV().equalsIgnoreCase(maNV)) {
                tong += hd.getThanhTien();
            }
        }
        return tong;
    }

    // Lọc sách sắp hết hàng (tồn kho dưới ngưỡng)
    public ArrayList<Sach> locSachSapHet(int nguong) {
        ArrayList<Sach> ketQua = new ArrayList<>();
        for (Sach s : danhSachSach) {
            if (s.getSoLuongTon() < nguong) {
                ketQua.add(s);
            }
        }
        return ketQua;
    }
}
