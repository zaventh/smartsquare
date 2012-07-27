package br.com.condesales.models;

import java.util.ArrayList;

public class Score {

	private int total;

	private ArrayList<ScoreItem> scores;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ArrayList<ScoreItem> getScores() {
		return scores;
	}

	public void setScores(ArrayList<ScoreItem> scores) {
		this.scores = scores;
	}

}
