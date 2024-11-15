package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consists of generic methods related to property file
 * @author Chaitra M
 *
 */
public class PropertyFileUtility {
	
	/**
	 * This method is used to read data from property file and return the value
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertyFile(String input) throws IOException
	{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(input);
		return value;
	}

}
