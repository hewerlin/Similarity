package net.poczone.similarity.metrics;

public class InCommonMetric extends SimilarityMetric {
	@Override
	public double calculateSimilarity(int firstCount, int secondCount, int bothCount) {
		return bothCount;
	}
}
