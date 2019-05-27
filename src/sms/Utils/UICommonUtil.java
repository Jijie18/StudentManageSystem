package sms.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * From Internet
 */

public class UICommonUtil {

    public static void makeFrameToCenter(JFrame frame) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = frame.getSize();

        if (size.width > screenSize.width) {
            size.width = screenSize.width;
        }
        if (size.height > screenSize.height) {
            size.height = screenSize.height;
        }
        frame.setSize(1000, 700);
        frame.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
    }
}
