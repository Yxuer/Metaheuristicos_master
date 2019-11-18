package ssGA;

// Multidimensional knapsack problem specification
// Given a set of 'j' items, each one with a value 'v' and a set of constraint values 'w1, w2, ..., wm'
// they must be inserted into the knapsack to maximize the total value of its items,
// without exceeding the total of constraint values FOR EACH CONSTRAINT W1, W2, ..., Wm
public class ProblemKnapsack extends Problem {

    private int numConstraints = 5;

    private int itemValues[] = {560, 1125, 300, 620, 2100, 431, 68, 328, 47, 122, 322, 196, 41, 25, 425, 4260,
            416, 115, 82, 22, 631, 132, 420, 86, 42, 103, 215, 81, 91, 26, 49, 420,
            316, 72, 71, 49, 108, 116, 90};

    private int itemConstraints[][] = {{40, 91, 10, 30, 160, 20, 3, 12, 3, 18, 9, 25, 1, 1, 10, 280, 10, 8, 1, 1, 49, 8, 21,
                    6, 1, 5, 10, 8, 2, 1, 0, 10, 42, 6, 4, 8, 0, 10, 1},
            {16, 92, 41, 16, 150, 23, 4, 18, 6, 0, 12, 8, 2, 1, 0, 200, 20, 6, 2, 1, 70, 9, 22, 4,
                    1, 5, 10, 6, 4, 0, 4, 12, 8, 4, 3, 0, 10, 0, 6},
            {38, 39, 32, 71, 80, 26, 5, 40, 8, 12, 30, 15, 0, 1, 23, 100, 0, 20, 3, 0, 40, 6, 8, 0,
                    6, 4,22, 4, 6, 1, 5, 14, 8, 2, 8, 0, 20, 0, 0},
            {8, 71, 30, 60, 200, 18, 6, 30, 4, 8, 31, 6, 3, 0, 18, 60, 21, 4, 0, 2, 32, 15, 31, 2,
                    2, 7, 8, 2, 8, 0, 2, 8, 6, 7, 1, 0, 0, 20, 8},
            {38, 52, 30, 42, 170, 9, 7, 20, 0, 3, 21, 4, 1, 2, 14, 310, 8, 4, 6, 1, 18, 15, 38, 10,
                    4, 8, 6, 0, 0, 3, 0, 10, 6, 1, 3, 0, 3, 5, 4}};

    private int constraintLimits[] = {600, 500, 500, 500, 600};

    public double Evaluate (Individual individual) {
        return MDIMKNAPSACK(individual);
    }

    private double MDIMKNAPSACK (Individual individual) {
        int fitness = 0;
        int constraintSums[] = new int[numConstraints];

        for (int i = 0; i < numConstraints; i++) {
            constraintSums[i] = 0;
        }

        for (int i = 0; i < CL; i++) {
            if (individual.get_allele(i) == 1) {
                fitness += itemValues[i];

                for (int j = 0; j < numConstraints; j++) {
                    constraintSums[j] += itemConstraints[j][i];
                    if (constraintSums[j] > constraintLimits[j]) {
                        return 0.0;
                    }
                }
            }
        }

        return (double)fitness;
    }
}
