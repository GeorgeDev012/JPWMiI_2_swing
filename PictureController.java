import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

class PictureController {

    private PictureView pictureView;
    private PictureModel pictureModel;

    PictureController(PictureView pictureView, PictureModel pictureModel) {
        this.pictureView = pictureView;
        this.pictureModel = pictureModel;

        this.pictureView.addButtonListener(new ButtonClickListener());
        this.pictureView.addLabelListener(new ImageResizeListener());
    }

    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(pictureView);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    pictureView.setChosenImage(ImageIO.read(file));
                    BufferedImage chosenImage = pictureView.getChosenImage();
                    Graphics2D graphics2d = (Graphics2D) chosenImage.getGraphics();
                    int addedTextWidth = graphics2d.getFontMetrics().stringWidth(pictureModel.getFirstAddedText());
                    int addedTextHeight = graphics2d.getFontMetrics().getHeight();

                    Random random = new Random();
                    graphics2d.drawString(pictureModel.getFirstAddedText(),random.nextInt(chosenImage.getWidth() - addedTextWidth),
                            random.nextInt(chosenImage.getHeight()));
                    graphics2d.translate(random.nextInt(chosenImage.getWidth() - addedTextWidth), random.nextInt(chosenImage.getHeight()) - addedTextHeight);
                    graphics2d.rotate(30, 50, 70);
                    graphics2d.drawString(pictureModel.getSecondAddedText(), 50, 70);
                    putResizedImageInLabel();
                } catch (Exception IOException) {
                    pictureView.setIOExceptionText("Chosen file was not an image.");
                }
            }
        }
    }

    class ImageResizeListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);
            if(pictureView.hasLabelIcon()) {
                putResizedImageInLabel();
            }
        }
    }

    private void putResizedImageInLabel() {
        Image imageTemp = pictureView.getChosenImage().getScaledInstance(pictureView.getLabelImageWidth(),
                pictureView.getLabelImageHeight(), java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(imageTemp);
        pictureView.setLabelIcon(imageIcon);
    }
}
