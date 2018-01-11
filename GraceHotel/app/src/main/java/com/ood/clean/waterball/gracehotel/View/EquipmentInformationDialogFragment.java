package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ood.clean.waterball.gracehotel.Model.Information;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/12/31.
 */

public class EquipmentInformationDialogFragment extends BaseDialogFragment{
    private static final String USER = "user";
    private MyAdapter myAdapter;
    private List<Information> equimpmentInformationList ;
    private String[] titleArray = {"Grace 餐廳","休閒娛樂中心","自助洗衣室","商務中心"};
    private String[] introductionArray = {"早餐供應時間：上午07:00至10:00，並提供，並提供多樣之中西式合併菜色給入住葛瑞斯商旅的貴賓使用。\n",
            "開放時間：上午7:00至晚間21:00，我們將多功能晉身器材(跑步機、飛輪、健腹板)及多項娛樂休閒遊戲，巧放在這快樂的空間裡，讓貴賓們在享受揮汗的快感外，還能夠擁有另一種不同感受的歡樂。\n",
    "開放時間:上午7:00-晚上23:00，開放投幣式自助洗衣、烘衣服務\n",
            "備有桌上型電腦，可以快速處理文書資料等等，也免費提供各種觀光資訊。\n" +
                    "設備：\n" +
                    "24小時提供無線寬頻網擄\n" +
                    "Windows 8 作業環境\n" +
                    "雷射列印服務\n" +
                    "影印及掃描服務\n" +
                    "傳真服務\n" +
                    "郵件/快遞服務\n" +
                    "免費咖啡、茶提供\n"};
    private int[] imageSrcArray = {R.drawable.restaurant,R.drawable.gym,R.drawable.washingmachine,R.drawable.businesscenter};
    public EquipmentInformationDialogFragment(){}

    public static EquipmentInformationDialogFragment newInstance(User user){
        EquipmentInformationDialogFragment fragment = new EquipmentInformationDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View createView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_equipment , null);
        ListView listView = (ListView) mView.findViewById(R.id.dialogEquipmentListView);
        initInformation();
        myAdapter = new MyAdapter(mView.getContext(),equimpmentInformationList);
        listView.setAdapter(myAdapter);

        return mView;
    }
    private void initInformation(){
        equimpmentInformationList = new ArrayList<Information>();
        for (int i = 0 ; i < 4 ; i++){
            equimpmentInformationList.add(new Information(titleArray[i],introductionArray[i],imageSrcArray[i]));
        }
    }
    private class MyAdapter extends BaseAdapter{
        private Context context;
        private LayoutInflater li;
        private List<Information> equimpmentInformationList;
        public MyAdapter(Context context , List<Information> equimpmentInformationList){
            this.context = context;
            this.equimpmentInformationList = equimpmentInformationList;
            li = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return equimpmentInformationList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = li.inflate(R.layout.equipment_listview_item,viewGroup,false);
            int resource = equimpmentInformationList.get(i).getImageSrc();

            TextView titleText = (TextView) view.findViewById(R.id.equipmentListViewItemTitle);
            TextView introductionText = (TextView) view.findViewById(R.id.equipmentListViewItemIntroduction);
            ImageView imageView = (ImageView) view.findViewById(R.id.equipmentListviewItemImageView);

            titleText.setText(equimpmentInformationList.get(i).getTitle());
            introductionText.setText(equimpmentInformationList.get(i).getIntroduction());
            Glide.with(context).load(resource).into(imageView);

            return view;
        }
    }
}
