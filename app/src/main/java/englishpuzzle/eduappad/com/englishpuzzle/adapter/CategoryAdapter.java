package englishpuzzle.eduappad.com.englishpuzzle.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import englishpuzzle.eduappad.com.englishpuzzle.R;
import englishpuzzle.eduappad.com.englishpuzzle.model.Category;

/**
 * Created by Vo Quang Hoa on 20/09/2015.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories){
        this.context = context;
        this.categories = categories;
    }

    public int getCount() {
        return categories.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.category_layout,parent, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.category_icon);
        TextView textView = (TextView)convertView.findViewById(R.id.category_name);

        if(categories.get(position).getIcon()==null){
            imageView.setImageResource(R.drawable.comment);
        }else{
            imageView.setImageBitmap(categories.get(position).getIcon());
        }

        textView.setText(categories.get(position).getDisplayName());

        return convertView;
    }
}
