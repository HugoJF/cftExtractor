package cftExtractor.extractors;

import ij.IJ;
import ij.WindowManager;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Extract features from an image ROI based on Image Moments calculated for each cell of grid imposed on the ROI
 */
public class CoOcorrenceMatrixExtractor extends Extractor {
	private final int anguloI = 0;
	private final int anguloF = 360;
	private final int anguloInc = 10;
	private final int distanciaInc = 1;

	private double[] entr, corr, diss, cont, asm, inv, idm;

	private static HashSet<String> attributeNames = new HashSet<String>(Arrays.asList("ENTR360", "ENTR315", "ENTR270", "ENTR225", "ENTR180", "ENTR135", "ENTR90", "ENTR45", "CORR360", "CORR315", "CORR270", "CORR225", "CORR180", "CORR135", "CORR90", "CORR45", "DISS360", "DISS315", "DISS270", "DISS225", "DISS180", "DISS135", "DISS90", "DISS45", "CONT360", "CONT315", "CONT270", "CONT225", "CONT180", "CONT135", "CONT90", "CONT45", "ASM360", "ASM315", "ASM270", "ASM225", "ASM180", "ASM135", "ASM90", "ASM45", "INV360", "INV315", "INV270", "INV225", "INV180", "INV135", "INV90", "INV45", "IDM360", "IDM315", "IDM270", "IDM225", "IDM180", "IDM135", "IDM90", "IDM45"));

	public double extractENTR360() {
		return getENTR(360);
	}

	public double extractENTR315() {
		return getENTR(315);
	}

	public double extractENTR270() {
		return getENTR(270);
	}

	public double extractENTR225() {
		return getENTR(225);
	}

	public double extractENTR180() {
		return getENTR(180);
	}

	public double extractENTR135() {
		return getENTR(135);
	}

	public double extractENTR90() {
		return getENTR(90);
	}

	public double extractENTR45() {
		return getENTR(45);
	}

	public double extractCORR360() {
		return getCORR(360);
	}

	public double extractCORR315() {
		return getCORR(315);
	}

	public double extractCORR270() {
		return getCORR(270);
	}

	public double extractCORR225() {
		return getCORR(225);
	}

	public double extractCORR180() {
		return getCORR(180);
	}

	public double extractCORR135() {
		return getCORR(135);
	}

	public double extractCORR90() {
		return getCORR(90);
	}

	public double extractCORR45() {
		return getCORR(45);
	}

	public double extractDISS360() {
		return getDISS(360);
	}

	public double extractDISS315() {
		return getDISS(315);
	}

	public double extractDISS270() {
		return getDISS(270);
	}

	public double extractDISS225() {
		return getDISS(225);
	}

	public double extractDISS180() {
		return getDISS(180);
	}

	public double extractDISS135() {
		return getDISS(135);
	}

	public double extractDISS90() {
		return getDISS(90);
	}

	public double extractDISS45() {
		return getDISS(45);
	}

	public double extractCONT360() {
		return getCONT(360);
	}

	public double extractCONT315() {
		return getCONT(315);
	}

	public double extractCONT270() {
		return getCONT(270);
	}

	public double extractCONT225() {
		return getCONT(225);
	}

	public double extractCONT180() {
		return getCONT(180);
	}

	public double extractCONT135() {
		return getCONT(135);
	}

	public double extractCONT90() {
		return getCONT(90);
	}

	public double extractCONT45() {
		return getCONT(45);
	}

	public double extractASM360() {
		return getASM(360);
	}

	public double extractASM315() {
		return getASM(315);
	}

	public double extractASM270() {
		return getASM(270);
	}

	public double extractASM225() {
		return getASM(225);
	}

	public double extractASM180() {
		return getASM(180);
	}

	public double extractASM135() {
		return getASM(135);
	}

	public double extractASM90() {
		return getASM(90);
	}

	public double extractASM45() {
		return getASM(45);
	}

	public double extractINV360() {
		return getINV(360);
	}

	public double extractINV315() {
		return getINV(315);
	}

	public double extractINV270() {
		return getINV(270);
	}

	public double extractINV225() {
		return getINV(225);
	}

	public double extractINV180() {
		return getINV(180);
	}

	public double extractINV135() {
		return getINV(135);
	}

	public double extractINV90() {
		return getINV(90);
	}

	public double extractINV45() {
		return getINV(45);
	}

	public double extractIDM360() {
		return getIDM(360);
	}

	public double extractIDM315() {
		return getIDM(315);
	}

	public double extractIDM270() {
		return getIDM(270);
	}

	public double extractIDM225() {
		return getIDM(225);
	}

	public double extractIDM180() {
		return getIDM(180);
	}

	public double extractIDM135() {
		return getIDM(135);
	}

	public double extractIDM90() {
		return getIDM(90);
	}

	public double extractIDM45() {
		return getIDM(45);
	}

	private double getENTR(int angle) {
		return entr[angle / anguloInc];
	}

	private double getCORR(int angle) {
		return corr[angle / anguloInc];
	}

	private double getDISS(int angle) {
		return diss[angle / anguloInc];
	}

	private double getCONT(int angle) {
		return cont[angle / anguloInc];
	}

	private double getASM(int angle) {
		return asm[angle / anguloInc];
	}

	private double getINV(int angle) {
		return inv[angle / anguloInc];
	}

	private double getIDM(int angle) {
		return idm[angle / anguloInc];
	}

	@Override
	public void extractAttributes() {
		WindowManager.setTempCurrentImage(image);
		IJ.run("8-bit");
		ImageProcessor ip = (ByteProcessor) image.getProcessor();
		Rectangle rect = image.getProcessor().getRoi();

		double matriz[][] = new double[257][257];
		int arraySize = (anguloF / anguloInc) + 1;
		entr = new double[arraySize];
		corr = new double[arraySize];
		diss = new double[arraySize];
		cont = new double[arraySize];
		asm = new double[arraySize];
		inv = new double[arraySize];
		idm = new double[arraySize];

		ip = (ByteProcessor) image.getProcessor();
		rect = image.getProcessor().getRoi();

		for (int i = getanguloF(); i > getanguloI(); i -= getanguloInc()) {
			matriz = CoOcorrencia(ip, rect, i, getdistanciaInc());
			int featureIndex = i / getanguloInc();
			entr[featureIndex] = ENT(matriz);
			idm[featureIndex] = IDM(matriz);
			diss[featureIndex] = DISS(matriz);
			corr[featureIndex] = CORR(matriz);
			cont[featureIndex] = CONT(matriz);
			asm[featureIndex] = ASM(matriz);
			inv[featureIndex] = INV(matriz);
		}
	}

	public double ENT(double[][] glcm) {
		double ENT = 0.0;

		for (int i = 0; i < 257; i++) {
			for (int j = 0; j < 257; j++) {
				if (glcm[i][j] != 0) {
					ENT -= glcm[i][j] * (Math.log(glcm[i][j]));
				}
			}
		}
		return ENT;
	}

	// Inverse Difference Moment
	public double IDM(double[][] glcm) {
		double IDM = 0.0;
		for (int i = 0; i < 257; i++) {
			for (int j = 0; j < 257; j++) {
				IDM = IDM + (glcm[i][j] / (1 + (i - j) * (i - j)));
			}
		}
		return IDM;
	}

	// Dissimilarity
	public double DISS(double[][] glcm) {
		double diss = 0.0;

		for (int i = 0; i < 257; i++) {
			for (int j = 0; j < 257; j++) {
				diss += (glcm[i][j] * Math.abs(i - j));
			}
		}

		return diss;
	}

	// Correlation
	// px [] and py [] are arrays to calculate the correlation
	// meanx and meany are variables to calculate the correlation
	// stdevx and stdevy are variables to calculate the correlation
	public double CORR(double[][] glcm) {
		// First step in the calculations will be to calculate px [] and py []
		double correlation = 0.0;
		double[] px = new double[257];
		double[] py = new double[257];
		double meanx = 0.0;
		double meany = 0.0;
		double stdevx = 0.0;
		double stdevy = 0.0;

		for (int i = 0; i < 257; i++) {
			for (int j = 0; j < 257; j++) {
				px[i] = px[i] + glcm[i][j];
				py[j] = py[j] + glcm[i][j];
			}
		}

		// Now calculate meanx and meany
		// Note that inside the loop meanx and meany are storing transient
		// values that are changed later
		for (int i = 0; i < 257; i++) {
			meanx = meanx + px[i];
			meany = meany + py[i];
		}

		// Here is the place where meanx and meany are calculated to their
		// definitive
		meanx = meanx / 257;
		meany = meany / 257;

		// Now calculate the standard deviations
		for (int i = 0; i < 257; i++) {
			stdevx = stdevx + ((px[i] - meanx) * (px[i] - meanx)) / 256;
			stdevy = stdevy + ((py[i] - meany) * (py[i] - meany)) / 256;
		}

		stdevx = Math.pow(stdevx, 0.5);
		stdevy = Math.pow(stdevy, 0.5);

		// Now finally calculate the correlation parameter
		for (int i = 0; i < 257; i++) {
			for (int j = 0; j < 257; j++) {
				correlation = correlation + ((i * j * glcm[i][j] - meanx * meany) / stdevx * stdevy);
			}
		}
		return correlation;
	}

	// Contrast
	public double CONT(double[][] glcm) {
		double contrast = 0.0;

		for (int i = 0; i < 257; i++) {
			for (int j = 0; j < 257; j++) {
				contrast = contrast + (i - j) * (i - j) * (glcm[i][j]);
			}
		}
		return contrast;
	}

	// Angular Second Moment
	// ASM == UNIFORMITY == ENERGY == HOMOGENEITY
	public double ASM(double[][] glcm) {
		double asm = 0.0;

		for (int i = 0; i < 257; i++) {
			for (int j = 0; j < 257; j++) {
				asm += (glcm[i][j] * glcm[i][j]);
			}
		}

		return asm;
	}

	// Inverse Diference
	public double INV(double[][] glcm) {
		double inv = 0.0;
		for (int i = 0; i < 257; i++) {
			for (int j = 0; j < 257; j++) {
				inv += (glcm[i][j] / (1 + Math.abs(i - j)));
			}
		}
		return inv;
	}

	// Faz uma matriz de co-ocorrencia de um determinado quadro de uma imagem.
	// ImageProcessor ip == imagem
	// As coordenadas do quadro sao passadas pela variavel Rectangle r.
	// int selectedStep == direcao : 0 , 90 , 180 , 270 .
	// in step distancia para a comparacao de um pixel ao outro
	// A imagem tem que estar em 8 bits
	public double[][] CoOcorrencia(ImageProcessor ip, Rectangle r, int selectedStep, int step) {
		// SelectedStep = Angulo
		// Step = Distancia

		int width = ip.getWidth();
		int offset, i;
		int a, b, y, x, C;
		double D, pixelCounter = 0;
		byte[] pixels = (byte[]) ip.getPixels();
		double glcm[][] = new double[257][257];

		// inicializa a matriz inteira com 0.
		for (a = 0; a < 257; a++) {
			for (b = 0; b < 257; b++) {
				glcm[a][b] = 0.0;
			}
		}

		a = b = 0;
		for (y = r.y; y < (r.y + r.height); y++) {
			offset = y * width;
			for (x = r.x; x < (r.x + r.width); x++) {
				i = offset + x;
				a = 0xff & pixels[i];
				D = Interpolation(y, x, selectedStep, step, ip);
				C = ip.getPixel(y, x);
				b = (int) Math.abs(C - D);

				glcm[a][b] = glcm[a][b] + 1;
				glcm[b][a] = glcm[b][a] + 1;

				pixelCounter += 2;
			}
		}

		for (a = 0; a < 257; a++) {
			for (b = 0; b < 257; b++) {
				glcm[a][b] = (glcm[a][b]) / (pixelCounter);
			}
		}
		return glcm;
	}

	double Interpolation(int i, int j, int a, int d, ImageProcessor IP2) {
		int x1, y1, z1, w1;
		double teste, e, b, c, f, x, y, z, w, J, K;
		double d1, d2, d3, d4, p1, p2, p3, p4, soma;

		x1 = y1 = z1 = w1 = 0;
		teste = e = b = c = f = x = y = z = w = J = K = 0.0;
		d1 = d2 = d3 = d4 = p1 = p2 = p3 = p4 = soma = 0.0;

		Rectangle ret = IP2.getRoi();

		K = i + (d * (Math.cos(a)));// cosseno
		J = j + (d * (Math.sin(a)));// seno
		e = x = Math.floor(K);// piso
		b = y = Math.ceil(K);// teto
		c = z = Math.floor(J);// piso
		f = w = Math.ceil(J);// teto

		// calculo da distancia
		d1 = Math.sqrt((Math.pow((K - x), 2)) + (Math.pow((J - z), 2)));
		d2 = Math.sqrt((Math.pow((K - x), 2)) + (Math.pow((J - w), 2)));
		d3 = Math.sqrt((Math.pow((K - y), 2)) + (Math.pow((J - z), 2)));
		d4 = Math.sqrt((Math.pow((K - y), 2)) + (Math.pow((J - w), 2)));

		soma = (d1 + d2 + d3 + d4);

		d2 = Math.sqrt((Math.pow((K - x), 2)) + (Math.pow((J - w), 2)));
		d3 = Math.sqrt((Math.pow((K - y), 2)) + (Math.pow((J - z), 2)));
		d4 = Math.sqrt((Math.pow((K - y), 2)) + (Math.pow((J - w), 2)));

		soma = (d1 + d2 + d3 + d4);

		if (soma == 0.0) {
			return 0.0;
		}

		// peso das intensidades
		p1 = (d1 / soma);
		p2 = (d2 / soma);
		p3 = (d3 / soma);
		p4 = (d4 / soma);

		x1 = (int) e;
		y1 = (int) b;
		z1 = (int) c;
		w1 = (int) f;

		if (x1 < ret.width && y1 < ret.width && z1 < ret.height && w1 < ret.height && x1 > -1 && y1 > -1 && z1 > -1 && w1 > -1) {
			teste = ((p1 * IP2.getPixel(x1, z1)) + (p2 * IP2.getPixel(x1, w1)) + (p3 * IP2.getPixel(y1, z1)) + (p4 * IP2.getPixel(y1, w1)));
		}

		return teste;
	}

	public int getanguloI() {
		return anguloI;
	}

	public int getanguloF() {
		return anguloF;
	}

	public int getanguloInc() {
		return anguloInc;
	}

	public int getdistanciaInc() {
		return distanciaInc;
	}

	@Override
	public HashSet<String> getAtributtesNames() {
		return attributeNames;
	}

}
