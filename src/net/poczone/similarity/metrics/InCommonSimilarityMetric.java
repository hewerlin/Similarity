package net.poczone.similarity.metrics;

public class InCommonSimilarityMetric implements SimilarityMetric {
	@Override
	public double calculateSimilarity(int firstCount, int secondCount, int bothCount, int totalTargetCount) {
		return totalTargetCount > 0 ? 1d * bothCount / totalTargetCount : 0;
	}
}
