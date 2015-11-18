package ua.regin.gif.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import ua.regin.gif.R;
import ua.regin.gif.api.entity.Data;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.ViewHolder> {

    private final Context context;
    private List<MultimediaData> dataList;

    public GifAdapter(Context context) {
        this.context = context;
        dataList = new ArrayList<>();
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = Stream.of(dataList).map(MultimediaData::new).collect(Collectors.toList());
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
        MultimediaData multimediaData = dataList.get(position);
        holder.setMultimediaData(multimediaData);
        holder.setOnClickListener(this::notifyItemChanged);
        holder.setPosition(position);

        DrawableTypeRequest request = Glide.with(context).load(multimediaData.data.getImages().getDownsized().getUrl());

        if (multimediaData.isPlaying) {
            request.asGif().centerCrop().into(holder.gifView);
        } else {
            request.asBitmap().centerCrop().into(holder.gifView);
        }
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        } else {
            return dataList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView gifView;
        private ProgressBar progressBar;
        private OnClickListener onClickListener;
        private MultimediaData multimediaData;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            gifView = (ImageView) itemView.findViewById(R.id.gifImage);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            itemView.setOnClickListener(this);
        }

        public void setMultimediaData(MultimediaData multimediaData) {
            this.multimediaData = multimediaData;
        }

        private void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        private void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            multimediaData.isPlaying = !multimediaData.isPlaying;
            onClickListener.onClick(position);
        }
    }

    private interface OnClickListener {
        void onClick(int position);
    }

    private class MultimediaData {
        private Data data;
        private boolean isPlaying;

        public MultimediaData(Data data) {
            this.data = data;
        }
    }
}
