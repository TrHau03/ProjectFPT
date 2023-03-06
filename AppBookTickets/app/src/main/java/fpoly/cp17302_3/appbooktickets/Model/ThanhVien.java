package fpoly.cp17302_3.appbooktickets.Model;

public class ThanhVien {
    private int matv;
    private String sdt;
    private String email;
    private String tentv;
    private String matkhau;
    private String loaitaikhoan;

    public ThanhVien(int matv, String sdt, String email, String tentv, String matkhau, String loaitaikhoan) {
        this.matv = matv;
        this.sdt = sdt;
        this.email = email;
        this.tentv = tentv;
        this.matkhau = matkhau;
        this.loaitaikhoan = loaitaikhoan;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }
}
