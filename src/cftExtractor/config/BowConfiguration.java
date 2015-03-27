package cftExtractor.config;

public class BowConfiguration {
	private String dataSetName, detectorType, descriptorType, matcherType;
	private int incrementDictionarySize, kindOfIncrement, defaultDictionarySize;
	private double hessianThreshold;
	
	public BowConfiguration(){
		this.dataSetName = "teste_soja";
		this.incrementDictionarySize = 1;
		this.kindOfIncrement = 1;
		this.hessianThreshold = 0.000001;
		this.defaultDictionarySize = 256;
		this.detectorType = "SURF";
		this.descriptorType = "SURF";
		this.matcherType = "FlannBased";
	}
	
	public BowConfiguration(double hessianThreshold, int defaultDictionarySize, String dataSetName){
		this.dataSetName = dataSetName;
		this.hessianThreshold = hessianThreshold;
		this.defaultDictionarySize = defaultDictionarySize;
		this.incrementDictionarySize = 1;
		this.kindOfIncrement = 1;
		this.detectorType = "SURF";
		this.descriptorType = "SURF";
		this.matcherType = "FlannBased";
	}
	
	public String getDataSetName() {
		return dataSetName;
	}
	
	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}
	
	public String getDetectorType() {
		return detectorType;
	}
	
	public void setDetectorType(String detectorType) {
		this.detectorType = detectorType;
	}
	
	public String getDescriptorType() {
		return descriptorType;
	}
	
	public void setDescriptorType(String descriptorType) {
		this.descriptorType = descriptorType;
	}
	
	public String getMatcherType() {
		return matcherType;
	}
	
	public void setMatcherType(String matcherType) {
		this.matcherType = matcherType;
	}
	
	public int getIncrementDictionarySize() {
		return incrementDictionarySize;
	}
	
	public void setIncrementDictionarySize(int incrementDictionarySize) {
		this.incrementDictionarySize = incrementDictionarySize;
	}
	
	public int getKindOfIncrement() {
		return kindOfIncrement;
	}
	
	public void setKindOfIncrement(int kindOfIncrement) {
		this.kindOfIncrement = kindOfIncrement;
	}
	
	public int getDefaultDictionarySize() {
		return defaultDictionarySize;
	}
	
	public void setDefaultDictionarySize(int defaultDictionarySize) {
		this.defaultDictionarySize = defaultDictionarySize;
	}
	
	public double getHessianThreshold() {
		return hessianThreshold;
	}
	
	public void setHessianThreshold(float hessianThreshold) {
		this.hessianThreshold = hessianThreshold;
	}
}
