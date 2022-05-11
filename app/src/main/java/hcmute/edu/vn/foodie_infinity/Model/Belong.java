package hcmute.edu.vn.foodie_infinity.Model;

public class Belong {
    int rid;
    int fid;

    public Belong(int rid, int fid) {
        this.rid = rid;
        this.fid = fid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }
}
