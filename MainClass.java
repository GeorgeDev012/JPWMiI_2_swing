public class MainClass {

    public static void main(String[] args) {
        PictureView pictureView = new PictureView();
        PictureModel pictureModel = new PictureModel();
        PictureController pictureController = new PictureController(pictureView, pictureModel);
        pictureView.setVisible(true);
    }
}

