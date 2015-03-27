package cftExtractor.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;

public class XMLConfigReader {

	private final InputStream input;

	public XMLConfigReader() {
		this(XMLConfigReader.class.getClassLoader().getResourceAsStream("default_configuration.xml"));
	}

	public XMLConfigReader(InputStream input) {
		this.input = input;
	}

	public Configuration read() {
//		InputStream teste = XMLConfigReader.class.getClassLoader().getResourceAsStream("default_configuration.xml");
//		File file = new File(XMLConfigReader.class.getClassLoader().getResource("default_configuration.xml").getFile());
		XStream xStream = new XStream();
		xStream.alias("configuration", Configuration.class);
		xStream.alias("attribute", String.class);
		return (Configuration) xStream.fromXML(input);
	}
	
	public void write(Configuration content){
		XStream xStream = new XStream();
		xStream.alias("configuration", Configuration.class);
		xStream.alias("attribute", String.class);
		String configuracaoXML = xStream.toXML(content);
		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		
		File file = new File(XMLConfigReader.class.getClassLoader().getResource("default_configuration.xml").getFile());
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(header);
			bw.write(configuracaoXML);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
