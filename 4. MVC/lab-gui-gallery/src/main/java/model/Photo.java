package model;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;


public class Photo {

    private StringProperty name;

    private ObjectProperty<Image> photoData;

    public Photo(String extension, byte[] photoData) {
        this.photoData= new SimpleObjectProperty<Image>(new Image(new ByteArrayInputStream(photoData)));
        this.name = new SimpleStringProperty(UUID.randomUUID().toString() + "." + extension);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public Image getPhotoData() {
        return photoData.getValue();
    }

    public ObjectProperty<Image> getPhotoDataProperty(){
        return photoData;
    }
    public StringProperty getNameProperty(){
        return name;
    }
}
