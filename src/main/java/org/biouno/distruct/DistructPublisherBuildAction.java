package org.biouno.distruct;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import hudson.FilePath;
import hudson.model.Action;

public class DistructPublisherBuildAction implements Action {

	private final FilePath outputFile;
	private final FilePath jpg;

	public String getIconFileName() {
		return null;
	}

	public String getDisplayName() {
		return "Distruct results";
	}

	public String getUrlName() {
		return "distructResults";
	}
	
	public DistructPublisherBuildAction(FilePath outputFile, FilePath jpg) {
		this.outputFile = outputFile;
		this.jpg = jpg;
	}
	
	public FilePath getOutputFile() {
		return outputFile;
	}
	
	public void doGraph(StaplerRequest request, StaplerResponse response) throws IOException {
		response.setContentType("image/jpeg");
		File f = new File(jpg.getRemote());
		BufferedImage bi = ImageIO.read(f);
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
	}

}
