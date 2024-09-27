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

The code for this taks can be seen in the `task-3` branch.

Here are the results (and can also be found in `task-3-results.txt`):

```
08026D69508127F4DE855678ABCE7E0A	375.0
0838C4C7DDFD9391AD66E316B5608B26	1814.9999
30B2ACBAF002305533FF0D31D34A7C06	702.0
4C3B2A31227663A59E1AA7B45157160D	625.0
57D463B8F4C3382081F206E6869AA095	3779.9998
69B6FBD28F84175AB1504F6BFF001A49	2399.9998
6E1D7195E38AA7A36B375C1C60AD8632	317.30768
7826BDE4CE3E44EE1BB7B926A38A4B2A	279.85715
A0AC85170C37E1D076ADE05B0238C58E	540.0
D3B2DEC5DB78D91D9AFADA53B3B521B5	329.99997
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
