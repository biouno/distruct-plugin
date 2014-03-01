package org.biouno.distruct;

import hudson.FilePath;
import hudson.model.Action;

public class DistructPublisherBuildAction implements Action {

	private final FilePath outputFile;

	public String getIconFileName() {
		return null;
	}

	public String getDisplayName() {
		return "Distruct results";
	}

	public String getUrlName() {
		return "distructResults";
	}
	
	public DistructPublisherBuildAction(FilePath outputFile) {
		this.outputFile = outputFile;
	}
	
	public FilePath getOutputFile() {
		return outputFile;
	}
	
	

}
