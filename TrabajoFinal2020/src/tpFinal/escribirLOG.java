package tpFinal;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class escribirLOG {

	private Logger logger;
	
	public escribirLOG(String dir) {
		this.logger = Logger.getLogger(dir);
		FileHandler fh;  
		logger.setUseParentHandlers(false);
		try {

			// This block configure the logger with handler and formatter  
			fh = new FileHandler("A:/Logs/MyLog.log");  
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);  

			// the following statement is used to log any messages  
		} catch (SecurityException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}
	}

	public void escribirLOG(String dato) {
		this.logger.info(dato);
	}

}
