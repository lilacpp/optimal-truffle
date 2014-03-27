package part2;

import java.io.IOException;

import dist.MixtureDistribution;

import func.EMClusterer;

import shared.DataSet;
import shared.Instance;
import shared.reader.DataSetLabelBinarySeperator;

public class RunEM {

	private static final int STARTING_K = 2;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataSet set;
		try {
			set = DataLoader
					.loadData("/Users/sephirothxxsama/git/abagail/bin/shared/test/abalone.arff");
			DataSetLabelBinarySeperator.seperateLabels(set);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void compareEMClustersOverK(DataSet set, double tolerance,
			int maxIterations, int maxValueOfK) {
		int dim = maxValueOfK + 1 - STARTING_K;
		int off = maxValueOfK + 1 - dim;
		EMClusterer em;
		MixtureDistribution[] mixtures = new MixtureDistribution[dim];
		for (int k = 0; k < dim; k++) {
			em = new EMClusterer(k, tolerance, maxIterations);
			em.estimate(set);
			mixtures[k] = em.getMixture();
		}
	}

}
