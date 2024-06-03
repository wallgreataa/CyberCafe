package CyberCafe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ArrangeInfo extends JPanel{
	public static int inforNum;
	public static ArrangeInfo[] arrangeList=new ArrangeInfo[15];//先假定最多15条
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	public JCheckBox isSel=new JCheckBox();
	String comid=new String();
	JLabel lComid;
	JLabel lComname;
	JLabel lCname;
	JLabel lCustomer;
	JLabel lStime;
	JPanel pCon=new JPanel();
	//排片列表初始化
	public static void setArrange(int index,String comname,String cname,String customerid,String stime,String comid){
		arrangeList[index]=new ArrangeInfo(comname, cname, customerid, stime);
		arrangeList[index].comid=comid;
		arrangeList[index].lComid.setText(comid);
	}
	ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			if(command.equals("删除")){
				
			}
		}
	};
	ArrangeInfo(String comname,String cname,String customerid,String stime){
		lComid=new JLabel(comid);
		lComname=new JLabel(comname);
		lCname=new JLabel(cname);
		lCustomer=new JLabel(customerid);
		lStime=new JLabel(stime);
		lComid.setFont(f);
		lComname.setFont(f);
		lCname.setFont(f);
		lCustomer.setFont(f);
		lStime.setFont(f);
		lComid.setHorizontalAlignment(JLabel.CENTER);
		lComname.setHorizontalAlignment(JLabel.CENTER);
		lCname.setHorizontalAlignment(JLabel.CENTER);
		lCustomer.setHorizontalAlignment(JLabel.CENTER);
		lStime.setHorizontalAlignment(JLabel.CENTER);
		//界面初始化
		setPreferredSize(new Dimension(1200,50));
		setLayout(new GridLayout(1,6,10,0));
		add(isSel);
		add(lComid);
		add(lComname);
		add(lCname);
		add(lCustomer);
		add(lStime);
	}
}
