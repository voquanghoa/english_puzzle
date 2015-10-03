package englishpuzzle.eduappad.com.englishpuzzle.control;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import englishpuzzle.eduappad.com.englishpuzzle.R;

/**
 * Created by Vo Quang Hoa on 03/10/2015.
 */
public class EffectfulImageView extends ImageView {
    private int effectColor = 0x77eeddff;
    private Runnable onClickAction;

    public EffectfulImageView(Context context) {
        super(context);
        initView();
    }

    public EffectfulImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public EffectfulImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        EffectfulImageView.this.setColorFilter(effectColor, PorterDuff.Mode.SRC_ATOP);
                        EffectfulImageView.this.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        clickFeedback();
                    case MotionEvent.ACTION_CANCEL: {
                        EffectfulImageView.this.clearColorFilter();
                        EffectfulImageView.this.invalidate();
                        break;
                    }
                }
                return true;
            }
        });
    }

    private void clickFeedback(){
        Runnable action = onClickAction;

        if(action != null){
            action.run();
        }
    }

    public void setOnClickAction(Runnable action){
        onClickAction = action;
    }
}
