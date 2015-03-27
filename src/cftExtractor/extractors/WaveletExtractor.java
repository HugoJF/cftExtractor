package cftExtractor.extractors;

import fractsplinewavelets.Filters;
import fractsplinewavelets.FractSplineWavelets;
import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.Roi;
import ij.process.ImageConverter;

import java.util.Arrays;
import java.util.HashSet;

import waveletJava.core.GLCM_Texture;

public class WaveletExtractor extends Extractor {

	private static HashSet<String> attributeNames = new HashSet<String>(Arrays.asList("QUARTER_1_ASM", "QUARTER_1_CONT", "QUARTER_1_CORR", "QUARTER_1_IDM", "QUARTER_1_ENT", "QUARTER_2_ASM", "QUARTER_2_CONT", "QUARTER_2_CORR", "QUARTER_2_IDM", "QUARTER_2_ENT", "QUARTER_3_ASM", "QUARTER_3_CONT", "QUARTER_3_CORR", "QUARTER_3_IDM", "QUARTER_3_ENT", "QUARTER_4_ASM", "QUARTER_4_CONT", "QUARTER_4_CORR", "QUARTER_4_IDM", "QUARTER_4_ENT"));

	private final int[] iters = { 1, 1, 0 };
	private final int asm_index = 0, cont_index = 1, corr_index = 2, idm_index = 3, ent_index = 4;
	private final double degree = -0.49;
	private final double shift = -0.50;

	private float[] firstQuarterAttributes = new float[5];
	private float[] secondQuarterAttributes = new float[5];
	private float[] thirdQuarterAttributes = new float[5];
	private float[] fourthQuarterAttributes = new float[5];
	private GLCM_Texture matrizCo;

	@SuppressWarnings("unused")
	private double extractQUARTER_1_ASM() {
		return firstQuarterAttributes[asm_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_1_CONT() {
		return firstQuarterAttributes[cont_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_1_CORR() {
		return firstQuarterAttributes[corr_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_1_IDM() {
		return firstQuarterAttributes[idm_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_1_ENT() {
		return firstQuarterAttributes[ent_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_2_ASM() {
		return secondQuarterAttributes[asm_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_2_CONT() {
		return secondQuarterAttributes[cont_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_2_CORR() {
		return secondQuarterAttributes[corr_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_2_IDM() {
		return secondQuarterAttributes[idm_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_2_ENT() {
		return secondQuarterAttributes[ent_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_3_ASM() {
		return thirdQuarterAttributes[asm_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_3_CONT() {
		return thirdQuarterAttributes[cont_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_3_CORR() {
		return thirdQuarterAttributes[corr_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_3_IDM() {
		return thirdQuarterAttributes[idm_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_3_ENT() {
		return thirdQuarterAttributes[ent_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_4_ASM() {
		return fourthQuarterAttributes[asm_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_4_CONT() {
		return fourthQuarterAttributes[cont_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_4_CORR() {
		return fourthQuarterAttributes[corr_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_4_IDM() {
		return fourthQuarterAttributes[idm_index];
	}

	@SuppressWarnings("unused")
	private double extractQUARTER_4_ENT() {
		return fourthQuarterAttributes[ent_index];
	}

	@Override
	public void extractAttributes() {
		preProcessing(image);
		matrizCo = new GLCM_Texture();
		ImagePlus transform = FractSplineWavelets.doTransform(image, iters, Filters.ORTHONORMAL, degree, shift);
		//transform.show();
		int width = image.getWidth() / 2;
		int height = image.getHeight() / 2;

		firstQuarterAttributes = getWaveletsAttributes(transform, 1, width, height);
		secondQuarterAttributes = getWaveletsAttributes(transform, 2, width, height);
		thirdQuarterAttributes = getWaveletsAttributes(transform, 3, width, height);
		fourthQuarterAttributes = getWaveletsAttributes(transform, 4, width, height);
	}

	private float[] getWaveletsAttributes(ImagePlus transform, int quarter, int width, int height) {
		WindowManager.setTempCurrentImage(transform);
		Roi roi;

		if (quarter == 1) {
			roi = new Roi(0, 0, width, height);
		} else if (quarter == 2) {
			roi = new Roi(width, 0, width, height);
		} else if (quarter == 3) {
			roi = new Roi(0, height, width, height);
		} else {
			roi = new Roi(width, height, width, height);
		}

		IJ.makeRectangle(roi.getBounds().x, roi.getBounds().y, roi.getBounds().width, roi.getBounds().height);
		IJ.run("Duplicate...", "new_image");

		ImagePlus aux = WindowManager.getCurrentImage();
		//aux.show();
		preProcessing(aux);
		matrizCo.run(aux.getProcessor());

		return matrizCo.getAtributes();
	}

	private void preProcessing(ImagePlus imp) {
		WindowManager.setTempCurrentImage(imp);
		ImageConverter conversor;
		conversor = new ImageConverter(imp);
		conversor.convertToGray8();
		WindowManager.setTempCurrentImage(imp);
	}

	@Override
	public HashSet<String> getAtributtesNames() {
		return attributeNames;
	}
}
