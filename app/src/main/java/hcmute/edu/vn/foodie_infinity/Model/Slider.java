package hcmute.edu.vn.foodie_infinity.Model;

import java.io.Serializable;

public class Slider implements Serializable {
    private int resourceId;

    public Slider(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
