package org.biouno.distruct;

import hudson.CopyOnWrite;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import hudson.util.FormValidation;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

public class DistructPublisherDescriptor  extends BuildStepDescriptor<Publisher> {
	
	public final Class<DistructPublisher> builderType = DistructPublisher.class;
	
	private static final String DISPLAY_NAME = "Invoke distruct";
	
	@CopyOnWrite
	private volatile DistructInstallation[] installations = new DistructInstallation[0];
	
	/**
	 * No args constructor to ensure the descriptor pattern.
	 */
	public DistructPublisherDescriptor() {
		super(DistructPublisher.class);
		load();
	}
	
	/* (non-Javadoc)
	 * @see hudson.model.Descriptor#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}
	
	public DistructInstallation[] getInstallations() {
		return this.installations;
	}
	
	public DistructInstallation getInstallationByName(String name) {
		DistructInstallation found = null;
		for(DistructInstallation installation : this.installations) {
			if (StringUtils.isNotEmpty(installation.getName())) {
				if(name.equals(installation.getName())) {
					found = installation;
					break;
				}
			}
		}
		return found;
	}
	
	/* (non-Javadoc)
	 * @see hudson.model.Descriptor#configure(org.kohsuke.stapler.StaplerRequest, net.sf.json.JSONObject)
	 */
	@Override
	public boolean configure(StaplerRequest req, JSONObject json)
			throws hudson.model.Descriptor.FormException {
		this.installations = req.bindParametersToList(DistructInstallation.class, "distruct.").toArray(new DistructInstallation[0]);
		save();
		return Boolean.TRUE;
	}
	
	@Override
	public Publisher newInstance(StaplerRequest arg0, JSONObject arg1)
			throws hudson.model.Descriptor.FormException {
		return super.newInstance(arg0, arg1);
	}
	
	/**
	 * Validates required fields.
	 * @param value the value
	 * @return FormValidation
	 */
	public FormValidation doRequired(@QueryParameter String value) {
		FormValidation returnValue = FormValidation.ok();
		if(StringUtils.isBlank(value)) {
			returnValue = FormValidation.error("This parameter is required");
		}
		return returnValue;
	}
	/**
	 * Validates required long fields.
	 * @param value the value
	 * @return FormValidation
	 */
	public FormValidation doLongRequired(@QueryParameter String value) {
		FormValidation returnValue = FormValidation.ok();
		if(StringUtils.isNotBlank(value)) {
			try {
				Long.parseLong(value);
			} catch ( NumberFormatException nfe ) {
				returnValue = FormValidation.error("This value must be an integer");
			}
		}
		return returnValue;
	}
	/**
	 * Validates required double fields.
	 * @param value the value
	 * @return FormValidation
	 */
	public FormValidation doDoubleRequired(@QueryParameter String value) {
		FormValidation returnValue = FormValidation.ok();
		if(StringUtils.isNotBlank(value)) {
			try {
				Double.parseDouble(value);
			} catch ( NumberFormatException nfe ) {
				returnValue = FormValidation.error("This value must be an float");
			}
		}
		return returnValue;
	}

	@Override
	public boolean isApplicable(@SuppressWarnings("rawtypes") Class<? extends AbstractProject> jobType) {
		return true;
	}

}
