package org.biouno.distruct;

import hudson.model.Action;

public class DistructPublisherBuildAction implements Action {

	private final String relativeName;

	public String getIconFileName() {
		return null;
	}

	public String getDisplayName() {
		return "Distruct results";
	}

	public String getUrlName() {
		return "distructResults";
	}
	
	public DistructPublisherBuildAction(String relativeName) {
		this.relativeName = relativeName;
	}

	public String getRelativeName() {
		return relativeName;
	}
	
}
