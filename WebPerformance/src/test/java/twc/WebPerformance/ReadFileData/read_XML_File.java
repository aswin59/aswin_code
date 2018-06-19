package twc.WebPerformance.ReadFileData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class read_XML_File {
	
	public String read_xml_into_buffer_string() throws Exception{
	int count=0;
	String results = null;
	String xml_file_path=null;
	File folder = new File("/Users/monocept/Documents/workspace_luna/WebPerformance/CapturedFile/");
	File[] listOfFiles = folder.listFiles();
	String Filename = null;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				Filename = file.getName();
				xml_file_path = folder+"/"+Filename;
				System.out.println("XML File Name is : "+Filename);
			}
		}
		
		StringBuilder sb=null;
	
		try {
			File xmlFile = new File(xml_file_path); 
			Reader fileReader = new FileReader(xmlFile); 
			BufferedReader bufReader = new BufferedReader(fileReader); 
			sb = new StringBuilder(); 
			String line = bufReader.readLine(); 
			
			while( (line=bufReader.readLine()) != null)
			{ 
				sb.append(line).append("\n"); 
				if(line.contains("transaction status=") && line.contains("host=")){
					sb.append(line.trim());
					count=count+1;
				}
			} 
			bufReader.close();
		} catch (Exception e) {
			System.out.println("No Data Found in XML File");
		}
		
	results = Integer.toString(count);
	count = 0;
	return results;
	}
}
