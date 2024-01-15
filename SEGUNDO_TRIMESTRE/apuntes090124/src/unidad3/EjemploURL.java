package unidad3;

import java.net.MalformedURLException;
import java.net.URL;

public class EjemploURL {
	
	public static void main(String[] args) {
		URL url;
		System.out.println("Constructor simple para una URL");
		try {
			url= new URL("https://www.youtube.com/watch?v=euOEE1lK410");
			Visualizar(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void Visualizar(URL url) {
		System.out.println("\tURL completa: " + url);
		System.out.println("\tgetProtocol(): "+ url.getProtocol());
		System.out.println("\tgetHost(): "+ url.getHost());
		System.out.println("\tgetDefaultPort()"+ url.getDefaultPort());
		System.out.println("\tgetPort(): "+url.getPort());
		System.out.println("\tgetPath(): "+url.getPath());
		System.out.println("\tgetQuery(): "+url.getQuery());
		System.out.println("\tgetFile(): "+url.getFile());
		System.out.println("\tgetAuthority(): "+url.getAuthority());
		System.out.println("===================================");
	}
	
}
