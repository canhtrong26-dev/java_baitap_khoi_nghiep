import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static QuanLyNhaSach ql = new QuanLyNhaSach();

    public static void main(String[] args) {
        napDuLieuMau();

        int chon;
        do {
            inMenuChinh();
            chon = nhapSoNguyen("Nhap lua chon: ");
            switch (chon) {
                case 1: menuSach(); break;
                case 2: menuNhanVien(); break;
                case 3: menuNhapHang(); break;
                case 4: menuBanHang(); break;
                case 5: menuThongKe(); break;
                case 0: System.out.println("Da thoat chuong trinh. Cam on!"); break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    // ===== MENU =====
    private static void inMenuChinh() {
        System.out.println();
        System.out.println("=========================================================");
        System.out.println("         PHAN MEM QUAN LY CUA HANG SACH - v1.0           ");
        System.out.println("=========================================================");
        System.out.println("  1. Quan ly Sach");
        System.out.println("  2. Quan ly Nhan vien");
        System.out.println("  3. Nhap hang");
        System.out.println("  4. Ban hang");
        System.out.println("  5. Thong ke - Bao cao");
        System.out.println("  0. Thoat");
        System.out.println("---------------------------------------------------------");
    }

    private static void menuSach() {
        int chon;
        do {
            System.out.println();
            System.out.println("------ QUAN LY SACH ------");
            System.out.println(" 1. Hien thi danh sach sach");
            System.out.println(" 2. Them sach moi");
            System.out.println(" 3. Sua thong tin sach");
            System.out.println(" 4. Xoa sach");
            System.out.println(" 5. Tim sach theo ma");
            System.out.println(" 6. Tim sach theo ten");
            System.out.println(" 7. Sap xep theo gia ban (tang)");
            System.out.println(" 8. Sap xep theo so luong ton (giam)");
            System.out.println(" 9. Sap xep theo ten (A-Z)");
            System.out.println(" 0. Quay lai");
            chon = nhapSoNguyen("Chon: ");
            switch (chon) {
                case 1: inDanhSachSach(ql.getDanhSachSach()); break;
                case 2: themSach(); break;
                case 3: suaSach(); break;
                case 4: xoaSach(); break;
                case 5: timSachTheoMa(); break;
                case 6: timSachTheoTen(); break;
                case 7:
                    ql.sapXepSachTheoGiaBanTang();
                    System.out.println("Da sap xep theo gia ban tang dan.");
                    inDanhSachSach(ql.getDanhSachSach());
                    break;
                case 8:
                    ql.sapXepSachTheoTonGiam();
                    System.out.println("Da sap xep theo so luong ton giam dan.");
                    inDanhSachSach(ql.getDanhSachSach());
                    break;
                case 9:
                    ql.sapXepSachTheoTen();
                    System.out.println("Da sap xep theo ten A-Z.");
                    inDanhSachSach(ql.getDanhSachSach());
                    break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    private static void menuNhanVien() {
        int chon;
        do {
            System.out.println();
            System.out.println("------ QUAN LY NHAN VIEN ------");
            System.out.println(" 1. Hien thi danh sach nhan vien");
            System.out.println(" 2. Them nhan vien moi");
            System.out.println(" 3. Sua thong tin nhan vien");
            System.out.println(" 4. Xoa nhan vien");
            System.out.println(" 5. Tim nhan vien theo ma");
            System.out.println(" 0. Quay lai");
            chon = nhapSoNguyen("Chon: ");
            switch (chon) {
                case 1: inDanhSachNhanVien(ql.getDanhSachNhanVien()); break;
                case 2: themNhanVien(); break;
                case 3: suaNhanVien(); break;
                case 4: xoaNhanVien(); break;
                case 5: timNhanVienTheoMa(); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    private static void menuNhapHang() {
        System.out.println();
        System.out.println("------ NHAP HANG ------");
        String maPhieu = nhapChuoi("Ma phieu nhap: ");
        String maSach = nhapChuoi("Ma sach can nhap: ");
        int soLuong = nhapSoNguyen("So luong nhap: ");
        String ngay = nhapChuoi("Ngay nhap (vd 30/10/2026): ");

        boolean ok = ql.nhapHang(maPhieu, maSach, soLuong, ngay);
        if (ok) {
            System.out.println("Nhap hang thanh cong! Da cap nhat ton kho.");
        } else {
            System.out.println("Nhap hang that bai (ma sach khong ton tai hoac so luong khong hop le).");
        }
    }

    private static void menuBanHang() {
        System.out.println();
        System.out.println("------ BAN HANG ------");
        String maHD = nhapChuoi("Ma hoa don: ");
        String maSach = nhapChuoi("Ma sach ban: ");
        String maNV = nhapChuoi("Ma nhan vien ban: ");
        int soLuong = nhapSoNguyen("So luong ban: ");
        String ngay = nhapChuoi("Ngay ban (vd 30/10/2026): ");

        int kq = ql.banHang(maHD, maSach, maNV, soLuong, ngay);
        switch (kq) {
            case 0: System.out.println("Ban hang thanh cong!"); break;
            case -1: System.out.println("Loi: Ma sach khong ton tai."); break;
            case -2: System.out.println("Loi: Ma nhan vien khong ton tai."); break;
            case -3: System.out.println("Loi: So luong khong hop le."); break;
            case -4: System.out.println("Loi: Khong du ton kho de ban."); break;
        }
    }

    private static void menuThongKe() {
        int chon;
        do {
            System.out.println();
            System.out.println("------ THONG KE - BAO CAO ------");
            System.out.println(" 1. Tong gia tri ton kho (theo gia nhap)");
            System.out.println(" 2. Tong doanh thu");
            System.out.println(" 3. Loi nhuan uoc tinh");
            System.out.println(" 4. Doanh thu theo tung nhan vien");
            System.out.println(" 5. Danh sach sach sap het hang");
            System.out.println(" 6. Hien thi tat ca phieu nhap");
            System.out.println(" 7. Hien thi tat ca hoa don");
            System.out.println(" 0. Quay lai");
            chon = nhapSoNguyen("Chon: ");
            switch (chon) {
                case 1:
                    System.out.printf("Tong gia tri ton kho: %.0f VND%n", ql.tinhTongGiaTriTonKho());
                    System.out.printf("Tong so luong ton: %d cuon%n", ql.tongSoLuongTon());
                    break;
                case 2:
                    System.out.printf("Tong doanh thu: %.0f VND%n", ql.tinhTongDoanhThu());
                    break;
                case 3:
                    System.out.printf("Loi nhuan uoc tinh: %.0f VND%n", ql.tinhLoiNhuan());
                    break;
                case 4:
                    System.out.println("Doanh thu theo nhan vien:");
                    for (NhanVien nv : ql.getDanhSachNhanVien()) {
                        double dt = ql.doanhThuTheoNhanVien(nv.getMaNV());
                        System.out.printf("  - %s (%s): %.0f VND%n",
                                nv.getMaNV(), nv.getHoTen(), dt);
                    }
                    break;
                case 5:
                    int nguong = nhapSoNguyen("Nhap nguong ton kho (sach co ton < nguong se duoc liet ke): ");
                    ArrayList<Sach> sh = ql.locSachSapHet(nguong);
                    if (sh.isEmpty()) System.out.println("Khong co sach nao duoi nguong.");
                    else inDanhSachSach(sh);
                    break;
                case 6:
                    inDanhSachPhieuNhap();
                    break;
                case 7:
                    inDanhSachHoaDon();
                    break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    // ===== HAM TIEN ICH NHAP =====
    private static int nhapSoNguyen(String msg) {
        while (true) {
            System.out.print(msg);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Loi: Ban phai nhap mot so nguyen. Vui long nhap lai.");
            }
        }
    }

    private static double nhapSoThuc(String msg) {
        while (true) {
            System.out.print(msg);
            String line = sc.nextLine().trim();
            try {
                double v = Double.parseDouble(line);
                if (v < 0) {
                    System.out.println("Loi: So khong duoc am. Vui long nhap lai.");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Loi: Ban phai nhap mot so. Vui long nhap lai.");
            }
        }
    }

    private static String nhapChuoi(String msg) {
        while (true) {
            System.out.print(msg);
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Loi: Khong duoc de trong. Vui long nhap lai.");
                continue;
            }
            return line;
        }
    }

    // ===== CHUC NANG SACH =====
    private static void themSach() {
        System.out.println();
        System.out.println("--- Them sach moi ---");
        String ma = nhapChuoi("Ma sach: ");
        if (ql.timSachTheoMa(ma) != null) {
            System.out.println("Loi: Ma sach da ton tai.");
            return;
        }
        String ten = nhapChuoi("Ten sach: ");
        String tg = nhapChuoi("Tac gia: ");
        String nxb = nhapChuoi("Nha xuat ban: ");
        String tl = nhapChuoi("The loai: ");
        double gn = nhapSoThuc("Gia nhap: ");
        double gb = nhapSoThuc("Gia ban: ");
        int sl = nhapSoNguyen("So luong ton ban dau: ");
        ql.themSach(new Sach(ma, ten, tg, nxb, tl, gn, gb, sl));
        System.out.println("Da them sach thanh cong!");
    }

    private static void suaSach() {
        String ma = nhapChuoi("Ma sach can sua: ");
        Sach s = ql.timSachTheoMa(ma);
        if (s == null) {
            System.out.println("Loi: Khong tim thay sach.");
            return;
        }
        System.out.println("Thong tin hien tai: " + s);
        String ten = nhapChuoi("Ten moi: ");
        String tg = nhapChuoi("Tac gia moi: ");
        String nxb = nhapChuoi("NXB moi: ");
        String tl = nhapChuoi("The loai moi: ");
        double gn = nhapSoThuc("Gia nhap moi: ");
        double gb = nhapSoThuc("Gia ban moi: ");
        ql.suaSach(ma, ten, tg, nxb, tl, gn, gb);
        System.out.println("Da cap nhat thanh cong!");
    }

    private static void xoaSach() {
        String ma = nhapChuoi("Ma sach can xoa: ");
        if (ql.xoaSach(ma)) {
            System.out.println("Da xoa thanh cong!");
        } else {
            System.out.println("Khong tim thay sach de xoa.");
        }
    }

    private static void timSachTheoMa() {
        String ma = nhapChuoi("Ma sach: ");
        Sach s = ql.timSachTheoMa(ma);
        if (s == null) {
            System.out.println("Khong tim thay.");
        } else {
            ArrayList<Sach> tmp = new ArrayList<>();
            tmp.add(s);
            inDanhSachSach(tmp);
        }
    }

    private static void timSachTheoTen() {
        String tu = nhapChuoi("Tu khoa trong ten sach: ");
        ArrayList<Sach> kq = ql.timSachTheoTen(tu);
        if (kq.isEmpty()) System.out.println("Khong tim thay sach phu hop.");
        else inDanhSachSach(kq);
    }

    // ===== CHUC NANG NHAN VIEN =====
    private static void themNhanVien() {
        String ma = nhapChuoi("Ma nhan vien: ");
        if (ql.timNhanVienTheoMa(ma) != null) {
            System.out.println("Loi: Ma nhan vien da ton tai.");
            return;
        }
        String ht = nhapChuoi("Ho ten: ");
        String cv = nhapChuoi("Chuc vu: ");
        String sdt = nhapChuoi("So dien thoai: ");
        double luong = nhapSoThuc("Luong: ");
        ql.themNhanVien(new NhanVien(ma, ht, cv, sdt, luong));
        System.out.println("Da them nhan vien thanh cong!");
    }

    private static void suaNhanVien() {
        String ma = nhapChuoi("Ma NV can sua: ");
        NhanVien nv = ql.timNhanVienTheoMa(ma);
        if (nv == null) {
            System.out.println("Khong tim thay nhan vien.");
            return;
        }
        System.out.println("Thong tin hien tai: " + nv);
        String ht = nhapChuoi("Ho ten moi: ");
        String cv = nhapChuoi("Chuc vu moi: ");
        String sdt = nhapChuoi("SDT moi: ");
        double luong = nhapSoThuc("Luong moi: ");
        ql.suaNhanVien(ma, ht, cv, sdt, luong);
        System.out.println("Da cap nhat!");
    }

    private static void xoaNhanVien() {
        String ma = nhapChuoi("Ma NV can xoa: ");
        if (ql.xoaNhanVien(ma)) System.out.println("Da xoa!");
        else System.out.println("Khong tim thay NV de xoa.");
    }

    private static void timNhanVienTheoMa() {
        String ma = nhapChuoi("Ma nhan vien: ");
        NhanVien nv = ql.timNhanVienTheoMa(ma);
        if (nv == null) System.out.println("Khong tim thay.");
        else {
            ArrayList<NhanVien> tmp = new ArrayList<>();
            tmp.add(nv);
            inDanhSachNhanVien(tmp);
        }
    }

    // ===== HAM IN BANG =====
    private static void inDanhSachSach(ArrayList<Sach> ds) {
        if (ds.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        System.out.println("+--------+------------------------------+--------------------+----------------+--------------+------------+------------+--------+");
        System.out.printf("| %-6s | %-28s | %-18s | %-14s | %-12s | %10s | %10s | %6s |%n",
                "MaSach", "TenSach", "TacGia", "NXB", "TheLoai", "GiaNhap", "GiaBan", "Ton");
        System.out.println("+--------+------------------------------+--------------------+----------------+--------------+------------+------------+--------+");
        for (Sach s : ds) {
            System.out.println(s);
        }
        System.out.println("+--------+------------------------------+--------------------+----------------+--------------+------------+------------+--------+");
    }

    private static void inDanhSachNhanVien(ArrayList<NhanVien> ds) {
        if (ds.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        System.out.println("+--------+------------------------+----------------+--------------+--------------+");
        System.out.printf("| %-6s | %-22s | %-14s | %-12s | %12s |%n",
                "MaNV", "HoTen", "ChucVu", "SDT", "Luong");
        System.out.println("+--------+------------------------+----------------+--------------+--------------+");
        for (NhanVien nv : ds) System.out.println(nv);
        System.out.println("+--------+------------------------+----------------+--------------+--------------+");
    }

    private static void inDanhSachPhieuNhap() {
        ArrayList<PhieuNhap> ds = ql.getDanhSachPhieuNhap();
        if (ds.isEmpty()) {
            System.out.println("Chua co phieu nhap nao.");
            return;
        }
        System.out.println("+--------+--------+--------+------------+--------------+--------------+");
        System.out.printf("| %-6s | %-6s | %6s | %10s | %12s | %-12s |%n",
                "MaPN", "MaSach", "SL", "GiaNhap", "ThanhTien", "NgayNhap");
        System.out.println("+--------+--------+--------+------------+--------------+--------------+");
        for (PhieuNhap p : ds) System.out.println(p);
        System.out.println("+--------+--------+--------+------------+--------------+--------------+");
    }

    private static void inDanhSachHoaDon() {
        ArrayList<HoaDon> ds = ql.getDanhSachHoaDon();
        if (ds.isEmpty()) {
            System.out.println("Chua co hoa don nao.");
            return;
        }
        System.out.println("+--------+--------+--------+--------+------------+--------------+--------------+");
        System.out.printf("| %-6s | %-6s | %-6s | %6s | %10s | %12s | %-12s |%n",
                "MaHD", "MaSach", "MaNV", "SL", "GiaBan", "ThanhTien", "NgayBan");
        System.out.println("+--------+--------+--------+--------+------------+--------------+--------------+");
        for (HoaDon h : ds) System.out.println(h);
        System.out.println("+--------+--------+--------+--------+------------+--------------+--------------+");
    }

    // ===== NAP DU LIEU MAU =====
    private static void napDuLieuMau() {
        // Sach
        ql.themSach(new Sach("S001", "Dac Nhan Tam", "Dale Carnegie", "Tong Hop TPHCM", "Ky nang", 60000, 85000, 50));
        ql.themSach(new Sach("S002", "Nha Gia Kim", "Paulo Coelho", "Hoi Nha Van", "Tieu thuyet", 70000, 100000, 40));
        ql.themSach(new Sach("S003", "Toi Tai Gioi, Ban Cung The", "Adam Khoo", "Phu Nu", "Ky nang", 55000, 80000, 35));
        ql.themSach(new Sach("S004", "Java Co Ban", "Nguyen Van A", "Giao Duc", "Lap trinh", 90000, 130000, 20));
        ql.themSach(new Sach("S005", "Harry Potter 1", "J.K.Rowling", "Tre", "Thieu nhi", 110000, 160000, 15));
        ql.themSach(new Sach("S006", "Doremon Tap 1", "Fujiko F. Fujio", "Kim Dong", "Truyen tranh", 20000, 30000, 100));

        // Nhan vien
        ql.themNhanVien(new NhanVien("NV01", "Nguyen Van An", "Quan ly", "0901111222", 12000000));
        ql.themNhanVien(new NhanVien("NV02", "Tran Thi Binh", "Thu ngan", "0902222333", 7000000));
        ql.themNhanVien(new NhanVien("NV03", "Le Van Cuong", "Ban hang", "0903333444", 6500000));

        // Nhap vai hoa don mau de co so lieu thong ke
        ql.banHang("HD001", "S001", "NV02", 5, "28/10/2026");
        ql.banHang("HD002", "S002", "NV02", 3, "29/10/2026");
        ql.banHang("HD003", "S006", "NV03", 10, "29/10/2026");
        ql.banHang("HD004", "S004", "NV03", 2, "30/10/2026");

        // Nhap them hang
        ql.nhapHang("PN001", "S005", 20, "28/10/2026");
    }
}
