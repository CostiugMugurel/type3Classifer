package ro.usv.rf;

import java.util.HashMap;
import java.util.Map;
import  ro.usv.rf.FileUtils;
public class MainClass {

	public static void main(String[] args)
	{
		// read learning set from file
		try {
		Map<Double, PatternClass> patternClassMap = new HashMap<Double, PatternClass>();
		//double[][] learningSet = new double[3][3];
		double[][] learningSet = FileUtils.readLearningSetFromFile("C:\\Users\\flore\\eclipse-workspace\\Type3Classifier\\in.txt");
		
		int numberOfFeatures = learningSet[0].length -1;
		// iterate learning set on each row		
		
		for (int k = 0; k < learningSet.length; k++)
		{
			double[] learningSetRow = learningSet[k];
			Double patternClass = learningSetRow[numberOfFeatures];
			PatternClass pc;
			
			if (patternClassMap.containsKey(patternClass)) {
				pc = patternClassMap.get(patternClass);
				double[] averages = pc.getFeatureAverages();
				int oldNumberOfPatternsProcessed = pc.getCurrentNumberOfPatterns();

				for (int i = 0; i < averages.length; i++) {
					averages[i] = ((averages[i] * oldNumberOfPatternsProcessed) + learningSetRow[i])
							/ (oldNumberOfPatternsProcessed + 1);
					pc.setCurrentNumberOfPatterns(oldNumberOfPatternsProcessed + 1);
					pc.setFeatureAverages(averages);
				}
			} 
			else {
				pc = new PatternClass(numberOfFeatures);
				double[] averages = pc.getFeatureAverages();
				for (int i = 0; i < averages.length; i++) {
					averages[i] = learningSetRow[i];
				}
				pc.setCurrentNumberOfPatterns(1);
				pc.setFeatureAverages(averages);

			}
			patternClassMap.put(patternClass, pc);
		}
		double[][] weightsMatrix = createWeightsMatrix(patternClassMap, numberOfFeatures);
		System.out.println();
		for (int i = 0; i < weightsMatrix.length; i++) {
			System.out.println();
			for (int j = 0; j < weightsMatrix[0].length; j++) {
				System.out.print(weightsMatrix[i][j]+ " ");
			}
			
		}
		}
		catch (Exception e) {
			System.out.println("errr");
			e.printStackTrace();
		}
	}
	
	private static double[][] createWeightsMatrix(Map<Double, PatternClass> patternClassMap, int numberOfFeatures)
	{
		double[][] weightsMatrix = new double[patternClassMap.entrySet().size()][numberOfFeatures+1];
		int lineCounter = 0;
		for (PatternClass patternClass : patternClassMap.values())
		{
			double[] averages = patternClass.getFeatureAverages();
			for (int k = 0 ; k < averages.length; k++)
			{
				weightsMatrix[lineCounter][k] = averages[k];
			}
			
			double lastTerm = 0; // caluclate last term
			System.out.println(lineCounter);
			System.out.println(averages.length+1);
			weightsMatrix[lineCounter][averages.length] = lastTerm;
			lineCounter++;
		}
		return weightsMatrix;
	}
}
