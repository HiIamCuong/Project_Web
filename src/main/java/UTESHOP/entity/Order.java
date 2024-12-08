package UTESHOP.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "order")
@NamedQuery(name = "order.findAll", query = "SELECT v FROM order v")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaDonHang")
	private int MaDonHang;
	
	@Column(name = "MaKhachHang", columnDefinition = "INT")
	private String MaKhachHang;
	
	
	@Column(name = "TenNguoiDat", columnDefinition = "NVARCHAR(200) NULL")
	private String TenNguoiDat;
	
	@Column(name = "TenNguoiNhan", columnDefinition = "NVARCHAR(200) NULL")
	private String TenNguoiNhan;
	
	@Column(name = "SDTNguoiDat", columnDefinition = "NVARCHAR(200) NULL")
	private String SDTNguoiDat;
	
	@Column(name = "TongTien", columnDefinition = "INT")
	private String TongTien;
	
	@Column(name = "NgayMuaHang", columnDefinition = "datetime")
	private String NgayMuaHang;
	
	@Column(name = "DiaChiNhan", columnDefinition = "NVARCHAR(200) NULL")
	private String DiaChiNhan;
	
	@Column(name = "TrangThai", columnDefinition = "NVARCHAR(200) NULL")
	private String TrangThai;
}
