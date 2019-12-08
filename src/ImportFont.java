import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImportFont {

	private static Font baseFont = null;
	private static Font font = null;
	private static InputStream inputStream = null;

	public Font createFont(String fontName, int fontSize) {

		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			inputStream = loader.getResourceAsStream(fontName);
			baseFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			font = baseFont.deriveFont(Font.PLAIN, fontSize);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("Font not loaded.");
		}
		return font;
	}

}
