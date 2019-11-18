import subprocess

# Configurations for each case. The last value is the seed, which is changed on each experiment
geneticParameters_lowEvals = ['java', 'ssGA.Exe', '0', '256', '0.8', '0.1', '500000', '0']
annealingParameters_lowEvals = ['java', 'ssGA.Exe', '1', '448', '0.0000015', '500000', '0']
geneticParameters_highEvals = ['java', 'ssGA.Exe', '0', '256', '0.8', '0.1', '5000000', '0']
annealingParameters_highEvals = ['java', 'ssGA.Exe', '1', '448', '0.0000015', '5000000', '0']

# BEST FITNESS COMPARISON: Let the algorithms run for a very limited time and obtain their results
archFitness = open('../Resultados/bestFitness.csv', 'w')
archFitness.write('genetic,annealing\n')

# Run each case 50 times. 30 should be enough to guarantee normality, but the more, the better.
# BEST FITNESS -- GENETIC ALGORITHM and SIMULATED ANNEALING
print('BEST FITNESS EXPERIMENTS:\n')
for i in range(50):
    # Set the random seeds
    geneticParameters_lowEvals[len(geneticParameters_lowEvals) - 1] = str(i)
    annealingParameters_lowEvals[len(annealingParameters_lowEvals) - 1] = str(i)

    # Run the algorithms
    resultGA = subprocess.run(geneticParameters_lowEvals, stdout=subprocess.PIPE)
    resultSA = subprocess.run(annealingParameters_lowEvals, stdout=subprocess.PIPE)

    # Write the results to the CSV
    formattedResultGA = resultGA.stdout.decode('utf-8').splitlines()
    formattedResultSA = resultSA.stdout.decode('utf-8').splitlines()
    bestFitnessGA = formattedResultGA[0]
    bestFitnessSA = formattedResultSA[0]
    archFitness.write(bestFitnessGA + ',' + bestFitnessSA + '\n')

    print('Run ' + str(i + 1) + ' of 50\n')

archFitness.close()

# NUMBER OF EVALUATIONS COMPARISON: Let the algorithms run for a long time, and check how long they took to obtain
# an optimal result. Capped at 5000000 evaluations.
# This section also measures time

archEvaluations = open('../Resultados/numEvaluations.csv', 'w')
archEvaluations.write('genetic,annealing\n')
archTime = open('../Resultados/time.csv', 'w')
archTime.write('genetic,annealing\n')

# Run each case 50 times. 30 should be enough to guarantee normality, but the more, the better.
# NUMBER OF EVALUATIONS -- GENETIC ALGORITHM and SIMULATED ANNEALING
print('NUMBER OF EVALUATIONS AND TIME EXPERIMENTS:\n')
for i in range(50):
    # Set the random seeds
    geneticParameters_highEvals[len(geneticParameters_lowEvals) - 1] = str(i)
    annealingParameters_highEvals[len(annealingParameters_lowEvals) - 1] = str(i)

    # Run the algorithms
    resultGA = subprocess.run(geneticParameters_highEvals, stdout=subprocess.PIPE)
    resultSA = subprocess.run(annealingParameters_highEvals, stdout=subprocess.PIPE)

    # Write the results to the CSV
    formattedResultGA = resultGA.stdout.decode('utf-8').splitlines()
    formattedResultSA = resultSA.stdout.decode('utf-8').splitlines()
    numEvalsGA = formattedResultGA[1]
    numEvalsSA = formattedResultSA[1]
    timeGA = formattedResultGA[2]
    timeSA = formattedResultSA[2]
    archEvaluations.write(numEvalsGA + ',' + numEvalsSA + '\n')
    archTime.write(timeGA + ',' + timeSA + '\n')

    print('Run ' + str(i + 1) + ' of 50\n')

archEvaluations.close()
archTime.close()
