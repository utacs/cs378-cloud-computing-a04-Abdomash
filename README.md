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
00153E36140C5B2A84EA308F355A7925	1.0
0035520A854E4F2769B37DAF5357426F	1.0
FFF010F904EF7B60DAF12560AFE5127C	1.0
FFFECF75AB6CC4FF9E8A8B633AB81C26	1.0
```

## Task 3:

The code for this taks can be seen in the `task3` branch.

Here are the results (and can also be found in `task-3-results.txt`):

```
28EAF0C54680C6998F0F2196F2DA2E21	179.99998
42AB6BEE456B102C1CF8D9D8E71E845A	191.55
74071A673307CA7459BCF75FBD024E09	209.99998
95A921A9908727D4DC03B5D25A4B0F62	210.0
A7C9E60EEE31E4ADC387392D37CD06B8	1260.0
D8E90D724DBD98495C1F41D125ED029A	630.0
E79402C516CEF1A6BB6F526A142597D4	144.54546
E9DA1D289A7E321CC179C51C0C526A73	231.29999
FA587EC2731AAB9F2952622E89088D4B	179.99998
FD2AE1C5F9F5FBE73A6D6D3D33270571	4094.9998
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
