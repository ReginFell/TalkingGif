package ua.regin.gif.ui.gif.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import ua.regin.gif.R;
import ua.regin.gif.api.entity.Data;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.ViewHolder> {

    private final Context context;
    private List<MultimediaData> multimediaDataList;

    public GifAdapter(Context context) {
        this.context = context;
        multimediaDataList = new ArrayList<>();
    }

    public void setDataList(List<Data> dataList) {
        this.multimediaDataList = Stream.of(dataList).map(MultimediaData::new).collect(Collectors.toList());
        notifyDataSetChanged();
    }

    public boolean getFocusedState(int position) {
        return multimediaDataList.get(position).isFocused;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gif, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MultimediaData multimediaData = multimediaDataList.get(position);
        holder.setMultimediaData(multimediaData);

        holder.setOnClickListener(() -> {
            disableFocusAll();
            multimediaData.isFocused = true;
        });

        DrawableTypeRequest request = Glide.with(context).load(multimediaData.data.getImages().getDownsized().getUrl());

        if (multimediaData.isPlaying) {
            request.asGif().centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.gifView);
        } else {
            request.asBitmap().centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.gifView);
        }
    }

    @Override
    public int getItemCount() {
        if (multimediaDataList == null) {
            return 0;
        } else {
            return multimediaDataList.size();
        }
    }

    private void disableFocusAll() {
        Stream.of(multimediaDataList).forEach(multimediaData -> multimediaData.isFocused = false);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView gifView;
        private OnClickListener onClickListener;
        private MultimediaData multimediaData;

        public ViewHolder(View itemView) {
            super(itemView);
            gifView = (ImageView) itemView.findViewById(R.id.gifImage);
            itemView.setOnClickListener(this);
        }

        public void setMultimediaData(MultimediaData multimediaData) {
            this.multimediaData = multimediaData;
        }

        private void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View v) {
            multimediaData.isPlaying = !multimediaData.isPlaying;
            onClickListener.onClick();
        }
    }

    public interface OnClickListener {
        void onClick();
    }

    private class MultimediaData {
        private Data data;
        private boolean isPlaying;
        private boolean isFocused;

        public MultimediaData(Data data) {
            this.data = data;
        }
    }
}
