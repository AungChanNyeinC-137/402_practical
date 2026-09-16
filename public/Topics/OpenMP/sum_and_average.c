#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <omp.h>

#define N 100000000 // 100 million elements

int main() {
    int *array = (int *)malloc(N * sizeof(int));
    if (array == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    // 1. Initialize the array with random integers between 1 and 100
    srand((unsigned int)time(NULL));
    for (int i = 0; i < N; i++) {
        array[i] = (rand() % 100) + 1;
    }

    // 2. Determine and display the number of OpenMP threads
    int num_threads;
    #pragma omp parallel
    {
        #pragma omp single
        {
            num_threads = omp_get_num_threads();
            printf("===========================================\n");
            printf("Running with OpenMP Threads : %d\n", num_threads);
            printf("Array Size (N)              : %d elements\n", N);
            printf("===========================================\n\n");
        }
    }

    // 3. Serial Computation
    long long serial_sum = 0;
    double start_serial = omp_get_wtime();

    for (int i = 0; i < N; i++) {
        serial_sum += array[i];
    }
    double serial_avg = (double)serial_sum / N;

    double end_serial = omp_get_wtime();
    double serial_time = end_serial - start_serial;

    printf("--- Serial Version ---\n");
    printf("Sum             : %lld\n", serial_sum);
    printf("Average         : %.2f\n", serial_avg);
    printf("Execution Time  : %f seconds\n\n", serial_time);

    // 4. Parallel Computation using OpenMP Reduction
    long long parallel_sum = 0;
    double start_parallel = omp_get_wtime();

    #pragma omp parallel for reduction(+:parallel_sum)
    for (int i = 0; i < N; i++) {
        parallel_sum += array[i];
    }
    double parallel_avg = (double)parallel_sum / N;

    double end_parallel = omp_get_wtime();
    double parallel_time = end_parallel - start_parallel;

    printf("--- Parallel Version ---\n");
    printf("Sum             : %lld\n", parallel_sum);
    printf("Average         : %.2f\n", parallel_avg);
    printf("Execution Time  : %f seconds\n\n", parallel_time);

    // 5. Speedup Calculation
    double speedup = serial_time / parallel_time;
    printf("--- Performance Summary ---\n");
    printf("Speedup         : %.2fx\n", speedup);
    printf("===========================================\n");

    free(array);
    return 0;
}