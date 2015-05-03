package cftExtractor.extractors;

import static cftExtractor.models.Constants.HSB_B;
import static cftExtractor.models.Constants.HSB_H;
import static cftExtractor.models.Constants.HSB_S;
import static cftExtractor.models.Constants.RGB_B;
import static cftExtractor.models.Constants.RGB_G;
import static cftExtractor.models.Constants.RGB_R;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.log4j.Logger;

/**
 * Class responsible for extract the following attributes of color: RGB_R", "RGB_G", "RGB_B", "HSB_H", "HSB_S", "HSB_B
 * 
 * @author victorjussiani
 */
public class ColorExtractor extends Extractor {

	private static Logger LOGGER = Logger.getLogger(ColorExtractor.class);
	private static HashSet<String> attributeNames = new HashSet<String>(Arrays.asList("RGB_R", "RGB_G", "RGB_B", "HSB_H", "HSB_S", "HSB_B"));

	/**
	 * Method responsible for extract the RGB channels from image
	 * 
	 * @param imageProcessor - Image processor to the target image
	 * @return Returns the average of the values ​​for the three channels: R[0] G[1] B[2]
	 */
	private double[] captureRGBPixels(ImageProcessor imageProcessor) {
		LOGGER.debug("Extracting RGB channels from image");

		ImagePlus imp = new ImagePlus("Teste", imageProcessor);
		imageProcessor = imp.getProcessor();
		double vetor[] = new double[3];
		for (int i = 0; i < imageProcessor.getWidth(); i++) {
			for (int j = 0; j < imageProcessor.getHeight(); j++) {
				Color color = new Color(imageProcessor.getPixel(i, j));
				vetor[RGB_R] += color.getRed();
				vetor[RGB_G] += color.getGreen();
				vetor[RGB_B] += color.getBlue();
			}
		}

		int qtdade = imageProcessor.getWidth() * imageProcessor.getHeight();
		vetor[RGB_R] = (vetor[RGB_R] / qtdade);
		vetor[RGB_G] = (vetor[RGB_G] / qtdade);
		vetor[RGB_B] = (vetor[RGB_B] / qtdade);
		return vetor;
	}

	/**
	 * Method responsible for extract the HSV channels from image
	 * 
	 * @param imageProcessor - Image processor to the target image
	 * @return Returns the average of the values ​​for the three channels
	 */
	public double[] captureHSBPixels(ImageProcessor imageProcessor) {
		LOGGER.debug("Extracting HSB channels from image");

		ImagePlus imp = new ImagePlus("Teste", imageProcessor);
		imageProcessor = imp.getProcessor();
		double vetorHSB[] = new double[3];
		for (int i = 0; i < imageProcessor.getWidth(); i++) {
			for (int j = 0; j < imageProcessor.getHeight(); j++) {
				Color color = new Color(imageProcessor.getPixel(i, j));
				float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
				vetorHSB[0] += hsb[0];
				vetorHSB[1] += hsb[1];
				vetorHSB[2] += hsb[2];
			}
		}

		int qtdade = imageProcessor.getWidth() * imageProcessor.getHeight();
		vetorHSB[0] = (vetorHSB[0] / qtdade);
		vetorHSB[1] = (vetorHSB[1] / qtdade);
		vetorHSB[2] = (vetorHSB[2] / qtdade);
		return vetorHSB;
	}

	@Override
	public HashSet<String> getAtributtesNames() {
		return attributeNames;
	}

	@Override
	public void extractAttributes() {
		double[] rgb, hsb;
		rgb = captureRGBPixels(this.image.getProcessor());
		hsb = captureHSBPixels(this.image.getProcessor());
		
	}

	// TODO Remover
	// public static void main(String[] args) throws Exception {
	// ColorExtractor colorExtractor = new ColorExtractor(null);
	// Configuration configuration = new Configuration();
	// configuration.addAttribute("RGB_R");
	//
	// HashMap<String, Double> attributes = new HashMap<String, Double>();
	// colorExtractor.extract(configuration, attributes);
	// }

}
