package common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;


public class MvcFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		File newFile = null;
		
		do {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
			DecimalFormat df = new DecimalFormat("000"); 
			
			//확장자가져오기
			String ext = "";
			String oldName = oldFile.getName(); //abc.jpg
			int dot = oldName.lastIndexOf(".");
			if(dot > -1)
				ext = oldName.substring(dot);//.jpg
			
			//새로운 파일명
			String newName = 
					sdf.format(new Date()) + df.format(Math.random() * 999)+ ext;
			
			//새로운 파일객체
			newFile = new File(oldFile.getParent(), newName);
			System.out.println("newFile@MvcFileRenamePolicy = " + newFile);
			
		} while(!createNewFile(newFile));
		
		return newFile;
	}
	
	
	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		} catch (IOException ignored) {
			return false;
		}
	}

}
