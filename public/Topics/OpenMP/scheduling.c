#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main(int argc, char *argv[])
{
    long long N = 10000000;
    int num_threads = 4;
    int chunk_size = 10000;

    if (argc >= 2)
        N = atoll(argv[1]);

    if (argc >= 3)
        num_threads = atoi(argv[2]);

    if (argc >= 4)
        chunk_size = atoi(argv[3]);

    int *A = malloc(N * sizeof(int));
    int *B = malloc(N * sizeof(int));
    int *C_serial = malloc(N * sizeof(int));
    int *C_parallel = malloc(N * sizeof(int));

    if (A == NULL || B == NULL ||
        C_serial == NULL || C_parallel == NULL)
    {
        printf("Memory allocation failed.\n");
        return 1;
    }

    /* Initialize arrays */
    for (long long i = 0; i < N; i++)
    {
        A[i] = i % 100;
        B[i] = (i * 2) % 100;
    }

    printf("Array size : %lld\n", N);
    printf("Threads    : %d\n", num_threads);
    printf("Chunk size : %d\n\n", chunk_size);

    /* ---------------- SERIAL VERSION ---------------- */

    double start = omp_get_wtime();

    for (long long i = 0; i < N; i++)
    {
        C_serial[i] = A[i] + B[i];
    }

    double serial_time = omp_get_wtime() - start;

    /* ---------------- PARALLEL VERSION ---------------- */

    omp_set_num_threads(num_threads);

    start = omp_get_wtime();

    #pragma omp parallel for schedule(static, chunk_size)
    for (long long i = 0; i < N; i++)
    {
        C_parallel[i] = A[i] + B[i];
    }

    double parallel_time = omp_get_wtime() - start;

    /* ---------------- CHECK RESULTS ---------------- */

    int correct = 1;

    for (long long i = 0; i < N; i++)
    {
        if (C_serial[i] != C_parallel[i])
        {
            correct = 0;
            break;
        }
    }

    double speedup = serial_time / parallel_time;

    printf("Serial Time   : %.6f seconds\n", serial_time);
    printf("Parallel Time : %.6f seconds\n", parallel_time);
    printf("Speedup       : %.2fx\n", speedup);

    if (correct)
        printf("Result Check  : PASS\n");
    else
        printf("Result Check  : FAIL\n");

    free(A);
    free(B);
    free(C_serial);
    free(C_parallel);

    return 0;
}