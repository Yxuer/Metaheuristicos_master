///////////////////////////////////////////////////////////////////////////////
///            Steady State Genetic Algorithm v1.0                          ///
///                by Enrique Alba, July 2000                               ///
///                                                                         ///
///   Executable: set parameters, problem, and execution details here       ///
///////////////////////////////////////////////////////////////////////////////

package ssGA;

import java.util.Random;

public class Exe
{
  public static void main(String args[]) throws Exception {

    int gn = 39;
    int gl = 1;
    int tf = 10618;
    long start;

    if (Integer.parseInt(args[0]) == 0) {
      // PARAMETERS KNAPSACK       // Gene length
      int popsize = Integer.parseInt(args[1]);                          // Population size
      double pc = Double.parseDouble(args[2]);                          // Crossover probability
      double pm = Double.parseDouble(args[3]);                               // Mutation probability
      long MAX_ISTEPS = Long.parseLong(args[4]);
      int seed = Integer.parseInt(args[5]);

      Problem problem;                             // The problem being solved

      problem = new ProblemKnapsack();

      problem.set_geneN(gn);
      problem.set_geneL(gl);
      problem.set_target_fitness(tf);

      Algorithm ga;          // The ssGA being used
      ga = new Algorithm(problem, popsize, gn, gl, pc, pm, seed);

      start = System.currentTimeMillis();
      for (int step = 0; step < MAX_ISTEPS; step++) {
        ga.go_one_step();
        // System.out.print(step);
        // System.out.print("  ");
        // System.out.println(ga.get_bestf());

        if ((problem.tf_known()) &&
                (ga.get_solution()).get_fitness() >= problem.get_target_fitness()
        ) {
          // System.out.print("Solution Found! After ");
          // System.out.print(problem.get_fitness_counter());
          // System.out.println(" evaluations");
          break;
        }

      }

      // Print the solution
      //for (int i = 0; i < gn * gl; i++)
        //System.out.print((ga.get_solution()).get_allele(i));
      //System.out.println();
      System.out.println(ga.get_bestf());
      System.out.println(problem.get_fitness_counter());
      System.out.println(System.currentTimeMillis() - start);
    }

    else {
      // PARAMETERS KNAPSACK
      double temp = Double.parseDouble(args[1]);
      double coolRate = Double.parseDouble(args[2]);
      long MAX_ISTEPS = Long.parseLong(args[3]);
      int seed = Integer.parseInt(args[4]);

      Problem problem;                             // The problem being solved

      problem = new ProblemKnapsack();

      problem.set_geneN(gn);
      problem.set_geneL(gl);
      problem.set_target_fitness(tf);

      SimulatedAnnealing ga;          // The ssGA being used
      ga = new SimulatedAnnealing(temp, coolRate, problem, (gn*gl), seed);

      start = System.currentTimeMillis();
      for (int step = 0; step < MAX_ISTEPS; step++) {
        ga.go_one_step();
        //System.out.print(step);
        //System.out.print("  ");
        //System.out.println(ga.get_bestf() + " // Temperature: " + ga.getTemperature());

        if ((problem.tf_known()) &&
                (ga.get_solution()).get_fitness() >= problem.get_target_fitness()
        ) {
          //System.out.print("Solution Found! After ");
          //System.out.print(problem.get_fitness_counter());
          //System.out.println(" evaluations");
          break;
        }

      }

      // Print the solution
      //for (int i = 0; i < gn * gl; i++)
        //System.out.print((ga.get_solution()).get_allele(i));
      //System.out.println();
      System.out.println(ga.get_bestf());
      System.out.println(problem.get_fitness_counter());
      System.out.println(System.currentTimeMillis() - start);
    }
  }
}
// END OF CLASS: Exe
