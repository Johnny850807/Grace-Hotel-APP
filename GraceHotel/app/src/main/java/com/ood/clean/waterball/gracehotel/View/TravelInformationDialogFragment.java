package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.os.Bundle;
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

public class TravelInformationDialogFragment extends BaseDialogFragment {
    private static final String USER = "user";
    private MyAdapter myAdapter;
    private List<Information> informationList;
    private String[] travelTitle = {"石碇湖","石碇老街(午餐)","手信坊創意和菓子(和菓子DIY)","樂華夜市"};
    private String[] travelIntroduction = {"台灣小千島的石碇千島湖，位在翡翠水庫的上游，眾多山巒與湖水形成集水潭，從定點看向遠方，山巒翠綠，湖水清澈，山與水的蜿蜒交錯、以及與茶田的相互交應，讓千島湖更增添了仙境的色彩。 \u000B千島湖附近的八卦茶園旁有一觀景平台，可以眺覽附近的茶園風光及北勢溪的風景。",
    "石碇老街以石碇溪上的萬壽橋分為東街及西街，這裡擁有台灣少數的不見天街，以及最特殊的吊腳樓建築，不見天街是因二樓樓層的空間涵蓋一樓走道所致，吊腳樓指的是利用柱子支撐於河床之上，是石碇老街的主要特色。老街還有百年老鐵舖及多棟老建築，瀰漫著濃厚的古樸氣息，民眾還可到此品嚐出名的豆腐製品。\n",
    "這裡被滿滿的日本文化風瀰漫，從步入手信坊文化館建築的入口處到DIY短廊中，" +
            "包括日本文化的鳥居、和風布幔、招財貓、櫻花、鯉魚幡、" +
            "感覺的假山、充滿禪意的手水舍、櫻花群布置在兩側，彷彿置身在日本京都的街道中。\n" +
            "\n" +
            "\n" +
            "開館時間\u000B＊開館時間：08：30-18：00\n" +
            "\n" +
            "體驗和菓子DIY教室\u000B＊「創意和菓子DIY」時間數量限定（安排專人指導）。 \u000B＊預約時間：每日 08：30-17：00\u000B＊採電話報名，預約專線：02-8262-0506， 請於14天前電話預約。 \u000B＊和菓子DIY課程：09:30、11:00、13:00、14:30、16:00、17:00 共7個時段\n" +
            "＊費用：150元( 製做內容: 桃小町*1顆+ 綠豆糕*1顆) \n" +
            " \n",
    ""};
    private int[] travelImageSrc = {R.drawable.stonelake,R.drawable.oldstreet,R.drawable.handdiy,R.drawable.nightmarket};
    public TravelInformationDialogFragment(){}

    public static TravelInformationDialogFragment newInstance(User user){
        TravelInformationDialogFragment fragment = new TravelInformationDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected View createView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_travel , null);
        initInformation();
        myAdapter = new MyAdapter(mView.getContext(),informationList);
        ListView listView = (ListView) mView.findViewById(R.id.dialog_travel_listview);
        listView.setAdapter(myAdapter);
        return mView;
    }
    private void initInformation(){
        informationList = new ArrayList<Information>();
        for(int i = 0 ; i < 4; i++){
            Information information = new Information(travelTitle[i],travelIntroduction[i],travelImageSrc[i]);
            informationList.add(information);
        }
    }
    private class MyAdapter extends BaseAdapter{
        private Context context;
        private LayoutInflater li;
        private List<Information> informationList;
        public MyAdapter(Context context,List<Information> informationList){
            this.li = LayoutInflater.from(context);
            this.context = context;
            this.informationList = informationList;
        }
        @Override
        public int getCount() {
            return informationList.size();
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
            view = li.inflate(R.layout.travel_listview_item,viewGroup,false);
            int resource = informationList.get(i).getImageSrc();
            TextView titleText = (TextView) view.findViewById(R.id.travelListViewItemTitle);
            TextView introductionText = (TextView) view.findViewById(R.id.travelListViewItemIntroduction);
            ImageView imageView = (ImageView) view.findViewById(R.id.travelListViewItemImage);

            titleText.setText(informationList.get(i).getTitle());
            introductionText.setText(informationList.get(i).getIntroduction());
            Glide.with(context).load(resource).into(imageView);
            return view;
        }
    }
}
