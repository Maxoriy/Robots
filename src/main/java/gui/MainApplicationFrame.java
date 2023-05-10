package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.*;

import log.Logger;

/**
 * Что требуется сделать:
 * 1. Метод создания меню перегружен функционалом и трудно читается.
 * Следует разделить его на серию более простых методов (или вообще выделить отдельный класс).
 */
public class MainApplicationFrame extends JFrame {
    private final JDesktopPane desktopPane = new JDesktopPane();
    private final ResourceBundle bundle;

    public MainApplicationFrame(ResourceBundle defaultBundle, int inset) {
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        bundle = defaultBundle;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        setContentPane(desktopPane);


        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        addWindow(new GameWindow(bundle, 400, 400));

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    protected LogWindow createLogWindow() {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource(), bundle);
        logWindow.setLocation(10, 10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug(bundle.getString("protocolWork"));
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(createLookAndFeelMenu());
        menuBar.add(createTestMenu());
        menuBar.add(createExitMenu());

        return menuBar;
    }

    private JMenu createLookAndFeelMenu() {
        JMenu lookAndFeelMenu = new JMenu(bundle.getString("lookAndFeelMenu"));
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription(
                bundle.getString("lookAndFeelMenuDescription"));

        JMenuItem systemLookAndFeel = new JMenuItem(bundle.getString("systemScheme"), KeyEvent.VK_S);
        systemLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            this.invalidate();
        });
        lookAndFeelMenu.add(systemLookAndFeel);

        JMenuItem crossplatformLookAndFeel = new JMenuItem(bundle.getString("universalScheme"), KeyEvent.VK_S);
        crossplatformLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            this.invalidate();
        });
        lookAndFeelMenu.add(crossplatformLookAndFeel);

        return lookAndFeelMenu;
    }

    private JMenu createTestMenu() {
        JMenu testMenu = new JMenu(bundle.getString("testMenu"));
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription(bundle.getString("testMenuDescription"));

        JMenuItem addLogMessageItem = new JMenuItem(bundle.getString("logItem"), KeyEvent.VK_S);
        addLogMessageItem.addActionListener((event) -> Logger.debug(bundle.getString("logDefaultMessage")));
        testMenu.add(addLogMessageItem);

        return testMenu;
    }

    private JMenu createExitMenu() {
        JMenu exitMenu = new JMenu(bundle.getString("quit"));
        exitMenu.setMnemonic(KeyEvent.VK_X);
        JMenuItem exitMenuItem = new JMenuItem(bundle.getString("ExitTheApplication"), KeyEvent.VK_S);
        exitMenuItem.addActionListener((event) -> {
            int confirm = JOptionPane.showOptionDialog(this,
                    bundle.getString("quitQuestion"),
                    bundle.getString("quitTitle"),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{bundle.getString("yes"), bundle.getString("no")},
                    null);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });
        exitMenu.add(exitMenuItem);
        return exitMenu;
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException
                 | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // just ignore
        }
    }
}
