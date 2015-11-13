import java.util.ArrayList;

import net.sourceforge.javaocr.gui.GUIController;
import net.sourceforge.javaocr.gui.meanSquareOCR.TrainingImageSpec;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.CharacterRange;

public class test {
	
	 
public static String ORC(String target) throws Exception{
		
		
	ArrayList<TrainingImageSpec> images = new ArrayList<TrainingImageSpec>();
		
		String trainingImageLoc = "C:\\Users\\binbin\\Desktop\\567.png";
        //获得地址
        System.out.println(trainingImageLoc);
        int startChar = 46;
        //System.out.println(startChar);
        int endChar = 57;
        //System.out.println(endChar);
        
        TrainingImageSpec newImage = new TrainingImageSpec();
        newImage.setFileLocation(trainingImageLoc);
        newImage.setCharRange(new CharacterRange(startChar, endChar));

        images.add(newImage);
        
        GUIController guiController2=new GUIController();
      
	
    //iamge存放的全是trainImage的地址
    String targImageLoc = target;
    //获得目标图片的地址
    System.out.println(targImageLoc);
    //分析的函数
    //OCRDisplay ocrDisplay = new OCRDisplay(guiController.performMSEOCR(images, targImageLoc)+"ok");
    //主要的分析函数还是guiController.performMSEOCR(images, targImageLoc)
    String str = guiController2.performMSEOCR(images, targImageLoc);
	return str;
    
    
		
		
	}
	
	public static void main(String[] args) throws Exception{
		
		System.out.print(test.ORC("F:\\YZtyqRgpM48%3d.gif"));
    
	}
	
	
}