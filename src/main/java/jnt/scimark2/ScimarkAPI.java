package jnt.scimark2;

public class ScimarkAPI {

    public static BenchmarkResult runBenchmark(){
        return runBenchmark(false);
    }

    public static BenchmarkResult runBenchmark(int seed){
        return runBenchmark(seed, false);
    }

    public static BenchmarkResult runBenchmark(boolean bigDataset){
        return runBenchmark(Constants.RANDOM_SEED, bigDataset);
    }

    public static BenchmarkResult runBenchmark(int seed, boolean bigDataset){
        int FFT_size, SOR_size, Sparse_size_M, Sparse_size_nz, LU_size;
        double resolutionDefault = Constants.RESOLUTION_DEFAULT;

        if(bigDataset){
            FFT_size = Constants.LG_FFT_SIZE;
            SOR_size = Constants.LG_SOR_SIZE;
            Sparse_size_M = Constants.LG_SPARSE_SIZE_M;
            Sparse_size_nz = Constants.LG_SPARSE_SIZE_nz;
            LU_size = Constants.LG_LU_SIZE;
        } else {
            FFT_size = Constants.FFT_SIZE;
            SOR_size = Constants.SOR_SIZE;
            Sparse_size_M = Constants.SPARSE_SIZE_M;
            Sparse_size_nz = Constants.SPARSE_SIZE_nz;
            LU_size = Constants.LU_SIZE;
        }
        return runBenchmark(seed, resolutionDefault, FFT_size, SOR_size, Sparse_size_M, Sparse_size_nz, LU_size);
    }

    public static BenchmarkResult runBenchmark(int seed, double min_time, int FFT_size, int SOR_size, int Sparse_size_M, int Sparse_size_nz, int LU_size){
        Random R = new Random(seed);
        var result =  new BenchmarkResult(
                seed,
                min_time,
                Kernel.measureFFT(FFT_size, min_time, R),
                Kernel.measureSOR(SOR_size, min_time, R),
                Kernel.measureMonteCarlo(min_time, R),
                Kernel.measureSparseMatmult(Sparse_size_M, Sparse_size_nz, min_time, R),
                Kernel.measureLU(LU_size, min_time, R)
        );
        result.validate();
        return result;
    }

    public static class BenchmarkResult {
        private final int randomSeed;
        private final double min_time, FFTScore, SORScore, MonteCarloScore, SparseMatmultScore, LUScore;

        public BenchmarkResult(int randomSeed, double min_time, double fftScore, double sorScore, double monteCarloScore, double sparseMatmultScore, double luScore) {
            this.randomSeed = randomSeed;
            this.min_time = min_time;
            FFTScore = fftScore;
            SORScore = sorScore;
            MonteCarloScore = monteCarloScore;
            SparseMatmultScore = sparseMatmultScore;
            LUScore = luScore;
        }

        public double getFFTScore() {
            return FFTScore;
        }

        public double getSORScore() {
            return SORScore;
        }

        public double getMonteCarloScore() {
            return MonteCarloScore;
        }

        public double getSparseMatmultScore() {
            return SparseMatmultScore;
        }

        public double getLUScore() {
            return LUScore;
        }

        public double getScore(){
            return (FFTScore + SORScore + MonteCarloScore + SparseMatmultScore + LUScore) / 5;
        }

        public void validate(){
            // Same validations as existing CommandLine
            if(FFTScore == 0){
                throw new BenchmarkException("Invalid FFT Score");
            }
            if(LUScore == 0){
                throw new BenchmarkException("Invalid LU Score");
            }
        }
    }

    public static class BenchmarkException extends RuntimeException{
        public BenchmarkException(String reason){
            super(reason);
        }
    }
}
