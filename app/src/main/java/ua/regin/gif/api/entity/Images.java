package ua.regin.gif.api.entity;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("fixed_height")
    private Image fixedHeight;

    @SerializedName("downsized")
    private Image downsized;

    public Image getDownsized() {
        return downsized;
    }

    public Image getFixedHeight() {
        return fixedHeight;
    }
}
