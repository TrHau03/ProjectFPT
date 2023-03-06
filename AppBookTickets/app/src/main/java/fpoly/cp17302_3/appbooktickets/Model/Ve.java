package fpoly.cp17302_3.appbooktickets.Model;

public class Ve {
    private int mave;
    private String thoigiandat;
    private String soghe;
    private String giave;
    private String ngaychieu;
    private String soluong;
    private String tenphim;
    private String theloai;
    private String maxuatchieu;
    private String hinhanh;
    private int matv;

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public Ve(int mave, String thoigiandat, String soghe, String giave, String ngaychieu, String soluong, String tenphim, String theloai,String hinhanh, String maxuatchieu, int matv) {
        this.mave = mave;
        this.thoigiandat = thoigiandat;
        this.soghe = soghe;
        this.giave = giave;
        this.ngaychieu = ngaychieu;
        this.soluong = soluong;
        this.tenphim = tenphim;
        this.theloai = theloai;
        this.hinhanh = hinhanh;
        this.maxuatchieu = maxuatchieu;
        this.matv = matv;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public int getMave() {
        return mave;
    }

    public void setMave(int mave) {
        this.mave = mave;
    }

    public String getThoigiandat() {
        return thoigiandat;
    }

    public void setThoigiandat(String thoigiandat) {
        this.thoigiandat = thoigiandat;
    }

    public String getSoghe() {
        return soghe;
    }

    public void setSoghe(String soghe) {
        this.soghe = soghe;
    }

    public String getGiave() {
        return giave;
    }

    public void setGiave(String giave) {
        this.giave = giave;
    }

    public String getNgaychieu() {
        return ngaychieu;
    }

    public void setNgaychieu(String ngaychieu) {
        this.ngaychieu = ngaychieu;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getMaxuatchieu() {
        return maxuatchieu;
    }

    public void setMaxuatchieu(String maxuatchieu) {
        this.maxuatchieu = maxuatchieu;
    }
}
