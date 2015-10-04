package englishpuzzle.eduappad.com.englishpuzzle.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import englishpuzzle.eduappad.com.englishpuzzle.configuration.IDataRequestHandler;
import englishpuzzle.eduappad.com.englishpuzzle.configuration.IRequestHande;
import englishpuzzle.eduappad.com.englishpuzzle.configuration.IWebService;
import englishpuzzle.eduappad.com.englishpuzzle.model.Category;
import englishpuzzle.eduappad.com.englishpuzzle.model.webmodel.Entry;
import englishpuzzle.eduappad.com.englishpuzzle.model.webmodel.WebCategory;

/**
 * Created by Vo Quang Hoa on 19/09/2015.
 */
public class DataController implements IWebService{

    private List<Category> categories;
    private Hashtable<String, List<String>> entriesData;

    private DataController(){
        categories = new ArrayList<Category>();
        entriesData = new Hashtable<String, List<String>>();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    private static DataController instance;
    public static synchronized DataController getInstance(){
        if(instance == null){
            instance = new DataController();
        }
        return instance;
    }

    public void LazyLoadCategories(Context context, final IDataRequestHandler dataHandler){
        IRequestHande<String> handler = new IRequestHande<String>() {
            public void onError(String reason) {
                if(dataHandler!=null){
                    dataHandler.onError(reason);
                }
            }

            public void onSuccess(String data) {
                Type listType = new TypeToken<ArrayList<WebCategory>>(){}.getType();
                List<WebCategory> webCategories = new Gson().fromJson(data, listType);
                categories = new ArrayList<Category>();
                for(int i=0;i<webCategories.size();i++){
                    Category category=new Category();
                    category.setName(webCategories.get(i).getFolder());
                    category.setDisplayName(webCategories.get(i).getDisplay());
                    categories.add(category);
                }
                if(dataHandler!=null) {
                    dataHandler.onSuccess();
                }
            }
        };
        WebController.getInstance().DownloadContent(context,"data/categories.json",handler);
    }

    public void AsyncLoadCategoryImages(final Context context, final  IDataRequestHandler dataHandler){
        new Thread( new Runnable() {
            public void run() {
                for(int i=0;i<categories.size();i++){
                    final Category category = categories.get(i);
                    if(category.getIcon()==null){
                        WebController.getInstance().DownloadImage(context,"data/"+ category.getName() + "/__cover.png", new IRequestHande<Bitmap>() {
                            public void onError(String reason) {
                                Utils.Log(reason);
                                dataHandler.onError(reason);
                            }
                            public void onSuccess(Bitmap data) {
                                category.setIcon(data);
                                dataHandler.onSuccess();
                            }
                        });
                    }
                }
            }
        }).start();
    }

    public void LoadPuzzle1ContentList(Context context, final String categoryDirectory,final IDataRequestHandler dataHandler){
        if(entriesData.containsKey(categoryDirectory)){
            if(dataHandler!=null) {
                dataHandler.onSuccess();
            }
        }
        else{
            IRequestHande<String> handler = new IRequestHande<String>() {
                public void onError(String reason) {
                    if(dataHandler!=null){
                        dataHandler.onError(reason);
                    }
                }

                public void onSuccess(String data) {
                    Type listType = new TypeToken<ArrayList<String>>(){}.getType();
                    List<String> words = new Gson().fromJson(data, listType);
                    entriesData.put(categoryDirectory,words);
                    if(dataHandler!=null) {
                        dataHandler.onSuccess();
                    }
                }
            };
            WebController.getInstance().DownloadContent(context,categoryDirectory+"/list.json",handler);
        }
    }

    public List<String> getWords(String category){
        return entriesData.get(category);
    }
}
