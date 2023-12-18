package main.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StaffDashBoard extends DashBoardStruct{

    public StaffDashBoard(){
        super();
        this.togglePanel.setSize(150, 250);
        this.togglePanel.btForm.setVisible(false);
        this.togglePanel.btInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StaffInfo staffInfo = new StaffInfo();
                staffInfo.setVisible(true);
            }
        });
        this.togglePanel.btResetPassword.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showPassword.getInstance("staff").setVisible(true);
            }
            
        });
    }
}