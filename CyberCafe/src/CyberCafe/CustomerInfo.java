package CyberCafe;

import java.awt.*;
import javax.swing.*;

public class CustomerInfo extends JPanel{
	public static int inforNum;
	public static CustomerInfo[] customerList=new CustomerInfo[15];//先假定最多15条
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	JLabel lCustomerid;
	JLabel lCustomername;
	JLabel lClike;
	public static void setCustomer(int index,String customerid,String customername,String clike){
		if(clike==null){
			clike="无";
		}
		customerList[index]=new CustomerInfo(customerid,customername,clike);
	}
	CustomerInfo(String customerid,String customername,String clike){
		//UI
		lCustomerid=new JLabel(customerid);
		lCustomername=new JLabel(customername);
		lClike=new JLabel(clike);
		lCustomerid.setFont(f);
		lCustomername.setFont(f);
		lClike.setFont(f);
		lCustomerid.setHorizontalAlignment(JLabel.CENTER);
		lCustomername.setHorizontalAlignment(JLabel.CENTER);
		lClike.setHorizontalAlignment(JLabel.CENTER);
		//界面初始化
		setPreferredSize(new Dimension(900,50));
		setLayout(new GridLayout(1,7,20,0));
		add(lCustomerid);
		add(lCustomername);
		add(lClike);
	}
}
