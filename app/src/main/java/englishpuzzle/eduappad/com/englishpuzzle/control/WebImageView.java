package englishpuzzle.eduappad.com.englishpuzzle.control;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.text.BoringLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.Toast;

import englishpuzzle.eduappad.com.englishpuzzle.configuration.IRequestHande;
import englishpuzzle.eduappad.com.englishpuzzle.controller.Utils;
import englishpuzzle.eduappad.com.englishpuzzle.controller.WebController;

/**
 * Created by Vo Quang Hoa on 21/09/2015.
 */
public class WebImageView extends ImageView {
    private String requestUrl;

    public WebImageView(Context context) {
        super(context);
    }

    public WebImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRequestUrl(String requestUrl){
        this.requestUrl = requestUrl;
        setWebImage();
    }

    private void setWebImage(){
        int width = getWidth();
        if(width == 0){
            width = getContext().getResources().getDisplayMetrics().widthPixels;
        }

        String request = requestUrl + "&w=" + width;
        Utils.Log("Get width " +request );
        if(requestUrl!=null && width>0){
            WebController.getInstance().DownloadImage(getContext(), request, new IRequestHande<Bitmap>() {
                public void onError(String reason) {
                    Toast.makeText(getContext(),"Loi xay khi tai hinh anh", Toast.LENGTH_LONG).show();
                }

                public void onSuccess(Bitmap data) {
                    WebImageView.this.setImageBitmap(data);
                }
            });
        }
    }
}
