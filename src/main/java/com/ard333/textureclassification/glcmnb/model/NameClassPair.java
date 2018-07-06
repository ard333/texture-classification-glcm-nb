package com.ard333.textureclassification.glcmnb.model;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class NameClassPair {
	private String fileName;
	private String fileClass;

	public NameClassPair(String fileName, String fileClass) {
		this.fileName = fileName;
		this.fileClass = fileClass;
	}
	
	public String getFileName() {
		return fileName;
	}
	public String getFileClass() {
		return fileClass;
	}
}
