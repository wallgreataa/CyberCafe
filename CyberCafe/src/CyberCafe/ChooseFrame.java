package CyberCafe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChooseFrame extends JFrame{
	private static ChooseFrame Instance;
	public static ChooseFrame getInstance(){
		if(Instance==null){
			Instance=new ChooseFrame();
		}
		return Instance;
	}
	//组件初始化
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,40);
	JPanel pBtn=new JPanel();
	JButton acBtn=new JButton("管理电脑");
	JButton arrBtn=new JButton("管理排机");
	JButton catBtn=new JButton("电脑类型");
	JButton custBtn=new JButton("查看顾客");
	JButton closeBtn=new JButton("关闭系统");
	
	//按钮触发器
	private ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			if(command.equals("管理电脑")){
				Computer.getInstance();
			}
			else if(command.equals("管理排机")){
				Arrange.getInstance();
			}
			else if(command.equals("电脑类型")){
				Category.getInstance();
			}
			else if(command.equals("查看顾客")){
				Customer.getInstance();
			}else if(command.equals("关闭系统")){
				System.exit(0);
			}
		}
	};
	
	ChooseFrame(){
		setTitle("选择操作");
		setLocation(600,300);
		setSize(1000,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//按钮大小设置
		acBtn.addActionListener(btnAL);
		arrBtn.addActionListener(btnAL);
		catBtn.addActionListener(btnAL);
		custBtn.addActionListener(btnAL);
		closeBtn.addActionListener(btnAL);
		acBtn.setFont(f);
		arrBtn.setFont(f);
		catBtn.setFont(f);
		custBtn.setFont(f);
		closeBtn.setFont(f);
		acBtn.setPreferredSize(new Dimension(200,100));
		arrBtn.setPreferredSize(new Dimension(200,100));
		catBtn.setPreferredSize(new Dimension(200,100));
		custBtn.setPreferredSize(new Dimension(200,100));
		closeBtn.setPreferredSize(new Dimension(200,100));
		//界面初始化
		pBtn.setLayout(new GridLayout(5,1,0,50));
		pBtn.add(acBtn);
		pBtn.add(arrBtn);
		pBtn.add(catBtn);
		pBtn.add(custBtn);
		pBtn.add(closeBtn);
		//界面空白填充
		pBtn.setPreferredSize(new Dimension(700, 800));
		JPanel p0=new JPanel();
		p0.setPreferredSize(new Dimension(150,1000));
		JPanel p2=new JPanel();
		p2.setPreferredSize(new Dimension(150,1000));
		setLayout(new BorderLayout());
		JPanel p3=new JPanel();
		p3.setPreferredSize(new Dimension(700,100));
		JPanel p4=new JPanel();
		p4.setPreferredSize(new Dimension(700,100));
		add("Center",pBtn);
		add("West",p0);
		add("East",p2);
		add("North",p3);
		add("South",p4);
		
		//设置可见
		setVisible(true);
	}
}
