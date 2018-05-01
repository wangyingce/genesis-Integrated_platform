package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OtherText {

	/**
	 * @param args
	 */
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String aa = "1454218268";
//		String createTime = sf.format(new Date(new Long(aa)*1000L));
//		System.out.println(createTime);
	        StringBuffer sb = new StringBuffer();  
	        Random random = new Random();  
	        for (int i = 0; i < 6; i++) {
	            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
	        }
	        System.out.println(sb.toString());
	}
	
	
	public static char[] getChar(){
        char[] passwordLit = new char[62];
        char fword = 'A';
        char mword = 'a';
        char bword = '0';
        for (int i = 0; i < 62; i++) {
            if (i < 26) {
                passwordLit[i] = fword;
                fword++;
            }else if(i<52){
                passwordLit[i] = mword;
                mword++;
            }else{
                passwordLit[i] = bword;
                bword++;
            }//方法的抽取，按功能
            //System.out.println(passwordLit[i]);
        }
     return passwordLit;
    }

}
