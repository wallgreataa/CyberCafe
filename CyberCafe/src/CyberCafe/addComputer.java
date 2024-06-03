package CyberCafe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class addComputer extends JFrame{
	private static addComputer Instance;
	public static addComputer getInstance(){
		if(Instance==null){
			Instance=new addComputer();
		}
		Instance.setVisible(true);
		return Instance;
	}
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,30);
	JLabel lComid=new JLabel("电脑编号");
	JLabel lComname=new JLabel("电脑名称");
	JLabel lComcid=new JLabel("类型编号");
	JLabel lComc=new JLabel("类型名称");
	JLabel lBuydate=new JLabel("购入日期");
	JLabel lRuntime=new JLabel("运行时长");
	JTextField ComidTF=new JTextField(20);
	JTextField ComnameTF=new JTextField(20);
	JTextField ComcTF=new JTextField(20);
	JTextField ComcidTF=new JTextField(20);
	JTextField BuydateTF=new JTextField(20);
	JTextField RuntimeTF=new JTextField(20);
	JButton yesBtn=new JButton("确定");
	JPanel pMain=new JPanel();
	JPanel pComid=new JPanel();
	JPanel pComname=new JPanel();
	JPanel pComc=new JPanel();
	JPanel pComcid=new JPanel();
	JPanel pBuydate=new JPanel();
	JPanel pRuntime=new JPanel();
	JPanel pBtn=new JPanel();
	JPanel pTips=new JPanel();
	JLabel lTips=new JLabel("注意：修改时已存在的电脑编号与电脑名称、类型编号与类型名称需一致");
	//按钮监听器
	ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String comid=ComidTF.getText();
			String comname=ComnameTF.getText();
			String comcid=ComcidTF.getText();
			String comcname=ComcTF.getText();
			String buydate=BuydateTF.getText();
			String runtime=RuntimeTF.getText();
			try {
				int rtn=SqlHelper.getInstance().addComputer(comid, comname,comcid, comcname, buydate, runtime);
				System.out.println("rtn"+rtn);
				if(rtn==0){
					lTips.setText("错误：类型编号与电脑类型冲突");
				}
				else if(rtn==1){
					lTips.setText("电脑信息更新成功");
				}
				else if(rtn==2){
					lTips.setText("错误：电脑编号与电脑名称不符");
				}
				else if(rtn==3){
					lTips.setText("添加新电脑成功");
				}
				Computer.getInstance().getComputer(SqlHelper.getInstance().getComputer());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			Category.getInstance().MyUpdate();
		}
	};
	
	addComputer(){
		setTitle("添加电脑");
		setLocation(1400,300);
		setSize(1000,1000);
		//设置字体
		lComid.setFont(f);
		lComname.setFont(f);
		lComc.setFont(f);
		lComcid.setFont(f);
		lBuydate.setFont(f);
		lRuntime.setFont(f);
		ComidTF.setFont(f);
		ComnameTF.setFont(f);
		ComcTF.setFont(f);
		ComcidTF.setFont(f);
		BuydateTF.setFont(f);
		RuntimeTF.setFont(f);
		yesBtn.setFont(f);
		//界面初始化
		pComid.setLayout(new FlowLayout());
		pComname.setLayout(new FlowLayout());
		pComcid.setLayout(new FlowLayout());
		pComc.setLayout(new FlowLayout());
		pBuydate.setLayout(new FlowLayout());
		pRuntime.setLayout(new FlowLayout());
		pBtn.setLayout(new FlowLayout());
		pComid.add(lComid);
		pComname.add(lComname);
		pComcid.add(lComcid);
		pComc.add(lComc);
		pBuydate.add(lBuydate);
		pRuntime.add(lRuntime);
		pComid.add(ComidTF);
		pComname.add(ComnameTF);
		pComcid.add(ComcidTF);
		pComc.add(ComcTF);
		pBuydate.add(BuydateTF);
		pRuntime.add(RuntimeTF);
		pBtn.add(yesBtn);
		yesBtn.setPreferredSize(new Dimension(120,80));
		yesBtn.addActionListener(btnAL);
		pTips.setPreferredSize(new Dimension(1000,60));
		lTips.setFont(f);
		pTips.add(lTips);
		//主界面初始化
		pMain.setLayout(new GridLayout(7,1,0,30));
		pMain.setPreferredSize(new Dimension(1000,850));
		JPanel p3=new JPanel();
		p3.setPreferredSize(new Dimension(1000,50));
		pMain.add(pComid);
		pMain.add(pComname);
		pMain.add(pComcid);
		pMain.add(pComc);
		pMain.add(pBuydate);
		pMain.add(pRuntime);
		pMain.add(pBtn);
		add("Center",pMain);
		add("North",p3);
		add("South",pTips);
		//设置可见
		setVisible(true);
	}
}