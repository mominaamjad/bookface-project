import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddComment extends JFrame {
    Color accent=new Color(24,119,242);
    Color accent2=new Color(81,180,5);
    String user;
    Post p;
    JLabel content;
    JTextField comment;
    JButton post;
    JButton back;
    AddComment(String user,Post p){
        MyActionListener a=new MyActionListener();
        this.user=user;
        this.p=p;
        setVisible(true);
        setLayout(new GridLayout(4,1));
        setSize(400,200);
        setTitle("Add a Comment");
        getContentPane().setBackground(Color.WHITE);

        content=new JLabel(p.getContent());
        comment=new JTextField();

        post=new JButton("Post Comment");
        post.setForeground(Color.WHITE);
        post.setBackground(accent2);
        post.addActionListener(a);

        back=new JButton("Back");
        back.setForeground(accent2);
        back.setBackground(Color.white);
        back.addActionListener(a);

        add(content);
        add(comment);
        add(post);
        add(back);
    }
    public class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(post)){
                FileOperations.commentOnPost(user,p,comment.getText());
                JOptionPane.showMessageDialog(new JFrame(),"Comment Posted");
                dispose();
            }
            if(e.getSource().equals(back)){
                dispose();
            }
        }
    }
}
