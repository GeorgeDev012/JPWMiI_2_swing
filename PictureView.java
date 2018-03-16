import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;

class PictureView extends JFrame {

    private JButton imageButton = new JButton("Button");
    private JLabel imageLabel = new JLabel("");
    private BufferedImage chosenImage;

     PictureView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200,200);
        this.setTitle("Choose Picture");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(imageButton, BorderLayout.EAST);
        jPanel.add(imageLabel, BorderLayout.CENTER);

        this.setLocationByPlatform(true);
        this.add(jPanel);
    }

    public int getLabelImageHeight() {
        return imageLabel.getSize().height;
    }

    public int getLabelImageWidth() {
        return imageLabel.getSize().width;
    }

    public JLabel getLabelImage() {
        return imageLabel;
    }

    public void setLabelIcon(ImageIcon imageIcon) {
        imageLabel.setIcon(imageIcon);
    }

    public boolean hasLabelIcon() {
        return imageLabel.getIcon() != null;
    }

    public BufferedImage getChosenImage() {
        return chosenImage;
    }

    public void setChosenImage(BufferedImage chosenImage) {
        this.chosenImage = chosenImage;
    }

    public void addButtonListener(ActionListener listenForButton) {
        imageButton.addActionListener(listenForButton);
    }

    public void addLabelListener(ComponentAdapter listenForResize) {
        imageLabel.addComponentListener(listenForResize);
    }

    public void setIOExceptionText(String errorMessage) {
        imageLabel.setText(errorMessage);
    }
}
