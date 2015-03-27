package cftExtractor.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;

public class XMLBowConfigReader {

	private final InputStream input;
	
	public XMLBowConfigReader() {
		this(XMLBowConfigReader.class.getClassLoader().getResourceAsStream("bow_configuration.xml"));
	}

	public XMLBowConfigReader(InputStream input) {
		this.input = input;
	}

	public BowConfiguration read() {
		XStream xStream = new XStream();
		xStream.alias("opencv_storage", BowConfiguration.class);
		return (BowConfiguration) xStream.fromXML(input);
	}
	
	public void write(BowConfiguration content){
		XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");  
		XStream xStream = new XStream(new DomDriver("UTF-8", replacer));
		
		xStream.alias("opencv_storage", BowConfiguration.class);
		String bowConfiguracaoXML = xStream.toXML(content);
		String header = "<?xml version=\"1.0\"?>";
		
		File file = new File(XMLBowConfigReader.class.getClassLoader().getResource("bow_configuration.xml").getFile());
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(header);
			bw.newLine();
			bw.write(bowConfiguracaoXML);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
