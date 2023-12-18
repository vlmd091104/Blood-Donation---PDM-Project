package main.frames;

import java.awt.event.ActionEvent;

// import main.encrypt.Encrypt;
// import main.sql.ConnectSQL;

public class AdminHome extends HomeStruct{

    private static AdminHome instance;

    public AdminHome(){
        super("Admin");
        this.lbLogin.setVisible(false);
        this.lbPassword.setVisible(false);
        this.lbUser.setVisible(false);
        this.tfUser.setVisible(false);
        this.pfPassword.setVisible(false);
        this.btLogin.setText("Enter");
        this.btLogin.setBounds(280,315, 250, 100);
        this.btSignUp.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btLogin){
            this.dispose();
            DashBoardStruct run = new AdminDashBoard();
            run.setVisible(true);
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