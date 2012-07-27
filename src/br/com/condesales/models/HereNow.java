package br.com.condesales.models;

import java.io.Serializable;

public class HereNow  implements Serializable{

	private static final long serialVersionUID = -2307624173279738888L;

	private int count;
	
	private String summary;
	
	private boolean marked;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCount() {
		return count;
	}

	public String getSummary() {
		return summary;
	}

	public boolean isMarked() {
		return marked;
	}
	
	
}
