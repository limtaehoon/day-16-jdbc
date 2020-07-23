package Com.Oracle.Namecard.Test;//2

import java.sql.*;
import java.util.ArrayList;
//DAO:data exxess objext >�����Ϳ� �����ϴ� ��ü
//DAOŬ������ �����Ͽ� ��ü ������
//NamecardDao dao= new namecarddao();
//�� dao.insert: �������Է� ,dao.update: ������ ����
public class NamecardDao {
	//127.0.0.1==localhost(����ǻ�� �ּ�) ���� �ٸ���� host������ �ٸ���� ����ҷ� �����
    static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";  
    //127.0.0.1
    
    static final String USER = "scott";
    static final String PASSWORD = "1234";
    //1.����̺�ε�
    public NamecardDao() {
        try { // ����Ŭ DB ����̹� �ε�
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //2.db����
    private Connection getConnection() throws SQLException {
    	// DB ������ �޼ҵ�� �����
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    		//���� ResultSet �� �ΰ��� �ƴϸ� ����, ���� Statement ����,
            //���������� Ŀ�ؼ� ����
    private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // �Է� �޼ҵ�(2)
    public void insert(Namecard card) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String sql = "INSERT INTO namecard VALUES (CARD_NO.NEXTVAL, ?, ?, ?, ?)";// ù��° ? : pstmt.setString(1, card.getName());
        																		 // �ι�° ? : pstmt.setString(2, card.getMobile());
        try {
        	//db ���� �޼ҵ�
            con = getConnection();
            pstmt = con.prepareStatement(sql);//sql �Է� �޼ҵ� 
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getMobile());
            pstmt.setString(3, card.getEmail());
            pstmt.setString(4, card.getCompany());
            pstmt.executeUpdate();
            con.commit();//������ ����
            System.out.println("�Է¿Ϸ�");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        } finally {
            close(null, pstmt, con);
        }
    }
    
    //��ȣ no �� ����    
    public void delete(int no) {//no �ڸ��� �� �ѹ� ������ �˻���
       //�ۼ��ϱ�
    	 Connection con = null;
         PreparedStatement pstmt = null;
         
         String sql = "DELETE FROM namecard WHERE NO = ?";
         																		 // �ι�° ? : pstmt.setString(2, card.getMobile());
         try {
         	//db ���� �޼ҵ�
             con = getConnection();
             pstmt = con.prepareStatement(sql);//sql �غ�
             pstmt.setInt(1, no);
             pstmt.executeUpdate();//����
             con.commit();//������ ����
             System.out.println("�����Ϸ�");
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println(sql);
         } finally {
             close(null, pstmt, con);
         }
    }
    
    //��ȣ no �� �˻�:
    public Namecard selectOne(int no) { 
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Namecard card = null;//Namecard Ŭ���� ��ü
        String sql = "SELECT * FROM namecard WHERE NO = ?";
        																		 // �ι�° ? : pstmt.setString(2, card.getMobile());
        try {
        	//db ���� �޼ҵ�
            con = getConnection();
            pstmt = con.prepareStatement(sql);//sql �غ�
            pstmt.setInt(1, no);
            //pstmt.executeupdate();//sql ����(����� ������
            rs = pstmt.executeQuery();//sql ����(����� ������
           
            if(rs.next()) {
            	card = new Namecard();
            	card.setNo(rs.getInt("no"));
            	card.setName(rs.getString("NAME")); 
            	card.setMobile(rs.getString("MOBLIE")); 
            	card.setEmail(rs.getString("EMAIL")); 
            	card.setCompany(rs.getString("COMPANY")); 
            	return card;
            }
            
           
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        } finally {
            close(rs, pstmt, con);
        }

        return null;//�����߻���, return��
    }
    
    //�̸����� ã��
    public ArrayList<Namecard> selectByKeyword(String keyword) {       
        ArrayList<Namecard> matched = new ArrayList<Namecard>();
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Namecard card = null;//Namecard Ŭ���� ��ü
       
        String sql = "SELECT * FROM namecard WHERE NAME=?";
        																		 // �ι�° ? : pstmt.setString(2, card.getMobile());
        try {
        	//db ���� �޼ҵ�
            con = getConnection();
            pstmt = con.prepareStatement(sql);//sql �غ�
            pstmt.setString(1, keyword);// �̸����� ���� �ؿ� keyword�� name���� �˻��� 
            //pstmt.executeupdate();//sql ����(����� ������
            rs = pstmt.executeQuery();//sql ����(����� ������
           
            while(rs.next()) {
            	card = new Namecard();
            	card.setNo(rs.getInt("no"));
            	card.setName(rs.getString("NAME")); 
            	card.setMobile(rs.getString("MOBLIE")); 
            	card.setEmail(rs.getString("EMAIL")); 
            	card.setCompany(rs.getString("COMPANY")); 
            	//return card;
            	matched.add(card);
            	
             }return matched;
        
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        } finally {
            close(rs, pstmt, con);
        }

        return null;//�����߻���, return��
    }
 

    
    //Namecard�� ��� ��/���� �������� ��ȣ������ 
    public ArrayList<Namecard> selectAll() {
        ArrayList<Namecard> all = new ArrayList<Namecard>();
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Namecard card = null;//Namecard Ŭ���� ��ü
       
        String sql = "SELECT * FROM namecard ORDER BY 1";
        																		 // �ι�° ? : pstmt.setString(2, card.getMobile());
        try {
        	//db ���� �޼ҵ�
            con = getConnection();
            pstmt = con.prepareStatement(sql);//sql �غ�
            //pstmt.executeupdate();//sql ����(����� ������
            rs = pstmt.executeQuery();//sql ����(����� ������
           
            while(rs.next()) {
            	card = new Namecard();
            	card.setNo(rs.getInt("no"));
            	card.setName(rs.getString("NAME")); 
            	card.setMobile(rs.getString("MOBLIE")); 
            	card.setEmail(rs.getString("EMAIL")); 
            	card.setCompany(rs.getString("COMPANY")); 
            	//return card;
            	all.add(card);
            	
             }
          return all;
          
           } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        } finally {
            close(rs, pstmt, con);
        }

        return null;//�����߻���, return��
    }
            
    
    //�����ϱ�
    public void update(Namecard card) {
    	//�ۼ��ϱ�
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String sql = "UPDATE namecard SET NAME =?,MOBLIE =?,EMAIL=?,"
        		+ "COMPANY=? WHERE NO=?";// ù��° ? : pstmt.setString(1, card.getName());
        																		 // �ι�° ? : pstmt.setString(2, card.getMobile());
        try {
        	//db ���� �޼ҵ�
            con = getConnection();
            pstmt = con.prepareStatement(sql);//sql �Է� �޼ҵ� 
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getMobile());
            pstmt.setString(3, card.getEmail());
            pstmt.setString(4, card.getCompany());
            pstmt.setInt   (5, card.getNo());
            pstmt.executeUpdate();
            con.commit();//������ ����
            System.out.println("�����Ϸ�");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        } finally {
            close(null, pstmt, con);
        }
    }
    }


