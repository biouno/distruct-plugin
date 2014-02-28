package org.biouno.distruct;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;

import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;

/**
 * Distruct publisher.
 */
public class DistructPublisher extends Notifier {

    private final String populationsFile;
    private final String individualsFile;
    private final String labelsFile;
    private final String languagesFile;
    private final String permutationsToPrintFile;
    private final String outputFile;
    
    private final Integer k;
    private final Integer numberOfPopulations;
    private final Integer numberOfIndividuals;
    
    private final Boolean printIndividuals;
    private final Boolean printLabelAtop;
    private final Boolean printLabelBelow;
    private final Boolean printSeparators;
    
    private final Double fontHeight;
    private final Double distanceAbove;
    private final Double distanceBelow;
    private final Double boxHeight;
    private final Double individualWidth;
    
    // extra
    private final Integer orientation;
    private final Double xOrigin;
    private final Double yOrigin;
    private final Double xScale;
    private final Double yScale;
    private final Double angleLabelAtop;
    private final Double angleLabelBelow;
    private final Double rimLineWidth;
    private final Double separatorLineWidth;
    private final Double individualsLineWidth;
    private final Boolean useGrayscale;
    private final Boolean echoDate;
    private final Boolean reprintData;
    private final Boolean printFileName;
    private final Boolean printColorBrewer;

    @DataBoundConstructor
    public DistructPublisher(String populationsFile, String individualsFile,
			String labelsFile, String languagesFile,
			String permutationsToPrintFile, String outputFile, Integer k,
			Integer numberOfPopulations, Integer numberOfIndividuals,
			Boolean printIndividuals, Boolean printLabelAtop,
			Boolean printLabelBelow, Boolean printSeparators,
			Double fontHeight, Double distanceAbove, Double distanceBelow,
			Double boxHeight, Double individualWidth, Integer orientation,
			Double xOrigin, Double yOrigin, Double xScale, Double yScale,
			Double angleLabelAtop, Double angleLabelBelow, Double rimLineWidth,
			Double separatorLineWidth, Double individualsLineWidth,
			Boolean useGrayscale, Boolean echoDate, Boolean reprintData,
			Boolean printFileName, Boolean printColorBrewer) {
		super();
		this.populationsFile = populationsFile;
		this.individualsFile = individualsFile;
		this.labelsFile = labelsFile;
		this.languagesFile = languagesFile;
		this.permutationsToPrintFile = permutationsToPrintFile;
		this.outputFile = outputFile;
		this.k = k;
		this.numberOfPopulations = numberOfPopulations;
		this.numberOfIndividuals = numberOfIndividuals;
		this.printIndividuals = printIndividuals;
		this.printLabelAtop = printLabelAtop;
		this.printLabelBelow = printLabelBelow;
		this.printSeparators = printSeparators;
		this.fontHeight = fontHeight;
		this.distanceAbove = distanceAbove;
		this.distanceBelow = distanceBelow;
		this.boxHeight = boxHeight;
		this.individualWidth = individualWidth;
		this.orientation = orientation;
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.xScale = xScale;
		this.yScale = yScale;
		this.angleLabelAtop = angleLabelAtop;
		this.angleLabelBelow = angleLabelBelow;
		this.rimLineWidth = rimLineWidth;
		this.separatorLineWidth = separatorLineWidth;
		this.individualsLineWidth = individualsLineWidth;
		this.useGrayscale = useGrayscale;
		this.echoDate = echoDate;
		this.reprintData = reprintData;
		this.printFileName = printFileName;
		this.printColorBrewer = printColorBrewer;
	}

	/**
	 * @return the populationsFile
	 */
	public String getPopulationsFile() {
		return populationsFile;
	}

	/**
	 * @return the individualsFile
	 */
	public String getIndividualsFile() {
		return individualsFile;
	}

	/**
	 * @return the labelsFile
	 */
	public String getLabelsFile() {
		return labelsFile;
	}

	/**
	 * @return the languagesFile
	 */
	public String getLanguagesFile() {
		return languagesFile;
	}

	/**
	 * @return the permutationsToPrintFile
	 */
	public String getPermutationsToPrintFile() {
		return permutationsToPrintFile;
	}

	/**
	 * @return the outputFile
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * @return the k
	 */
	public Integer getK() {
		return k;
	}

	/**
	 * @return the numberOfPopulations
	 */
	public Integer getNumberOfPopulations() {
		return numberOfPopulations;
	}

	/**
	 * @return the numberOfIndividuals
	 */
	public Integer getNumberOfIndividuals() {
		return numberOfIndividuals;
	}

	/**
	 * @return the printIndividuals
	 */
	public Boolean getPrintIndividuals() {
		return printIndividuals;
	}

	/**
	 * @return the printLabelAtop
	 */
	public Boolean getPrintLabelAtop() {
		return printLabelAtop;
	}

	/**
	 * @return the printLabelBelow
	 */
	public Boolean getPrintLabelBelow() {
		return printLabelBelow;
	}

	/**
	 * @return the printSeparators
	 */
	public Boolean getPrintSeparators() {
		return printSeparators;
	}

	/**
	 * @return the fontHeight
	 */
	public Double getFontHeight() {
		return fontHeight;
	}

	/**
	 * @return the distanceAbove
	 */
	public Double getDistanceAbove() {
		return distanceAbove;
	}

	/**
	 * @return the distanceBelow
	 */
	public Double getDistanceBelow() {
		return distanceBelow;
	}

	/**
	 * @return the boxHeight
	 */
	public Double getBoxHeight() {
		return boxHeight;
	}

	/**
	 * @return the individualWidth
	 */
	public Double getIndividualWidth() {
		return individualWidth;
	}

	/**
	 * @return the orientation
	 */
	public Integer getOrientation() {
		return orientation;
	}

	/**
	 * @return the xOrigin
	 */
	public Double getxOrigin() {
		return xOrigin;
	}

	/**
	 * @return the yOrigin
	 */
	public Double getyOrigin() {
		return yOrigin;
	}

	/**
	 * @return the xScale
	 */
	public Double getxScale() {
		return xScale;
	}

	/**
	 * @return the yScale
	 */
	public Double getyScale() {
		return yScale;
	}

	/**
	 * @return the angleLabelAtop
	 */
	public Double getAngleLabelAtop() {
		return angleLabelAtop;
	}

	/**
	 * @return the angleLabelBelow
	 */
	public Double getAngleLabelBelow() {
		return angleLabelBelow;
	}

	/**
	 * @return the rimLineWidth
	 */
	public Double getRimLineWidth() {
		return rimLineWidth;
	}

	/**
	 * @return the separatorLineWidth
	 */
	public Double getSeparatorLineWidth() {
		return separatorLineWidth;
	}

	/**
	 * @return the individualsLineWidth
	 */
	public Double getIndividualsLineWidth() {
		return individualsLineWidth;
	}

	/**
	 * @return the useGrayscale
	 */
	public Boolean getUseGrayscale() {
		return useGrayscale;
	}

	/**
	 * @return the echoDate
	 */
	public Boolean getEchoDate() {
		return echoDate;
	}

	/**
	 * @return the reprintData
	 */
	public Boolean getReprintData() {
		return reprintData;
	}

	/**
	 * @return the printFileName
	 */
	public Boolean getPrintFileName() {
		return printFileName;
	}

	/**
	 * @return the printColorBrewer
	 */
	public Boolean getPrintColorBrewer() {
		return printColorBrewer;
	}

	public BuildStepMonitor getRequiredMonitorService() {
		return null;
	}
	
	@Override
	public boolean perform(AbstractBuild<?,?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
		return super.perform(build, launcher, listener);
	}

}

