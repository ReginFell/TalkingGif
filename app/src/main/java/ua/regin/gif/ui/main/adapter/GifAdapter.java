package ua.regin.gif.ui.main.adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import ua.regin.gif.R;
import ua.regin.gif.api.entity.Data;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.ViewHolder> {

    private final Context context;
    private List<Data> dataList;

    public GifAdapter(Context context) {
        this.context = context;
        dataList = new ArrayList<>();
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gif, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data = dataList.get(position);
        Uri uri = Uri.parse(data.getImages().getFixedHeight().getUrl());

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(false)
                .setUri(uri).build();

        holder.gifView.setController(controller);

        Animatable animatable = controller.getAnimatable();

        if (animatable != null) {

            if (!holder.isPlaying) {
                animatable.stop();
            } else if (!animatable.isRunning()) {
                animatable.start();
            }
        }

        holder.setOnClickListener(this::notifyItemChanged);
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        if (dataList == null)
            return 0;
        else {
            return dataList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SimpleDraweeView gifView;
        private boolean isPlaying;
        private OnClickListener onClickListener;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            gifView = (SimpleDraweeView) itemView.findViewById(R.id.gifView);
            itemView.setOnClickListener(this);
        }

        private void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        private void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            isPlaying = !isPlaying;
            onClickListener.onClick(position);
        }
    }

    private interface OnClickListener {
        void onClick(int position);
    }

}
