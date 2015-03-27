package cftExtractor.models;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageSet {

	private String imagesFolderPath;
	private List<String> classes = new ArrayList<String>();
	private List<List<String>> images = new ArrayList<List<String>>();

	public ImageSet() {
	}

	public ImageSet(String imageFolderPath) {
		this.imagesFolderPath = imageFolderPath;
		this.classes = readClassesName();
		this.images = readImageFilesPath();
	}

	public void addImage(String clazz, String image) {
		List<String> images = getImagesFromClass(clazz);
		images.add(image);
	}

	public void addImages(String clazz, List<String> image) {
		List<String> images = getImagesFromClass(clazz);
		images.addAll(image);
	}

	public List<String> getImagesFromClass(String clazz) {
		if (!classes.contains(clazz)) {
			classes.add(clazz);
			images.add(classes.indexOf(clazz), new ArrayList<String>());
		}
		return images.get(classes.indexOf(clazz));
	}

	private FilenameFilter filterForHiddenFiles = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			return !name.startsWith(".");
		}
	};

	private List<List<String>> readImageFilesPath() {
		List<List<String>> imagesPaths = new ArrayList<List<String>>();

		for (int i = 0; i < getTotalClasses(); ++i) {
			String filePath = imagesFolderPath + "/" + getClassName(i);
			File dir = new File(filePath);
			String[] imagesNames = dir.list(filterForHiddenFiles);
			Arrays.sort(imagesNames);
			imagesPaths.add(Arrays.asList(imagesNames));
		}

		return imagesPaths;
	}

	private List<String> readClassesName() {
		File directory = new File(imagesFolderPath);
		System.out.println("Caminho do root do imageSet: ");
		System.out.println(directory.getAbsolutePath());
		String[] list = directory.list(filterForHiddenFiles);
		Arrays.sort(list);
		return Arrays.asList(list);
	}

	public int getTotalClasses() {
		return classes.size();
	}

	public String getClassName(int i) {
		return classes.get(i);
	}

	public int getTotalImagesForClass(int i) {
		return images.get(i).size();

	}

	public String getImageName(int indiceClasse, int indiceImagem) {
		return imagesFolderPath + "/" + getClassName(indiceClasse) + "/" + images.get(indiceClasse).get(indiceImagem);
	}

	public String getImagesFolderPath() {
		return imagesFolderPath;
	}

	public void setImagesFolderPath(String imagesFolderPath) {
		this.imagesFolderPath = imagesFolderPath;
	}

	public List<String> getClasses() {
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
	}

	public List<List<String>> getImages() {
		return images;
	}

	public void setImages(List<List<String>> images) {
		this.images = images;
	}

}
