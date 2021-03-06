package com.mycompany.myapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Book.
 */

@Document(collection = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(min = 1, max = 50)
    @Field("ten_sach")
    private String tenSach;

    @NotNull
    @Field("trang_thai_con_hang")
    private Boolean trangThaiConHang;

    @Size(max = 2000)
    @Field("tom_tat")
    private String tomTat;

    @Min(value = 0)
    @Max(value = 5)
    @Field("rating")
    private Integer rating;

    @Size(max = 400)
    @Field("anh_dai_dien")
    private String anhDaiDien;

    @Size(max = 50)
    @Field("tag")
    private String tag;

    @Field("gia_cu")
    private Integer giaCu;

    @Field("gia_moi")
    private Integer giaMoi;

    @Size(max = 100)
    @Field("tac_gia")
    private String tacGia;

    @Min(value = 0)
    @Max(value = 9999)
    @Field("nam_xuat_ban")
    private Integer namXuatBan;

    @Size(max = 100)
    @Field("nha_xuat_ban")
    private String nhaXuatBan;

    @Size(max = 150)
    @Field("ten_tacgia")
    private String tenTacgia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public Book tenSach(String tenSach) {
        this.tenSach = tenSach;
        return this;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Boolean isTrangThaiConHang() {
        return trangThaiConHang;
    }

    public Book trangThaiConHang(Boolean trangThaiConHang) {
        this.trangThaiConHang = trangThaiConHang;
        return this;
    }

    public void setTrangThaiConHang(Boolean trangThaiConHang) {
        this.trangThaiConHang = trangThaiConHang;
    }

    public String getTomTat() {
        return tomTat;
    }

    public Book tomTat(String tomTat) {
        this.tomTat = tomTat;
        return this;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public Integer getRating() {
        return rating;
    }

    public Book rating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public Book anhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
        return this;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public String getTag() {
        return tag;
    }

    public Book tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getGiaCu() {
        return giaCu;
    }

    public Book giaCu(Integer giaCu) {
        this.giaCu = giaCu;
        return this;
    }

    public void setGiaCu(Integer giaCu) {
        this.giaCu = giaCu;
    }

    public Integer getGiaMoi() {
        return giaMoi;
    }

    public Book giaMoi(Integer giaMoi) {
        this.giaMoi = giaMoi;
        return this;
    }

    public void setGiaMoi(Integer giaMoi) {
        this.giaMoi = giaMoi;
    }

    public String getTacGia() {
        return tacGia;
    }

    public Book tacGia(String tacGia) {
        this.tacGia = tacGia;
        return this;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public Integer getNamXuatBan() {
        return namXuatBan;
    }

    public Book namXuatBan(Integer namXuatBan) {
        this.namXuatBan = namXuatBan;
        return this;
    }

    public void setNamXuatBan(Integer namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public Book nhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
        return this;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getTenTacgia() {
        return tenTacgia;
    }

    public Book tenTacgia(String tenTacgia) {
        this.tenTacgia = tenTacgia;
        return this;
    }

    public void setTenTacgia(String tenTacgia) {
        this.tenTacgia = tenTacgia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        if (book.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", tenSach='" + tenSach + "'" +
            ", trangThaiConHang='" + trangThaiConHang + "'" +
            ", tomTat='" + tomTat + "'" +
            ", rating='" + rating + "'" +
            ", anhDaiDien='" + anhDaiDien + "'" +
            ", tag='" + tag + "'" +
            ", giaCu='" + giaCu + "'" +
            ", giaMoi='" + giaMoi + "'" +
            ", tacGia='" + tacGia + "'" +
            ", namXuatBan='" + namXuatBan + "'" +
            ", nhaXuatBan='" + nhaXuatBan + "'" +
            ", tenTacgia='" + tenTacgia + "'" +
            '}';
    }
}
