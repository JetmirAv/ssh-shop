package org.fiek.utils;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageUploadHandler {

    FileChooser fileChooser;
    FileChooser.ExtensionFilter[] extensionFilters = {
            new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"),
            new FileChooser.ExtensionFilter("png", "*.png"),
            new FileChooser.ExtensionFilter("jpg", "*.jpg"),
            new FileChooser.ExtensionFilter("jpeg", "*.jpeg")
    };

    String[] extensions = {"jpg", "jpeg", "png"};


    public ImageUploadHandler() {
        this.fileChooser = new FileChooser();
        this.fileChooser.getExtensionFilters().addAll(extensionFilters);
    }

    public Tuple<Image, String> uploadImage() throws FileNotFoundException {
        File selectedImage = fileChooser.showOpenDialog(null);
        String imagePath = selectedImage.getPath();

        if (!this.filterFile(imagePath)) throw new Error();

        System.out.println(imagePath);
        FileInputStream imageHandler = new FileInputStream(imagePath);
        Image image = new Image(imageHandler);

        return new Tuple<>(image, imagePath);

    }

    public ArrayList<Tuple<Image, String >> uploadFiles() throws FileNotFoundException {
        List<File> selectedImage = fileChooser.showOpenMultipleDialog(null);
        ArrayList<Tuple<Image, String>> images = new ArrayList<>();
        ArrayList<String> imagePaths = new ArrayList<>();

        for (File file : selectedImage) {
            String filePath = file.getPath();
            if (!this.filterFile(filePath)) throw new Error();

            imagePaths.add(filePath);
        }

        for (String path : imagePaths) {
            FileInputStream fileStream = new FileInputStream(path);
            Image image = new Image(fileStream);
            images.add(new Tuple<>(image, path));
        }

        return images;
    }

    public boolean filterFile(String path) {
        String[] extensions = path.split("\\.");
        System.out.println(extensions[0]);
        String extension = extensions[extensions.length - 1];
        return Arrays.asList(this.extensions).contains(extension);
    }

}
