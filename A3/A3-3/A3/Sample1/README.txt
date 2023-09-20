The simulation was run with the following command:

java A3 30 3 Process1.txt Process2.txt Process3.txt Process4.txt

The simulation made the following assumptions:

-Issuing a page fault and subsequently blocking a process takes no time (this explains why all four processes are able to issue a page fault "simultaneously" at t=0)
-Execution of a swapped page can occur immediately after the page arrives in main memory (e.g. page 1 of process1 arrived in main memory at t=6 and executed at t=6, 
then a page fault was issued for page 2 at t=7). It also means that page placement/replacement should occur at every time instance before running a process at that time instance.
-Working is shown for the Fixed Allocation with Local Replacement policy


Note: In Sample1 dataset 30 frames are used, so no page replacement needed for this set of 4 procecesses.
Therefore the output is the same for both local or global scope of the replacement policy.


