package com.ysyl.common.qrcode;  
  
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;
import com.swetake.util.Qrcode;
import com.ysyl.common.service.facade.CommonService;
  
public class TwoDimensionCode {  
    /** 
     * 解析二维码（QRCode） 
     * @param imgPath 图片路径 
     * @return 
     */  
    public String decoderQRCode(String imgPath) {  
        // QRCode 二维码图片的文件   
        File imageFile = new File(imgPath);  
        BufferedImage bufImg = null;  
        String content = null;  
        try {  
            bufImg = ImageIO.read(imageFile);  
            QRCodeDecoder decoder = new QRCodeDecoder();  
            content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");   
        } catch (IOException e) {  
            System.out.println("Error: " + e.getMessage());  
            e.printStackTrace();  
        } catch (DecodingFailedException dfe) {  
            System.out.println("Error: " + dfe.getMessage());  
            dfe.printStackTrace();  
        }  
        return content;  
    }  
      
    /** 
     * 解析二维码（QRCode） 
     * @param input 输入流 
     * @return 
     */  
    public String decoderQRCode(InputStream input) {  
        BufferedImage bufImg = null;  
        String content = null;  
        try {  
            bufImg = ImageIO.read(input);  
            QRCodeDecoder decoder = new QRCodeDecoder();  
            content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");   
        } catch (IOException e) {  
            System.out.println("Error: " + e.getMessage());  
            e.printStackTrace();  
        } catch (DecodingFailedException dfe) {  
            System.out.println("Error: " + dfe.getMessage());  
            dfe.printStackTrace();  
        }  
        return content;  
    }  
  
    /** 
     * 生成二维码（QRCode） 
     * @param content 内容 
     * @param imgPath 二维码路径
     * @param logo logo路径
     * @return 
     */  
    public void crtQRCode(String content,String path,String logo,String nickName,String type) {  
        try {  

            Qrcode qrcodeHandler = new Qrcode();  

            qrcodeHandler.setQrcodeErrorCorrect('M');  

            qrcodeHandler.setQrcodeEncodeMode('B');  

            qrcodeHandler.setQrcodeVersion(7);  

            // System.out.println(content);  

            byte[] contentBytes = content.getBytes("gb2312");  

            //构造一个BufferedImage对象 设置宽、高

            BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);  

            Graphics2D gs = bufImg.createGraphics();  

            gs.setBackground(Color.WHITE);  

            gs.clearRect(0, 0, 140, 140);  

            // 设定图像颜色 > BLACK  

            if("zfb"==type||"zfb".equals(type)){
            	Color skyblue 	= new Color(0, 161, 232);
            	gs.setColor(skyblue);
            }else{
            	gs.setColor(Color.BLACK);
            }
              

            // 设置偏移量 不设置可能导致解析出错  

            int pixoff = 2;  

            // 输出内容 > 二维码  

            if (contentBytes.length > 0 && contentBytes.length < 120) {  

                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  

                for (int i = 0; i < codeOut.length; i++) {  

                    for (int j = 0; j < codeOut.length; j++) {  

                        if (codeOut[j][i]) {  

                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  

                        }  

                    }  

                }  

            } else {  

                System.err.println("QRCode content bytes length = "+ contentBytes.length + " not in [ 0,120 ]. ");  

            }  

            //写入Logo-S
            Image img = ImageIO.read(new File(logo));//实例化一个Image对象。
            gs.drawImage(img, 50, 50, 40, 40, null);
            //写入Logo-E

            //写入文字-s
            /*Graphics2D g = (Graphics2D)bufImg.getGraphics();
	        g.fillRect(0, 223, 223, 15);
	        g.setColor(Color.gray);//设置图片颜色
	        g.setFont(new Font("微软雅黑",Font.PLAIN,10));
	        String incode = nickName+" 专用微信支付二维码";
	        g.drawString(incode, 0, 235);//画图片*/
	        //写入文字-e
            
            gs.dispose();  
            bufImg.flush();  

            // 生成二维码QRCode图片  
            File imgFile = new File(path);  
            ImageIO.write(bufImg, "png", imgFile);  
            
        }catch (Exception e){  
            e.printStackTrace();  
        }  


    }

    /**
     * 生成展示图片
     * @param imgPath
     */
	public void crtShowImage(String imgPath,String topimg,String bottomimg,String wximgPath,String zfbimgPath,String nickName) {
		try {
			BufferedImage bufImg = new BufferedImage(375, 375, BufferedImage.TYPE_INT_RGB);  
		    Graphics2D gs = bufImg.createGraphics();  
		    gs.setBackground(Color.WHITE);  
		    gs.clearRect(0, 0, 375, 375);  
		    gs.setColor(Color.WHITE);  
		    
		    Image top = ImageIO.read(new File(topimg));
			gs.drawImage(top, 0, 0, 375, 150, null);
			
			Image zfb = ImageIO.read(new File(zfbimgPath));
			gs.drawImage(zfb,25, 155, 140, 140, null);
			
			Image wx = ImageIO.read(new File(wximgPath));
			gs.drawImage(wx,210, 155, 140, 140, null);
			
			Image bot = ImageIO.read(new File(bottomimg));
			gs.drawImage(bot, 0, 295, 375, 80, null);
			
			 //写入文字-s
	        gs.setFont(new Font("微软雅黑",Font.PLAIN,12));
	        String incode = "店铺：“"+nickName+"”支付二维码，由友商友良科技友情提供";
	        gs.drawString(incode, 0, 360);//画图片
	        //写入文字-e
			
			gs.dispose();  
            bufImg.flush();  
            File imgFile = new File(imgPath);  
            ImageIO.write(bufImg, "png", imgFile);  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}  