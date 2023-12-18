package main.frames;
import java.awt.event.ActionEvent;

import javax.swing.*;

import main.encrypt.Encrypt;
import main.sql.ConnectSQL;

public class StaffHome extends HomeStruct{

    private static StaffHome instance;
    
    public StaffHome(){
        super("Staff");
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btSignUp){
            if(isSignUpClicked == false){
                super.pressed1SignUp("staff");
                super.repaintMethod();
            }else{
                super.pressed2SignUp();

                if(tfUser.getText().trim().isEmpty()||getStrToEncrypt().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "There is some empty fields, pls fill in the empty", "Message", JOptionPane.WARNING_MESSAGE);
                    isSignUpClicked = true;
                }else{
                    SignUpStruct nextFrame = new StaffSignUp();
                    this.dispose();
                    nextFrame.setVisible(true);
                }
            }
        }else if(e.getSource() == btLogin){
            super.getInput();
            boolean isValid = ConnectSQL.isValid("staff", user,Encrypt.encrypt(getStrToEncrypt()));
            if(isValid == true){
                this.dispose();

                DashBoardStruct run = new StaffDashBoard();
                run.setVisible(true);
            }
        }else if(e.getSource() == btBack){
            if(isSignUpClicked == true){
                super.processInBackGround("staff");
            }else{
                super.backToHome();
            }
        }
    }

    public static StaffHome getInstance() {
        if (instance == null) {
            instance = new StaffHome();
        }
        return instance;
    }
    public static StaffHome restInstance(){
        instance = null;
        return instance;
    }
}