package ua.regin.gif.manager;

import java.util.List;

import rx.Observable;
import ua.regin.gif.api.entity.MediaObject;

public interface IGifManager {

    Observable<MediaObject> loadRandomGif();

    Observable<MediaObject> loadTrendingGifList();
}
