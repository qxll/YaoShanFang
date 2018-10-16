package cn.food.medicinal.com.yaoshanfang.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import cn.food.medicinal.com.yaoshanfang.R;

public class FragmentMe extends Fragment {
    private View view;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, null);
        listView = view.findViewById(R.id.fragmentme_listview_listitem);
        return view;
    }
}

/**
 * 我的---页面列表适配器
 */
class FragmentMeListViewAdapter extends ArrayAdapter<BeanMeItemView> {

    public FragmentMeListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<BeanMeItemView> list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = ((LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.adapter_fragmentme_listitem, null);

        return super.getView(position, convertView, parent);
    }
}
class BeanMeItemView{
    public String title; //标题
    public int imgRes;  //图标
    public String rightPasswd;  //右边的修改密码
}
