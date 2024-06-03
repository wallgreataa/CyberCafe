package CyberCafe;

import java.sql.*;

public class SqlHelper{
	private static SqlHelper Instance;
	public static SqlHelper getInstance(){
		if(Instance==null){
			Instance=new SqlHelper();
		}
		return Instance;
	}
	//连接
	Connection conn;
	//添加管理者函数
	void addUser(String user,String pw) throws Exception{
		String sql="insert into administrator values(?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, user);
		pstmt.setString(2, pw);
		pstmt.executeUpdate();
		pstmt.close();
	}
	//登录判定
	boolean Login(String user,String pw) throws Exception{
		String sql="select * from administrator where account=? AND pw=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, user);
		pstmt.setString(2, pw);
		ResultSet rst=pstmt.executeQuery();
		if(rst.first()){
			pstmt.close();
			return true;
		}
		pstmt.close();
		return false;
	}
	//获得电脑列表函数
	int getComputer() throws Exception{
		String sql="select * from index_com";
		Statement stmt=conn.createStatement();
		ResultSet rst=stmt.executeQuery(sql);
		int num=0;
		while(rst.next()){
			String comid=rst.getString(1);
			String comname=rst.getString(2);
			String comcateid=rst.getString(3);
			String comcatename=rst.getString(4);
			String buydate=rst.getString(5);
			String comprice=rst.getString(6);
			ComputerInfo.setComputer(num++, comid, comname, comcateid,comcatename, buydate, comprice);
		}
		stmt.close();
		ComputerInfo.inforNum=num;
		return num;
	}
	//获得排片列表函数
	int getArrange() throws Exception{
		String sql="select * from arr";
		Statement stmt=conn.createStatement();
		ResultSet rst=stmt.executeQuery(sql);
		int num=0;
		while(rst.next()){
			String comname=rst.getString(1);
			String cname=rst.getString(2);
			String customerid=rst.getString(3);
			String stime=rst.getString(4);
			String comid=rst.getString(5);
			ArrangeInfo.setArrange(num++, comname, cname, customerid, stime, comid);
		}
		stmt.close();
		ArrangeInfo.inforNum=num;
		return num;
	}
	//搜索电脑函数
	int searchComputer(String key) throws Exception{
		if(key.equals("")){
			return getComputer();
		}
		else{
			String sql="select * from index_com where comname like '%"+key+"%'";
			Statement stmt=conn.createStatement();
			ResultSet rst=stmt.executeQuery(sql);
			System.out.println(sql);
			int num=0;
			while(rst.next()){
				String comid=rst.getString(1);
				String comname=rst.getString(2);
				String comcateid=rst.getString(3);
				String comcatename=rst.getString(4);
				String buydate=rst.getString(5);
				String comprice=rst.getString(6);
				ComputerInfo.setComputer(num++, comid, comname, comcateid,comcatename,buydate, comprice);
			}
			stmt.close();
			ComputerInfo.inforNum=num;
			return num;
		}
	}
	//搜索排片函数
	int searchArrange(String key) throws Exception{
		if(key.equals("")){
			return getArrange();
		}
		else{
			String sql="select * from arr where comname like '%"+key+"%'";
			Statement stmt=conn.createStatement();
			ResultSet rst=stmt.executeQuery(sql);
			System.out.println(sql);
			int num=0;
			while(rst.next()){
				String comname=rst.getString(1);
				String cname=rst.getString(2);
				String customerid=rst.getString(3);
				String stime=rst.getString(4);
				String comid=rst.getString(5);
				ArrangeInfo.setArrange(num++, comname, cname, customerid, stime,comid);
			}
			stmt.close();
			ArrangeInfo.inforNum=num;
			return num;
		}
	}
	//删除电脑函数
	boolean delComputer() throws SQLException{
		conn.setAutoCommit(false);
		boolean flag=false;
		String sql1="delete from arrange where comid=?";
		String sql2="delete from computer where comid=?";
		PreparedStatement Pst1 = null;
		PreparedStatement Pst2=null;
		try {
			Pst1=conn.prepareStatement(sql1);
			Pst2=conn.prepareStatement(sql2);
			for(int i=0;i<ComputerInfo.inforNum;i++){
				if(ComputerInfo.comList[i].isSel.isSelected()){
					Pst1.setString(1, ComputerInfo.comList[i].lComid.getText());
					Pst2.setString(1, ComputerInfo.comList[i].lComid.getText());
					System.out.println(ComputerInfo.comList[i].lComid.getText());
					Pst1.addBatch();
					Pst2.addBatch(); //检测每一个电脑是否被选中，批量执行删除
				}
			}
			Pst1.executeBatch();   //执行任务
			Pst2.executeBatch();   //执行任务
			conn.commit();           //最后提交事务
			flag=true;
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				conn.rollback();     //执行失败进行rollback
				System.out.println("失败");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			Pst1.close();
			Pst2.close();
		}
		conn.setAutoCommit(true);
		return flag;
	}
	//删除排机函数
	boolean delArrange() throws SQLException{
		int num=0;
		conn.setAutoCommit(false);
		boolean flag=false;
		String sql="delete from arrange where comid=? and customerid=? and stime=?";
		PreparedStatement Pst = null;
		try {
			Pst=conn.prepareStatement(sql);
			for(int i=0;i<ArrangeInfo.inforNum;i++){
				if(ArrangeInfo.arrangeList[i].isSel.isSelected()){
					Pst.setString(1, ArrangeInfo.arrangeList[i].comid);
					Pst.setString(2, ArrangeInfo.arrangeList[i].lCustomer.getText());
					Pst.setString(3, ArrangeInfo.arrangeList[i].lStime.getText());
					Pst.addBatch();
					num++;
				}
			}
			Pst.executeBatch(); //执行任务
			conn.commit();        //最后提交事务
			System.out.println(num);
			flag=true;
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				conn.rollback();  //执行失败进行rollback
				System.out.println("执行失败");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			Pst.close();
		}
		conn.setAutoCommit(true);
		return flag;
	}
	//添加或修改电脑函数
	int addComputer(String comid,String comname,String cateid,String catename,String buydate,String runtime){
		int rtn=0;
		String sql0="set @mycomid='"+comid+"'";
		String sql1="set @mycomname='"+comname+"'";
		String sql2="set @mycid='"+cateid+"'";
		String sql5="set @mycname='"+catename+"'";
		String sql3="set @mybuydate='"+buydate+"'";
		String sql4="set @myruntime='"+runtime+"'";
		String sql6="call addcom(@mycomid,@mycomname,@mycid,@mycname,@mybuydate,@myruntime,@rtn)";
		String sql7="select @rtn";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(sql0);
			stmt.executeQuery(sql1);
			stmt.executeQuery(sql2);
			stmt.executeQuery(sql3);
			stmt.executeQuery(sql4);
			stmt.executeQuery(sql5);
			stmt.executeQuery(sql6);
			ResultSet rst=stmt.executeQuery(sql7);
			if(rst.next()){
				rtn=rst.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("错误错误错误");
			e.printStackTrace();
		}
		return rtn;
	}
	//添加排机函数
	boolean addArranges(String comid,String customerid,String stime){
		String sql="insert into arrange values(?,?,?)";
		PreparedStatement pstmt=null;
		int row=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comid);
			pstmt.setString(2, customerid);
			pstmt.setString(3, stime);
			row=pstmt.executeUpdate();
			addArrange.getInstance().lTips.setText("添加上机成功");
			
		} catch (SQLException e) {
			addArrange.getInstance().lTips.setText("错误：不存在该电脑或顾客上机冲突");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(row==1){
			return true;
		}
		return false;
	}
	//获取category函数
	int getCategory() throws SQLException{
		String sql="select * from category";
		Statement stmt=conn.createStatement();
		ResultSet rst=stmt.executeQuery(sql);
		int num=0;
		while(rst.next()){
			String cid=rst.getString(1);
			String cname=rst.getString(2);
			CategoryInfo.setCat(num++, cid, cname);
		}
		stmt.close();
		CategoryInfo.inforNum=num;
		return num;
	}
	//获取customer函数
	int getCustomer() throws SQLException{
			String sql="select Customer.customerid,customername,clike from customer left join vipc on customer.customerid=vipc.customerid order by customer.customerid";
			Statement stmt=conn.createStatement();
			ResultSet rst=stmt.executeQuery(sql);
			int num=0;
			while(rst.next()){
				String customerid=rst.getString(1);
				String customername=rst.getString(2);
				String clike=rst.getString(3);
				CustomerInfo.setCustomer(num++, customerid, customername,clike);
			}
			stmt.close();
			CustomerInfo.inforNum=num;
			return num;
		}
	
	SqlHelper(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载驱动成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("加载驱动失败");
		}
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cybercafe?useUnicode=true&characterEncoding=UTF-8","root","123456");
			System.out.println("连接数据库成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("连接数据库失败");
		}
	}
}