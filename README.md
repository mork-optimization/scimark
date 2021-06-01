# SciMark 2.0: Java Numerical Benchmark

## What

SciMark 2.0 is a composite Java benchmark measuring the  performance of
numerical kernels occurring in scientific and engineering applications.  
It consists of five kernels which typify computational routines
commonly found in numeric codes: Fast Fourier Transforms (FFTs),
Jacobi Successive Over-relaxation (SOR), Sparse matrix-multiply,
Monte Carlo integration, and dense LU matrix factorization.

This is a maintained copy of the original software available at
https://math.nist.gov/scimark2/index.html

## Original authors
Roldan Pozo, Bruce Miller

See the following page for more details:
https://math.nist.gov/scimark2/credits.html


## Usage

### Independently

Download latest release or compile from source using Maven:

    mvn clean package

Execute standard benchmark using:

    java -jar scimark.jar

or if compiled locally
    
    java -jar target/scimark-2.0.1.jar

or large benchmark using `-large` switch:

    java -jar scimark.jar -large

In a few seconds, a similar output to this will appear:

```bash

SciMark 2.0a

Composite Score: 2179.5331097294147
FFT (1024): 1421.5490160647023
SOR (100x100):   1549.6220481439555
Monte Carlo : 956.5629209220217
Sparse matmult (N=1000, nz=5000): 1698.373753314555
LU (100x100): 5271.557810201838

java.vendor: AdoptOpenJDK
java.version: 11.0.6
os.arch: x86_64
os.name: Mac OS X
os.version: 10.16

Process finished with exit code 0

```

### As a library

TODO