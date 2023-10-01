import studentpack.student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class login_page extends Component implements ActionListener{
    login_page(){
        JFrame f = new JFrame("Login page");
        f.setSize(320,220);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(3);
        JLabel l1,l2;
        l1=new JLabel("Username");
        l2=new JLabel("Password");
        l1.setBounds(35,30,80,30);
        l2.setBounds(35,75,80,30);


        JTextField t1;
        t1= new JTextField();
        t1.setBounds(120,30,150,25);
        JPasswordField t2=new JPasswordField();
        t2.setBounds(120,75,150,25);
        JButton b1 = new JButton("Admin Login");
        b1.setBounds(20,130,110,25);

        JButton b2 = new JButton("Student Login");
        b2.setBounds(150,130,120,25);

        String url="jdbc:mysql://localhost:3306";
        String uname="root";
        String paswd="5361";
        String dataBaseName = "admins";
        String studdataBaseName="college";



        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, paswd);
                    Statement st = con.createStatement();
                    String q1= "use "+dataBaseName+";";
                    String t11=t1.getText();
                    String t12=t2.getText();
                    st.executeUpdate(q1);
                    String q2 = "SELECT * FROM login WHERE user='" + t11 + "' AND passw='" + t12 + "';";
                    ResultSet rs = st.executeQuery(q2);

                    if(rs.next()){
                        if (t11.equals(rs.getObject(1)) && t12.equals(rs.getObject(2))){

                            adminpack.admin a = new adminpack.admin();
                        }}
                    else{
                        JOptionPane.showMessageDialog(login_page.this, "Wrong username or password","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                ;
            }
        });

        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, paswd);
                    Statement st = con.createStatement();
                    String q1= "use "+studdataBaseName+";";
                    String t11=t1.getText();
                    String t12=t2.getText();
                    st.executeUpdate(q1);
                    String q2 = "SELECT * FROM student1 WHERE name='" + t11 + "' AND dob='" + t12 + "';";
                    ResultSet rs = st.executeQuery(q2);

                    if(rs.next()){
                        if (t11.equals(rs.getObject(1)) && t12.equals(rs.getObject(6))){

                            student s = new student();
                        }}
                    else{
                        JOptionPane.showMessageDialog(login_page.this, "Wrong username or password","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });


        f.add(l1);
        f.add(l2);
        f.add(t1);
        f.add(t2);
        f.add(b1);
        f.add(b2);
        f.setBackground(Color.gray);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        System.out.println("Button pressed.");
    }

    public static void main(String args[]){
        new login_page();

    }}