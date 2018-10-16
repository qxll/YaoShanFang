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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.food.medicinal.com.yaoshanfang.R;

public class FragmentMe extends Fragment {
    private View view;
    private ListView listView;
    private ArrayList<BeanMeItemView> arrayList;
    private FragmentMeListViewAdapter fragmentMeListViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, null);
        listView = view.findViewById(R.id.fragmentme_listview_listitem);
        initListData();
        return view;
    }

    private void initListData(){
        arrayList = new ArrayList<>();
        arrayList.add(new BeanMeItemView("我的订单",R.mipmap.icon_fm_dingdan));
        arrayList.add(new BeanMeItemView("密码管理",R.mipmap.icon_fm_passwd, true));
        arrayList.add(new BeanMeItemView("地址管理",R.mipmap.icon_fm_locmanager));
        arrayList.add(new BeanMeItemView("帮助",R.mipmap.icon_fm_help));
        arrayList.add(new BeanMeItemView("建议",R.mipmap.icon_fm_advice));
        arrayList.add(new BeanMeItemView("其它",R.mipmap.icon_fm_else));
        arrayList.add(new BeanMeItemView("登出系统",R.mipmap.icon_fm_exit));
        fragmentMeListViewAdapter = new FragmentMeListViewAdapter(getActivity(), R.layout.adapter_fragmentme_listitem, arrayList);
        listView.setAdapter(fragmentMeListViewAdapter);
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
        TextView title = view.findViewById(R.id.adapter_fragmentme_listitem_txvtitle);
        ImageView icon = view.findViewById(R.id.adapter_fragmentme_listitem_imgicon);
        View alterPasswd = view.findViewById(R.id.adapter_fragmentme_listitem_txvright);
        BeanMeItemView beanMeItemView = getItem(position);
        title.setText(beanMeItemView.title);
        icon.setImageResource(beanMeItemView.imgRes);
        alterPasswd.setVisibility(beanMeItemView.showRightPasswd ? View.VISIBLE : View.GONE);
        return view;
    }
}
class BeanMeItemView{
    public String title; //标题
    public int imgRes;  //图标
    public boolean showRightPasswd = false;  //显示右边的修改密码

    public BeanMeItemView(String title, int imgRes) {
        this.title = title;
        this.imgRes = imgRes;
    }

    public BeanMeItemView(String title, int imgRes, boolean showRightPasswd) {
        this.title = title;
        this.imgRes = imgRes;
        this.showRightPasswd = showRightPasswd;
    }
}
