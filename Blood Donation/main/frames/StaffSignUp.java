package main.frames;

import java.awt.event.ActionEvent;

public class StaffSignUp extends SignUpStruct{
    public StaffSignUp(){
        super();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            super.signUpCompleted(false);
        }
    }
}
