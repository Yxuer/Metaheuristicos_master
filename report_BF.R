# Activate the command line arguments
args <- commandArgs(TRUE)

# Imports
library(tools)
library(exreport)

# From the name of this metric, obtain
# the path where the results are stored
metricPath <- 'Resultados/bestFitness.csv'

# From the name of this metric, obtain the
# path where the report will be stored
reportPath <- 'Resultados/'

# Read the DataFrame with the results for this metric
ds <- read.csv(metricPath, row.names=1, check.names=FALSE)

# Set the name of the column for the methods
ds <- cbind(rownames(ds), ds)
colnames(ds)[1] <- "method"

# Initialize the report
r <- exreport("Report")

# Create the experiment from this metric
e <- expCreateFromTable(data=ds,
                        output='fitness',
                        name="Report",
                        respectOrder=FALSE)

# Ensure that the data is numeric
e$data[['fitness']] <- as.numeric(e$data[['fitness']])

# Instantiate the experiments
ei <- expInstantiate(e)

# Obtain the experiment table for this metric
tabexp <- tabularExpSummary(exp=ei,
                            outputs='fitness',
                            boldfaceColumns='max',
                            format="f", digits=5,
                            rowsAsMethod=TRUE)

# Obtain the experiment plots for this metric
plotexp <- plotExpSummary(exp=ei,
                          output='fitness',
                          columns=5,
                          freeScale=TRUE)

# Add the experiment table and plots to the report
r <- exreportAdd(r, tabexp)
r <- exreportAdd(r, plotexp)

# Execute the pairwise test
testm <- testPaired(ei, 'fitness', rankOrder='max', alpha=0.05)
r <- exreportAdd(r, testm)

# Generate the HTML report
exreportRender(r,
               destination=reportPath,
               target="html",
               safeMode=FALSE,
               visualize=FALSE)

