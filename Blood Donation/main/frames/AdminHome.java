package main.frames;

import java.awt.event.ActionEvent;

import main.encrypt.Encrypt;
import main.sql.ConnectSQL;

public class AdminHome extends HomeStruct{

    private static AdminHome instance;

    public AdminHome(){
        super("Admin");
        this.btLogin.setBounds(280,550, 250, 100);
        this.btSignUp.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btLogin){
            super.getInput();
            boolean isValid = ConnectSQL.isValid("admin", user,Encrypt.encrypt(getStrToEncrypt()));
            if(isValid == true){
                this.dispose();

                DashBoardStruct run = new DashBoardStruct();
                run.setVisible(true);
            }
        }else if(e.getSource() == btBack){
            if(isSignUpClicked == true){
                super.processInBackGround("admin");
            }else{
                super.backToHome();
            }
        }
    }

    public static AdminHome getInstance() {
        if (instance == null) {
            instance = new AdminHome();
        }
        return instance;
    }
}
