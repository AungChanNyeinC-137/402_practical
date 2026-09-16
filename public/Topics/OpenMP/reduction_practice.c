#include<stdio.h>
#include<stdlib.h>
#include<omp.h>
#define N 1000000
#define NUM_OF_THREADS 4
int main(){
int *A = malloc(N * sizeof(int))
if(A ==0) 
{printf("Memory Allocation Failed")
return 1}

for (int i= 0; i<N;i++ )
{
    A[i] = 1;
}
printf("Number of threads : %d", NUM_OF_THREADS);

//serial sum calculation
long long serial_sum = 0;
double start = omp_get_wtime();
for(int i; i< N; i++){
    serial_sum += A[i];
}
double serial_time = omp_get_wtime() - start;
//parallel sum calculation
long long parallel_sum =0;
start = omp_get_wtime();
#pragma omp parallel for reduction(+:parallel_sum){
    parallel_sum += A[i];
}
double parallel_time = omp_get_wtime() - start;
double speedup = serial_time/parallel_time
printf("serial sum: %d", serial_sum);
printf("parallel sum: %d", parallel_sum);
printf("serial time: %d", serial_time);
printf("parallel time: %d", parallel_time);
printf("speedup: %d", speedup);
}


