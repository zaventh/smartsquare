package br.com.condesales.models;

public class Icon {

	private String prefix;

	private String name;

	private int[] sizes;

	private String suffix;

	public String getSuffix() {
		return suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getSizes() {
		return sizes;
	}

	public void setSizes(int[] sizes) {
		this.sizes = sizes;
	}

}
