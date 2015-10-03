package englishpuzzle.eduappad.com.englishpuzzle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import englishpuzzle.eduappad.com.englishpuzzle.adapter.CategoryAdapter;
import englishpuzzle.eduappad.com.englishpuzzle.configuration.IDataRequestHandler;
import englishpuzzle.eduappad.com.englishpuzzle.configuration.IRequestHande;
import englishpuzzle.eduappad.com.englishpuzzle.configuration.IWebService;
import englishpuzzle.eduappad.com.englishpuzzle.configuration.UiConstant;
import englishpuzzle.eduappad.com.englishpuzzle.controller.DataController;
import englishpuzzle.eduappad.com.englishpuzzle.controller.Utils;
import englishpuzzle.eduappad.com.englishpuzzle.controller.WebController;
import englishpuzzle.eduappad.com.englishpuzzle.useinterface.BaseActivity;

public class MainActivity extends BaseActivity implements IDataRequestHandler, AdapterView.OnItemClickListener {
    private GridView gridView;
    private CategoryAdapter categoryAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gridView = new GridView(this);
        gridView.setNumColumns(3);
        setContentView(gridView);
        showLoadingDialog();
        DataController.getInstance().LazyLoadCategories(this, this);
        gridView.setOnItemClickListener(this);
    }

    public void onSuccess() {
        categoryAdapter = new CategoryAdapter(this, DataController.getInstance().getCategories());
        gridView.setAdapter(categoryAdapter);
        closeLoadingDialog();
        DataController.getInstance().AsyncLoadCategoryImages(this, new IDataRequestHandler() {
            public void onSuccess() {
                categoryAdapter.notifyDataSetChanged();
            }

            public void onError(String errorMessgae) {
            }
        });
    }

    public void onError(String errorMessgae) {
        showMessage(errorMessgae);
        closeLoadingDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this,Puzzle1Activity.class);
        Bundle bundle = new Bundle();
        String category = DataController.getInstance().getCategories().get(position).getName();
        String messageName = UiConstant.CATEGORY_MESSAGE;
        Utils.Log(messageName+" == "+category);
        bundle.putString(messageName,category);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
