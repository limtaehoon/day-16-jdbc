package Com.Oracle.Namecard.Test;//2

import java.sql.*;
import java.util.ArrayList;
//DAO:data exxess objext >데이터에 접급하는 객체
//DAO클래스를 정의하여 객체 생성시
//NamecardDao dao= new namecarddao();
//예 dao.insert: 데이터입력 ,dao.update: 데이터 수정
public class NamecardDao {
	//127.0.0.1==localhost(내컴퓨터 주소) 만약 다른사람 host적으면 다른사람 저장소로 저장됨
    static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";  
    //127.0.0.1
    
    static final String USER = "scott";
    static final String PASSWORD = "1234";
    //1.드라이브로드
    public NamecardDao() {
        try { // 오라클 DB 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //2.db연결
    private Connection getConnection() throws SQLException {
    	// DB 연결을 메소드로 만들기
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    		//먼저 ResultSet 이 널값이 아니면 종료, 다음 Statement 종료,
            //마지막으로 커넥션 종료
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
    // 입력 메소드(2)
    public void insert(Namecard card) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String sql = "INSERT INTO namecard VALUES (CARD_NO.NEXTVAL, ?, ?, ?, ?)";// 첫번째 ? : pstmt.setString(1, card.getName());
        																		 // 두번째 ? : pstmt.setString(2, card.getMobile());
        try {
        	//db 연결 메소드
            con = getConnection();
            pstmt = con.prepareStatement(sql);//sql 입력 메소드 
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getMobile());
            pstmt.setString(3, card.getEmail());
            pstmt.setString(4, card.getCompany());
            pstmt.executeUpdate();
            con.commit();//데이터 저장
            System.out.println("입력완료");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        } finally {
            close(null, pstmt, con);
        }
    }
    
    //번호 no 행 삭제    
    public void delete(int no) {//no 자리에 행 넘버 넣으면 검색됨
       //작성하기
    	 Connection con = null;
         PreparedStatement pstmt = null;
         
         String sql = "DELETE FROM namecard WHERE NO = ?";
         																		 // 두번째 ? : pstmt.setString(2, card.getMobile());
         try {
         	//db 연결 메소드
             con = getConnection();
             pstmt = con.prepareStatement(sql);//sql 준비
             pstmt.setInt(1, no);
             pstmt.executeUpdate();//실행
             con.commit();//데이터 저장
             System.out.println("삭제완료");
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println(sql);
         } finally {
             close(null, pstmt, con);
         }
    }
    
    //번호 no 행 검색:
    public Namecard selectOne(int no) { 
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Namecard card = null;//Namecard 클래스 객체
        String sql = "SELECT * FROM namecard WHERE NO = ?";
        																		 // 두번째 ? : pstmt.setString(2, card.getMobile());
        try {
        	//db 연결 메소드
            con = getConnection();
            pstmt = con.prepareStatement(sql);//sql 준비
            pstmt.setInt(1, no);
            //pstmt.executeupdate();//sql 실행(결과값 없을때
            rs = pstmt.executeQuery();//sql 실행(결과값 있을때
           
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

        return null;//에러발생시, return됨
    }
    
    //이름으로 찾기
    public ArrayList<Namecard> selectByKeyword(String keyword) {       
        ArrayList<Namecard> matched = new ArrayList<Namecard>();
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Namecard card = null;//Namecard 클래스 객체
       
        String sql = "SELECT * FROM namecard WHERE NAME=?";
        																		 // 두번째 ? : pstmt.setString(2, card.getMobile());
        try {
        	//db 연결 메소드
            con = getConnection();
            pstmt = con.prepareStatement(sql);//sql 준비
            pstmt.setString(1, keyword);// 이름으로 착기 밑에 keyword가 name으로 검생됨 
            //pstmt.executeupdate();//sql 실행(결과값 없을때
            rs = pstmt.executeQuery();//sql 실행(결과값 있을때
           
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

        return null;//에러발생시, return됨
    }
 

    
    //Namecard의 모든 행/열을 가져오기 번호순으로 
    public ArrayList<Namecard> selectAll() {
        ArrayList<Namecard> all = new ArrayList<Namecard>();
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Namecard card = null;//Namecard 클래스 객체
       
        String sql = "SELECT * FROM namecard ORDER BY 1";
        																		 // 두번째 ? : pstmt.setString(2, card.getMobile());
        try {
        	//db 연결 메소드
            con = getConnection();
            pstmt = con.prepareStatement(sql);//sql 준비
            //pstmt.executeupdate();//sql 실행(결과값 없을때
            rs = pstmt.executeQuery();//sql 실행(결과값 있을때
           
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

        return null;//에러발생시, return됨
    }
            
    
    //수정하기
    public void update(Namecard card) {
    	//작성하기
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String sql = "UPDATE namecard SET NAME =?,MOBLIE =?,EMAIL=?,"
        		+ "COMPANY=? WHERE NO=?";// 첫번째 ? : pstmt.setString(1, card.getName());
        																		 // 두번째 ? : pstmt.setString(2, card.getMobile());
        try {
        	//db 연결 메소드
            con = getConnection();
            pstmt = con.prepareStatement(sql);//sql 입력 메소드 
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getMobile());
            pstmt.setString(3, card.getEmail());
            pstmt.setString(4, card.getCompany());
            pstmt.setInt   (5, card.getNo());
            pstmt.executeUpdate();
            con.commit();//데이터 저장
            System.out.println("수정완료");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        } finally {
            close(null, pstmt, con);
        }
    }
    }


