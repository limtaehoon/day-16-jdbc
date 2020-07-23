package Com.Oracle.Namecard.Test;//3

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
        //드리이브 로드됨
		NamecardDao dao = new NamecardDao();
        
        // //1.insert(Namecard) test: 입력하기
        //Namecard hong = new Namecard("GARR","010-2221-5534", "GG@naver.com", "부산IT");
        //dao.insert(hong); //실행후 sql developer 확인
        //no 삭제하기
        // dao.delete(3);
		// no행의 데이터를 Namecard 클래스에 입력하여 리턴
		//Namecard card1 = dao.selectOne(1);
		//System.out.println(card1);//클래스의 값 리턴받음//card1  앞에 toString 생략되있음 
		
		
		
		
//		//2.selectAll() test: 다 가져와서 리스트에 입력한후 콘솔에 출력
//        ArrayList<Namecard> list = dao.selectAll();
//        int size = list.size();
//        System.out.println(size);
//        for (int i = 0; i < size; i++) {
//          Namecard card = list.get(i);
//          System.out.println(card);
//        }
//
//
//
//        //3.selectByKeyword(String) test: 이름으로 찾기
//        ArrayList<Namecard> matched = dao.selectByKeyword("LALA");
//        int length = matched.size();
//        System.out.println(length + "명 찾음.");
//        for (int i = 0; i < length; i++) {
//            Namecard namecard = matched.get(i);
//            System.out.println(namecard);
//        }



        //4.selectOne(int) test : 번호로 찾기
 //       Namecard card = dao.selectOne(1);
 //       System.out.println("1번 찾음");
 //       System.out.println(card);



        //5.delete(int) test : 번호로 삭제하기
//        dao.delete(6);
//        ArrayList<Namecard> all = dao.selectAll();
//      int cardNum = all.size();
//        for (int i = 0; i < cardNum; i++) {
//            Namecard namecard = all.get(i);
//           System.out.println(namecard);
//        }
 

      
        //6. update(Namecard) test :  card의 no번호와 같은 NAMECARD 행을  이름,
        //전화번호, 이메일, 회사 업데이트(수정하기)
//        Namecard card = dao.selectOne(7);//DB에서 5번째행을 가져옴
//        System.out.println(card);
//        
//        card.setEmail("lalala@daum.net");
//        card.setMobile("010-9999-9999");
//        dao.update(card);
//        System.out.println(card);
//       
//        Namecard card1 = dao.selectOne(7);
//        System.out.println(card1);
    }
}
