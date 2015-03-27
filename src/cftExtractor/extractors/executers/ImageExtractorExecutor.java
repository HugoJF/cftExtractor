package cftExtractor.extractors.executers;

import ij.ImagePlus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import weka.core.Instance;

import cftExtractor.extractors.Extractor;

public abstract class ImageExtractorExecutor {

	private static final Logger LOGGER = Logger
			.getLogger(ImageExtractorExecutor.class);
	private List<Extractor> extractors = new LinkedList<Extractor>();
	private List<String> attributesNames = new LinkedList<String>();

	public ImageExtractorExecutor() {
		setExtractors(this.extractors);
		setAttributes(attributesNames);
	}

	public abstract void setExtractors(List<Extractor> extractors);

	public abstract void setAttributes(List<String> attributes);

	public Instance execute(ImagePlus image) {
		LOGGER.info("Gerando instancias");
		return generateInstance(image);
	}

	private Instance generateInstance(ImagePlus image) {
		ArrayList<Double> attributes = new ArrayList<Double>();

		for (Extractor extractor : this.extractors) {
			extractor.setImage(image);
			extractor.extract(attributesNames, attributes);
		}
		return new Instance(1.0, Extractor.toPrimitive(attributes));
	}

}