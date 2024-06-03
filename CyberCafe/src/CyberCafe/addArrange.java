package CyberCafe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class addArrange extends JFrame{
	private static addArrange Instance;
	public static addArrange getInstance(){
		if(Instance==null){
			Instance=new addArrange();
		}
		Instance.setVisible(true);
		return Instance;
	}
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,30);
	JLabel lComid=new JLabel("电脑编号");
	JLabel lCustomerid=new JLabel("顾客编号");
	JLabel lStime=new JLabel("开机时间");
	JTextField comidTF=new JTextField(20);
	JTextField custmoeridTF=new JTextField(20);
	JTextField stimeTF=new JTextField(20);
	JButton yesBtn=new JButton("确定");
	JPanel pMain=new JPanel();
	JPanel pComid=new JPanel();
	JPanel pCustomerid=new JPanel();
	JPanel pStime=new JPanel();
	JPanel pBtn=new JPanel();
	JPanel pTips=new JPanel();
	JLabel lTips=new JLabel();
	//按钮监听器
	ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String movid=comidTF.getText();
			String customerid=custmoeridTF.getText();
			String stime=stimeTF.getText();
			System.out.println(stime);
			SqlHelper.getInstance().addArranges(movid, customerid, stime);
			try {
				Arrange.getInstance().getArrange(SqlHelper.getInstance().getArrange());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	
	addArrange(){
		setTitle("添加排机");
		setLocation(1400,300);
		setSize(1000,1000);
		//设置字体
		lComid.setFont(f);
		lCustomerid.setFont(f);
		lStime.setFont(f);
		comidTF.setFont(f);
		custmoeridTF.setFont(f);
		stimeTF.setFont(f);
		yesBtn.setFont(f);
		//界面初始化
		pComid.setLayout(new FlowLayout());
		pCustomerid.setLayout(new FlowLayout());
		pStime.setLayout(new FlowLayout());
		pBtn.setLayout(new FlowLayout());
		pComid.add(lComid);
		pCustomerid.add(lCustomerid);
		pStime.add(lStime);
		pComid.add(comidTF);
		pCustomerid.add(custmoeridTF);
		pStime.add(stimeTF);
		pBtn.add(yesBtn);
		yesBtn.setPreferredSize(new Dimension(120,80));
		yesBtn.addActionListener(btnAL);
		
		pTips.setPreferredSize(new Dimension(1000,50));
		lTips.setFont(f);
		pTips.add(lTips);
		//主界面初始化
		pMain.setLayout(new GridLayout(5,1,0,30));
		pMain.setPreferredSize(new Dimension(1000,900));
		JPanel p3=new JPanel();
		p3.setPreferredSize(new Dimension(1000,50));
		pMain.add(pComid);
		pMain.add(pCustomerid);
		pMain.add(pStime);
		pMain.add(pBtn);
		add("Center",pMain);
		add("North",p3);
		add("South",pTips);
		//设置可见
		setVisible(true);
	}
}
