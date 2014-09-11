Order of growth estimator
=========================

A funny thing I wrote during Coursera algorithms course. Supposed to be capable of estimating complexity of the algorithm.

*Theoretically*, it does so. However, this is the result I got after running the thing on a real algorithm implementation:

![the graph](http://i.imgur.com/U9sn4TN.png) 

Looks weird... This due to JVM being smarter then me and optimizing everything with caches and JIT and stuff.

I probably should try [caliper](https://code.google.com/p/caliper/).
