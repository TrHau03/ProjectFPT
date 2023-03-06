package fpoly.cp17302_3.appbooktickets.Model;

public class Phim {
    private int maphim;
    private String tenphim;
    private String hinhanh;
    private String tenloai;
    private int maloai;

    public Phim(int maphim, String tenphim, String hinhanh, String tenloai, int maloai) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.tenloai = tenloai;
        this.maloai = maloai;
    }

    public Phim(int maphim, String tenphim, String hinhanh, String tenloai) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.tenloai = tenloai;
    }

    public int getMaphim() {
        return maphim;
    }

    public void setMaphim(int maphim) {
        this.maphim = maphim;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}
