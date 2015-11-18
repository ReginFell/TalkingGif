package ua.regin.gif.api.entity;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("fixed_height")
    private Image fixedHeight;

    public Image getFixedHeight() {
        return fixedHeight;
    }

    public void setFixedHeight(Image fixedHeight) {
        this.fixedHeight = fixedHeight;
    }
}
