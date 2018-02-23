package net.poczone.similarity;

import java.util.List;

import net.poczone.similarity.data.ArraySimilaritySource;
import net.poczone.similarity.data.SimilarityRankedPair;
import net.poczone.similarity.data.SimilaritySource;

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

		SimilaritySource<String, Integer> source = new ArraySimilaritySource<>(groups);

		String user = null; // "HEE";
		List<SimilarityRankedPair<String>> rankedPairs = Similarity.calculateStats(source).getRankedPairs(user);

		for (SimilarityRankedPair<String> pair : rankedPairs) {
			System.out.println(pair);
		}
	}
}
