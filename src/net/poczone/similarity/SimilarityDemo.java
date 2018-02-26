package net.poczone.similarity;

import java.util.List;

import net.poczone.similarity.data.ArraySimilaritySource;
import net.poczone.similarity.data.SimilarityRankedPair;
import net.poczone.similarity.data.SimilaritySource;
import net.poczone.similarity.data.SimilarityStats;
import net.poczone.similarity.metrics.GeometricMeanFractionSimilarityMetric;
import net.poczone.similarity.metrics.InCommonSimilarityMetric;
import net.poczone.similarity.metrics.PearsonCorrelationSimilarityMetric;
import net.poczone.similarity.metrics.SimilarityMetric;
import net.poczone.similarity.metrics.SingleFractionSimilarityMetric;

public class SimilarityDemo {
	public static void main(String[] args) {
		final String[][] groups = new String[][] {

				{ "HEE", "MAB" },

				{ "HEE", "MAB", "MBU", "LAB", "DIW" },

				{ "HEE" },

				{ "HEE", "MAB" },

				{ "MBU", "LAB" },

				{ "MBU" },

				{ "MBU", "DIW" },

				{ "NIZ" },

				{ "NIZ", "ELZ" },

				{ "NIZ", "ELZ" },

				{ "HEE" },

				{ "MBU", "THW" },

				{ "MBU", "SAK", "KIS" } };

		SimilarityMetric[] metrics = new SimilarityMetric[] {

				new GeometricMeanFractionSimilarityMetric(),

				new SingleFractionSimilarityMetric(),

				new PearsonCorrelationSimilarityMetric(),

				new InCommonSimilarityMetric()

		};

		SimilaritySource<String, Integer> source = new ArraySimilaritySource<>(groups);

		String user = null; // "HEE";
		SimilarityStats<String> stats = Similarity.calculateStats(source);

		for (SimilarityMetric metric : metrics) {
			List<SimilarityRankedPair<String>> rankedPairs = stats.getRankedPairs(user, metric);

			System.out.println(metric.getClass().getSimpleName());
			for (SimilarityRankedPair<String> pair : rankedPairs) {
				System.out.println("\t" + pair);
			}
		}
	}
}
