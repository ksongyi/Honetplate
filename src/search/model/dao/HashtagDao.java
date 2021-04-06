package search.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;





public class HashtagDao {
	
	private Properties prop = new Properties();
	
	public HashtagDao() {
		String fileName = "/sql/search/search-query.properties";
		String path = HashtagDao.class.getResource(fileName).getPath();
		
//		System.out.println("path@MemberDao = " + path);
//		System.out.println("prop@MemberDao = " + prop);
		
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	
	

	
	
	
	
	
	
	
	
	

}
