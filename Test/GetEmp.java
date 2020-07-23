package Com.Oracle.Test;

import java.sql.*;

public class GetEmp {

	public static void main(String[] args) {
		//(oracle) jdbc:oracle:thin:@localhost:1521:xe
		//(mySQL) jdbc:mysql://localhost:3306/db�̸�
		//�̸��Է��� ����Ŭ�ּ� ���� ��й�ȣ�� ������ ����
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";//*����Ŭ�� �ƴ϶�� �ٲ�ߵ�
        String DB_USER = "scott";//*����Ŭ�� �ƴ϶�� �ٲ�ߵ�
        String DB_PASSWORD = "1234";//*����Ŭ�� �ƴ϶�� �ٲ�ߵ�
        //Connection �� ����Ŭ ������ ���� ��ü
        Connection conn = null;
        //Statement�� sql���� �����ϱ����� ��ü
        Statement stmt = null;
        //ResultSet �� sql�� ������ ������� �޾ƿ��°���
        ResultSet rs = null;

        String query = "SELECT * FROM emp";
        String query2 = "SELECT * FROM emp where empno= 7369";
        try {
        	//1. ����Ŭ ����̹� �ε�   ( ojdbc6_g.jar )    		
            Class.forName("oracle.jdbc.driver.OracleDriver");//*����Ŭ�� �ƴ϶�� �ٲ�ߵ�
        } catch (ClassNotFoundException e ) { //Ŭ������ ��ã������ (����̹� ������ )
            e.printStackTrace();
        }

        try { //DB ���� �κ��� try-catch ������ ���� �߻��� ó��
        	//2. DB ����
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //3. SQL �� �غ�
            stmt = conn.createStatement();//stmt ��ü ����
            //4. SQL ���� �����ϰ� ��� �ޱ�
            rs = stmt.executeQuery(query2);

            while (rs.next()) { // ���̺��� �� �྿ ����
                String empno = rs.getString("EMPNO");  // ù��° �� (1)  //���� ��� ���� �޾ƿ� ("EMPNO")
                String ename = rs.getString(2);
                String job = rs.getString(3);
                String mgr = rs.getString(4);
                String hiredate = rs.getString(5);
                String sal = rs.getString(6);
                String comm = rs.getString(7);
                String depno = rs.getString(8);

                System.out.println(empno + " : " + ename + " : " + job + " : " + mgr + " : " + hiredate + " : " + sal + " : " + comm + " : " + depno); 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close(); 		//ResultSet (���� ���) �ݱ�
                stmt.close();		//State�� �ݱ�
                conn.close();		//DB���� ����
            } catch (SQLException e) {}
        }
    }

}
