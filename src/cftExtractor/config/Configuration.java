package cftExtractor.config;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

	private List<String> attributes;

	public Configuration() {
		attributes = new ArrayList<String>();
	}
	
	public Configuration(boolean colorRGB, boolean colorHSB, boolean form, boolean coorelationMatrix,int angleIncrement, boolean textureLBP, boolean textureWavelets){
		attributes = new ArrayList<String>();
		if(colorRGB){
			attributes.add("RGB_R");
			attributes.add("RGB_G");
			attributes.add("RGB_B");
		}
		if(colorHSB){
			attributes.add("HSB_H");
			attributes.add("HSB_S");
			attributes.add("HSB_B");
		}
		if(form){
			attributes.add("Form_factor");
			attributes.add("Roundness");
			attributes.add("Compactness");
			attributes.add("Aspect_Ratio");
		} 
		if(coorelationMatrix){
			for(int i = 360; i > 0; i -= angleIncrement){
				attributes.add("ENTR"+String.valueOf(i));
				attributes.add("CORR"+String.valueOf(i));
				attributes.add("DISS"+String.valueOf(i));
				attributes.add("CONT"+String.valueOf(i));
				attributes.add("ASM"+String.valueOf(i));
				attributes.add("INV"+String.valueOf(i));
				attributes.add("IDM"+String.valueOf(i));
			}
		}
		if(textureLBP){
			attributes.add("LBP11");
			attributes.add("LBPROT11");
		}
		if(textureWavelets){
			for(int i = 1; i >= 4; i ++){
				attributes.add("QUARTER_"+String.valueOf(i)+"_ASM");
				attributes.add("QUARTER_"+String.valueOf(i)+"_CONT");
				attributes.add("QUARTER_"+String.valueOf(i)+"_CORR");
				attributes.add("QUARTER_"+String.valueOf(i)+"_IDM");
				attributes.add("QUARTER_"+String.valueOf(i)+"_ENT");
			}
		}
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

	public void addAttribute(String attribute) {
		this.attributes.add(attribute);
	}

	public boolean contain(String attribute) {
		for (String attributeName : attributes) {
			if (attributeName.equals(attribute)) {
				return true;
			}
		}
		return false;
	}

}
