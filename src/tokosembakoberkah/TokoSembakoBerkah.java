/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tokosembakoberkah;

import tokosembakoberkah.view.LoginFrame;

/**
 *
 * @author Lenovo
 */
public class TokoSembakoBerkah {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setLoginListener(new LoginListener() {
                    @Override
                    public void onLoginSuccess() {
                        MainFrame mainFrame = new MainFrame();
                        mainFrame.setVisible(true);
                        loginFrame.dispose();
                    }
                });
                loginFrame.setVisible(true);
            }
        });
    }
}
