package net.poczone.similarity.metrics;

/**
 * @see https://de.wikipedia.org/wiki/Korrelationskoeffizient
 */
public class PearsonCorrelationSimilarityMetric implements SimilarityMetric {
	@Override
	public double calculateSimilarity(int firstCount, int secondCount, int bothCount, int totalTargetCount) {
		if (firstCount > 0 && secondCount > 0 && firstCount < totalTargetCount && secondCount < totalTargetCount) {
			double numerator = 1d * totalTargetCount * bothCount - 1d * firstCount * secondCount;
			double denominator = Math.sqrt(
					1d * firstCount * (totalTargetCount - firstCount) * secondCount * (totalTargetCount - secondCount));

			return 0.5d + 0.5d * numerator / denominator;
		} else {
			return 0;
		}
	}
}
