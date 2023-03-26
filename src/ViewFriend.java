import java.awt.*;
public class ViewFriend extends ViewUser{
    ViewFriend(User searcher, User u) {
        super(searcher, u);
        super.remove(super.sendRequest);
        setTitle("Friend");
        setSize(265,260);
        setLayout(new GridLayout(4,1));
        super.back.setBackground(accent2);
        super.back.setForeground(Color.WHITE);
    }
}
