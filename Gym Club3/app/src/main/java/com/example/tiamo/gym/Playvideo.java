package com.example.tiamo.gym;

import android.content.Intent;
import android.graphics.ColorSpace;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class Playvideo extends Fragment {

    private RecyclerView recyclerView;
    private List<videomodel> datas;
    private MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_playvideo, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        //模拟的数据（实际开发中一般是从网络获取的）
        datas = new ArrayList<>();
        videomodel model;
        for (int i = 0; i < 15; i++) {
            switch (i){
                case 0:
                    model = new videomodel();
                    model.setTitle("垂直引体向上");
                    model.setContent("种常见的垂直引体向上的变式动作包括有宽握,对握,窄握和反手引体向上(手掌对着自己)。");
                    model.setImgUrl(R.drawable.video1);
                    datas.add(model);
                    break;
                case 1:
                    model = new videomodel();
                    model.setTitle("二头绳索弯举");
                    model.setContent("这是一个很好的锻炼肱二头肌的动作,这种单独的锻炼肱二头肌会出现意想不到的的效果! ");
                    model.setImgUrl(R.drawable.video2);
                    datas.add(model);
                    break;
                case 2:
                    model = new videomodel();
                    model.setTitle("俯身");
                    model.setContent("徒手俯身动作是一种复合型的训练方式");
                    model.setImgUrl(R.drawable.video3);
                    datas.add(model);
                    break;
                case 3:
                    model = new videomodel();
                    model.setTitle("俯身杠铃划船");
                    model.setContent("它能帮你建立一个强大的核心");
                    model.setImgUrl(R.drawable.video4);
                    datas.add(model);
                    break;
                case 4:
                    model = new videomodel();
                    model.setTitle("俯身哑铃臂屈伸");
                    model.setContent("哑铃俯身臂屈伸(Dumbbell Triceps Kickback)这个动作无法用杠铃来替代");
                    model.setImgUrl(R.drawable.video5);
                    datas.add(model);
                    break;
                case 5:
                    model = new videomodel();
                    model.setTitle("杠铃平板卧推");
                    model.setContent("卧推是一个复合动作，既涉及到多个关节的动作，参与运动的有肩关节和肘关节");
                    model.setImgUrl(R.drawable.video6);
                    datas.add(model);
                    break;
                case 6:
                    model = new videomodel();
                    model.setTitle("杠铃直立划船");
                    model.setContent("杠铃直立划船动作简单而且锻炼效果明显,立正划船也可以采用哑铃");
                    model.setImgUrl(R.drawable.video7);
                    datas.add(model);
                    break;
                case 7:
                    model = new videomodel();
                    model.setTitle("三角肌后束");
                    model.setContent("三角肌后束可以说是肩部训练中最难训练的部位");
                    model.setImgUrl(R.drawable.video8);
                    datas.add(model);
                    break;
                case 8:
                    model = new videomodel();
                    model.setTitle("上斜哑铃卧推");
                    model.setContent("在上斜卧推的方式中,建议使用杠铃、哑铃进行多次的练习");
                    model.setImgUrl(R.drawable.video9);
                    datas.add(model);
                    break;
                case 9:
                    model = new videomodel();
                    model.setTitle("绳索屈臂下压");
                    model.setContent("拉力器屈臂下压 - 站姿双臂拉力器屈臂下压动作图解当肱三头肌围度基本满意时,需进行刻画肌肉线条练习");
                    model.setImgUrl(R.drawable.video10);
                    datas.add(model);
                    break;
                case 10:
                    model = new videomodel();
                    model.setTitle("托臂弯举");
                    model.setContent(" 斜托臂弯举 哑铃用双手在特制板凳上弯举");
                    model.setImgUrl(R.drawable.video11);
                    datas.add(model);
                    break;
                case 11:
                    model = new videomodel();
                    model.setTitle("哑铃侧平举");
                    model.setContent("哑铃侧平举是一个用哑铃锻炼时的动作");
                    model.setImgUrl(R.drawable.video12);
                    datas.add(model);
                    break;
                case 12:
                    model = new videomodel();
                    model.setTitle("哑铃集中弯举");
                    model.setContent("哑铃集中弯举(Dumbbell Concentration Cur)其实就是单臂哑铃蹲坐弯举");
                    model.setImgUrl(R.drawable.video13);
                    datas.add(model);
                    break;
                case 13:
                    model = new videomodel();
                    model.setTitle("侧卧杠铃臂屈伸");
                    model.setContent("仰卧杠铃臂屈伸常见错误! 仰卧杠铃臂屈伸是肱三头肌锻炼中最经典的动作之一!");
                    model.setImgUrl(R.drawable.video14);
                    datas.add(model);
                    break;
                case 14:
                    model = new videomodel();
                    model.setTitle("站姿绳索臂外展");
                    model.setContent("形象的也称为站姿直腿侧平举,一般用绳索拉力器外侧拉引来实现,很多健身房也有专用的腿侧展训练器");
                    model.setImgUrl(R.drawable.video15);
                    datas.add(model);
                    break;
                default:
                    break;
            }

        }

        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new MyAdapter(R.layout.videoinfo, datas);

        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);
        adapter.openLoadAnimation();
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        adapter.isFirstOnly(false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent i = new Intent(getActivity(), video.class);
                String ss =String.valueOf(position+1);
                i.putExtra("videolist",ss);
                startActivity(i);
                //Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
