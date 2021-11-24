package life.zhk.life.zhk.community;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DouDiZhuTest {
    public static void main(String[] args) {
        List<String> stringList =new ArrayList<String>();
        String[] colour ={"♥","♣","♦","♠"};
        String[] puke={"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
        for (String s : colour) {
            for (String s1 : puke) {
                stringList.add(s+s1);
            }
        }
        stringList.add("小王");
        stringList.add("大王");

        List<String> liuyan =new ArrayList<String>();
        List<String> wangzhaojun =new ArrayList<String>();
        List<String> xishi =new ArrayList<String>();
        List<String> dipai =new ArrayList<String>();
        Collections.shuffle(stringList);
        for (int i=0;i<stringList.size();i++) {
            if(i>=stringList.size()-3){
                dipai.add(stringList.get(i));
            }else if (i%3==0){
                liuyan.add(stringList.get(i))  ;
            }else if (i%3==1){
                wangzhaojun.add(stringList.get(i))  ;
            }else if (i%3==2){
                xishi.add(stringList.get(i))  ;
            }
        }
        lookPai();
        System.out.println(liuyan);
        System.out.println(wangzhaojun);
        System.out.println(xishi);
        System.out.println(dipai);
    }
    public  static  void  lookPai(){

    }
}
