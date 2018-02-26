package net.poczone.similarity.metrics;

@FunctionalInterface
public interface SimilarityMetric {
	double calculateSimilarity(int firstCount, int secondCount, int bothCount, int totalTargetCount);
}
