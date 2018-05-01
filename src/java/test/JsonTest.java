package test;  
  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
  
import com.google.gson.reflect.TypeToken;  
import com.ysyl.common.util.JsonUtil;
  
public class JsonTest {  
      
    /** 
     * 一般对象的转换 
     */  
    public static void objectToJson(){  
        Person person = new Person();  
        person.setId(1);  
        person.setName("one");  
//        person.setName1("one1");  
        Text text = new Text();
        text.setContent("欢迎关注");
        person.setText(text);
         //javabean转换成json字符串  
        String jsonStr = JsonUtil.toJson(person);  
        System.out.println(jsonStr);  
          
        //json字符串转换成javabean  
        Person newPerson = JsonUtil.fromJson(jsonStr, Person.class);  
        System.out.println(person == newPerson);  
        System.out.println(newPerson.getId()+","+newPerson.getName());  
    }  
      
    /** 
     * 复合结构数据转换(List) 
     */  
    public static void listToJson(){  
        Person person1 = new Person();  
        person1.setId(1);  
        person1.setName("one");  
          
        Person person2 = new Person();  
        person2.setId(2);  
        person2.setName("two");  
          
        List<Person> list = new ArrayList<Person>();  
        list.add(person1);  
        list.add(person2);  
          
        //javabean转换成json字符串  
        String jsonStr = JsonUtil.toJson(list);  
        System.out.println(jsonStr);  
          
          
        //json字符串转换成javabean对象  
        List<Person> rtn = JsonUtil.fromJson(jsonStr, new TypeToken<List<Person>>(){}.getType());  
        for(Person person : rtn){  
            System.out.println(person.getId()+","+person.getName());  
        }  
    }  
      
    /** 
     * 复合结构数据转换(Map) 
     */  
    public static void mapToJson(){  
        Person person1 = new Person();  
        person1.setId(1);  
        person1.setName("one");  
        Person person2 = new Person();  
        person2.setId(2);  
        person2.setName("two");  
          
        Map<Integer,Person> map = new HashMap<Integer,Person>();  
        map.put(person1.getId(), person1);  
        map.put(person2.getId(), person2);  
          
         //javabean转换成json字符串  
        String jsonStr = JsonUtil.toJson(map);  
        System.out.println(jsonStr);  
          
        //json字符串转换成Map对象  
        Map<Integer,Person> rtn = JsonUtil.fromJson(jsonStr, new TypeToken<Map<Integer,Person>>(){}.getType());  
        for(Entry<Integer, Person> entry : rtn.entrySet()){  
            Integer key = entry.getKey();  
            Person newPerson = entry.getValue();  
            System.out.println("key:"+key+","+newPerson.getId()+","+newPerson.getName());  
        }  
    }  
      
  
    public static void main(String[] args) {  
        objectToJson();  
//        System.out.println("****************************");  
//        listToJson();  
//        System.out.println("****************************");  
//        mapToJson();  
    }  
      
   
  
} 