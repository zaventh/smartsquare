package br.com.condesales.listeners;

import android.graphics.Bitmap;

/**
 * Interface to provide Image request status
 * 
 * @author Felipe Conde <condesales@gmail.com>
 * 
 */
public interface ImageRequestListener extends ErrorListener {
	public void onImageFetched(Bitmap bmp);
}