package englishpuzzle.eduappad.com.englishpuzzle.control;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Arrays;

import englishpuzzle.eduappad.com.englishpuzzle.R;

/**
 * Created by Vo Quang Hoa on 04/10/2015.
 */
public class KeyboardControl extends LinearLayout {
    private String word="Animal";
    private EffectfulImageView[] answerViews;

    private char[] keyboardLayoutConfig = new char[]{'a','h','n','t'};
    private char[] userText = new char[30];
    private final int MAX_ANSWER_LENGTH = 10;

    private OnClickListener keyboardListener = new OnClickListener() {
        public void onClick(View v) {
            char code = (char)v.getTag();
            int maxIndex = Math.min(answerViews.length,userText.length);
            for(int i=0;i<maxIndex;i++){
                if(userText[i]==0){
                    setContent(i,code);
                    break;
                }
            }
        }
    };
    public KeyboardControl(Context context) {
        super(context);
        initializing();
    }

    public KeyboardControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializing();
    }

    public KeyboardControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializing();
    }

    private void initializing(){
        LayoutInflater.from(this.getContext()).inflate(R.layout.keyboard_layout, this);

        initAnswerLayout();
        initKeyboard();
    }

    private void initAnswerLayout(){
        LinearLayout answerLayout =(LinearLayout) findViewById(R.id.layout_answer);
        OnClickListener clickListener = new OnClickListener() {
            public void onClick(View v) {
                EffectfulImageView button = (EffectfulImageView)v;
                int index = (int)button.getTag();
                setContent(index,(char)0);
            }
        };

        answerViews = new EffectfulImageView[MAX_ANSWER_LENGTH];
        answerLayout.setWeightSum(MAX_ANSWER_LENGTH);
        for(int i=0;i<answerViews.length;i++){
            EffectfulImageView imageView = new EffectfulImageView(getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
            imageView.setImageResource(R.drawable.character_bg);
            imageView.setOnClickAction(clickListener);
            imageView.setTag(i);
            answerLayout.addView(imageView);

            answerViews[i]=imageView;
        }
    }

    private void initKeyboard(){
        LinearLayout keyboardLayout = (LinearLayout) findViewById(R.id.layout_keyboard);
        keyboardLayout.setWeightSum(keyboardLayoutConfig.length);
        LinearLayout currentLayout = null;

        for(char c = 'a';c<='z';c++){
            if(currentLayout == null || isNewLine(c)){
                currentLayout = createNewRow();
                keyboardLayout.addView(currentLayout);
            }

            currentLayout.addView(createKeyboardButton(c));
        }
    }

    private LinearLayout createNewRow(){
        LinearLayout newRow = (LinearLayout)LayoutInflater.from(getContext()).inflate(R.layout.keyboard_row_layout, null);
        newRow.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1));
        newRow.setGravity(Gravity.CENTER);

        return newRow;
    }

    private boolean isNewLine(char c){
        for(int i=0;i<keyboardLayoutConfig.length;i++){
            if(keyboardLayoutConfig[i]==c){
                return true;
            }
        }
        return false;
    }

    private void setContent(int index, char c){
        userText[index]=c;
        answerViews[index].setTextOverlay(c==0?"":""+c);
    }

    private EffectfulImageView createKeyboardButton(char c){
        EffectfulImageView button = (EffectfulImageView)LayoutInflater.from(getContext()).inflate(R.layout.keyboard_button_layout,null);
        int id = getContext().getResources().getIdentifier(""+c, "drawable", getContext().getPackageName());
        button.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
        button.setImageResource(id);
        button.setTag(c);
        button.setOnClickAction(keyboardListener);
        return button;
    }

    public void setWord(String word){
        this.word = word;

        for(int i=0;i<answerViews.length;i++){
            if(i<word.length()){
                answerViews[i].setVisibility(View.VISIBLE);
            }else{
                answerViews[i].setVisibility(View.GONE);
            }
        }
    }
}
