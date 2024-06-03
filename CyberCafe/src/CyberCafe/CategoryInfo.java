package CyberCafe;

import java.awt.*;
import javax.swing.*;

public class CategoryInfo extends JPanel{
	public static int inforNum;
	public static CategoryInfo[] categoryList=new CategoryInfo[15];//先假定最多15条
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	JLabel lCid;
	JLabel lCname;
	public static void setCat(int index,String cid,String cname){
		categoryList[index]=new CategoryInfo(cid, cname);
	}
	CategoryInfo(String cid,String cname){
		lCid=new JLabel(cid);
		lCname=new JLabel(cname);
		lCid.setFont(f);
		lCname.setFont(f);
		lCid.setHorizontalAlignment(JLabel.CENTER);
		lCname.setHorizontalAlignment(JLabel.CENTER);
		//界面初始化
		setPreferredSize(new Dimension(900,50));
		setLayout(new GridLayout(1,7,20,0));
		add(lCid);
		add(lCname);
	}
}
