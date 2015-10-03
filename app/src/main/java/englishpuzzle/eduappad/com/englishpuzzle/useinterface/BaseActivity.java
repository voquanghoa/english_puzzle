package englishpuzzle.eduappad.com.englishpuzzle.useinterface;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import englishpuzzle.eduappad.com.englishpuzzle.configuration.IWebService;
import englishpuzzle.eduappad.com.englishpuzzle.configuration.UiConstant;

/**
 * Created by Vo Quang Hoa on 20/09/2015.
 */
public class BaseActivity extends Activity implements IWebService, DialogInterface.OnCancelListener {
    private ProgressDialog progressDialog;

    protected  void showLoadingDialog(){
        progressDialog = ProgressDialog.show(this, "", "Loading", true);
        progressDialog.setOnCancelListener(this);
    }

    protected  void closeLoadingDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    public void onCancel(DialogInterface dialog) {

    }

    protected void showMessage(String content){
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

    protected void showNetworkError(String error){
        showMessage("Không thể kết nối được mạng.");
    }
}
