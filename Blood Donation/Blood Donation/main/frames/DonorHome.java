package main.frames;
import java.awt.event.ActionEvent;

import javax.swing.*;

import main.encrypt.Encrypt;
import main.sql.ConnectSQL;


public class DonorHome extends HomeStruct{

    private static DonorHome instance;

    public DonorHome(){
        super("Donor");
        this.lbSign.setBounds(120,220,300,200);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btSignUp){
            if(isSignUpClicked == false){
                super.pressed1SignUp("donor");
                super.repaintMethod();
            }else{
                super.pressed2SignUp();

                if(tfUser.getText().trim().isEmpty()||getStrToEncrypt().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "There is some empty fields, pls fill in the empty", "Message", JOptionPane.WARNING_MESSAGE);
                    isSignUpClicked = true;
                }else{
                    SignUpStruct nextFrame = new DonorSignUp();
                    this.dispose();
                    nextFrame.setVisible(true);
                }
            }
        }else if(e.getSource() == btLogin){
            super.getInput();
            boolean isValid = ConnectSQL.isValid("donor",user,Encrypt.encrypt(getStrToEncrypt()));
            if(isValid == true){
                this.dispose();

                DashBoardStruct run = new DonorDashBoard();
                run.setVisible(true);
            }
        }else if(e.getSource() == btBack){;
            if(isSignUpClicked == true){
                super.processInBackGround("donor");
            }else{
                super.backToHome();
            }
        }
    }
    public static DonorHome getInstance() {
        if (instance == null) {
            instance = new DonorHome();
        }
        return instance;
    }
    public static DonorHome restInstance(){
        instance = null;
        return instance;
    }
}
