package main.frames;
import java.awt.event.ActionEvent;

import main.encrypt.Encrypt;
import main.sql.ConnectSQL;


public class DonorHome extends HomeStruct{
    DonorHome(){
        super("DONOR");
        this.lbSign.setBounds(100,220,300,200);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btSignUp){
            if(isSignUpClicked == false){
                super.pressed1SignUp();
                this.tfUser.setText(ConnectSQL.loadID());
                super.repaintMethod();
            }else{
                super.pressed2SignUp();
                ConnectSQL.addPassword(Integer.valueOf(this.tfUser.getText()), Encrypt.encrypt(this.getStrToEncrypt()));

                SignUpStruct nextFrame = new DonorSignUp();
                this.dispose();
                nextFrame.setVisible(true);
            }
        }else if(e.getSource() == btLogin){
            super.getInput();
            ConnectSQL.isValid(Integer.valueOf(this.user),Encrypt.encrypt(this.getStrToEncrypt()));
        }
    }
}
