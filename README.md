# SciMark 2: Java Numerical Benchmark

## What

SciMark 2 is a composite Java benchmark measuring the  performance of
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

Requires Java 11 to execute, and Maven for compiling from sources.

### Get it

Download latest release from the following link
https://github.com/rmartinsanta/scimark/releases/

or compile from source (requires maven):
```bash
git clone https://github.com/rmartinsanta/scimark
cd scimark
mvn clean package
```

Executable will be at `target/scimark-version.jar`.

### Executing standalone

Execute standard benchmark using:

    java -jar scimark.jar


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

Standard benchmark uses small datasets that should be small enough to fit in CPU caches.
Large benchmark should not fit in cache so CPU-RAM performance is also measured in the benchmark.

### As a library

Available at maven central: https://search.maven.org/artifact/es.urjc.etsii.grafo/scimark/

Example for Maven project
```xml
<dependency>
    <groupId>es.urjc.etsii.grafo</groupId>
    <artifactId>scimark</artifactId>
    <version>2.2</version>
</dependency>
```

Example usage:
```java
var benchmarkResult = ScimarkAPI.runBenchmark(); // Optionally set a seed, large run, or custom config
double score = benchmarkResult.getScore();
```

The benchmark may fail under rare circunstances, throwing `BenchmarkException` and a message explaining why.

