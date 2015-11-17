package ua.regin.gif.ui.main;

import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ua.regin.gif.R;
import ua.regin.gif.api.entity.MediaObject;
import ua.regin.gif.manager.IGifManager;
import ua.regin.gif.manager.impl.GifManager;
import ua.regin.gif.ui.BaseActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Bean(GifManager.class)
    protected IGifManager gifManager;

    @ViewById(R.id.imageView)
    protected SimpleDraweeView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gifManager.loadRandomGif().compose(bindToLifecycle()).subscribe(this::updateData);
    }

    private void updateData(MediaObject mediaObject) {
        Uri uri = Uri.parse(mediaObject.getData().getImageUrl());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        imageView.setController(controller);
    }

    @Click(R.id.random)
    protected void randomGif() {
        gifManager.loadRandomGif().compose(bindToLifecycle()).subscribe(this::updateData);
    }
}
