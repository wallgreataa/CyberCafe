package CyberCafe;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

public class Category extends JFrame{
	private static Category Instance;
	public static Category getInstance(){
		if(Instance==null){
			Instance=new Category();
		}
		Instance.setVisible(true);
		return Instance;
	}
	//字体
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	//显示电脑类型信息模块
	JPanel pInfor=new JPanel();
	JPanel pInforInit=new JPanel();
	JLabel lCid=new JLabel("类型编号");
	JLabel lCname=new JLabel("类型名称");
	
	//获取列表函数
	void getCategory(int num) throws Exception{
		pInfor.removeAll();
		pInfor.setLayout(new BoxLayout(pInfor, BoxLayout.Y_AXIS));
		pInfor.add(pInforInit);
		System.out.println(num);
		for(int i=0;i<num;i++){
			pInfor.add(CategoryInfo.categoryList[i]);
		}
		JPanel pNull=new JPanel();
		pNull.setPreferredSize(new Dimension(900,800-num*100));
		pInfor.add(pNull);
		pInfor.updateUI();
	}
	public static void MyUpdate(){
		try {
			Instance.getCategory(SqlHelper.getInstance().getCategory());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Category(){
		setTitle("电脑类型");
		setLocation(200,200);
		setSize(700,700);
		//展示信息面板初始化
		pInfor.setPreferredSize(new Dimension(1200,870));
		pInfor.setBackground(Color.white);
		pInforInit.setPreferredSize(new Dimension(1200,50));
		pInforInit.setLayout(new GridLayout(1,7,65,0));
		lCid.setFont(f);
		lCname.setFont(f);
		lCid.setHorizontalAlignment(JLabel.CENTER);
		lCname.setHorizontalAlignment(JLabel.CENTER);
		pInforInit.add(lCid);
		pInforInit.add(lCname);
		
		try {
			getCategory(SqlHelper.getInstance().getCategory());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//界面初始化
		add("Center",pInfor);
		setVisible(true);
	}
}
