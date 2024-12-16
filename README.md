TRƯỜNG ĐẠI HỌC SƯ PHẠM KỸ THUẬT TP.HCM
KHOA CÔNG NGHỆ THÔNG TIN
BỘ MÔN LẬP TRÌNH WEB




ĐỀ TÀI: XÂY DỰNG WEBSITE BÁN HÀNG UTESHOP BẰNG SERVLET + JSP/JSTL+ BOOTSTRAP + JPA + SQLSERVER/MYSQL/ POSTGRESQL +DECORATOR SITEMESH + JWT

Nhóm 13
STT	MSSV	Họ và tên
1	22133007	Nguyễn Sỹ Cường
2	22133010	Phù Ngọc Dương
3	22133030	Nguyễn Yên Khang
4	22133050	Trần Danh Tân


GVHD: Nguyễn Hữu Trung


Thành phố Hồ Chí Minh, tháng 12 năm 2024
Nhận xét giáo viên






















Lược đồ chức năng
1.Xem chi tiết sản phẩm
Use case:	Xem chi tiết sản phẩm 
Short Description:	Người dùng bấm vào nút “VIEW” của một sản phẩm trong danh sách ”All Products”, hệ thống đưa người dùng đến trang thông tin chi tiết của sản phẩm đó
Actor(s):	Khách, Khách hàng, Admin
Post-Conditions:	- Xem thông tin chi tiết của sản phẩm bao gồm Name,Price ,Color ,Height ,Length,Material  Stored, Quantity, Width.
Main Flow:	1.Actor ở trang chủ
2.Actor bấm vào nút “VIEW” của một sản phẩm trong danh sách ”All Products”, hệ thống đưa người dùng đến trang thông tin chi tiết của sản phẩm đó
3.Hệ thống kiểm tra xem sản phẩm có tồn tại không
4. Hệ thống đưa Actor đến trang thông tin chi tiết của sản phẩm
5. Trong trang chi tiết sản phẩm sẽ có các thông số Color, Height,  Length, Material,  Stored, Quantity, Width
Exception Flow(s):	E3: Nếu gặp lỗi thì sẽ đưa đến trang với thông báo:”Sản phẩm không tồn tại”
Đưa Actor về bước 1
 
2.Xem tất cả sản phẩm 
Use case:	Xem tất cả sản phẩm 
Short Description:	Người dùng xem các sản phẩm khi vào trang chủ website
Actor(s):	Khách, Khách hàng, Admin
Pre-Conditions:	
Post-Conditions:	- Khi lăn chuột qua trang  chủ của website sẽ thấy bảng:”All Product”
Main Flow:	1. Actor ở trang chủ chính
2. Actor truy cập được trang chủ của website
3. Lăn chuột ở gần đầu trang sẽ thấy bảng hiện:”All Product”
4. Actor có thể bấm mũi tên hai bên trên bảng danh sáchProducts” để thấy các sản phẩm trước sau trong bảng danh sách
Exception Flow(s):	E3: Nếu hệ thống gặp lỗi kỹ thuật trong trang chủ, hệ thống hiển thị thông báo lỗi và yêu cầu khách hàng thử lại sau.
 
 
3.Xem Gợi ý sản phẩm liên quan
Use case:	Gợi ý sản phẩm liên quan 
Short Description:	Người dùng xem các sản phẩm bán liên quan khi ở trang chi tiết sản phẩm xem một sản phẩm thì có thể thấy bảng danh sách “RELATED PRODUCTS”.
Actor(s):	Khách, Khách hàng, Admin
Pre-Conditions:	Actor thực hiện usecase ”Xem sản phẩm chi tiết”
Post-Conditions:	- Thấy được  Bảng :”RELATED PRODUCTS” hiện danh sách các sản phẩm có cùng loại trong trang chi tiết sản phẩm tương ứng với sản phẩm đó
Main Flow:	1. Khách ở trong một trang chi tiết sản phẩm bất kì
2. Khách hàng khi xem xong chi tiết thông tin, thông số của một sản phẩm khi kéo xuống sẽ thấy một bảng danh sách:”Các sản phẩm liên quan”.
3. Bảng :”RELATED PRODUCTS” hiện danh sách các sản phẩm có cùng chủ đề, được nhiều người mua khác mua,đánh giá...
4. Người dùng có thể dùng mũi tên ở hai bên trong bảng :”RELATED PRODUCTS” để chọn xem các sản phẩm trước và sau trong bảng danh sách
5.Người dùng có thể bấm nút "VIEW" để xem trang chi tiết của sản phẩm liên quan
Exception Flow(s):	E2: Nếu hệ thống gặp lỗi kỹ thuật, hệ thống hiển thị thông báo lỗi và yêu cầu khách hàng thử lại sau. người dùng quay lại trang chủ




















PHÂN CÔNG
Thành viên	Nhiệm vụ
Nguyễn Yên Khang	Tìm kiếm và lọc sản phẩm, Xem tất cả sản phẩm, Xem chi tiết sản phẩm, Xem gợi ý sản phẩm bán chạy, Xem gợi ý sản phẩm liên quan, báo cáo
Trần Danh Tân	lịch sử mua hàng theo trạng thái, tạo chương trình khuyến mãi, quản lý doanh thu của shop, báo cáo
Phù Ngọc Dương	Xem thông tin tài khoản, sửa tài khoản, thay đổi avatar, thay đỏi password, xóa tài khoản, Quên mật khẩu có gửi mã OTP, Xem các sản phẩm trong giỏ hàng, sửa số lượng sản phẩm, xóa sản phẩm trong giỏ, xóa giỏ hàng, báo cáo
Nguyễn Sỹ Cường	Đăng nhập, Đăng ký, Thanh toán, mã giảm giá, Tổng hợp báo cáo
