package cftExtractor.cft_extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import cftExtractor.config.Configuration;
import cftExtractor.config.XMLConfigReader;
import cftExtractor.extractors.CoOcorrenceMatrixExtractor;
import cftExtractor.extractors.ColorExtractor;
import cftExtractor.extractors.Extractor;
import cftExtractor.extractors.FormExtractor;
import cftExtractor.extractors.LBPExtractor;
import cftExtractor.extractors.WaveletExtractor;
import cftExtractor.extractors.executers.ImageSetExtractorExecutor;
import cftExtractor.models.ImageSet;

public class Main {

	private static Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) throws FileNotFoundException {
		
		// TODO Auto-generated method stub

		//XMLConfigReader xmlcr = new XMLConfigReader(new FileInputStream("default_configuration.xml"));
		//final Configuration configuration = xmlcr.read();
		
		final Configuration configuration = new Configuration(true, true, true, true, 10, true, true);

		String name = "polen23e+cft";

		ImageSet imageSet = new ImageSet("C:/polen23e_min/");

		String outputPath = "d:/output.cft";

		ImageSetExtractorExecutor extractorExecutor = new ImageSetExtractorExecutor(name) {

			@Override
			public void setExtractors(List<Extractor> extractors) {
				extractors.add(new ColorExtractor());
				extractors.add(new FormExtractor());
				extractors.add(new CoOcorrenceMatrixExtractor());
				extractors.add(new LBPExtractor());
				extractors.add(new WaveletExtractor());
			}

			@Override
			public void setAttributes(List<String> attributes) {
				attributes.addAll(configuration.getAttributes());
			}
		};

		Instances instances = extractorExecutor.execute(imageSet);

		LOGGER.info("Gerando arquivo .arff");
		ArffSaver saver = new ArffSaver();
		saver.setInstances(instances);
		try {
			saver.setFile(new File(outputPath));
			saver.writeBatch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("Processo finalizado");

	}

}
