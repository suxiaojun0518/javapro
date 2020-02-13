package com.neuedu.text;

import java.util.*;

/**
 * @Author SuXiaojun
 * @Date 2020/2/13 9:55
 * @Version 1.0
 */
public class LangRenSha {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入玩家数：");
        int player = scanner.nextInt();
        if(player>18||player<12){
            System.out.println("人数不够，无法游戏");
        }else{
            List <String> all = new ArrayList<>();//总的牌数
            List <String> play = new ArrayList<>();//玩家手牌
            List <String> di = new ArrayList<>();//底牌
            init(all);
            play(all,player);
            all.remove("盗贼");
            play.add("盗贼");
            dipai(all,di);
            play.addAll(all);
            Collections.shuffle(play);//打乱顺序，(洗牌)
            System.out.println("手牌为"+play);
            System.out.println("底牌为"+di);
        }

    }
    //初始化12张基本牌
    public static void init(List<String >list){
        for(int i = 0 ;i<4;i++){
            list.add("狼人");
        }
        for(int i = 0 ;i<4;i++){
            list.add("村民");
        }
        list.add("预言家");
        list.add("女巫");
        list.add("丘比特");
        list.add("守护");
        list.add("猎人");
        list.add("村长");
        list.add("盗贼");
    }
    //根据不同的人数增加不同牌数
    public static void play(List<String> list,int player){
        if(player>12)
            list.add("村民");
        if(player>13)
            list.add("替罪羊");
        if(player>14)
            list.add("狼人");
        if(player>15)
            list.add("村民");
        if(player>16)
            list.add("村民");
        if(player>17)
            list.add("吹笛者");
    }
    //从所有牌中随机抽取三张底牌
    public static void dipai(List<String> list,List<String> di){
        while (di.size()<3){
            Random random=new Random();
            int index=random.nextInt(list.size());
            if(di.contains("狼人")&&list.get(index).equals("狼人")){
                continue;
            }else {
                /**
                 * get()方法与remove()的区别：
                 * 两者返回的都是String类型，但是get(index)只会查找该索引下的值，不会删除原List，
                 * remove(index)返回该值并且删除List中该值
                 *
                 * **/
                di.add(list.remove(index));
            }
        }
    }
}
