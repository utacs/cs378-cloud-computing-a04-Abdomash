# Author

**Abdulrahman Alshahrani**

**_(UT EID: ama8347)_**

# Course

**_CS378 - Cloud Computing_**

**_Unique Number: 51515_**

# Report

## Task 1:

The code for this task can be found in the `task-1` branch in my code repo.

Here are the results (and can also be found in `task-1-results.txt`):
(I manually sorted the results so it is easier to read)

```
1       197787
2       149205
3       113109
4       85731
5       63409
6       50492
7       104405
8       179262
9       218326
10      228540
11      221861
12      227322
13      241283
14      239548
15      249273
16      246005
17      212890
18      242402
19      302299
20      315883
21      294776
22      289992
23      278228
24      246378
```

# Project Template

# Running on Laptop

Prerequisite:

- Maven 3

- JDK 1.6 or higher

- (If working with eclipse) Eclipse with m2eclipse plugin installed

The java main class is:

edu.cs.utexas.HadoopEx.WordCount

Input file: Book-Tiny.txt

Specify your own Output directory like

# Running:

## Create a JAR Using Maven

To compile the project and create a single jar file with all dependencies:
`	mvn clean package `

## Run your application

Inside your shell with Hadoop

Running as Java Application:

`java -jar target/MapReduce-WordCount-example-0.1-SNAPSHOT-jar-with-dependencies.jar SOME-Text-Fiel.txt  output`

Or has hadoop application

`hadoop jar your-hadoop-application.jar edu.cs.utexas.HadoopEx.WordCount arg0 arg1 ... `

## Create a single JAR File from eclipse

Create a single gar file with eclipse

- File export -> export -> export as binary -> "Extract generated libraries into generated JAR"
