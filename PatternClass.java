package ro.usv.rf;

public class PatternClass 
{
	private double className;
	private double[] featureAverages;
	private int currentNumberOfPatterns;
	
	
	public PatternClass(int numberOfFeatures) {
		featureAverages = new double[numberOfFeatures];
	}
	public double getClassName() {
		return className;
	}
	public void setClassName(double className) {
		this.className = className;
	}
	public double[] getFeatureAverages() {
		return featureAverages;
	}
	public void setFeatureAverages(double[] featureAverages) {
		this.featureAverages = featureAverages;
	}
	public int getCurrentNumberOfPatterns() {
		return currentNumberOfPatterns;
	}
	public void setCurrentNumberOfPatterns(int currentNumberOfPatterns) {
		this.currentNumberOfPatterns = currentNumberOfPatterns;
	}
	
	
}
