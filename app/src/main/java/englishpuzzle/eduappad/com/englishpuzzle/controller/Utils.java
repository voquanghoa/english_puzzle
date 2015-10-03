package englishpuzzle.eduappad.com.englishpuzzle.controller;

import android.util.Log;

/**
 * Created by Vo Quang Hoa on 20/09/2015.
 */
public class Utils {
    private static String TAG = "ENGLISH_PUZZLE";
    public  static void Log(String content){
        if(content==null){
            content = "(Empty log)";
        }
        Log.i(TAG,content);
    }
}
