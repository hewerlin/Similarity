package net.poczone.similarity.metrics;

public class SingleFractionSimilarityMetric implements SimilarityMetric {
	@Override
	public double calculateSimilarity(int firstCount, int secondCount, int bothCount, int totalTargetCount) {
		if (firstCount > 0 && secondCount > 0) {
			return 2d * bothCount / (firstCount + secondCount);
		} else {
			return 0;
		}
	}
}
