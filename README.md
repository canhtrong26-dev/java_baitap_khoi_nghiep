# Quản lý Cửa hàng Sách

> Bài tập lớn môn **Lập trình Java** — Đề tài 02 (Định hướng khởi nghiệp)  
> Lớp **TH30.12** · Nhóm **devcamp120.j2605**

Phần mềm quản lý nhà sách nhỏ chạy trên **Console (Terminal)**, viết bằng **Java Core**, sử dụng `Scanner` để nhập dữ liệu và `ArrayList` để lưu trữ trong bộ nhớ (không dùng cơ sở dữ liệu, không dùng Swing).

---

## 1. Mục tiêu

Giúp chủ cửa hàng sách nhỏ chuyển từ **ghi sổ thủ công** sang **quản lý bằng máy tính**, với các nghiệp vụ:

- Quản lý danh mục sách và nhân viên.
- Nhập hàng và bán hàng (tự động cập nhật tồn kho).
- Thống kê doanh thu, lợi nhuận, cảnh báo sách sắp hết.

---

## 2. Cấu trúc dự án

```
QuanLyNhaSach/
├── src/
│   ├── Sach.java            # Entity Sách
│   ├── NhanVien.java        # Entity Nhân viên
│   ├── PhieuNhap.java       # Entity Phiếu nhập hàng
│   ├── HoaDon.java          # Entity Hóa đơn bán hàng
│   ├── QuanLyNhaSach.java   # Class quản lý chính (CRUD + nghiệp vụ)
│   └── Main.java            # Menu, validate input, nạp dữ liệu mẫu
├── BaoCao_PhanIII.docx      # Báo cáo khởi nghiệp (Phần III đề bài)
└── README.md                # File này
```

### Chi tiết các class

| Class | Vai trò | Thuộc tính chính |
|---|---|---|
| `Sach` | Thông tin một cuốn sách | maSach, tenSach, tacGia, nhaXuatBan, theLoai, giaNhap, giaBan, soLuongTon |
| `NhanVien` | Thông tin một nhân viên | maNV, hoTen, chucVu, soDienThoai, luong |
| `PhieuNhap` | Phiếu nhập hàng từ nhà cung cấp | maPhieu, maSach, soLuong, giaNhap, ngayNhap |
| `HoaDon` | Hóa đơn bán cho khách | maHD, maSach, maNV, soLuong, giaBan, ngayBan |
| `QuanLyNhaSach` | 4 `ArrayList` + toàn bộ hàm nghiệp vụ (thêm, sửa, xóa, tìm, sắp xếp, nhập hàng, bán hàng, thống kê) | — |
| `Main` | Giao diện menu, nhận lệnh người dùng, nạp dữ liệu mẫu | — |

---

## 3. Yêu cầu môi trường

- **JDK 8** trở lên (khuyến nghị JDK 17 hoặc JDK 21).
- Terminal / Command Prompt, hoặc IDE Java bất kỳ: NetBeans, IntelliJ IDEA, Eclipse, VS Code.

Kiểm tra Java đã cài chưa:

```bash
java -version
javac -version
```

---

## 4. Cách chạy

### Cách 1 – Dùng Terminal / CMD

```bash
# Di chuyển vào thư mục src
cd QuanLyNhaSach/src

# Biên dịch tất cả file .java
javac *.java

# Chạy chương trình
java Main
```

### Cách 2 – Dùng NetBeans / IntelliJ / Eclipse

1. Tạo Java Project mới (không chọn tạo class `main`).
2. Copy 6 file `.java` trong `src/` vào package `default`.
3. Mở `Main.java` → Run (`F6` hoặc `Shift + F10`).

### Cách 3 – Dùng VS Code

1. Mở thư mục `QuanLyNhaSach` bằng VS Code.
2. Cài extension **Extension Pack for Java** (nếu chưa có).
3. Mở `Main.java` → bấm nút `Run` ở góc trên bên phải.

---

## 5. Hướng dẫn sử dụng

Khi chạy, chương trình hiện menu chính:

```

         PHẦN MỀM QUẢN LÝ CỬA HÀNG SÁCH - v1.0

  1. Quản lý Sách
  2. Quản lý Nhân viên
  3. Nhập hàng
  4. Bán hàng
  5. Thống kê - Báo cáo
  0. Thoát
```

Gõ số tương ứng rồi `Enter` để vào menu con.

### 5.1. Menu Quản lý Sách

| Chọn | Chức năng |
|:---:|---|
| 1 | Hiển thị tất cả sách (dạng bảng) |
| 2 | Thêm sách mới (kiểm tra trùng mã) |
| 3 | Sửa thông tin sách theo mã |
| 4 | Xóa sách theo mã |
| 5 | Tìm sách theo mã |
| 6 | Tìm sách theo tên (tìm gần đúng) |
| 7 | Sắp xếp theo giá bán tăng dần |
| 8 | Sắp xếp theo số lượng tồn giảm dần |
| 9 | Sắp xếp theo tên A → Z |
| 0 | Quay lại menu chính |

### 5.2. Menu Quản lý Nhân viên

| Chọn | Chức năng |
|:---:|---|
| 1 | Hiển thị tất cả nhân viên |
| 2 | Thêm nhân viên mới |
| 3 | Sửa thông tin nhân viên |
| 4 | Xóa nhân viên |
| 5 | Tìm nhân viên theo mã |
| 0 | Quay lại |

### 5.3. Nhập hàng

- Nhập: mã phiếu, mã sách, số lượng, ngày nhập.
- Hệ thống kiểm tra mã sách có tồn tại không, số lượng > 0.
- Nếu hợp lệ: tạo phiếu nhập mới và **tự động tăng tồn kho** của sách đó.

### 5.4. Bán hàng

- Nhập: mã hóa đơn, mã sách, mã nhân viên, số lượng, ngày bán.
- Hệ thống kiểm tra:
  - Mã sách có tồn tại không.
  - Mã nhân viên có tồn tại không.
  - Số lượng > 0.
  - Tồn kho đủ để bán không.
- Nếu hợp lệ: tạo hóa đơn mới và **tự động giảm tồn kho**.

### 5.5. Menu Thống kê – Báo cáo

| Chọn | Chức năng |
|:---:|---|
| 1 | Tổng giá trị tồn kho (theo giá nhập) + tổng số lượng tồn |
| 2 | Tổng doanh thu từ tất cả hóa đơn |
| 3 | Lợi nhuận ước tính (doanh thu − giá vốn hàng đã bán) |
| 4 | Doanh thu theo từng nhân viên |
| 5 | Lọc sách sắp hết hàng (nhập ngưỡng tùy ý, ví dụ 20) |
| 6 | Hiển thị tất cả phiếu nhập |
| 7 | Hiển thị tất cả hóa đơn |
| 0 | Quay lại |

---

## 6. Dữ liệu mẫu

Chương trình **tự động nạp dữ liệu mẫu** khi khởi động để có thể dùng ngay mà không phải nhập tay:

- **6 cuốn sách:** Đắc Nhân Tâm, Nhà Giả Kim, Tôi Tài Giỏi Bạn Cũng Thế, Java Cơ Bản, Harry Potter 1, Doremon Tập 1.
- **3 nhân viên:** 1 Quản lý, 1 Thu ngân, 1 Bán hàng.
- **4 hóa đơn mẫu** đã bán trước đó.
- **1 phiếu nhập mẫu**.

Sau khi khởi động, có thể vào ngay mục `5 → 2` để xem tổng doanh thu, hoặc `5 → 4` để xem doanh thu theo nhân viên.

---

## 7. Đối chiếu yêu cầu đề bài

| Yêu cầu đề bài | Trạng thái |
|---|:---:|
| Java Core, giao diện Console | OK |
| Dùng `Scanner` và `ArrayList` | OK |
| OOP: class, constructor, getter/setter, phương thức | OK |
| CRUD: Thêm, Hiển thị, Sửa, Xóa | OK |
| Tìm kiếm theo mã / tên | OK |
| Sắp xếp theo ít nhất 2 tiêu chí | OK (3 tiêu chí: giá, tồn, tên) |
| ≥ 2 chức năng nghiệp vụ đặc thù | OK (Nhập hàng, Bán hàng, Thống kê, Cảnh báo sách sắp hết) |
| Menu chức năng | OK (menu nhiều cấp) |
| Kiểm tra dữ liệu đầu vào & xử lý lỗi cơ bản | OK |
| Không bắt buộc dùng CSDL / Swing | Đúng, không dùng |

---

## 8. Hướng phát triển

- Chuyển giao diện Console sang **JavaFX / Swing**.
- Lưu dữ liệu bằng **MySQL** hoặc **SQLite** thay cho `ArrayList`.
- Thêm module khách hàng thân thiết, khuyến mãi, đọc mã vạch.
- In hóa đơn ra máy in nhiệt; xuất báo cáo Excel / PDF.
- Phiên bản Web (Spring Boot + ReactJS) để xem báo cáo từ điện thoại.

Chi tiết đầy đủ xem trong file **`BaoCao_PhanIII.docx`** đi kèm.

---

## 9. Thông tin nhóm

- **Nhóm và các thành viên khác:** Võ Trọng Cảnh
