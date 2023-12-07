package main.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import main.button.RoundedButton;
import main.Frame;

public class HomeStruct extends Frame implements ActionListener{

    String user;
    private char[] password;
    private String passwordStr;

    Font mainFont = new Font(null, Font.CENTER_BASELINE, 30);
    Font signFont = mainFont.deriveFont(80f);
    Font btFont = mainFont.deriveFont(50f);

    Color bloodColor = new Color(240, 132, 134);

    JPanel PnMain = new JPanel();
 
    JPanel PnSign = new JPanel();
    JLabel lbSign = new JLabel();

    JPanel PnPlace = new JPanel();
    JLabel lbLogin = new JLabel("LOGIN");
    JLabel lbSignUp = new JLabel("SIGN UP");
    JLabel lbUser = new JLabel("User:");
    JLabel lbPassword = new JLabel("Password:");
    
    JTextField tfUser = new JTextField();
    JPasswordField pfPassword = new JPasswordField();
    
    RoundedButton btLogin = new RoundedButton("Login");
    RoundedButton btSignUp = new RoundedButton("Sign Up");

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);

    boolean isSignUpClicked = false;

    public HomeStruct(String Who){
        super();
        /*Stuffs set */
        //Sign Place
        lbSign.setBounds(125, 220, 300, 200);
        lbSign.setFont(signFont);
        lbSign.setForeground(Color.WHITE);
        lbSign.setText(Who);

        PnSign.setBounds(0, 0, 500, 720);
        PnSign.setBackground(bloodColor);
        PnSign.setLayout(null);
        PnSign.add(lbSign);

        //Login Place
        lbLogin.setBounds(280, 100, 300, 100);
        lbLogin.setFont(signFont);
        lbLogin.setForeground(Color.BLACK);

        lbUser.setBounds(100, 250, 150, 50);
        lbUser.setFont(mainFont);
        lbUser.setForeground(Color.BLACK);

        tfUser.setBounds(300, 250, 300, 50);
        tfUser.setFont(mainFont);
        tfUser.setBorder(blackLine);

        lbPassword.setBounds(100, 350, 200, 50);
        lbPassword.setFont(mainFont);
        lbPassword.setForeground(Color.BLACK);

        pfPassword.setBounds(300, 350, 300, 50);
        pfPassword.setFont(mainFont);
        pfPassword.setBorder(blackLine);

        btLogin.setBounds(100, 550, 250, 100);
        btLogin.setFont(btFont);
        btLogin.setBackground(bloodColor);
        btLogin.setForeground(Color.WHITE);
        btLogin.setFocusable(false);
        btLogin.addActionListener(this);

        btSignUp.setBounds(450, 550, 250, 100);
        btSignUp.setFont(btFont);
        btSignUp.setBackground(bloodColor);
        btSignUp.setForeground(Color.WHITE);
        btSignUp.setFocusable(false);
        btSignUp.addActionListener(this);

        lbSignUp.setBounds(260, 100, 350, 100);
        lbSignUp.setFont(signFont);
        lbSignUp.setForeground(Color.BLACK);

        PnPlace.setBounds(500, 0, 780, 720);
        PnPlace.setBackground(Color.WHITE);
        PnPlace.setLayout(null);
        PnPlace.add(lbLogin);
        PnPlace.add(lbUser);
        PnPlace.add(tfUser);
        PnPlace.add(lbPassword);
        PnPlace.add(pfPassword);
        PnPlace.add(btLogin);
        PnPlace.add(btSignUp);

        PnMain.setBounds(0, 0, 1280, 720);
        PnMain.setLayout(null);
        PnMain.add(PnPlace);
        PnMain.add(PnSign);

        /*Frame setting */
        this.add(PnMain);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btSignUp){
            if(isSignUpClicked == false){
                pressed1SignUp();
            }else{
                pressed2SignUp();
            }
        }
        else if(e.getSource() == btLogin){
            getInput();
        }
    }

    public void getInput(){
        this.user = tfUser.getText();
        this.password = pfPassword.getPassword();
        this.passwordStr = new String(password);
    }

    public void pressed1SignUp(){
        PnPlace.remove(lbLogin);
        PnPlace.remove(btLogin);

        PnPlace.add(lbSignUp);
        btSignUp.setBounds(280, 550, 300, 100);
        tfUser.setEditable(false);
        pfPassword.setText(null);

        isSignUpClicked = true;
    }
    public void pressed2SignUp(){
        getInput();
        isSignUpClicked = false;
    }

    public void  repaintMethod(){
        PnPlace.revalidate();
        PnPlace.repaint();
    }

    public String getStrToEncrypt() {
        return passwordStr;
    }
}
