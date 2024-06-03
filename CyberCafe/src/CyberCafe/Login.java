package CyberCafe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login extends JFrame{
	private static Login Instance;
	public static Login getInstance(){
		if(Instance==null){
			Instance=new Login();
		}
		Instance.setVisible(true);
		return Instance;
	}
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,40);
	Font f1=new Font("黑体",Font.BOLD|Font.ITALIC,50);
	JLabel Accounts=new JLabel("账号");
	JLabel Password=new JLabel("密码");
	JTextField inputAccounts=new JTextField(20);
	JPasswordField inputPassword=new JPasswordField(20);
	JButton registerBtn=new JButton("注册");
	JButton loginBtn=new JButton("登录");
	JLabel Tips=new JLabel();
	JPanel login_p=new JPanel();
	JLabel word=new JLabel();
	//按钮监听器
	private ActionListener alBtn=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			//注册界面
			if(command.equals("注册")){
				Register.getInstance();
			}
			//登陆界面
			else if(command.equals("登录")){
				String getAcc=inputAccounts.getText();
				String getPw=new String(inputPassword.getPassword());
				try {
					if(SqlHelper.getInstance().Login(getAcc, getPw)){
						Tips.setText("登录成功");
						ChooseFrame.getInstance();
						dispose();
					}
					else{
						Tips.setText("账号或密码错误");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
	Login(){
		setTitle("管理者登陆");
		setLocation(600,300);
		setSize(1000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//文本框界面初始化
		word.setText("   欢迎使用网吧管理系统");
		word.setFont(f1);
		word.setForeground(Color.pink);
		//word.setLocation(x, y);
		JPanel pnull=new JPanel();
		JPanel pnull0=new JPanel();
		JPanel pnull1=new JPanel();
		login_p.setBounds(0, 0, 1000, 625);
		login_p.setLayout(new GridLayout(9,1,0,0));
		JPanel p_b=new JPanel();
		p_b.setLayout(new GridLayout(1,3,0,0));
		//按钮界面初始化
		registerBtn.setFont(f);
		loginBtn.setFont(f);
		registerBtn.addActionListener(alBtn);
		loginBtn.addActionListener(alBtn);
		p_b.add(registerBtn);
		p_b.add(pnull0);
		p_b.add(loginBtn);
		//界面初始化
		Accounts.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Accounts.setFont(f);
		Password.setFont(f);
		inputAccounts.setFont(f);
		inputPassword.setFont(f);
		login_p.add(word);
		login_p.add(pnull1);
		login_p.add(Accounts);
		login_p.add(inputAccounts);
		login_p.add(Password);
		login_p.add(inputPassword);
		login_p.add(pnull);
		login_p.add(p_b);
		Tips.setFont(f);
		login_p.add(Tips);
		//空白界面填充
		login_p.setPreferredSize(new Dimension(1000, 625));
		JPanel p0=new JPanel();
		p0.setPreferredSize(new Dimension(150,1000));
		JPanel p2=new JPanel();
		p2.setPreferredSize(new Dimension(150,1000));
		setLayout(new BorderLayout());
		JPanel p3=new JPanel();
		p3.setPreferredSize(new Dimension(700,200));
		JPanel p4=new JPanel();
		p4.setPreferredSize(new Dimension(700,200));
		add("Center",login_p);
		add("West",p0);
		add("East",p2);
		add("North",p3);
		add("South",p4);
		
		//设置可见
		setVisible(true);
	}
}
class buildPanel extends JPanel{
	ImageIcon icon = new ImageIcon("src/img/login.png");//定义image用来调用图片
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(icon.getImage(), 0, 0, getWidth(),getHeight(),null);
        //在0，0位置绘制图片,宽高设为面板的宽高
	}
//	public void paint(Graphics g){
//		String str = "欢迎使用网吧管理系统";
////		Font font = new Font("楷体",Font.BOLD,60);
////		g.setFont(font);
//		g.drawImage(image.getImage(), 0,0,1000, 625,null);//图片的大小
////		g.drawString(str, 380, 250);//文字的位置
//	}
}


