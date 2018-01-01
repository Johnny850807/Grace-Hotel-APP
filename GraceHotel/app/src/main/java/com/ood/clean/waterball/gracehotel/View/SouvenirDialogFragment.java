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

import com.ood.clean.waterball.gracehotel.Model.Information;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/1.
 */

public class SouvenirDialogFragment extends BaseDialogFragment {
    private static final String USER = "user";
    private User user;
    private MyAdapter myAdapter;
    private List<Information> souvenirInformationList ;
    private String[] titleArray = {"老天祿滷味","芳堤納克菓子工房","大黑松小倆口-牛軋糖博物館","來春嬤柴燒麥芽糖"};
    private String[] introductionArray = {"位於西門町的老天祿滷味不僅是國內報導的常客，也是許多國外觀光客的必吃美食。尤其是鴨舌，更是許多香港天王天后級藝人每到台灣的指定必吃，因而紅遍香港。\n" +
            "台北市武昌街二段55號\n" +
            "營業時間：9：30 – 22：00\n" +
            "https://www.lautianlu.com.tw/products.php\n",
            "用20多年烘焙經驗，融合客家粢粑（麻糬）的彈牙口感及法國泡芙的香脆美味，譜出客家點心新戀曲\n" +
                    "主要商品、規格說明：起士蛋糕、幕斯、海綿蛋糕、鮮奶油蛋糕、手工餅乾、各式塔類、布丁類、蛋黃酥、麻糬、三Q餅、綠豆凸等。\n" +
                    "新北市板橋區中山路1段158巷24號1樓\n" +
                    "週一～週六，上午10點到晚上8點( 週日公休 母親節除外 )\n" +
                    "http://ftnc.pixnet.net/blog\n",
            "米、蔗糖、牛奶、花生的組合口齒留香的好滋味，美味的牛軋糖，館內設施相當豐富，適合親子共遊。\n" +
                    "新北市土城區自強街31-2號\n" +
                    "09:00~17:30(全年無休)\n" +
                    "http://www.9420.com.tw/\n",
            "堅持傳統，遵古法製作，原料單純（糯米、小麥草），天然純正，有一股獨特得焦糖味道，這正是古人智慧的結晶。\n" +
                    "新北市板橋區府中路29之1號B1(板橋農會超市)\n" +
                    "9:00-21:00(過年公休)\n"};
    private int[] imageSrcArray = {R.drawable.oldskyfood,R.drawable.foodwork,R.drawable.cowcandy,R.drawable.businesscenter};
    public SouvenirDialogFragment(){}

    public static SouvenirDialogFragment newInstance(User user){
        SouvenirDialogFragment fragment = new SouvenirDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable(USER);
    }

    @Override
    protected View createView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_souvenir , null);
        initInformation();
        myAdapter = new MyAdapter(mView.getContext(),souvenirInformationList);
        ListView listView = (ListView) mView.findViewById(R.id.dialog_souvenir_listview);
        listView.setAdapter(myAdapter);
        return mView;
    }
    private void initInformation(){
        souvenirInformationList = new ArrayList<Information>();
        for(int i = 0 ; i < 4; i++){
            Information information = new Information(titleArray[i],introductionArray[i],imageSrcArray[i]);
            souvenirInformationList.add(information);
        }
    }

    private class MyAdapter extends BaseAdapter{
        private LayoutInflater li;
        private List<Information> informationList;

        public MyAdapter(Context context, List<Information> informationList){
            this.li = LayoutInflater.from(context);
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
            view = li.inflate(R.layout.souvenir_listview_item,viewGroup,false);

            TextView titleText = (TextView) view.findViewById(R.id.souvenirListViewItemTitle);
            TextView introductionText = (TextView) view.findViewById(R.id.souvenirListViewItemIntroduction);
            ImageView imageView = (ImageView) view.findViewById(R.id.souvenirListViewItemImage);

            titleText.setText(informationList.get(i).getTitle());
            introductionText.setText(informationList.get(i).getIntroduction());
            imageView.setImageResource(informationList.get(i).getImageSrc());

            return view;
        }
    }
}
