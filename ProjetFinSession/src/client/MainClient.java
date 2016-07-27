package client;


import client.ui.JFrameConnectionServeur;

public class MainClient {
	
	

	public static void main(String[] args) {
		
		JFrameConnectionServeur frame = new JFrameConnectionServeur();
		frame.setVisible(true);
		
	}
	
	public static String encrypt(String md5) {
	   try {
		   	md5 = "un)Peu[De.Sel"+md5+"et?Du*Poivre"+md5+"!";
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(md5.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	        }
	        return sb.toString();
	    } catch (java.security.NoSuchAlgorithmException e) {
	    }
	    return null;
	}
	
}
