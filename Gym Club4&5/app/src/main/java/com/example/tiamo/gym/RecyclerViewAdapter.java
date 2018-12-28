package com.example.tiamo.gym;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    //数据
    private String[] mDataset;
    private  Context context;
    private  String selectname;

    public RecyclerViewAdapter(String[] mDataset, Context context){
        super();
        this.mDataset = mDataset;
        this.context= context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        final View view = View.inflate(viewGroup.getContext(), R.layout.fragment_page3, null);
        final ViewHolder holder = new ViewHolder(view) ;
        holder.coachItem_TextView = (TextView) view.findViewById(R.id.coach_item);
        holder.coach_item_provider_TextView = (TextView) view.findViewById(R.id.coach_item_provider);
        holder.coach_item_provider_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectname= holder.coach_item_provider_TextView.getText().toString();
                Log.d("RecyclerViewAdapter", "ssssssssssssssssssssssssssssssssssss");
                Log.d("RecyclerViewAdapter", selectname);
                Intent intent = new Intent(context, trainerinfo.class);
                intent.putExtra("namemsg",selectname);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                context.startActivity(intent);
            }
        });
        holder.coach_item_time_TextView = (TextView) view.findViewById(R.id.coach_item_time);
        holder.coach_item_cost_TextView = (TextView) view.findViewById(R.id.coach_item_cost);
        holder.again_button = (Button) view.findViewById(R.id.again_button);
        holder.again_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.again_button.setText("记得参加");
            }
        });
        //holder.evaluate_button = (Button) view.findViewById(R.id.evaluate_button);
        holder.route_plan = (ImageView) view.findViewById(R.id.route);
        holder.route_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WalkRouteActivity.class);
                context.startActivity(intent);
            }
        });
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
        int provider = (int) (Math.random() * 3);
        switch (provider){
            case 1:
                viewHolder.coach_item_provider_TextView.setText("冉旭松");
                viewHolder.coach_item_time_TextView.setText("874030912@qq.com");
                break;
            case 2:
                viewHolder.coach_item_provider_TextView.setText("薛飞跃");
                viewHolder.coach_item_time_TextView.setText("731372395@qq.com");
                break;
            case 0:
                viewHolder.coach_item_provider_TextView.setText("彭浩伟");
                viewHolder.coach_item_time_TextView.setText("7472232323@qq.com");
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
        public TextView coach_item_provider_TextView;
        public TextView coach_item_time_TextView;
        public TextView coach_item_cost_TextView;
        public Button again_button;
       // public Button evaluate_button;
        public ImageView route_plan;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
