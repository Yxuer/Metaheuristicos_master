# Activate the command line arguments
args <- commandArgs(TRUE)

# Imports
library(tools)
library(exreport)

# From the name of this metric, obtain
# the path where the results are stored
metricPath <- 'Resultados/numEvaluations.csv'

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
                        output='evaluations',
                        name="Report",
                        respectOrder=FALSE)

# Ensure that the data is numeric
e$data[['evaluations']] <- as.numeric(e$data[['evaluations']])

# Instantiate the experiments
ei <- expInstantiate(e)

# Obtain the experiment table for this metric
tabexp <- tabularExpSummary(exp=ei,
                            outputs='evaluations',
                            boldfaceColumns='min',
                            format="f", digits=5,
                            rowsAsMethod=TRUE)

# Obtain the experiment plots for this metric
plotexp <- plotExpSummary(exp=ei,
                          output='evaluations',
                          columns=5,
                          freeScale=TRUE)

# Add the experiment table and plots to the report
r <- exreportAdd(r, tabexp)
r <- exreportAdd(r, plotexp)

# Execute the pairwise test
testm <- testPaired(ei, 'evaluations', rankOrder='min', alpha=0.05)
r <- exreportAdd(r, testm)

# Generate the HTML report
exreportRender(r,
               destination=reportPath,
               target="html",
               safeMode=FALSE,
               visualize=FALSE)

