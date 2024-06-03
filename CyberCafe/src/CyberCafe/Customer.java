package CyberCafe;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class Customer extends JFrame{
	private static Customer Instance;
	public static Customer getInstance(){
		if(Instance==null){
			Instance=new Customer();
		}
		Instance.setVisible(true);
		return Instance;
	}
	//字体
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	//显示顾客信息模块
	JPanel pInfor=new JPanel();
	JPanel pInforInit=new JPanel();
	JLabel lCustomerid=new JLabel("顾客编号");
	JLabel lCustomername=new JLabel("顾客姓名");
	JLabel lClike=new JLabel("顾客喜好(vip)");
	
	//获取列表函数
	void getCustomer(int num) throws Exception{
		pInfor.removeAll();
		pInfor.setLayout(new BoxLayout(pInfor, BoxLayout.Y_AXIS));
		pInfor.add(pInforInit);
		System.out.println(num);
		for(int i=0;i<num;i++){
			pInfor.add(CustomerInfo.customerList[i]);
		}
		JPanel pNull=new JPanel();
		pNull.setPreferredSize(new Dimension(900,800-num*100));
		pInfor.add(pNull);
		pInfor.updateUI();
	}
	public static void MyUpdate(){
		try {
			Instance.getCustomer(SqlHelper.getInstance().getCustomer());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Customer(){
		setTitle("顾客信息");
		setLocation(200,200);
		setSize(700,700);
		//展示信息面板初始化
		pInfor.setPreferredSize(new Dimension(1200,870));
		pInfor.setBackground(Color.white);
		pInforInit.setPreferredSize(new Dimension(1200,50));
		pInforInit.setLayout(new GridLayout(1,7,65,0));
		lCustomerid.setFont(f);
		lCustomername.setFont(f);
		lClike.setFont(f);
		lCustomerid.setHorizontalAlignment(JLabel.CENTER);
		lCustomername.setHorizontalAlignment(JLabel.CENTER);
		lClike.setHorizontalAlignment(JLabel.CENTER);
		pInforInit.add(lCustomerid);
		pInforInit.add(lCustomername);
		pInforInit.add(lClike);
		
		try {
			getCustomer(SqlHelper.getInstance().getCustomer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//界面初始化
		add("Center",pInfor);
		setVisible(true);
	}
}
