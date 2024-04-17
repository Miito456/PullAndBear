package PullAndBear;

import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComponent;

public class CustomScrollBarUI extends BasicScrollBarUI {

    @Override
    protected void configureScrollBarColors() {
        thumbColor = Color.gray;
        thumbDarkShadowColor = Color.gray  ; 
        thumbHighlightColor = Color.BLACK; 
        thumbLightShadowColor = Color.BLACK; 
        trackColor = Color.decode("#F2F2F2"); 
        trackHighlightColor = Color.decode("#F2F2F2"); 
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton button = new JButton();
        Dimension zeroDim = new Dimension(0, 0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(Color.decode("#F2F2F2")); 
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }
        g.setColor(Color.BLACK); 
        ((Graphics2D) g).fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10); 
    }
}
