package main.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DonorDashBoard extends DashBoardStruct{

    public DonorDashBoard(){
        super();
        this.togglePanel.btInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fuck");
                StaffInfo staffInfo = new StaffInfo();
                staffInfo.setVisible(true);
            }
            
        });
    }
}
