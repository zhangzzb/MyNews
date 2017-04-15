package com.zzb.mynew.inter;

/**
 * 监听webview上的图片
 *
 */
public interface OnWebViewImageListener {

	/**
	 * 点击webview上的图片，传入该缩略图的位置
	 * @param index
	 */
	void showImagePreview(int index);
	
}
