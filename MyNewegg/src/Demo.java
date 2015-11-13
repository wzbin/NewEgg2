import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.asprise.util.ocr.OCR;

//这个是识别图片中的数字的类
public class Demo {
	
	public static void main(String args[]){
		
		
		try {
			Demo.run("D:\\EKdDPFRFc1k%3d.gif");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

	public static String run(String target) throws IOException {
		if(("1.4").compareTo(System.getProperty("java.vm.version")) > 0) {
			System.err.println("Warining: \n\nYou need Java version 1.4 or above for ImageIO to run this demo.");
			System.err.println("Your current Java version is: " + System.getProperty("java.vm.version"));
			System.err.println("\nSolutions: \n");
			System.err.println("(1) Download JRE/JDK version 1.4 or above; OR \n");
			System.err.println("(2) Run DemoUI, which can run on your current Java virtual machine.");
			System.err.println("    Double click the 'runDemoUI' to invoke it.\n");
			return null;
		}

		

		
	
		File file = new File(target);
	
		//System.out.println("Trying to perform OCR on image: " + file.getAbsolutePath());
		
		

		BufferedImage image =
			ImageIO.read(file);
		
		
			
			return new OCR().recognizeEverything(image);
			
		
	}
	
}
