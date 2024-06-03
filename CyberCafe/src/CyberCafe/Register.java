package CyberCafe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Register extends JFrame{
	private static Register Instance;
	public static Register getInstance(){
		if(Instance==null){
			Instance=new Register();
		}
		return Instance;
	}
	//字体
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,40);
	Font f1=new Font("黑体",Font.BOLD,50);
	//文本框
	JPanel p1=new JPanel();
	JLabel Accounts=new JLabel("账号");
	JLabel Password=new JLabel("密码");
	JTextField inputAccounts=new JTextField(20);
	JPasswordField inputPassword=new JPasswordField(20);
	//注册按钮
	JPanel pBtn=new JPanel();
	JButton registerBtn=new JButton("注册");
	//Tips
	JLabel Tips=new JLabel();
	JLabel word=new JLabel();
	//按钮监听器（添加注册用户）
	private ActionListener alBtn=new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			String user=inputAccounts.getText();
			String pw=new String(inputPassword.getPassword());
			try {
				SqlHelper.getInstance().addUser(user, pw);
				Tips.setText("注册成功");
				Thread.sleep(1000);
				Login.getInstance();
			} catch (Exception e) {
				Tips.setText("账号重复，注册失败");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	Register(){
		setTitle("账号注册");
		setLocation(600,300);
		setSize(1000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//文本框界面初始化
		Accounts.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Accounts.setFont(f);
		Password.setFont(f);
		inputAccounts.setFont(f);
		inputPassword.setFont(f);
		p1.setLayout(new GridLayout(8,1,0,0));
		p1.add(word);
		p1.add(Accounts);
		p1.add(inputAccounts);
		p1.add(Password);
		p1.add(inputPassword);
		p1.add(new JPanel());
		pBtn.setLayout(new FlowLayout());
		registerBtn.setPreferredSize(new Dimension(200,100));
		registerBtn.addActionListener(alBtn);
		registerBtn.setFont(f);
		pBtn.add(registerBtn);
		p1.add(pBtn);
		Tips.setFont(f);
		word.setText("       管理者注册");
		word.setFont(f1);
		word.setForeground(Color.blue);
		p1.add(Tips);
		//界面空白填充
		p1.setPreferredSize(new Dimension(700,1000));
		JPanel p0=new JPanel();
		p0.setPreferredSize(new Dimension(150,1000));
		JPanel p2=new JPanel();
		p2.setPreferredSize(new Dimension(150,1000));
		setLayout(new BorderLayout());
		add("Center",p1);
		add("West",p0);
		add("East",p2);
		//界面可见
		setVisible(true);
	}
}