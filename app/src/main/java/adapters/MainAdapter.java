package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a86155.day01text01.R;

import java.util.List;

import beans.FuliBean;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {

    private Context mContext;
    private List<FuliBean.ResultsBean> mList;

    public MainAdapter(Context context, List<FuliBean.ResultsBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
//        myHolder.desc.setText(mList.get(i).getDesc());
        Glide.with(mContext).load(mList.get(i).getUrl()).into(myHolder.img);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOnclick.onclick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView desc;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_it);
//            desc = itemView.findViewById(R.id.desc_it);
        }
    }

    private MyOnclick MyOnclick;

    public void setMyOnclick(MainAdapter.MyOnclick myOnclick) {
        MyOnclick = myOnclick;
    }

    public interface MyOnclick{
        void onclick(int position);
    }
}
