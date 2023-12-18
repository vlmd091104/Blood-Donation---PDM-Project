package main.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

import main.button.RoundedButton;
import main.Frame;
import main.sql.ConnectSQL;

public class HomeStruct extends Frame implements ActionListener{

    static String user;
    private char[] password;
    private static String passwordStr;
    private static int ID;

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
    RoundedButton btBack = new RoundedButton("Back");

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);

    boolean isSignUpClicked = false;

    public HomeStruct(String Who){
        super();

        /*Stuffs set */
        //Sign Place
        lbSign.setBounds(150, 220, 300, 200);
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
        btLogin.setEnabled(true);
        btLogin.addActionListener(this);

        btSignUp.setBounds(450, 550, 250, 100);
        btSignUp.setFont(btFont);
        btSignUp.setBackground(bloodColor);
        btSignUp.setForeground(Color.WHITE);
        btSignUp.setFocusable(false);
        btSignUp.setEnabled(true);
        btSignUp.addActionListener(this);

        lbSignUp.setBounds(260, 100, 350, 100);
        lbSignUp.setFont(signFont);
        lbSignUp.setForeground(Color.BLACK);

        btBack.setBounds(50,620, 150, 50);
        btBack.setFont(mainFont.deriveFont(30f));
        btBack.setBackground(Color.WHITE);
        btBack.setForeground(bloodColor);
        btBack.setEnabled(true);
        btBack.setFocusable(false);
        btBack.addActionListener(this);

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

        PnSign.add(btBack);

        PnMain.setBounds(0, 0, 1280, 720);
        PnMain.setLayout(null);
        PnMain.add(PnPlace);
        PnMain.add(PnSign);

        /*Frame setting */
        this.add(PnMain);
    }

    public HomeStruct(){
        super();

        lbPassword.setBounds(100, 50, 200, 50);
        lbPassword.setFont(mainFont);
        lbPassword.setForeground(Color.BLACK);

        pfPassword.setBounds(300, 50, 300, 50);
        pfPassword.setFont(mainFont);
        pfPassword.setBorder(blackLine);

        this.add(lbPassword);
        this.add(pfPassword);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void getInput(){
        user = tfUser.getText();
        this.password = pfPassword.getPassword();
        passwordStr = new String(password);
    }

    public void pressed1SignUp(String userType){
        PnPlace.remove(lbLogin);
        PnPlace.remove(btLogin);

        PnPlace.add(lbSignUp);
        btSignUp.setBounds(280, 550, 300, 100);
        tfUser.setText(null);
        pfPassword.setText(null);

        if(ConnectSQL.loadID(userType) != 0){
            ID = ConnectSQL.loadID(userType);
        }else{
            JOptionPane.showMessageDialog(null, "There is some error, pls try again", "Warning", JOptionPane.WARNING_MESSAGE);
            this.dispose();
        }

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

    public static String getStrToEncrypt() {
        return passwordStr;
    }

    public static int getID(){
        return ID;
    }

    public static void setID(int id){
        ID = id;
    }
     
    public void backToHome(){
        this.dispose();
        Home home = new Home();
        home.setVisible(true);
    }

    public void processInBackGround(String userType){
        SwingWorker <Void, Void > worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground(){
                btBack.setEnabled(false);
                if(userType.equals("donor")){
                    setVisible(false);

                    DonorHome.getInstance().setVisible(true);
                    DonorHome.restInstance();
                }else if(userType.equals("staff")){
                    setVisible(false);
                    
                    StaffHome.getInstance().setVisible(true);
                    StaffHome.restInstance();
                }
                return null;
            }
            protected void done(){
                btBack.setEnabled(true);
            }
        };
        worker.execute();
    }
}
