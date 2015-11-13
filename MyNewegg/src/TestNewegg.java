

import java.io.File;
import java.io.IOException;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class TestNewegg implements PageProcessor{
	 
	   private Site site = Site
	        .me().setUserAgent("Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 ")
	        .setDomain("http://www.newegg.cn/SubCategory/1043.htm").setTimeOut(30000);
	   
	   private GetImage getImage;
	   
	   Integer count = 0;
	        
	   //手机分类页面下可能存在的url
	   String LIST = "http://www\\.newegg\\.cn/SubCategory/[0-9]+\\.htm";  //分类页面
	   String OTHERLIST = "http://www\\.newegg\\.cn/SubCategory/[0-9-]+\\.htm#itemGrid1"; //页面的页数(第一，二页之类)
	   String PRODUCT = "http://www\\.newegg\\.cn/Product/[A-Z0-9-]+\\.htm"; //商品页面
	  
	 

	 
	  public void process(Page page){	    
	     Selectable selectUrl = page.getUrl();
	    
		 //若是清单页面
	     if(selectUrl.regex(LIST).match()){
	     
	    	 
	    	 //将该页面下所有商品页面的链接加进去
	    	page.addTargetRequests(page.getHtml().links().regex(PRODUCT).all());
	    	//将该页面下所有页数的链接加进去
	    	page.addTargetRequests(page.getHtml().regex("http://www.newegg.cn/SubCategory/[0-9-]+.htm#itemGrid1").all());
	    	page.setSkip(true);
	     }
	     //若是。。。
	     else if(selectUrl.regex(OTHERLIST).match()){
	    	 
	    	 page.addTargetRequests(page.getHtml().regex("http://www.newegg.cn/SubCategory/[0-9-]+.htm#itemGrid1").all());
	    	 page.addTargetRequests(page.getHtml().links().regex(PRODUCT).all());
	    	 page.setSkip(true);
	     }
	     //若是商品页面
	     else if(selectUrl.regex(PRODUCT).match()){
		  
		  //输出商品的名字
		  page.putField("title", page.getHtml().xpath("//*[@id=\"productTitle\"]/h1"));
		  
		  
		  //在html中把价格的图片匹配出来
		 String pricetarget = page.getHtml().regex("http://www.newegg.cn/Common/PriceImage.aspx?.+%3d").toString();
	    		   
		 String filename = pricetarget.substring(48);
		 
		 //System.out.println(filename);
	    	 
		  
		  //把图片下载到F盘去
		 String fileName = filename+".gif";
		 
		 File file = new File("D:\\\\" + fileName);
		  
		  byte[] btImg = getImage.getImageFromNetByUrl(pricetarget);  
	         if(null != btImg && btImg.length > 0){  
	             
	             getImage.writeImageToDisk(btImg, fileName);  
	         }else{  
	             System.out.println("没有从该连接获得内容");  
	         }
	         
	     
	     //识别图片中的数字并输出		
	     
			//String price =Demo.run(file.getAbsolutePath());
	    	 String price;
	    	 try {
				price = test.ORC(file.getAbsolutePath());
				page.putField("price", price);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	     		
		  
		  
	  }
	  
	  }    
	    public Site getSite() {
		return site;
	    }//http://blog.sina.com.cn/lm/index.html
	    
	    public static void main(String[] args) {
	    	Spider.create(new TestNewegg())
         .addUrl("http://www.newegg.cn/SubCategory/1043.htm").thread(10)
         .pipeline(new ConsolePipeline())
         .run();
	    }
}