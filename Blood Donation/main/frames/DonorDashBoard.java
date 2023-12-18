package main.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DonorDashBoard extends DashBoardStruct{

    public DonorDashBoard(){
        super();
        System.out.println(HomeStruct.getID());
        this.togglePanel.btInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DonorInfo donorInfo = new DonorInfo();
                donorInfo.setVisible(true);
            }
        });
        this.togglePanel.btResetPassword.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showPassword.getInstance("donor").setVisible(true);
            }
            
        });
    }
}