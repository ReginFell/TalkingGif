package ua.regin.gif.manager;

import rx.Observable;
import ua.regin.gif.api.entity.MediaObject;

public interface IGifManager {

    Observable<MediaObject> loadRandomGif();
}
