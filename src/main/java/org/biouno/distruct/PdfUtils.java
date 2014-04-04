package org.biouno.distruct;

import hudson.FilePath;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.ghost4j.converter.ConverterException;
import org.ghost4j.converter.PDFConverter;
import org.ghost4j.document.DocumentException;
import org.ghost4j.document.PSDocument;

public class PdfUtils {

	// Crop PDF methods

	public static void cropToJPEG(File pdf, File jpeg, File croppedPdf)
			throws IOException {
		OutputStream outputStream = null;
		OutputStream modifiedStream = null;

		try {
			outputStream = new FileOutputStream(jpeg);
			modifiedStream = new FileOutputStream(croppedPdf);

			PDDocument doc = PDDocument.load(pdf);
			@SuppressWarnings("unchecked")
			// API specifics
			List<PDPage> pages = (List<PDPage>) doc.getDocumentCatalog()
					.getAllPages();
			PDPage page = pages.get(0);
			// 24 bit image, 100dpi:
			BufferedImage image = page.convertToImage(
					BufferedImage.TYPE_3BYTE_BGR, 100);
			ImageIO.write(image, "jpg", outputStream);

			BufferedImage image2 = getCroppedImage(image, 0.0);
			ImageIO.write(image2, "jpg", modifiedStream);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (modifiedStream != null) {
				try {
					modifiedStream.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	// From:
	// http://stackoverflow.com/questions/10678015/how-to-auto-crop-an-image-white-border-in-java
	public static BufferedImage getCroppedImage(BufferedImage source,
			double tolerance) {
		// Get our top-left pixel color as our "baseline" for cropping
		int baseColor = source.getRGB(0, 0);

		int width = source.getWidth();
		int height = source.getHeight();

		int topY = Integer.MAX_VALUE, topX = Integer.MAX_VALUE;
		int bottomY = -1, bottomX = -1;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (colorWithinTolerance(baseColor, source.getRGB(x, y),
						tolerance)) {
					if (x < topX)
						topX = x;
					if (y < topY)
						topY = y;
					if (x > bottomX)
						bottomX = x;
					if (y > bottomY)
						bottomY = y;
				}
			}
		}

		BufferedImage destination = new BufferedImage((bottomX - topX + 1),
				(bottomY - topY + 1), BufferedImage.TYPE_3BYTE_BGR);

		destination.getGraphics().drawImage(source, 0, 0,
				destination.getWidth(), destination.getHeight(), topX, topY,
				bottomX, bottomY, null);

		return destination;
	}

	private static boolean colorWithinTolerance(int a, int b, double tolerance) {
		int aAlpha = (int) ((a & 0xFF000000) >>> 24); // Alpha level
		int aRed = (int) ((a & 0x00FF0000) >>> 16); // Red level
		int aGreen = (int) ((a & 0x0000FF00) >>> 8); // Green level
		int aBlue = (int) (a & 0x000000FF); // Blue level

		int bAlpha = (int) ((b & 0xFF000000) >>> 24); // Alpha level
		int bRed = (int) ((b & 0x00FF0000) >>> 16); // Red level
		int bGreen = (int) ((b & 0x0000FF00) >>> 8); // Green level
		int bBlue = (int) (b & 0x000000FF); // Blue level

		double distance = Math.sqrt((aAlpha - bAlpha) * (aAlpha - bAlpha)
				+ (aRed - bRed) * (aRed - bRed) + (aGreen - bGreen)
				* (aGreen - bGreen) + (aBlue - bBlue) * (aBlue - bBlue));

		// 510.0 is the maximum distance between two colors
		// (0,0,0,0 -> 255,255,255,255)
		double percentAway = distance / 510.0d;

		return (percentAway > tolerance);
	}

	// http://www.ghost4j.org/highlevelapisamples.html
	public static void ps2pdf(File ps, File pdf) throws IOException,
			DocumentException, ConverterException {
		FileOutputStream fos = null;
		try {

			// load PostScript document
			PSDocument document = new PSDocument();
			document.load(ps);

			// create OutputStream
			fos = new FileOutputStream(pdf);

			// create converter
			PDFConverter converter = new PDFConverter();

			// set options
			converter.setPDFSettings(PDFConverter.OPTION_PDFSETTINGS_PREPRESS);

			// convert
			converter.convert(document, fos);

		} finally {
			IOUtils.closeQuietly(fos);
		}
	}
	
	public static void main(String[] args) throws Exception {
		PdfUtils.ps2pdf(
				new File("/home/kinow/java/biouno/distruct-plugin/work/jobs/test-distruct/workspace/structure-pipeline.ps"), 
				new File("/tmp/converted.pdf"));
	}

	public static void main2(String[] args) throws Exception {
		PdfUtils.cropToJPEG(
				new File(
						"/home/kinow/java/biouno/distruct-plugin/work/jobs/test-distruct/workspace/structure-pipeline.ps"),
				new File("/tmp/cropped.jpg"), new File("/tmp/cropped.pdf"));
	}

}
