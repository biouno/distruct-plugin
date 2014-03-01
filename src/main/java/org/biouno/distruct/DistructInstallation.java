package org.biouno.distruct;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;

public class DistructInstallation implements Serializable {

	private static final long serialVersionUID = 2735212329704530159L;
	
	private final String name;
	private final String pathToDistruct;
	
	@DataBoundConstructor
	public DistructInstallation(String name, String pathToDistruct) {
		this.name = name;
		this.pathToDistruct = pathToDistruct;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the pathToDistruct
	 */
	public String getPathToDistruct() {
		return pathToDistruct;
	}
	
}
