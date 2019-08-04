# Data sorting and filtering

Read the 3 input files reports.json, reports.csv, reports.xml and output a combined CSV file with the following characteristics:

- The same column order and formatting as reports.csv
- All report records with packets-serviced equal to zero should be excluded
- records should be sorted by request-time in ascending order

Additionally, the application should print a summary showing the number of records in the output file associated with each service-guid.

Please provide source, documentation on how to run the program and an explanation on why you chose the tools/libraries used.

## Submission

Steps to run the code:

1. Build the project code using "mvn clean install"
2. Copy the test files to the a folder in the target folder (where the jar and lib files are present)
3. Exceute java -jar mariner-report-builder-1.0-SNAPSHOT.jar source-dir-name destination-dir-name



You may fork this repo, commit your work and let us know of your project's location, or you may email us your project files in a zip file.
