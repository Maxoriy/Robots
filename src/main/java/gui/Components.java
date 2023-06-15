package gui;

import java.util.ResourceBundle;
import javax.swing.*;

public class Components {
    public static void translate(ResourceBundle bundle) {
        UIManager.put("InternalFrame.iconButtonToolTip", bundle.getString("minimizeButtonToolTip"));
        UIManager.put("InternalFrame.maxButtonToolTip", bundle.getString("maximizeButtonToolTip"));
        UIManager.put("InternalFrame.closeButtonToolTip", bundle.getString("closeButtonToolTip"));

        UIManager.put("InternalFrameTitlePane.restoreButtonText", bundle.getString("restoreButtonText"));
        UIManager.put("InternalFrameTitlePane.moveButtonText", bundle.getString("moveButtonText"));
        UIManager.put("InternalFrameTitlePane.sizeButtonText", bundle.getString("sizeButtonText"));
        UIManager.put("InternalFrameTitlePane.minimizeButtonText", bundle.getString("minimizeButtonText"));
        UIManager.put("InternalFrameTitlePane.maximizeButtonText", bundle.getString("maximizeButtonText"));
        UIManager.put("InternalFrameTitlePane.closeButtonText", bundle.getString("closeButtonText"));
    }
}
