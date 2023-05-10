package gui;

import java.util.ResourceBundle;
import javax.swing.*;

public class Components {
    public static void translateСomponents(ResourceBundle bundle) {
        UIManager.put("InternalFrame.iconButtonToolTip", bundle.getString("minimize"));
        UIManager.put("InternalFrame.maxButtonToolTip", bundle.getString("minimize"));
        UIManager.put("InternalFrame.closeButtonToolTip", bundle.getString("close"));

        UIManager.put("InternalFrameTitlePane.restoreButtonText", bundle.getString("restore"));
        UIManager.put("InternalFrameTitlePane.moveButtonText", bundle.getString("move"));
        UIManager.put("InternalFrameTitlePane.sizeButtonText", bundle.getString("size"));
        UIManager.put("InternalFrameTitlePane.minimizeButtonText", bundle.getString("minimize"));
        UIManager.put("InternalFrameTitlePane.maximizeButtonText", bundle.getString("maximize"));
        UIManager.put("InternalFrameTitlePane.closeButtonText", bundle.getString("close"));

    }
}
