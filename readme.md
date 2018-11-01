### MARS ROVERS

##### Test Input  

5 5 <br />
1 2 N <br />
LMLMLMLMM <br />
3 3 E <br />
MMRMMRMRRM <br />

[Empty line ends the input]

##### Expected Output 

1 3 N <br />
5 1 E

#### Key decisions 

The orientations are represented using an array,  sacrificing a bit on the readability. However, a performance improvement than all other approaches.  Also, operations to find a number is odd or even is done using the bitwise operators for the same reason.

The Robot class is not initalized on getting the right-most coordinates because, if we follow the approach of intializing the Robot class and then the position , we will have to check if positions are initialized before each operations. On the other hand, the approach I took only involves an additional null check for each pair of position and action.  



