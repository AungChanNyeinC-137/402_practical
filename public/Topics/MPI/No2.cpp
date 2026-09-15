#include <mpi.h>
#include <iostream>

using namespace std;

int main(int argc, char *argv[])
{
    MPI_Init(&argc, &argv);

    int rank, size;

    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    // Check that exactly 3 processes are used
    if (size != 3)
    {
        if (rank == 0)
        {
            cout << "Error: This program must be run with exactly 3 processes."
                 << endl;
        }

        MPI_Finalize();
        return 1;
    }

    int value;
    int sum;

    // Process 0 initializes the value
    if (rank == 0)
    {
        value = 50;
    }

    // Broadcast value from process 0 to all processes
    MPI_Bcast(
        &value,
        1,
        MPI_INT,
        0,
        MPI_COMM_WORLD
    );

    // Each process adds its rank
    value += rank;

    cout << "Process " << rank
         << ": value after adding rank = "
         << value << endl;

    // Reduce all values to process 0 using sum
    MPI_Reduce(
        &value,
        &sum,
        1,
        MPI_INT,
        MPI_SUM,
        0,
        MPI_COMM_WORLD
    );

    // Process 0 prints the final sum
    if (rank == 0)
    {
        cout << "Final sum = " << sum << endl;
    }

    MPI_Finalize();

    return 0;
}