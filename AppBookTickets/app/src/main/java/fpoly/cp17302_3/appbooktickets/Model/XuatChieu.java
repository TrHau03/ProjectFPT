package fpoly.cp17302_3.appbooktickets.Model;

public class XuatChieu {
    private int maxuatchieu;
    private String ngaychieu;
    private String thoigianchieu;
    private String tenloai;
    private String tenphim;
    private String hinhanh;
    private int maphim;

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public XuatChieu(int maxuatchieu, String ngaychieu, String thoigianchieu, int maphim) {
        this.maxuatchieu = maxuatchieu;
        this.ngaychieu = ngaychieu;
        this.thoigianchieu = thoigianchieu;
        this.maphim = maphim;
    }

    public XuatChieu(int maxuatchieu, String ngaychieu, String thoigianchieu, String tenloai, String tenphim,String hinhanh, int maphim) {
        this.maxuatchieu = maxuatchieu;
        this.ngaychieu = ngaychieu;
        this.thoigianchieu = thoigianchieu;
        this.tenloai = tenloai;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.maphim = maphim;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public int getMaxuatchieu() {
        return maxuatchieu;
    }

    public void setMaxuatchieu(int maxuatchieu) {
        this.maxuatchieu = maxuatchieu;
    }

    public String getNgaychieu() {
        return ngaychieu;
    }

    public void setNgaychieu(String ngaychieu) {
        this.ngaychieu = ngaychieu;
    }

    public String getThoigianchieu() {
        return thoigianchieu;
    }

    public void setThoigianchieu(String thoigianchieu) {
        this.thoigianchieu = thoigianchieu;
    }

    public int getMaphim() {
        return maphim;
    }

    public void setMaphim(int maphim) {
        this.maphim = maphim;
    }
}
