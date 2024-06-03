package CyberCafe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.*;


public class Computer extends JFrame{
	private static Computer Instance;
	public static Computer getInstance(){
		if(Instance==null){
			Instance=new Computer();
		}
		Instance.setVisible(true);
		return Instance;
	}
	//字体
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	//搜索模块
	JPanel pSearch=new JPanel();
	JLabel lSearch=new JLabel("电脑名称");
	JTextField searchTF=new JTextField(20);
	JButton searchBtn=new JButton("搜索");
	JButton addBtn=new JButton("添加或修改");
	JButton delBtn=new JButton("删除");
	JCheckBox allSel=new JCheckBox();
	Container container = this.getContentPane();
	//显示电脑信息模块
	JPanel pInfor=new JPanel();
	JPanel pInforInit=new JPanel();
	JLabel lComid=new JLabel("电脑编号");
	JLabel lComname=new JLabel("电脑名称");
	JLabel lComcid=new JLabel("类型编号");
	JLabel lComcname=new JLabel("类型名称");
	JLabel lBuydate=new JLabel("购入日期");
	JLabel lRuntime=new JLabel("运行时长");
	
	//获取电影列表函数
	void getComputer(int num) throws Exception{
		pInfor.removeAll();
		pInfor.setLayout(new BoxLayout(pInfor, BoxLayout.Y_AXIS));
		pInfor.add(pInforInit);
		System.out.println(num);
		for(int i=0;i<num;i++){
			pInfor.add(ComputerInfo.comList[i]);
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
					getComputer(SqlHelper.getInstance().searchComputer(key));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(command.equals("添加或修改")){
				addComputer.getInstance();
			}
			else if(command.equals("删除")){
				try {
					SqlHelper.getInstance().delComputer();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					getComputer(SqlHelper.getInstance().getComputer());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Arrange.getInstance().getArrange(SqlHelper.getInstance().getArrange());
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
				for(int i=0;i<ComputerInfo.inforNum;i++){
					ComputerInfo.comList[i].isSel.setSelected(true);
				}
			}
			else{
				for(int i=0;i<ComputerInfo.inforNum;i++){
					ComputerInfo.comList[i].isSel.setSelected(false);
				}
			}
		}
	};
	
	Computer(){
		setTitle("管理电脑");
		//setLocation(0,0);
		setSize(1200,800);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		//搜索模块初始化
		pInfor.setPreferredSize(new Dimension(1200,150));
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
		pInfor.setPreferredSize(new Dimension(1200,650));
		pInfor.setBackground(Color.white);
		pInforInit.setPreferredSize(new Dimension(1200,50));
		pInforInit.setLayout(new GridLayout(1,7,10,0));
		lComcid.setFont(f);
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
		pInforInit.add(allSel);
		pInforInit.add(lComid);
		pInforInit.add(lComname);
		pInforInit.add(lComcid);
		pInforInit.add(lComcname);
		pInforInit.add(lBuydate);
		pInforInit.add(lRuntime);
		
		
		try {
			getComputer(SqlHelper.getInstance().getComputer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//界面初始化
		add("North",pSearch);
		add("Center",pInfor);		
		//设置可见
		setVisible(true);
	}
}