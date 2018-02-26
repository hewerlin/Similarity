package net.poczone.similarity.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.poczone.similarity.metrics.GeometricMeanFractionSimilarityMetric;
import net.poczone.similarity.metrics.SimilarityMetric;

public class SimilarityStats<User extends Comparable<User>> {
	private static final SimilarityMetric DEFAULT_METRIC = new GeometricMeanFractionSimilarityMetric();

	private int totalTargetCount;
	private Map<User, Integer> singleCounts = new HashMap<>();
	private Map<SimilarityPair<User>, Integer> pairCounts = new HashMap<>();

	public void addTarget() {
		totalTargetCount++;
	}

	public void add(User user) {
		addOne(singleCounts, user);
	}

	public void add(User user1, User user2) {
		SimilarityPair<User> pair = SimilarityPair.createOrdered(user1, user2);
		addOne(pairCounts, pair);
	}

	private static <T> void addOne(Map<T, Integer> map, T key) {
		Integer count = map.get(key);
		count = count != null ? count + 1 : 1;
		map.put(key, count);
	}

	public int count(User user) {
		Integer count = singleCounts.get(user);
		return count != null ? count : 0;
	}

	public int count(User user1, User user2) {
		Integer count = pairCounts.get(SimilarityPair.createOrdered(user1, user2));
		return count != null ? count : 0;
	}

	public int getTotalTargetCount() {
		return totalTargetCount;
	}

	public List<SimilarityRankedPair<User>> getRankedPairs(SimilarityMetric metric) {
		return getRankedPairs(null, metric);
	}

	public List<SimilarityRankedPair<User>> getRankedPairs() {
		return getRankedPairs(null, DEFAULT_METRIC);
	}

	public List<SimilarityRankedPair<User>> getRankedPairs(User user) {
		return getRankedPairs(user, DEFAULT_METRIC);
	}

	public List<SimilarityRankedPair<User>> getRankedPairs(User user, SimilarityMetric metric) {
		List<SimilarityRankedPair<User>> result = new ArrayList<>();
		for (SimilarityPair<User> pair : pairCounts.keySet()) {
			if (user == null || pair.has(user)) {
				result.add(getEntry(pair, metric));
			}
		}
		Collections.sort(result);
		return result;
	}

	private SimilarityRankedPair<User> getEntry(SimilarityPair<User> pair, SimilarityMetric metric) {
		User first = pair.getFirst();
		User second = pair.getSecond();
		int firstCount = count(first);
		int secondCount = count(second);
		int bothCount = count(first, second);
		double similarity = metric.calculateSimilarity(firstCount, secondCount, bothCount, totalTargetCount);

		return new SimilarityRankedPair<User>(first, second, firstCount, secondCount, bothCount, similarity);
	}
}
