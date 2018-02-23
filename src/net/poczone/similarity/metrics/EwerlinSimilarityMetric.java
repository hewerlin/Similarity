package net.poczone.similarity.metrics;

public class EwerlinSimilarityMetric extends SimilarityMetric {
	@Override
	public double calculateSimilarity(int firstCount, int secondCount, int bothCount) {
		if (firstCount == 0 || secondCount == 0) {
			return 0;
		}
		return Math.sqrt(1.0d * bothCount / firstCount * bothCount / secondCount);
	}
}
