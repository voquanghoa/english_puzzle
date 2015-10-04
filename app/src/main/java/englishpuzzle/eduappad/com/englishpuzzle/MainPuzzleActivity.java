package englishpuzzle.eduappad.com.englishpuzzle;

import android.os.Bundle;

import englishpuzzle.eduappad.com.englishpuzzle.control.KeyboardControl;
import englishpuzzle.eduappad.com.englishpuzzle.useinterface.BaseActivity;

/**
 * Created by Vo Quang Hoa on 04/10/2015.
 */
public class MainPuzzleActivity extends BaseActivity {
    private KeyboardControl keyboardControl;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_puzzle_layout);
        keyboardControl =(KeyboardControl) findViewById(R.id.keyboard);
        keyboardControl.setWord("Animation");
    }
}
