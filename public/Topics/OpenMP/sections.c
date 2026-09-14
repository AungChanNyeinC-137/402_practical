#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <limits.h>

int main(int argc, char *argv[])
{
    long long N = 10000000;
    int num_threads = 3;

    if (argc >= 2)
        N = atoll(argv[1]);

    if (argc >= 3)
        num_threads = atoi(argv[2]);

    int *A = malloc(N * sizeof(int));

    if (A == NULL)
    {
        printf("Memory allocation failed.\n");
        return 1;
    }

    /* Initialize array */
    for (long long i = 0; i < N; i++)
        A[i] = i % 100000;

    printf("Array size : %lld\n", N);
    printf("Threads    : %d\n\n", num_threads);

    /* =====================================================
       SERIAL VERSION
       ===================================================== */

    long long serial_sum = 0;
    int serial_max = INT_MIN;
    int serial_min = INT_MAX;

    double start = omp_get_wtime();

    /* Calculate SUM */
    for (long long i = 0; i < N; i++)
        serial_sum += A[i];

    /* Calculate MAX */
    for (long long i = 0; i < N; i++)
    {
        if (A[i] > serial_max)
            serial_max = A[i];
    }

    /* Calculate MIN */
    for (long long i = 0; i < N; i++)
    {
        if (A[i] < serial_min)
            serial_min = A[i];
    }

    double serial_time = omp_get_wtime() - start;

    /* =====================================================
       PARALLEL VERSION USING SECTIONS
       ===================================================== */

    long long parallel_sum = 0;
    int parallel_max = INT_MIN;
    int parallel_min = INT_MAX;

    omp_set_num_threads(num_threads);

    start = omp_get_wtime();

    #pragma omp parallel sections
    {
        /* Section 1: SUM */
        #pragma omp section
        {
            long long local_sum = 0;

            for (long long i = 0; i < N; i++)
                local_sum += A[i];

            parallel_sum = local_sum;
        }

        /* Section 2: MAX */
        #pragma omp section
        {
            int local_max = INT_MIN;

            for (long long i = 0; i < N; i++)
            {
                if (A[i] > local_max)
                    local_max = A[i];
            }

            parallel_max = local_max;
        }

        /* Section 3: MIN */
        #pragma omp section
        {
            int local_min = INT_MAX;

            for (long long i = 0; i < N; i++)
            {
                if (A[i] < local_min)
                    local_min = A[i];
            }

            parallel_min = local_min;
        }
    }

    double parallel_time = omp_get_wtime() - start;

    /* =====================================================
       RESULTS
       ===================================================== */

    double speedup = serial_time / parallel_time;

    printf("SERIAL RESULTS\n");
    printf("Sum : %lld\n", serial_sum);
    printf("Max : %d\n", serial_max);
    printf("Min : %d\n", serial_min);

    printf("\nPARALLEL RESULTS\n");
    printf("Sum : %lld\n", parallel_sum);
    printf("Max : %d\n", parallel_max);
    printf("Min : %d\n", parallel_min);

    printf("\nSerial Time   : %.6f seconds\n", serial_time);
    printf("Parallel Time : %.6f seconds\n", parallel_time);
    printf("Speedup       : %.2fx\n", speedup);

    if (serial_sum == parallel_sum &&
        serial_max == parallel_max &&
        serial_min == parallel_min)
    {
        printf("Result Check  : PASS\n");
    }
    else
    {
        printf("Result Check  : FAIL\n");
    }

    free(A);

    return 0;
}