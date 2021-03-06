/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictacktoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moh
 */
public class LoginController implements ActionListener {
    private LoginModel model ;
    private Login view ;

    public LoginController(LoginModel model, Login view) {
        this.model = model;
        this.view = view;
        addActionEvent();
    }
    
    
    public boolean login () throws SQLException, InterruptedException {       
        String email = view.getEmail();
        String password = view.getPassword();
        if (model.login(email, password)) {
            return true ;
        } else {
            return false ;
        }    
    }

      public void actionPerformed(ActionEvent e)  {
        // if the user clicked login check the controller function if the password and username matches the database 
        if (e.getSource() == view.login) {
            try {
                if (view.getEmail().equals("") || view.getPassword().equals("")) {
                    view.showOptionPane("Please Fill in All the required Fields");
                } else {
                if (login()){
                  view.showOptionPane("welcome " + BinaryFiles.read());
                  Dashboard dashboard  = new Dashboard();
                  view.dispose();
                } else {
                    view.showOptionPane("The Email or Password that you've entered is incorrect");
                }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (e.getSource() == view.register) {
            try {
                openRegisterWindow();
                view.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void addActionEvent() {
        //adding Action listener to components
        view.login.addActionListener(this);
        view.register.addActionListener(this);
    }
     
     private void openRegisterWindow () throws SQLException {
         Register view = new Register ();
         RegisterModel model = new RegisterModel();
         RegisterController controller = new RegisterController (model,view);
     }
    
    
    
}
