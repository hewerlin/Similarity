package net.poczone.similarity.metrics;

import java.util.Comparator;

import net.poczone.similarity.data.SimilarityRankedPair;

public abstract class SimilarityMetric implements Comparator<SimilarityRankedPair<?>> {
	@Override
	public int compare(SimilarityRankedPair<?> o1, SimilarityRankedPair<?> o2) {
		return Double.compare(calculateSimilarity(o2), calculateSimilarity(o1));
	}

	public double calculateSimilarity(SimilarityRankedPair<?> entry) {
		return calculateSimilarity(entry.getFirstCount(), entry.getSecondCount(), entry.getBothCount());
	}

	public abstract double calculateSimilarity(int firstCount, int secondCount, int bothCount);
}
