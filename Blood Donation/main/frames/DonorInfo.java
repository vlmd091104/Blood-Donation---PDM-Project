package main.frames;

import java.awt.event.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.sql.ConnectSQL;

public class DonorInfo extends DonorSignUp{

    private List<String> data;

    public DonorInfo(){
        super();

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit this frame?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        
        this.data = ConnectSQL.loadData("donor", HomeStruct.getID());

        super.setData(data.get(0), data.get(1), data.get(4), data.get(5), data.get(6));
        this.tfDoB.setText(data.get(2));
        this.box.setSelectedItem(data.get(3));
        
        this.btBack.setVisible(false);
        this.btClear.setVisible(false);
        this.btSignUp.setVisible(false);
        this.btUpdate.setVisible(true);
        this.btUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you want to change your information", "Message", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                    btUpdate.setEnabled(false);
                    if(ConnectSQL.updateData("donor", HomeStruct.getID(),tfFirstName.getText(), tfLastName.getText(), tfDoB.getText(), (String)box.getSelectedItem(), tfEmail.getText(), tfPhone.getText(), tfAddress.getText(), null)){
                        JOptionPane.showMessageDialog(null, "Update successfully", "Message", JOptionPane.DEFAULT_OPTION);
                    }
                }
                btUpdate.setEnabled(true);
            }
        
        });
    }

}
