package com.example.tiamo.gym;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    //数据
    private String[] mDataset;

    public RecyclerViewAdapter(String[] mDataset){
        super();
        this.mDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = View.inflate(viewGroup.getContext(), R.layout.fragment_page3, null);
        ViewHolder holder = new ViewHolder(view) ;
        holder.coachItem_TextView = (TextView) view.findViewById(R.id.coach_item);
        holder.coachStatus_TextView = (TextView) view.findViewById(R.id.coach_status_item);
        holder.coach_item_provider_TextView = (TextView) view.findViewById(R.id.coach_item_provider);
        holder.coach_item_time_TextView = (TextView) view.findViewById(R.id.coach_item_time);
        holder.coach_item_cost_TextView = (TextView) view.findViewById(R.id.coach_item_cost);
        holder.again_button = (Button) view.findViewById(R.id.again_button);
        holder.evaluate_button = (Button) view.findViewById(R.id.evaluate_button);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        //绑定数据到Holder上
        int  name = (int) (Math.random() * 3);
        switch (name){
            case 0:
                viewHolder.coachItem_TextView.setText("    腹肌撕裂计划");
                break;
            case 1:
                viewHolder.coachItem_TextView.setText("    燃爆脂肪计划");
                break;
            case 2:
                viewHolder.coachItem_TextView.setText("    练死我的腿计划");
                break;
        }
        int status = (int) (Math.random() * 3);
        switch (status){
            case 1:
                viewHolder.coachStatus_TextView.setText("已完成   ");
                break;
            case 2:
                viewHolder.coachStatus_TextView.setText("待完成   ");
                viewHolder.again_button.setVisibility(View.GONE);
                viewHolder.evaluate_button.setText("记得参加计划");
                break;
            case 0:
                viewHolder.coachStatus_TextView.setText("正在进行");
                break;
        }
        int provider = (int) (Math.random() * 3);
        switch (provider){
            case 1:
                viewHolder.coach_item_provider_TextView.setText("冉旭松诊疗中心");
                break;
            case 2:
                viewHolder.coach_item_provider_TextView.setText("薛飞跃训练中心");
                break;
            case 0:
                viewHolder.coach_item_provider_TextView.setText("彭浩伟康复中心");
                break;
        }

        int cost = (int) (Math.random() * 600) + 200;
        viewHolder.coach_item_cost_TextView.setText("总价：¥ " + cost);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView coachItem_TextView;
        public TextView coachStatus_TextView;
        public TextView coach_item_provider_TextView;
        public TextView coach_item_time_TextView;
        public TextView coach_item_cost_TextView;
        public Button again_button;
        public Button evaluate_button;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
