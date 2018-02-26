package net.poczone.similarity.metrics;

public class GeometricMeanFractionSimilarityMetric implements SimilarityMetric {
	@Override
	public double calculateSimilarity(int firstCount, int secondCount, int bothCount, int totalTargetCount) {
		if (firstCount > 0 && secondCount > 0) {
			return Math.sqrt(1d * bothCount / firstCount * bothCount / secondCount);
		} else {
			return 0;
		}
	}
}
