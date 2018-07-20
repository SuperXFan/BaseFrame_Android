package cc.ewell.baseframe.utils;

import java.util.UUID;

/**
 * Created by Mr.Dong on 2017/4/7.
 */
public class GetUUID {
    //获得全球唯一性的id
    public static String getId(){
        String id= UUID.randomUUID().toString();//生成的id942cd30b-16c8-449e-8dc5-028f38495bb5中间含有横杠，<span style="color: rgb(75, 75, 75); font-family: Verdana, Arial, Helvetica, sans-serif; line-height: 20.7999992370605px;">用来生成数据库的主键id是很实用的。</span>
        id=id.replace("-", "");//替换掉中间的那个斜杠
        return id;
    }
}