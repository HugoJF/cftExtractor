package cftExtractor.extractors.executers;

import ij.IJ;
import ij.ImagePlus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import cftExtractor.extractors.Extractor;
import cftExtractor.models.ImageSet;

public abstract class ImageSetExtractorExecutor {

	private static final Logger LOGGER = Logger.getLogger(ImageSetExtractorExecutor.class);
	private List<Extractor> extractors = new LinkedList<Extractor>();
	private List<String> attributesNames = new LinkedList<String>();
	private String name;

	public ImageSetExtractorExecutor(String name) {
		this.name = name;
		setExtractors(this.extractors);
		setAttributes(this.attributesNames);
	}

	public abstract void setExtractors(List<Extractor> extractors);

	public abstract void setAttributes(List<String> attributes);

	public Instances execute(ImageSet imageSet) {
		LOGGER.info("Gerando atributos");
		FastVector attributes = generateAttributes(imageSet);
		LOGGER.info("Gerando instancias");
		return generateInstances(attributes, imageSet, name);
	}

	private FastVector generateAttributes(ImageSet imageSet) {
		FastVector attributes = new FastVector();
		FastVector classes = new FastVector();

		for (String attributeName : attributesNames) {
			attributes.addElement(new Attribute(attributeName));
		}

		for (String clazz : imageSet.getClasses()) {
			classes.addElement(clazz);
		}

		attributes.addElement(new Attribute("class", classes));
		return attributes;
	}

	private Instances generateInstances(FastVector attributes, ImageSet imageSet, String name) {
		Instances instances = new Instances(name, attributes, 0);
		for (int classIndex = 0; classIndex < imageSet.getTotalClasses(); ++classIndex) {
			generateInstanceForClass(instances, imageSet, classIndex);
		}
		return instances;
	}

	private void generateInstanceForClass(Instances instances, ImageSet imageSet, int classIndex) {
		String className = imageSet.getClassName(classIndex), imagePath;
		Integer imagesLength = imageSet.getTotalImagesForClass(classIndex);
		ImagePlus image;

		for (int imageIndex = 0; imageIndex < imagesLength; imageIndex++) {
			ArrayList<Double> attributes = new ArrayList<Double>();

			LOGGER.info("Processando imagem [" + (imageIndex + 1) + "/" + imagesLength + "] da classe " + className);

			imagePath = imageSet.getImageName(classIndex, imageIndex);
			image = IJ.openImage(imagePath);

			for (Extractor extractor : this.extractors) {
				extractor.setImage(image);
				extractor.extract(attributesNames, attributes);
			}

			attributes.add((double) classIndex);
			instances.add(new Instance(1.0, Extractor.toPrimitive(attributes)));
		}
	}
}
