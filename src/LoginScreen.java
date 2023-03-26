import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;

public class LoginScreen extends JFrame {
    JPanel header,info;
    JButton login,signup;
    JTextField name;
    JPasswordField password;
    JLabel title, nameLabel,passwordLabel;
    JLabel partition;

    public LoginScreen(){
        Color accent=new Color(24,119,242);
        Color accent2=new Color(81,180,5);
        Font f=new Font("Segoe UI",BOLD,70);
        Font f1=new Font("Arial",PLAIN,12);
        Font f2=new Font("Arial",BOLD,20);
        MyActionListener a=new MyActionListener();

        setSize(1370,760);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Bookface Log in");

        title=new JLabel("bookface");
        title.setFont(f);
        title.setForeground(accent);
        header=new JPanel();
        header.setBounds(250,290,350,90);
        header.add(title);

        name=new JTextField();
        name.setBounds(20,45,360,30);

        password=new JPasswordField();
        password.setEchoChar('*');
        password.setBounds(20,110,360,30);

        nameLabel=new JLabel("Name");
        nameLabel.setFont(f1);
        nameLabel.setBounds(20,20,100,20);

        passwordLabel=new JLabel("Password");
        passwordLabel.setFont(f1);
        passwordLabel.setBounds(20,85,100,20);

        login=new JButton("Log in");
        login.setFont(f2);
        login.addActionListener(a);
        login.setBackground(accent);
        login.setForeground(Color.WHITE);
        login.setBounds(20,155,360,40);

        partition=new JLabel("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        partition.setBounds(20,215,360,10);
        partition.setForeground(Color.lightGray);

        signup=new JButton("Sign up");
        signup.setFont(f2);
        signup.addActionListener(a);
        signup.setBackground(accent2);
        signup.setForeground(Color.WHITE);
        signup.setBounds(20,240,360,40);

        info=new JPanel(null);
        info.setBounds(750,200,400,300);
        info.setBackground(Color.WHITE);
        info.add(nameLabel);
        info.add(name);
        info.add(passwordLabel);
        info.add(password);
        info.add(login);
        info.add(partition);
        info.add(signup);

        add(header);
        add(info);
        setVisible(true);
    }
    public class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Log in")){
                User u = FileOperations.userLogin(name.getText(),password.getText());
                if(u!=null){
                    dispose();
                    MainScreen ms = new MainScreen(u);
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(),"Invalid Username or Password");
                }
            }
            if(e.getActionCommand().equals("Sign up")){
                dispose();
                SignUpScreen sus = new SignUpScreen();
            }
        }
    }
}
