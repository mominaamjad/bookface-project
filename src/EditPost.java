import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPost extends JFrame {
    Color accent=new Color(24,119,242);
    Color accent2=new Color(81,180,5);
    String user;
    Post p;
    JTextField edits;
    JButton save;
    JButton back;
    EditPost(String user,Post p){
        MyActionListener a=new MyActionListener();
        this.user=user;
        this.p=p;
        setVisible(true);
        setLayout(new GridLayout(3,1));
        setSize(400,180);
        setTitle("Edit Post");
        getContentPane().setBackground(Color.WHITE);

        edits=new JTextField();
        edits.setText(p.getContent());

        save=new JButton("Save Changes");
        save.setForeground(Color.WHITE);
        save.setBackground(accent);
        save.addActionListener(a);

        back=new JButton("Back");
        back.setForeground(accent);
        back.setBackground(Color.white);
        back.addActionListener(a);

        add(edits);
        add(save);
        add(back);
    }
    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(save)){
                FileOperations.editPost(user,p,edits.getText());
                JOptionPane.showMessageDialog(new JFrame(),"Changes saved !");
                dispose();
            }
            if(e.getSource().equals(back)){
                dispose();
            }
        }
    }
}
