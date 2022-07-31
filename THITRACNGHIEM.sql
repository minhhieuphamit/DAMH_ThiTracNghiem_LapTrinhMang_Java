-- Create tables section -------------------------------------------------

-- Table BODE

CREATE TABLE [BODE]
(
 [CAUHOI] Int NOT NULL,
 [NOIDUNG] Ntext NULL,
 [A] Ntext NULL,
 [B] Ntext NULL,
 [C] Ntext NULL,
 [D] Ntext NULL,
 [DAP_AN] Char(1) NULL
)
go

-- Add keys for table BODE

ALTER TABLE [BODE] ADD CONSTRAINT [PK_BODE] PRIMARY KEY ([CAUHOI])
go

-- Table SINHVIEN

CREATE TABLE [SINHVIEN]
(
 [MASV] Char(10) NOT NULL,
 [HOTEN] Nvarchar(50) NULL,
 [SODIENTHOAI] Char(10) NULL,
 [DIEM] Float NULL,
 [NGAYTHI] Date NULL
)
go

-- Add keys for table SINHVIEN

ALTER TABLE [SINHVIEN] ADD CONSTRAINT [PK_SINHVIEN] PRIMARY KEY ([MASV])
go

INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (1, N'Phát biểu nào sau đây đúng?', N'Đường kính bằng bán kính.', N'Đường kính hơn bán kính 2 đơn vị.', N'Đường kính gấp 2 lần bán kính.', N'Bán kính gấp 2 lần đường kính.', N'C')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (2, N'157% = ……..', N'157', N'15,7', N'1,57', N'0,157', N'C')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (3, N'412,3 x …… = 4,123. Số điền vào chỗ chấm là:', N'100', N'10', N'0,1', N'0,01', N'D')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (4, N'Có bao nhiêu số tự nhiên y thỏa mãn điều kiện 3,2 x y < 15,6', N'7', N'6', N'5', N'4', N'C')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (5, N'Một thuyền khi xuôi dòng có vận tốc là 13,2 km/giờ. Vận tốc của thuyền khi ngược dòng là 7,4 km/giờ. Như vậy vận tốc của dòng nước là:', N'5,8 km/giờ', N'2,9 km/giờ', N'6,8 km/giờ', N'10,3 km/giờ', N'B')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (6, N'Số thích hợp điền vào chỗ chấm để 7m325cm3 = ……. cm3 là:', N'7000025', N'700025', N'70025', N'7025', N'A')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (7, N'Cho hình tròn có đường kính là 10cm. Diện tích của hình tròn đó là:', N'314 cm2', N'15,7 cm2', N'31,4 cm2', N'78,5 cm2', N'D')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (8, N'Số thập phân gồm 55 đơn vị, 7 phần trăm, 2 phần nghìn viết là:', N'55,720', N'55,072', N'55,027', N'55,702', N'B')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (9, N'Phép trừ 712,54 - 48,9 có kết quả đúng là:', N'70,765', N'223,54', N'663,64', N'707,65', N'C')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (10, N'Một hình hộp chữ nhật có thể tích 300dm3, chiều dài 15dm, chiều rộng 5dm. Vậy chiều cao của hình hộp chữ nhật là:', N'10dm', N'4dm', N'8dm', N'6dm', N'B')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (11, N'Viết số thích hợp vào chỗ chấm: 55 ha 17 m2 = ..........ha', N'55,17', N'550,017', N'55,017', N'55,000,017', N'B')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (12, N'Lớp học có 18 nữ và 12 nam. Hỏi số học sinh nữ chiếm bao nhiêu phần trăm số học sinh cả lớp?', N'150%', N'60%', N'40%', N'80%', N'B')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (13, N'Số lớn nhất trong các số thập phân: 4,031; 4,31; 4,103; 4,130 là:', N'4,031', N'4,31', N'4,103', N'4,130', N'B')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (14, N'Một hình hộp chữ nhật có chiều dài 3,5m; chiều rộng 2m; chiều cao 1,5m. Thể tích hình hộp chữ nhật đó là?', N'7m3', N'1,05m', N'10,5m3', N'105m3', N'C')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (15, N'1 phút 15 giây = ?', N'1,15 phút', N'1,25 phút', N'115 giây', N'1,25 giây', N'B')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (16, N'Diện tích hình tròn có đường kính 2 cm là:', N'3,14 cm2', N'6,28 cm2', N'6,28 cm', N'12,56 cm2', N'A')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (17, N'Một hình thang có trung bình cộng hai đáy là 5cm và chiều cao là 3,2 cm thì diện tích hình thang đó là:', N'8 cm2', N'32 cm2', N'16 cm2', N'164 cm2', N'C')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (18, N'Một ô tô đi quãng đường AB dài 120 km mất 2 giờ 30 phút. Vận tốc ô tô là:', N'80 km/giờ', N'60 km/giờ', N'50 km/giờ', N'48 km/giờ', N'D')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (19, N'Chữ số 9 trong số 25, 309 thuộc hàng nào?', N'Hàng đơn vị', N'Hàng trăm', N'Hàng phần trăm', N'Hàng phần nghìn', N'D')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (20, N'Thể tích của một hình lập phương có cạnh 5 dm là:', N'125 dm3', N'100 dm2', N'100 dm3', N'125 dm2', N'A')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (21, N'Cách tính diện tích hình thang có đáy lớn là 25 cm, đáy nhỏ 21 cm và chiều cao 8 cm là:', N'(25 x 21) x 8 : 2', N'(25 + 21) x 8 : 2', N'(25 + 21) : 8 x 2', N'(25 x 21) : 8 x 2', N'B')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (22, N'Số bé nhất trong các số: 3,055; 3,050; 3,005; 3,505 là:', N'3,505', N'3,050', N'3,005', N'3,055', N'C')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (23, N'Trung bình một người thợ làm một sản phẩm hết 1 giờ 30 phút. Người đó làm 5 sản phẩm mất bao lâu?', N'7 giờ 30 phút', N'7 giờ 50 phút', N'6 giờ 50 phút', N'6 giờ 15 phút', N'A')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (24, N'Có bao nhiêu số thập phân ở giữa 0,5 và 0,6:', N' Không có số nào', N'1 số', N'9 số', N'Rất nhiều số', N'D')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (25, N'Tìm một số biết 20% của nó là 16. Số đó là:', N'0,8', N'8', N'80', N'800', N'C')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (26, N'Một hình lập phương có diện tích xung quanh là 36 dm2. Thể tích hình lập phương đó là:', N'27 dm3', N'2700 cm3', N'54 dm3', N'27000 cm3', N'A')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (27, N'Chữ số 5 trong số 162,57 chỉ:', N'5 đơn vị', N'5 phần trăm', N'5 chục', N'5 phần mười', N'D')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (28, N'5840g = .... kg', N'58,4kg', N'5,84kg', N'0,584kg', N'0,0584kg', N'B')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (29, N'Có 20 viên bi, trong đó có 3 viên bi nâu, 4 viên bi xanh, 5 viên bi đỏ, 8 viên bi vàng. Như vậy 1/5 số viên bi có màu: (1 điểm)', N'Nâu', N'Đỏ', N'Xanh', N'Trắng', N'C')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (30, N'Khoảng thời gian từ lúc 9 giờ kém 10 phút đến 9 giờ 30 phút là:', N'10 phút', N'20 phút', N'30 phút', N'40 phút', N'D')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (31, N'Một huyện có 320ha đất trồng cây cà phê và 480ha đất trồng cây cao su. Hỏi diện tích đất trồng cây cao su bằng bao nhiêu phần trăm diện tích đất trồng cây cà phê?', N'150%', N'15%', N'1500%', N'105%', N'A')
INSERT [dbo].[BODE] ([CAUHOI], [NOIDUNG], [A], [B], [C], [D], [DAP_AN]) VALUES (32, N'Hình lập phương có cạnh là 5m. Vậy thể tích hình đó là:', N'150 m3', N'125 m3', N'100 m3', N'25 m3', N'B')
go