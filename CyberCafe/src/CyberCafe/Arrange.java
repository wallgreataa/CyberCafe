package CyberCafe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import javax.swing.*;

public class Arrange extends JFrame{
	private static Arrange Instance;
	public static Arrange getInstance(){
		if(Instance==null){
			Instance=new Arrange();
		}
		Instance.setVisible(true);
		return Instance;
	}
	//字体
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	//搜索模块
	JPanel pSearch=new JPanel();
	JLabel lSearch=new JLabel("顾客名称");
	JTextField searchTF=new JTextField(20);
	JButton searchBtn=new JButton("搜索");
	JButton addBtn=new JButton("添加");
	JButton delBtn=new JButton("删除");
	//显示信息模块
	JPanel pInfor=new JPanel();
	JPanel pInforInit=new JPanel();
	JCheckBox allSel=new JCheckBox();
	JLabel lComid=new JLabel("电脑编号");
	JLabel lComname=new JLabel("电脑名称");
	JLabel lCname=new JLabel("电脑类型");
	JLabel lCustomer=new JLabel("顾客ID");
	JLabel lStime=new JLabel("开机时间");
	//获取顾客与电脑列表函数
	void getArrange(int num) throws Exception{
		pInfor.removeAll();
		pInfor.setLayout(new BoxLayout(pInfor, BoxLayout.Y_AXIS));
		pInfor.add(pInforInit);
		System.out.println(num);
		for(int i=0;i<num;i++){
			pInfor.add(ArrangeInfo.arrangeList[i]);
		}
		JPanel pNull=new JPanel();
		pNull.setPreferredSize(new Dimension(900,800-num*100));
		pInfor.add(pNull);
		pInfor.updateUI();
	}
	//搜索按钮监听函数
	ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			if(command.equals("搜索")){
				String key=searchTF.getText();
				try {
					getArrange(SqlHelper.getInstance().searchArrange(key));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(command.equals("添加")){
				addArrange.getInstance();
			}
			else if(command.equals("删除")){
				try {
					SqlHelper.getInstance().delArrange();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					getArrange(SqlHelper.getInstance().getArrange());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
	//全选按钮监听器
	ItemListener btnIL=new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox command=(JCheckBox)e.getItem();
			if(command.isSelected()){
				for(int i=0;i<ArrangeInfo.inforNum;i++){
					ArrangeInfo.arrangeList[i].isSel.setSelected(true);
				}
			}
			else{
				for(int i=0;i<ArrangeInfo.inforNum;i++){
					ArrangeInfo.arrangeList[i].isSel.setSelected(false);
				}
			}
		}
	};
	Arrange(){
		setTitle("管理开机");
		setLocation(400,300);
		setSize(1800,1000);
		//搜索模块初始化
		pInfor.setPreferredSize(new Dimension(1000,130));
		lSearch.setFont(f);
		searchTF.setFont(f);
		searchBtn.setFont(f);
		addBtn.setFont(f);
		delBtn.setFont(f);
		pSearch.setLayout(new FlowLayout());
		pSearch.add(delBtn);
		pSearch.add(lSearch);
		pSearch.add(searchTF);
		pSearch.add(searchBtn);
		pSearch.add(addBtn);
		searchBtn.addActionListener(btnAL);
		addBtn.addActionListener(btnAL);
		delBtn.addActionListener(btnAL);
		allSel.addItemListener(btnIL);
		//展示信息面板初始化
		pInfor.setPreferredSize(new Dimension(1200,870));
		pInfor.setBackground(Color.white);
		pInforInit.setPreferredSize(new Dimension(1200,50));
		pInforInit.setLayout(new GridLayout(1,6,10,0));
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
		pInforInit.add(allSel);
		pInforInit.add(lComid);
		pInforInit.add(lComname);
		pInforInit.add(lCname);
		pInforInit.add(lCustomer);
		pInforInit.add(lStime);
		
		try {
			getArrange(SqlHelper.getInstance().getArrange());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//界面初始化
		add("North",pSearch);
		add("South",pInfor);
		
		//设置可见
		setVisible(true);
	}
}