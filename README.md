Order of growth estimator
=========================

A funny thing I wrote during [Coursera algorithms course](https://www.coursera.org/course/algs4partI). Somewhat capable of estimating complexity of the algorithm.

The first naive approach was purely pathetic — I tried measuring running time with manual timestampes and failed miserably -) JVM is smart enough with all the JITs and caches.

Project currently uses [Caliper](https://code.google.com/p/caliper/) — a Java microbenchmarking tool. So far so good, at least bubble sort reports to run at quadratic complexity -)
