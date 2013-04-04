DistributedComputing
====================

Simple example to show how a problem is solved using multiple machines

1. There is a client (C) which will connect to a server via socket connection and send two numbers say 1 and 100.

2. The server (S) has has the responsibility of finding all the prime numbers between two inputs entered by client (Ex 1-100)
 
3. The server should be able to handle multiple request concurrently. Multiple client can invoke with different input numbers. 

4. The server should divide work into 4 parts ...EX (1-100 will be divided into 1-25,26-50,51-75,76-100) 

5. This server which is primary server will only do 1/4th of its work on the same machine and it should ask three other server to complete the work . 
   Ex S1,S2,S3. Lets call it S1,S2,S3 as secondary servers. 

6. Finally the primary server will assemble the results done by itself and by all others server and send the response to client.

7. All server primary or secondary will be able to handle multiple request at the same time.  
