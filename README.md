# Hadoop-MapReduce
Dataset is of directed graph and the Location for the same is on the accessible Cluster: Data location: /data/graph
There is a Sample Data present in the following location : data/graph/sample.txt. Remove the noise in the dataset and ignore the Line starting with # in the Dataset.
First, We have to convert the directed graph into an Undirected Graph; i.e if there are sub branches from Node 1 to Node 2, Node 3 etc, in the directed graph, it means that Node 2 wont be connected to Node 1. We have to convert the Dataset in a way that Node 2 again connects to Node 1.
Then, We have to Create Adjacency List for Both Directed and Undirected Graphs and find the Longest Adjacency List present in both the Graphs.
Calculate the Maximum and Minimum Connectivity of each Node and print those Node values.
Map- Reduce Code is written in JAVA oin order to perform the above tasks.
