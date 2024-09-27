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

```
1	1739
2	1368
3	1081
4	869
5	812
6	675
7	1576
8	2676
9	2898
10	2787
11	2773
12	3044
13	2982
14	2832
15	2869
16	2868
17	3036
18	3378
19	3931
20	3860
21	3290
22	3015
23	2874
24	2162
```

## Task 2:

The code for this task can be seen in the `task-2` branch.

Here are the results (and can also be found in `task-2-results.txt`):

```
00005007A9F30E289E760362F69E4EAD	1.0
0004688E04E043CBD108E1A560BB6D68	1.0
001D3B86C2ACDEE4D1B98AFE52969F3D	1.0
010CFFF37C390AB7DB196B83DB225BB8	1.0
04D0035791B61BF046D0263EBA5EE34C	1.0
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
