package adminpack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class admin extends Component {
    public admin(){
        String url="jdbc:mysql://localhost:3306";
        String uname="root";
        String paswd="5361";
        ImageIcon studenticon = new ImageIcon("C:\\Users\\Tejaswini\\Desktop\\yashu\\images\\students.png");
        ImageIcon deleteicon = new ImageIcon("C:\\Users\\Tejaswini\\Desktop\\yashu\\images\\delete.png");
        ImageIcon showicon = new ImageIcon("C:\\Users\\Tejaswini\\Desktop\\yashu\\images\\view.png");
        ImageIcon editicon = new ImageIcon("C:\\Users\\Tejaswini\\Desktop\\yashu\\images\\writing.png");
        ImageIcon riticon = new ImageIcon("C:\\Users\\Tejaswini\\Desktop\\yashu\\images\\rit4.png");


        JLabel label1 = new JLabel("Ramaiah Institute of Technology");
        label1.setFont(new Font("Arial", Font.BOLD, 26));
        label1.setBackground(Color.white);
        JLabel label2 = new JLabel("M.S.R Nagar,Mathikere,Bengaluru,560054");
        label2.setFont(new Font("Arial", Font.ITALIC, 18));
        JLabel label3=new JLabel(riticon);
        String databaseName="college";
        JFrame adminpage= new JFrame("Admin Home Page");
        adminpage.setSize(1200,800);
        adminpage.setLayout(null);
        JPanel upperp = new JPanel();
        upperp.setBounds(0,0,1200,250);
        upperp.setBackground(Color.LIGHT_GRAY);
        adminpage.setVisible(true);
        adminpage.setLocationRelativeTo(null);
        adminpage.setResizable(false);
        adminpage.setDefaultCloseOperation(3);

        label1.setBounds(500, 60, 450, 40);
        label2.setBounds(510, 100, 400, 30);
        label3.setBounds(400, 50, 64, 84);
        adminpage.add(label2);
        adminpage.add(label1);
        adminpage.add(label3);

        JPanel belowp= new JPanel();
        belowp.setBounds(0,250,1200,550);
        belowp.setBackground(Color.GRAY);
        adminpage.setVisible(true);

        adminpage.setLocationRelativeTo(null);
        adminpage.setResizable(false);
        adminpage.setDefaultCloseOperation(3);

        JButton b1,b2,b3,b4;
        b1= new JButton("Add Student",studenticon);
        b2= new JButton("Edit Student",editicon);
        b3= new JButton("Delete Student",deleteicon);
        b4= new JButton("View List",showicon);

        b1.setBounds(150,300,400,150);
        b2.setBounds(650,300,400,150);
        b3.setBounds(150,500,400,150);
        b4.setBounds(650,500,400,150);

        b1.setFont(new Font("Arial", Font.BOLD, 18));
        b2.setFont(new Font("Arial", Font.BOLD, 18));
        b3.setFont(new Font("Arial", Font.BOLD, 18));
        b4.setFont(new Font("Arial", Font.BOLD, 18));


        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editstudent();
            }
            public void editstudent() {
                JFrame j = new JFrame("EDIT STUDENT DETAILS");
                j.setSize(580, 800);
                j.setLayout(new BorderLayout(0,50));

                JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JLabel l1 = new JLabel("Enter the registration number to search:");
                JTextField t1 = new JTextField(20);
                JButton b1 = new JButton("Search");
                panel.setBounds(0,0,580,30);

                l1.setPreferredSize(new Dimension(230,30));
                t1.setPreferredSize(new Dimension(200,30));
                panel.add(l1);
                panel.add(t1);
                panel.add(b1);



                JLabel l2,l3,l4,l5,l6,l7,l8,l9,l10;
                l2=new JLabel("NAME:",JLabel.CENTER);
                l3=new JLabel("REGISTRATION No.:",JLabel.CENTER);
                l4 = new JLabel("DEPARTMENT:",JLabel.CENTER);
                l5 = new JLabel("FEES PAID:",JLabel.CENTER);
                l6 = new JLabel("SEMESTER:",JLabel.CENTER);
                l7 = new JLabel("D.O.B:",JLabel.CENTER);
                l8 = new JLabel("PHONE NUMBER:",JLabel.CENTER);
                l9 = new JLabel();
                l10 = new JLabel();

                JTextField t2,t3,t4,t5,t6,t7,t8;
                t2= new JTextField();
                t3= new JTextField();
                t4= new JTextField();
                t5= new JTextField();
                t6= new JTextField();
                t7= new JTextField();
                t8= new JTextField();


                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection(url, uname, paswd);
                            try (Statement st = con.createStatement()) {
                                String t11 = t1.getText();
                                String q1 = "USE " + databaseName + ";";
                                String q2 = "select * from student1 where Register_No=" + t11 + ";";
                                st.execute(q1);
                                ResultSet rs = st.executeQuery(q2);
                                int columncount = rs.getMetaData().getColumnCount();
                                Object[] row = new Object[columncount];
                                if (rs.next()) {
                                    for (int i = 1; i <= columncount; i++) {
                                        row[i - 1] = rs.getObject(i);
                                    }
                                }
                                t2.setText((String) row[0]);
                                t3.setText((String) row[1]);
                                t4.setText((String) row[2]);
                                t5.setText((String) row[3]);
                                t6.setText((String) row[4]);
                                t7.setText((String) row[5]);
                                t8.setText((String) row[6]);





                                st.executeUpdate(q1);

                                System.out.print(columncount + " columns");
                            }
                            con.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                JPanel panel1=new JPanel(new GridLayout(10,2,10,10));
                panel1.setBounds(0,200,580,700);
                panel1.add(l2);panel1.add(t2);panel1.add(l3);panel1.add(t3);panel1.add(l4);panel1.add(t4);panel1.add(l5);
                panel1.add(t5);panel1.add(l6);panel1.add(t6);panel1.add(l7);panel1.add(t7);panel1.add(l8);panel1.add(t8);
                panel1.add(l9);panel1.add(l10);

                JButton b2= new JButton("UPDATE");
                JButton b3 = new JButton("BACK");
//        JPanel panel2=new JPanel(new GridLayout(1,2,10,10));
//        panel2.add(b2);panel2.add(b3);
                panel1.add(b3);
                panel1.add(b2);

                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection(url, uname, paswd);
                            Statement st = con.createStatement();

                            String q1= "use "+databaseName+";";
                            String q2 = "delete from student1 where Register_No="+t1.getText()+";";
                            String t12= t2.getText();
                            String t13= t3.getText();
                            String t14= t4.getText();
                            String t15= t5.getText();
                            String t16= t6.getText();
                            String t17= t7.getText();
                            String t18= t8.getText();
                            t1.setText(t13);
                            String q3 = "insert into student1 VALUES('" + t12 + "', " + t13 + ", '" + t14 + "', " + t15 + ", '" + t16 + "', '" + t17 + "', '" + t18 + "');";
                            st.executeUpdate(q1);
                            st.executeUpdate(q2);
                            st.executeUpdate(q3);
                            con.close();
                            st.close();
                            JOptionPane.showMessageDialog(admin.this, "Student details edited","Success",JOptionPane.INFORMATION_MESSAGE);

                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });

                b3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        j.dispose();

                    }
                });

                j.add(panel,BorderLayout.NORTH);
                j.add(panel1,BorderLayout.CENTER);
//        j.add(panel2,BorderLayout.SOUTH);

                j.setVisible(true);
                j.setLocationRelativeTo(null);
                j.setResizable(false);
                j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }

        });


        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete_student();
            }
            public void delete_student() {
                JFrame j = new JFrame("DELETE STUDENT DETAILS");
                j.setSize(580, 800);
                j.setLayout(new BorderLayout(0,50));

                JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JLabel l1 = new JLabel("Enter the registration number to search:");
                JTextField t1 = new JTextField(20);
                JButton b1 = new JButton("Search");
                panel.setBounds(0,0,580,30);

                l1.setPreferredSize(new Dimension(230,30));
                t1.setPreferredSize(new Dimension(200,30));
                panel.add(l1);
                panel.add(t1);
                panel.add(b1);


                JLabel l2,l3,l4,l5,l6,l7,l8,l9,l10;
                l2=new JLabel("NAME:",JLabel.CENTER);
                l3=new JLabel("REGISTRATION No.:",JLabel.CENTER);
                l4 = new JLabel("DEPARTMENT:",JLabel.CENTER);
                l5 = new JLabel("FEES PAID:",JLabel.CENTER);
                l6 = new JLabel("SEMESTER:",JLabel.CENTER);
                l7 = new JLabel("D.O.B:",JLabel.CENTER);
                l8 = new JLabel("PHONE NUMBER:",JLabel.CENTER);
                l9 = new JLabel();
                l10 = new JLabel();

                JTextField t2,t3,t4,t5,t6,t7,t8;
                t2= new JTextField();
                t3= new JTextField();
                t4= new JTextField();
                t5= new JTextField();
                t6= new JTextField();
                t7= new JTextField();
                t8= new JTextField();

                JPanel panel1=new JPanel(new GridLayout(10,2,10,10));
                panel1.setBounds(0,200,580,700);
                panel1.add(l2);panel1.add(t2);panel1.add(l3);panel1.add(t3);panel1.add(l4);panel1.add(t4);panel1.add(l5);
                panel1.add(t5);panel1.add(l6);panel1.add(t6);panel1.add(l7);panel1.add(t7);panel1.add(l8);panel1.add(t8);
                panel1.add(l9);panel1.add(l10);

                JButton b2= new JButton("DELETE");
                JButton b3 = new JButton("BACK");
//        JPanel panel2=new JPanel(new GridLayout(1,2,10,10));
//        panel2.add(b2);panel2.add(b3);
                panel1.add(b3);
                panel1.add(b2);

                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con=DriverManager.getConnection(url,uname,paswd);
                            Statement st=con.createStatement();
                            String q2="use "+databaseName+";";
                            String q1="delete from student1 where Register_No="+t1.getText()+";";
                            st.executeUpdate(q2);
                            st.executeUpdate(q1);

                            t2.setText("");
                            t3.setText("");
                            t4.setText("");
                            t5.setText("");
                            t6.setText("");
                            t7.setText("");
                            t8.setText("");
                            t1.setText("");

                            JOptionPane.showMessageDialog(admin.this, "Student details deleted","Success",JOptionPane.INFORMATION_MESSAGE);

                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });
                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con =DriverManager.getConnection(url,uname,paswd);
                            try (Statement st = con.createStatement()) {
                                String t11=t1.getText();
                                String q1="USE "+databaseName+";";
                                String q2="select * from student1 where Register_No="+t11+";";
                                st.execute(q1);
                                ResultSet rs=st.executeQuery(q2);
                                int columncount=rs.getMetaData().getColumnCount();
                                Object[] row=new Object[columncount];
                                if(rs.next()){
                                    for(int i=1;i<=columncount;i++){
                                        row[i-1]=rs.getObject(i);
                                    }
                                }
                                t2.setText((String) row[0]);
                                t3.setText((String) row[1]);
                                t4.setText((String) row[2]);
                                t5.setText((String) row[3]);
                                t6.setText((String) row[4]);
                                t7.setText((String) row[5]);
                                t8.setText((String) row[6]);
                                t2.setEditable(false);
                                t3.setEditable(false);
                                t4.setEditable(false);
                                t5.setEditable(false);
                                t6.setEditable(false);
                                t7.setEditable(false);
                                t8.setEditable(false);

                                st.executeUpdate(q1);

                                System.out.print(columncount + " columns");
                            }
                            con.close();
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });

                b3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        j.dispose();

                    }
                });

                j.add(panel,BorderLayout.NORTH);
                j.add(panel1,BorderLayout.CENTER);
//        j.add(panel2,BorderLayout.SOUTH);

                j.setVisible(true);
                j.setLocationRelativeTo(null);
                j.setResizable(false);
                j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        adminpage.add(upperp);
        adminpage.add(b1);
        adminpage.add(b2);
        adminpage.add(b3);
        adminpage.add(b4);
        adminpage.add(belowp);

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Table Display Example");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(1200, 800);

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JTable table = new JTable();

                JScrollPane scrollPane = new JScrollPane(table);
                panel.add(scrollPane, BorderLayout.CENTER);

                DefaultTableModel model = new DefaultTableModel();
                table.setModel(model);

                try {

                    Connection connection = DriverManager.getConnection(url, uname, paswd);
                    Statement statement = connection.createStatement();
                    String q1="use "+databaseName+";";
                    statement.execute(q1);
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM student1");

                    // Retrieve column names from ResultSet metadata
                    // ResultSetMetaData columnCount=resultSet.getMetaData();
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    //System.out.println(columnCount);
                    for (int i = 1; i <= columnCount; i++) {
                        model.addColumn(resultSet.getMetaData().getColumnName(i));
                        //System.out.println(resultSet.getCursorName());
                    }

                    // Populate the table model with data from the ResultSet
                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        model.addRow(row);
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (Exception xe) {
                    xe.printStackTrace();
                }

                frame.add(panel);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);


            }
        });





        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_student();
            }

            public void add_student() {
                JFrame j = new JFrame("ADD STUDENT DETAILS");

                j.setSize(1200, 800);
                j.setLocationRelativeTo(null);
                j.setResizable(false);
                j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
                l1 = new JLabel("NAME:", JLabel.CENTER);
                l2 = new JLabel("REGISTRATION No.:", JLabel.CENTER);
                l3 = new JLabel("DEPARTMENT:", JLabel.CENTER);
                l4 = new JLabel("FEES PAID:", JLabel.CENTER);
                l5 = new JLabel("SEMESTER:", JLabel.CENTER);
                l6 = new JLabel("D.O.B:", JLabel.CENTER);
                l7 = new JLabel("PHONE NUMBER:", JLabel.CENTER);
                l8 = new JLabel();
                l9 = new JLabel();

                JTextField t1, t2, t3, t4, t5, t6, t7;
                t1 = new JTextField();
                t1.setPreferredSize(new Dimension(50, 30));
                t2 = new JTextField();
                t3 = new JTextField();
                t4 = new JTextField();
                t5 = new JTextField();
                t6 = new JTextField();
                t7 = new JTextField();


                j.setLayout(new GridLayout(10, 2, 25, 25));


                j.add(l1);
                j.add(t1);
                j.add(l2);
                j.add(t2);
                j.add(l3);
                j.add(t3);
                j.add(l4);
                j.add(t4);
                j.add(l5);
                j.add(t5);
                j.add(l6);
                j.add(t6);
                j.add(l7);
                j.add(t7);
                j.add(l8);
                j.add(l9);

                JButton back, save;
                back = new JButton("Previous Page");
                save = new JButton("Save details");
                j.add(back);
                j.add(save);

                List<JTextField> textFields=new ArrayList<>();
                textFields.add(t1);
                textFields.add(t2);
                textFields.add(t3);
                textFields.add(t4);
                textFields.add(t5);
                textFields.add(t6);
                textFields.add(t7);

                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        j.dispose();
                    }
                });



                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String t11=t1.getText();
                        String t12=t2.getText();
                        String t13=t3.getText();
                        String t14=t4.getText();
                        String t15=t5.getText();
                        String t16=t6.getText();
                        String t17=t7.getText();
                        try{
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con =DriverManager.getConnection(url,uname,paswd);
                            try (Statement st = con.createStatement()) {
                                String query1 = "CREATE DATABASE IF NOT EXISTS " + databaseName + ";";
                                String query4="USE "+databaseName+";";
                                //String query2 = "insert into student1 VALUES(t11,t12,t13,t14,t15,t16,t17);";
                                String query2 = "insert into student1 VALUES('" + t11 + "', " + t12 + ", '" + t13 + "', " + t14 + ", '" + t15 + "', '" + t16 + "', '" + t17 + "');";

                                String query3 = "CREATE TABLE IF NOT EXISTS student1 (Name VARCHAR(50), Register_No VARCHAR(50) PRIMARY KEY, Department VARCHAR(50) , Fee_paid VARCHAR(50) NOT NULL, Semester VARCHAR(50) NOT NULL, dob VARCHAR(50) NOT NULL, Phone_No VARCHAR(15) NOT NULL);";
                                //model.addRow(new Object[]{t11,t12,t13,t14,t15,t16,t17});
                                //departmentComboBox.setSelectedItem("Select Department");
                                for (JTextField textField : textFields) {
                                    textField.setText("");
                                }
                                st.executeUpdate(query1);
                                st.executeUpdate(query4);
                                st.executeUpdate(query3);
                                int count = st.executeUpdate(query2);
                                System.out.print(count + " Rows affected");

                                JOptionPane.showMessageDialog(admin.this, "Student details added","Success",JOptionPane.INFORMATION_MESSAGE);
                            }
                            con.close();
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });


                j.setBackground(Color.gray);
                j.setVisible(true);

            }

        });
    }
    public static void main(String args[]){
        admin a = new admin();
    }
}