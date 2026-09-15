#include <mpi.h>
#include <iostream>
#include <thread>
#include <chrono>

using namespace std;

int main(int argc, char *argv[])
{
    MPI_Init(&argc, &argv);

    int rank, size;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    // Check that exactly 2 processes are used
    if (size != 2)
    {
        if (rank == 0)
        {
            cout << "Error: This program must be run with exactly 2 processes."
                 << endl;
        }

        MPI_Finalize();
        return 1;
    }

    int message;
    MPI_Request request;
    MPI_Status status;
    int flag;

    if (rank == 0)
    {
        // Sender
        message = 100;

        // Delay so that the receiver can test before
        // the message is available.
        this_thread::sleep_for(chrono::seconds(2));

        MPI_Isend(
            &message,
            1,
            MPI_INT,
            1,
            0,
            MPI_COMM_WORLD,
            &request
        );

        cout << "Sender: Message sent using MPI_Isend." << endl;

        MPI_Wait(&request, MPI_STATUS_IGNORE);

        cout << "Sender: MPI_Isend operation completed." << endl;
    }
    else
    {
        // Receiver
        MPI_Irecv(
            &message,
            1,
            MPI_INT,
            0,
            0,
            MPI_COMM_WORLD,
            &request
        );

        cout << "Receiver: MPI_Irecv posted." << endl;

        // Case 1: Test before the message has arrived
        MPI_Test(&request, &flag, &status);

        if (flag == 0)
        {
            cout << "Receiver: MPI_Test -> Receive is NOT complete."
                 << endl;
        }
        else
        {
            cout << "Receiver: MPI_Test -> Receive is complete."
                 << endl;
        }

        // Wait until the message is available
        MPI_Wait(&request, &status);

        cout << "Receiver: Message received = " << message << endl;

        // Case 2: Test after the receive has completed
        MPI_Test(&request, &flag, &status);

        if (flag == 0)
        {
            cout << "Receiver: MPI_Test -> Receive is NOT complete."
                 << endl;
        }
        else
        {
            cout << "Receiver: MPI_Test -> Receive is complete."
                 << endl;
        }
    }

    MPI_Finalize();

    return 0;
}