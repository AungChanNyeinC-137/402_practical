#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

#define N 10000000       // Array size fits in int
#define NUM_THREADS 4    // Thread count fits in int

int main()
{
    int *A = malloc(N * sizeof(int));
    if (A == NULL)
    {
        printf("Memory allocation failed.\n");
        return 1;
    }

    // Loop iterator uses standard int
    for (int i = 0; i < N; i++)
    {
        A[i] = 1;
    }

    printf("=== OpenMP Reduction Benchmark ===\n");
    printf("Array size       : %d\n", N);
    printf("Number of threads: %d\n\n", NUM_THREADS);

    /* ---------------- SERIAL VERSION ---------------- */

    // Keep sum as long long to prevent integer overflow
    long long serial_sum = 0;
    double start = omp_get_wtime();

    for (int i = 0; i < N; i++)
    {
        serial_sum += A[i];
    }

    double serial_time = omp_get_wtime() - start;

    /* ---------------- PARALLEL VERSION ---------------- */

    long long parallel_sum = 0;
    omp_set_num_threads(NUM_THREADS);

    start = omp_get_wtime();

    #pragma omp parallel for reduction(+:parallel_sum)
    for (int i = 0; i < N; i++)
    {
        parallel_sum += A[i];
    }

    double parallel_time = omp_get_wtime() - start;

    /* ---------------- RESULTS ---------------- */

    double speedup = serial_time / parallel_time;

    printf("Serial Sum    : %lld\n", serial_sum);
    printf("Parallel Sum  : %lld\n\n", parallel_sum);

    printf("Serial Time   : %.6f seconds\n", serial_time);
    printf("Parallel Time : %.6f seconds\n", parallel_time);
    printf("Speedup       : %.2fx\n\n", speedup);

    if (serial_sum == parallel_sum)
        printf("Result Check  : PASS\n");
    else
        printf("Result Check  : FAIL\n");

    free(A);
    return 0;
}