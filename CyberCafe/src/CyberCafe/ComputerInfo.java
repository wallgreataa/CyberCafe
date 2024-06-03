package CyberCafe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class ComputerInfo extends JPanel{
	public static int inforNum;
	public static ComputerInfo[] comList=new ComputerInfo[15];//先假定最多15条
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	JCheckBox isSel=new JCheckBox();
	JLabel lComid;
	JLabel lComname;
	JLabel lComcid;
	JLabel lComcname;
	JLabel lBuydate;
	JLabel lRuntime;
	//电影列表初始化
	public static void setComputer(int index,String comid,String comname,String comcid,String comcname,String buydate,String runtime){
		comList[index]=new ComputerInfo(comid, comname, comcid,comcname, buydate, runtime);
	}
	
	ComputerInfo(String comid,String comname,String comcid,String comcname, String buydate,String runtime){
		lComid=new JLabel(comid);
		lComname=new JLabel(comname);
		lComcid=new JLabel(comcid);
		lComcname=new JLabel(comcname);
		lBuydate=new JLabel(buydate);
		lRuntime=new JLabel(runtime+"分钟");
		lComid.setFont(f);
		lComname.setFont(f);
		lComcid.setFont(f);
		lComcname.setFont(f);
		lBuydate.setFont(f);
		lRuntime.setFont(f);
		lComid.setHorizontalAlignment(JLabel.CENTER);
		lComname.setHorizontalAlignment(JLabel.CENTER);
		lComcid.setHorizontalAlignment(JLabel.CENTER);
		lComcname.setHorizontalAlignment(JLabel.CENTER);
		lBuydate.setHorizontalAlignment(JLabel.CENTER);
		lRuntime.setHorizontalAlignment(JLabel.CENTER);
		//界面初始化
		setPreferredSize(new Dimension(900,50));
		setLayout(new GridLayout(1,7,10,0));
		add(isSel);
		add(lComid);
		add(lComname);
		add(lComcid);
		add(lComcname);
		add(lBuydate);
		add(lRuntime);
	}
}