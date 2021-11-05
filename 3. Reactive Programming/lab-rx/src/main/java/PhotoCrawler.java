import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.util.ObservableQueueDrain;
import model.Photo;
import util.PhotoDownloader;
import util.PhotoProcessor;
import util.PhotoSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhotoCrawler {

    private static final Logger log = Logger.getLogger(PhotoCrawler.class.getName());

    private final PhotoDownloader photoDownloader;

    private final PhotoSerializer photoSerializer;

    private final PhotoProcessor photoProcessor;

    public PhotoCrawler() throws IOException {
        this.photoDownloader = new PhotoDownloader();
        this.photoSerializer = new PhotoSerializer("./photos");
        this.photoProcessor = new PhotoProcessor();
    }

    public void resetLibrary() throws IOException {
        photoSerializer.deleteLibraryContents();
    }

    public void downloadPhotoExamples() {
        try {
            /*
           List<Photo> downloadedExamples = photoDownloader.getPhotoExamples();

            for (Photo photo : downloadedExamples) {
                photoSerializer.savePhoto(photo);
            }
            */
            Observable<Photo> source = photoDownloader.getPhotoExamples();
            source.subscribe(photo -> {
                photoSerializer.savePhoto(photo);
            });
        } catch (IOException e) {
            log.log(Level.SEVERE, "Downloading photo examples error", e);
        }
    }

    public void downloadPhotosForQuery(String query) throws IOException {
        // TODO Implement me :(

        try {
            Observable<Photo> source = photoDownloader.searchForPhotos(query);
            source.subscribe(photo -> {
                photoSerializer.savePhoto(photo);
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    ;

    public void downloadPhotosForMultipleQueries(List<String> topics) {
            try{
                 photoDownloader.searchForPhotos(topics)
                         .blockingSubscribe(photo ->{
                    photoSerializer.savePhoto(photo);
                });

        }catch (Error e) {
                e.printStackTrace();
            }

    }
}

