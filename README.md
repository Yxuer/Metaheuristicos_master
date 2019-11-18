# Metaheuristicos_master

This repository has all the code and results for the Metaheuristics assignment.
It is not neccesary to change any files contained, since all the contents are thought to be read or executed. The structure is as it follows:

+ **Resultados/**: this folder contains the graphical results obtained with `exreport`. Just open each subfolder to find the .html documents detailing such results.
+ **Informe.pdf**: this PDF file contains the delivered project report.
+ **ga/**: this folder contains the code itself used to run the experiments and to generate the results. Inside of it, there is another important file: `experiment_runner.py`. Run it to automatically run and collect the results of the experiments into the CSV files at the **Resultados/** folder in the root folder.

In order to run individual experiments, navigate to the **ga/** folder, and run the following command on a terminal:

`java ssGA.Exe p1 p2 p3 p4 p5 p6`

, where:

+ p1 indicates whether to run GA (0) or SA (1). In the first case, the parameters have the following meanings:
  + p2: *population size* (integer)
  + p3: *crossover probability* (float between 0 and 1)
  + p4: *mutation probability* (float between 0 and 1)
  + p5: *objective function evaluation limit* (integer)
  + p6: *random seed* (integer)

If p1 is 1, then the parameters have the following meanings:
  + p2: *starting temperature* (float)
  + p3: *cooling rate* (float between 0 and 1)
  + p4: *objective function evaluation limit* (integer)
  + p5: *random seed* (integer)
  + p6 **is left empty**
