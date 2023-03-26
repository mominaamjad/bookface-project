import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.awt.Font.BOLD;

public class ViewUser extends JFrame {
    Color accent=new Color(24,119,242);
    Color accent2=new Color(81,165,5);
    Font f =new Font("Segoe UI",BOLD,15);
    User searcher;
    User u;
    JLabel[] info;
    JButton sendRequest;
    JButton back;

    ViewUser(User searcher,User u){
        this.u=u;
        this.searcher=searcher;
        setVisible(true);
        setTitle("User");
        setSize(265,300);
        setLayout(new GridLayout(5,1));
        getContentPane().setBackground(Color.WHITE);
        MyActionListener a=new MyActionListener();

        info=new JLabel[3];
        for(int i=0;i<info.length;i++){
            info[i]=new JLabel();
        }
        info[0].setText("Username: "+u.getUserName());
        add(info[0]);
        info[1].setText("       *:･ﾟ✧*:･ﾟ POSTS PREVIEW *:･ﾟ✧*:･ﾟ");

        info[1].setForeground(accent);
        add(info[1]);

        ArrayList<Post> posts=u.getPost();
        if(posts.size()>0){
            info[2].setText(posts.get(0).getContent());
        }
        else{
            info[2].setText("                    << no posts found >>");
        }
        add(info[2]);

        sendRequest=new JButton("Send Request");
        sendRequest.setBackground(accent2);
        sendRequest.setForeground(Color.WHITE);
        sendRequest.addActionListener(a);
        add(sendRequest);
        if(searcher.equals(u))
            sendRequest.setEnabled(false);

        back=new JButton("Back");
        back.setBackground(Color.WHITE);
        back.addActionListener(a);
        add(back);
    }

    public class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Send Request")) {
                boolean req=FileOperations.sendRequest(searcher.getUserName(),u.getUserName());
                sendRequest.setEnabled(false);
                if(req){
                    JOptionPane.showMessageDialog(new JFrame(),"Request Sent");
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(),"Request Already sent || Already friends <3");
                }
            }
            if(e.getActionCommand().equals("Back")){
                dispose();
            }
        }
    }
}