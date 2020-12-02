# Wireless Sensor Network Simulator

## Problem
In this exercise you are invited to implement a Wireless Sensor Network Simulator program for the exchange and management of information within such a network. A Wireless Sensor Network (WSN) is a set of computing units with limited computing power and low memory. WSN can be placed in a forest, factories, etc. by dispersing the wireless sensors in such a way that they are repossessive to communicate with each other for data exchange. Wireless sensors have wireless tendons that allow communication between a sensor/node and nodes within its communication range. Also, each node has circuits that measure different environmental parameters such as temperature, humidity, etc.


## Implementation Details 
Your program should take as an argument [in the command line), the value of d, and the name of a file that contains node elements. Each line in the file will contain information about a node and more specifically its identity, the coordinates it is at, and the temperature it detects. The information is tab-separated. An example of such a file is shown below: 
01 [42, 85] 30 <br />123 [210, 50] 32 <br />353 [12, 345] 28 <br />02 [190, 300] 30 <br />12 [48, 105] 58 <br />821 [153, 46] 25 <br />Note that if the node identity starts from 0 then the node is a fire node, otherwise it is a computational node

## Menu
1. Calculation of the minimum birth tree of the writing. 
2. Print (rather than calculate) the minimum birth tree of the writing: Printing will start at node 02 and navigate the tree by applying a wide investigation.
 3. Introduction of a new node (identity, coordinates and temperature): This procedure should make the necessary changes to the minimum birth tree stored. The process of recalculating the minimum birth tree should be as fast as possible (so you probably should not use the method of menu 1, but the fact that you already have the birth tree from before). 
 4. Node withdrawal (identity): This procedure should again make the necessary changes to the minimum birth tree stored. The process of recalculating the minimum birth tree should be as fast as possible (so you probably should not use the method of menu 1, but the fact that you already have the birth tree from before). 
 5. Inform the fire-fighting centre A of the highest temperature of the network, where A is a node ID and is given as a parameter by the user. The procedure should only use paths belonging to the minimum birth tree of the writing. 
 6. Exit the program 

## For further details
Read Implementation.pdf
### Homework 3 EPL231 University of Cyprus Computer Science Department

