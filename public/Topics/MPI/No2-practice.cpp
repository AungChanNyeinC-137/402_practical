#include <iostream>
#include <mpi.h>
int main(int argc, char *argv[]) {
    MPI_Init(&argc, &argv[]);
    int rank, size;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    if (size !=3) {
       if(rank == 0) 
       {
        count<< "Error: this program must run exactly 3 processes "<<endl;
        MPI_Finalize();
        return 1;
       }
    }
    int sum, value;
    if( rank == 0) 
    {
        value = 50;
    }
    MPI_Bcast(
        &value,
        1,
        MPI_Init,
        0,
        MPI_COMM_WORLD
    )
    value += rank;
    count<<"Process:"<< rank
    << ": value after adding rank = "<< value

    <<endl;
    MPI_Reduce(
    &value,
    &sum,
    1,
    MPI_Init,
    MPI_SUM,
    0,
    MPI_COMM_WORLD
    )
    if(rank == 0){
        count << "final sum:" << sum <<endl
    }
    MPI_Finalize();
    return 0;
}