package englishpuzzle.eduappad.com.englishpuzzle;

import android.os.Bundle;
import android.widget.GridView;

import java.util.List;
import java.util.Random;

import englishpuzzle.eduappad.com.englishpuzzle.configuration.IDataRequestHandler;
import englishpuzzle.eduappad.com.englishpuzzle.configuration.UiConstant;
import englishpuzzle.eduappad.com.englishpuzzle.control.WebImageView;
import englishpuzzle.eduappad.com.englishpuzzle.controller.DataController;
import englishpuzzle.eduappad.com.englishpuzzle.controller.WebController;
import englishpuzzle.eduappad.com.englishpuzzle.useinterface.BaseActivity;

/**
 * Created by Vo Quang Hoa on 21/09/2015.
 */
public class Puzzle1Activity extends BaseActivity implements IDataRequestHandler {
    private WebImageView webImageView;
    private String category;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webImageView =(WebImageView) findViewById(R.id.imageView);
        category = getIntent().getExtras().getString(UiConstant.CATEGORY_MESSAGE);
        showLoadingDialog();
        DataController.getInstance().LoadPuzzle1ContentList(this, category, this);

    }

    public void onSuccess() {
        closeLoadingDialog();
        getRandomWord();
    }

    private void getRandomWord(){
        Random random = new Random();
        List<String> words = DataController.getInstance().getWords(category);
        String word = words.get(random.nextInt(words.size()));

        webImageView.setRequestUrl("thumb.php?img=/"+category+"/"+word+".png");
    }

    public void onError(String errorMessgae) {
        closeLoadingDialog();
        showNetworkError(errorMessgae);
    }
}
