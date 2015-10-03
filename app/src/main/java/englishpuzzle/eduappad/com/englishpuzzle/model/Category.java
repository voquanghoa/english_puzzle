package englishpuzzle.eduappad.com.englishpuzzle.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Vo Quang Hoa on 20/09/2015.
 */
public class Category {
    private String name;
    private String displayName;
    private Bitmap icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
