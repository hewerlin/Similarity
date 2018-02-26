package net.poczone.similarity;

import java.util.List;

import net.poczone.similarity.data.SimilaritySource;
import net.poczone.similarity.data.SimilarityStats;

public class Similarity {
	public static <User extends Comparable<User>, Target> SimilarityStats<User> calculateStats(
			SimilaritySource<User, Target> source) {
		SimilarityStats<User> stats = new SimilarityStats<>();

		for (Target target : source.getTargets()) {
			stats.addTarget();
			
			List<User> supporters = source.getSupporters(target);
			for (int i = 0; i < supporters.size(); i++) {
				User user1 = supporters.get(i);
				stats.add(user1);

				for (int j = 0; j < i; j++) {
					User user2 = supporters.get(j);
					stats.add(user1, user2);
				}
			}
		}

		return stats;
	}
}
