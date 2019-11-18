# Activate the command line arguments
args <- commandArgs(TRUE)

# Imports
library(tools)
library(exreport)

# From the name of this metric, obtain
# the path where the results are stored
metricPath <- 'Resultados/time.csv'

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
                        output='time',
                        name="Report",
                        respectOrder=FALSE)

# Ensure that the data is numeric
e$data[['time']] <- as.numeric(e$data[['time']])

# Instantiate the experiments
ei <- expInstantiate(e)

# Obtain the experiment table for this metric
tabexp <- tabularExpSummary(exp=ei,
                            outputs='time',
                            boldfaceColumns='min',
                            format="f", digits=5,
                            rowsAsMethod=TRUE)

# Obtain the experiment plots for this metric
plotexp <- plotExpSummary(exp=ei,
                          output='time',
                          columns=5,
                          freeScale=TRUE)

# Add the experiment table and plots to the report
r <- exreportAdd(r, tabexp)
r <- exreportAdd(r, plotexp)

# Execute the pairwise test
testm <- testPaired(ei, 'time', rankOrder='min', alpha=0.05)
r <- exreportAdd(r, testm)

# Generate the HTML report
exreportRender(r,
               destination=reportPath,
               target="html",
               safeMode=FALSE,
               visualize=FALSE)
