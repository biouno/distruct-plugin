package org.biouno.distruct;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.jenkinsci.remoting.Role;
import org.jenkinsci.remoting.RoleChecker;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.AbortException;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.remoting.Callable;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.util.ArgumentListBuilder;

/**
 * Distruct publisher.
 */
public class DistructPublisher extends Notifier {

	private final static String LINE_SEPARATOR = System.getProperty("line.separator");

	private final String distructInstallationName;

	private final String populationsFile;
	private final String individualsFile;
	private final String labelsFile;
	private final String languagesFile;
	private final String permutationsToPrintFile;
	private final String outputFile;

	private final String k;
	private final String numberOfPopulations;
	private final String numberOfIndividuals;

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
	private final Boolean echoData;
	private final Boolean reprintData;
	private final Boolean printFileName;
	private final Boolean printColorBrewer;

	@DataBoundConstructor
	public DistructPublisher(String distructInstallationName,
			String populationsFile, String individualsFile, String labelsFile,
			String languagesFile, String permutationsToPrintFile,
			String outputFile, String k, String numberOfPopulations,
			String numberOfIndividuals, Boolean printIndividuals,
			Boolean printLabelAtop, Boolean printLabelBelow,
			Boolean printSeparators, Double fontHeight, Double distanceAbove,
			Double distanceBelow, Double boxHeight, Double individualWidth,
			Integer orientation, Double xOrigin, Double yOrigin, Double xScale,
			Double yScale, Double angleLabelAtop, Double angleLabelBelow,
			Double rimLineWidth, Double separatorLineWidth,
			Double individualsLineWidth, Boolean useGrayscale,
			Boolean echoData, Boolean reprintData, Boolean printFileName,
			Boolean printColorBrewer) {
		super();
		this.distructInstallationName = distructInstallationName;
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
		this.echoData = echoData;
		this.reprintData = reprintData;
		this.printFileName = printFileName;
		this.printColorBrewer = printColorBrewer;
	}

	/**
	 * @return the distructInstallationName
	 */
	public String getDistructInstallationName() {
		return distructInstallationName;
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
	public String getK() {
		return k;
	}

	/**
	 * @return the numberOfPopulations
	 */
	public String getNumberOfPopulations() {
		return numberOfPopulations;
	}

	/**
	 * @return the numberOfIndividuals
	 */
	public String getNumberOfIndividuals() {
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
	 * @return the echoData
	 */
	public Boolean getEchoData() {
		return echoData;
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
		return BuildStepMonitor.NONE;
	}

	@Extension
	public static final DistructPublisherDescriptor DESCRIPTOR = new DistructPublisherDescriptor();

	private static final int DEFAULT_FONT_HEIGHT = 6;
	private static final double DEFAULT_DISTANCE_ABOVE = 5;
	private static final double DEFAULT_DISTANCE_BELOW = -7;
	private static final double DEFAULT_BOX_HEIGHT = 36;
	private static final double DEFAULT_INDIVIDUAL_WIDTH = 1.5;
	private static final int DEFAULT_ORIENTATION = 0;
	private static final double DEFAULT_XORIGIN = 72;
	private static final double DEFAULT_YORIGIN = 288;
	private static final double DEFAULT_XSCALE = 1;
	private static final double DEFAULT_YSCALE = 1;
	private static final double DEFAULT_ANGLE_LABEL_ATOP = 60;
	private static final double DEFAULT_ANGLE_LABEL_BELOW = 60;
	private static final double DEFAULT_RIM_LINE_WIDTH = 3;
	private static final double DEFAULT_SEPARATOR_LINE_WIDTH = 0.3;
	private static final double DEFAULT_INDIVIDUALS_LINE_WIDTH = 0.3;

	@Override
	public boolean perform(AbstractBuild<?, ?> build, Launcher launcher,
			BuildListener listener) throws InterruptedException, IOException {

		listener.getLogger().println("Distruct publisher.");

		// Get the distruct installation used
		final DistructInstallation distructInstallation = DESCRIPTOR
				.getInstallationByName(this.distructInstallationName);
		if (distructInstallation == null) {
			throw new AbortException("Invalid CLUMPP installation");
		}

		final EnvVars envVars = build.getEnvironment(listener);
		envVars.overrideAll(build.getBuildVariables());

		final String outputFile = envVars.expand(this.outputFile);

		final FilePath workspace = build.getWorkspace();
		final Map<String, String> env = build.getEnvironment(listener);

		listener.getLogger().println("Creating drawparams");

		final ArgumentListBuilder args = new ArgumentListBuilder();
		try {
			FilePath labelsFile = new FilePath(workspace, "labelsFileJenkins");
			labelsFile.write(this.labelsFile, "UTF-8");
			FilePath languagesFile = new FilePath(workspace,
					"languagesFileJenkins");
			languagesFile.write(this.languagesFile, "UTF-8");
			FilePath permutationsToPrintFile = new FilePath(workspace,
					"permutationsFileJenkins");
			permutationsToPrintFile
					.write(this.permutationsToPrintFile, "UTF-8");
			final FilePath drawParamsFilePath = createDrawParams(workspace,
					labelsFile.getName(), languagesFile.getName(),
					permutationsToPrintFile.getName(), envVars);
			args.add(distructInstallation.getPathToDistruct());
			args.add("-d");
			args.add(drawParamsFilePath.getName());
		} catch (IOException ioe) {
			ioe.printStackTrace(listener.getLogger());
			throw new AbortException("Failed to create drawparams: "
					+ ioe.getMessage());
		} catch (InterruptedException ie) {
			ie.printStackTrace(listener.getLogger());
			throw new AbortException("Failed to create drawparams: "
					+ ie.getMessage());
		}

		listener.getLogger()
				.println(
						"Executing distruct. Command args: "
								+ args.toStringWithQuote());
		final Integer exitCode = launcher.launch().cmds(args).envs(env)
				.stdout(listener).pwd(build.getModuleRoot()).join();

		// collect results
		if (exitCode != 1) {
			listener.getLogger().println(
					"Error executing distruct. Exit code: " + exitCode);
			return Boolean.FALSE;
		} else {
			FilePath outputFilePath = new FilePath(workspace, outputFile);

			String relativeName = new File(workspace.getRemote()).toURI().relativize(new File(outputFilePath.getRemote()).toURI()).getPath();
//			
//			final File parent = new File(outputFilePath.getParent().getRemote());
//			final String pdfName = outputFilePath.getBaseName() + ".pdf";
//			final File pdfFileName = new File(parent, pdfName).getAbsoluteFile();
//			listener.getLogger().println(String.format("Converting %s to PDF %s", outputFilePath.getRemote(), pdfName));
//			ArgumentListBuilder convertArgs = new ArgumentListBuilder();
//			convertArgs.add("ps2pdf");
//			convertArgs.add("-dPDFSETTINGS=/prepress");
//			convertArgs.add(outputFilePath.getRemote());
//			convertArgs.add(pdfFileName);
//			listener.getLogger().println("Executing ps2pdf. Command args: " + convertArgs.toStringWithQuote());
//			Integer convertExitCode = launcher.launch().cmds(convertArgs).envs(env).stdout(listener).pwd(build.getModuleRoot()).join();
//			if (convertExitCode == 0) {
//				listener.getLogger().println("Document converted to PDF");
//				
//				final CropCallable callable = new CropCallable(pdfFileName, listener);
//				launcher.getChannel().call(callable);
//			} else {
//				listener.getLogger().println("Could not convert document to PDF. Check if ps2pdf is in the PATH env var");
//			}
			
			// add action
			DistructPublisherBuildAction action = new DistructPublisherBuildAction(relativeName);
			build.addAction(action);
			return Boolean.TRUE;
		}
	}

	/**
	 * Callable to convert PS to PDF, PDF to JPEG and then crop it.
	 */
	private static class CropCallable implements Callable<Void, RuntimeException> {
		
		private static final long serialVersionUID = 1L;
		
		private File pdfFile;
		private final BuildListener listener;

		public CropCallable(File pdfFile, BuildListener listener) {
			this.pdfFile = pdfFile;
			this.listener = listener;
		}

		public Void call() throws RuntimeException {
			listener.getLogger().println("Cropping PDF blank area into new JPEG");

			final File jpegFile = new File(pdfFile.getParent(), FilenameUtils.removeExtension(pdfFile.getName()) + ".jpg");
			final File croppedFile = new File(pdfFile.getParent(), FilenameUtils.removeExtension(pdfFile.getName()) + "-cropped.jpg");
			
			CropPDF crop = new CropPDF();
			try {
				crop.cropToJPEG(
							pdfFile, 
							jpegFile, 
							croppedFile);
				listener.getLogger().println("Document cropped and converted to JPEG");
			} catch (Throwable e) {
				e.printStackTrace(listener.getLogger());
			}
			
			return null;
		}

        public void checkRoles(RoleChecker rc) throws SecurityException {
            rc.check(this, Role.UNKNOWN);
        }
	}

	/**
	 * Create distruct input file.
	 * @param workspace
	 * @param labelsFile
	 * @param languagesFile
	 * @param permutationsToPrintFile
	 * @param envVars
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private FilePath createDrawParams(FilePath workspace, String labelsFile,
			String languagesFile, String permutationsToPrintFile,
			EnvVars envVars) throws IOException, InterruptedException {
		final FilePath drawparams = new FilePath(workspace, "drawparamsJenkins");
		final StringBuilder sb = new StringBuilder();
		// params
		sb.append("#define INFILE_POPQ        " + populationsFile
				+ LINE_SEPARATOR);
		sb.append("#define INFILE_INDIVQ      " + individualsFile
				+ LINE_SEPARATOR);
		sb.append("#define INFILE_LABEL_BELOW " + labelsFile + LINE_SEPARATOR);
		sb.append("#define INFILE_LABEL_ATOP  " + languagesFile
				+ LINE_SEPARATOR);
		sb.append("#define INFILE_CLUST_PERM  " + permutationsToPrintFile
				+ LINE_SEPARATOR);
		sb.append("#define OUTFILE            " + outputFile + LINE_SEPARATOR);
		sb.append("#define K	" + k + LINE_SEPARATOR);
		sb.append("#define NUMPOPS " + numberOfPopulations + LINE_SEPARATOR);
		sb.append("#define NUMINDS " + numberOfIndividuals + LINE_SEPARATOR);
		sb.append("#define PRINT_INDIVS      " + getBoolean(printIndividuals)
				+ LINE_SEPARATOR);
		sb.append("#define PRINT_LABEL_ATOP  " + getBoolean(printLabelAtop)
				+ LINE_SEPARATOR);
		sb.append("#define PRINT_LABEL_BELOW " + getBoolean(printLabelBelow)
				+ LINE_SEPARATOR);
		sb.append("#define PRINT_SEP         " + getBoolean(printSeparators)
				+ LINE_SEPARATOR);
		sb.append("#define FONTHEIGHT "
				+ getDouble(fontHeight, DEFAULT_FONT_HEIGHT) + LINE_SEPARATOR);
		sb.append("#define DIST_ABOVE "
				+ getDouble(distanceAbove, DEFAULT_DISTANCE_ABOVE)
				+ LINE_SEPARATOR);
		sb.append("#define DIST_BELOW "
				+ getDouble(distanceBelow, DEFAULT_DISTANCE_BELOW)
				+ LINE_SEPARATOR);
		sb.append("#define BOXHEIGHT  "
				+ getDouble(boxHeight, DEFAULT_BOX_HEIGHT) + LINE_SEPARATOR);
		sb.append("#define INDIVWIDTH "
				+ getDouble(individualWidth, DEFAULT_INDIVIDUAL_WIDTH)
				+ LINE_SEPARATOR);
		// extra
		sb.append("#define ORIENTATION "
				+ getInteger(orientation, DEFAULT_ORIENTATION) + LINE_SEPARATOR);
		sb.append("#define XORIGIN " + getDouble(xOrigin, DEFAULT_XORIGIN)
				+ LINE_SEPARATOR);
		sb.append("#define YORIGIN " + getDouble(yOrigin, DEFAULT_YORIGIN)
				+ LINE_SEPARATOR);
		sb.append("#define XSCALE " + getDouble(xScale, DEFAULT_XSCALE)
				+ LINE_SEPARATOR);
		sb.append("#define YSCALE " + getDouble(yScale, DEFAULT_YSCALE)
				+ LINE_SEPARATOR);
		sb.append("#define ANGLE_LABEL_ATOP "
				+ getDouble(angleLabelAtop, DEFAULT_ANGLE_LABEL_ATOP)
				+ LINE_SEPARATOR);
		sb.append("#define ANGLE_LABEL_BELOW "
				+ getDouble(angleLabelBelow, DEFAULT_ANGLE_LABEL_BELOW)
				+ LINE_SEPARATOR);
		sb.append("#define LINEWIDTH_RIM  "
				+ getDouble(rimLineWidth, DEFAULT_RIM_LINE_WIDTH)
				+ LINE_SEPARATOR);
		sb.append("#define LINEWIDTH_SEP "
				+ getDouble(separatorLineWidth, DEFAULT_SEPARATOR_LINE_WIDTH)
				+ LINE_SEPARATOR);
		sb.append("#define LINEWIDTH_IND "
				+ getDouble(individualsLineWidth,
						DEFAULT_INDIVIDUALS_LINE_WIDTH) + LINE_SEPARATOR);
		sb.append("#define GRAYSCALE " + getBoolean(useGrayscale)
				+ LINE_SEPARATOR);
		sb.append("#define REPRINT_DATA " + getBoolean(reprintData)
				+ LINE_SEPARATOR);
		sb.append("#define PRINT_INFILE_NAME " + getBoolean(printFileName)
				+ LINE_SEPARATOR);
		sb.append("#define PRINT_COLOR_BREWER " + getBoolean(printColorBrewer)
				+ LINE_SEPARATOR);

		String content = envVars.expand(sb.toString());

		// end
		drawparams.write(content, "UTF-8");
		return drawparams;
	}

	private String getBoolean(Boolean variable) {
		if (null != variable) {
			return variable ? "1" : "0";
		}
		return "0";
	}

	private String getInteger(Integer variable, int defaultValue) {
		if (null != variable) {
			return Integer.toString(variable);
		}
		return Integer.toString(defaultValue);
	}

	private String getDouble(Double variable, double defaultValue) {
		if (null != variable) {
			return Double.toString(variable);
		}
		return Double.toString(defaultValue);
	}

}
