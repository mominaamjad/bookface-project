import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewComments extends JFrame {
    Color accent=new Color(24,119,242);
    Post post;
    JPanel heading;
    JLabel postContent,extra;
    JLabel[] comments;
    ViewComments(Post post){
        this.post=post;
        setVisible(true);
        setLayout(new GridLayout(10,1,10,10));
        setTitle("Comments");
        setSize(400,500);
        getContentPane().setBackground(Color.WHITE);

        postContent=new JLabel(post.getContent());

        extra=new JLabel("*:･ﾟ✧*:･ﾟ COMMENTS *:･ﾟ✧*:･ﾟ");
        extra.setForeground(Color.WHITE);

        heading=new JPanel();
        heading.setBackground(accent);
        heading.add(extra);

        comments=new JLabel[10];
        int count=0;
        ArrayList<String> c=post.getComment();
        if(c.size()>0){
            for(int i=0;i<c.size()&&i<10;i++){
                comments[i]=new JLabel("⬤   "+c.get(i));
                count++;
            }
        }

        add(postContent);
        add(heading);
        for(int i=0;i<count;i++){
            add(comments[i]);
        }
    }
}
