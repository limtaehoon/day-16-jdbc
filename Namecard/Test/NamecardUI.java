package Com.Oracle.Namecard.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NamecardUI {
    
    private NamecardDao dao = new NamecardDao();
    
    public NamecardUI() {}

    private String readCommandLine() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        return input;
    }
    
    public void showMenu() {
        String menu = null;
        
        do {
            System.out.println(" *** 메뉴를 선택하세요 *** ");
            System.out.println("1.Namecard 등록");
            System.out.println("2.Namecard 리스트 출력");
            System.out.println("3.Namecard 삭제");
            System.out.println("4.Namecard 수정");
            System.out.println("5.Namecard 검색");
            System.out.println("q.종료");
            
            try {
                menu = readCommandLine();    
            
                if (menu.equals("1")) {
                    System.out.println("이름을 입력하세요>>");
                    String name = readCommandLine();
                    System.out.println("전화번호를 입력하세요>>");
                    String mobile = readCommandLine();
                    System.out.println("이메일을 입력하세요>>");
                    String email = readCommandLine();
                    System.out.println("회사를 입력하세요>>");
                    String company = readCommandLine();
                    Namecard card = new Namecard(name, mobile, email, company);
                    dao.insert(card);
                } else if (menu.equals("2")) {
                    ArrayList<Namecard> all = dao.selectAll();
                    for(int i = 0; i < all.size(); i++) {
                        System.out.println(all.get(i));
                    }
                } else if (menu.equals("3")) {
                    System.out.println("삭제할 이름을 입력하세요>>");
                    String name = readCommandLine();
                    ArrayList<Namecard> matched = dao.selectByKeyword(name);
                    if (matched.size() > 0) {
                        for (int i = 0; i < matched.size(); i++) {
                            System.out.println(matched.get(i));
                        }
                        System.out.println("삭제할 번호를 입력하세요>>");
                        int id = Integer.parseInt(readCommandLine());
                        dao.delete(id);
                    }
                } else if (menu.equals("4")) {
                    System.out.println("수정할 이름을 입력하세요>>");
                    String name = readCommandLine();
                    ArrayList<Namecard> matched = dao.selectByKeyword(name);
                    if (matched.size() > 0) {
                        for (int i = 0; i < matched.size(); i++) {
                            System.out.println(matched.get(i));
                        }
                        System.out.println("수정할 번호를 입력하세요>>");
                        int id = Integer.parseInt(readCommandLine());
                        Namecard card = dao.selectOne(id);
                        System.out.println("새이름 입력>>");
                        name = readCommandLine();
                        if (!name.equals("")) {
                            card.setName(name);
                        }
                        System.out.println("새전화번호를 입력하세요>>");
                        String mobile = readCommandLine();
                        if (!mobile.equals("")) {
                            card.setMobile(mobile);
                        }    
                        System.out.println("새이메일을 입력하세요>>");
                        String email = readCommandLine();
                        if (!email.equals("")) {
                            card.setEmail(email);
                        }
                        System.out.println("새회사를 입력하세요>>");
                        String company = readCommandLine();
                        if (!company.equals("")) {
                            card.setCompany(company);
                        }
                        dao.update(card);
                    }
                } else if (menu.equals("5")) {
                    System.out.print("찾을 이름을 입력하세요>>");
                    String name = readCommandLine();
                    ArrayList<Namecard> matched = dao.selectByKeyword(name);
                    for(int i = 0; i < matched.size(); i++) {
                        System.out.println(matched.get(i));
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while(!menu.equals("q"));
    }
    
    public static void main(String[] args) {
        new NamecardUI().showMenu();
    }

}