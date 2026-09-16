#include<mpi.h>
#include<iostream>
#include <thread>
#include <chrono>
using namespace std;
int main()
{
    MPI_Init(int argc, char *argv[]);
    int rank, size;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    if(size != 2)
    {
        if(rank == 0) {
            cout << "This program requires exactly 2 processes." << endl;
        }
        MPI_Finalize();
        return 1;
    }
    int message = 0;
    MPI_request request;
    MPI_Status status;
    int flag;
    if(rank == 0)
    {
         message = 100;
         this_thread::sleep_for(chrono::seconds(2));
         MPI_Isend(
            &message, 
            1,
            MPI_INT,
            1,
            0,
            MPI_COMM_WORLD,
            &request
         )
         cout << "Sender: message sent with MPI_Isend()" << endl;
         MPI_Wait(&request, MPI_STATUS_IGNORE);
         cout << "Sender: MPI_Isend() operation completed." << endl;

//SEND
    } else {
//RECEIVE
MPI_Irecv(
            &message,
            1,
            MPI_INT,
            0,
            0,
            MPI_COMM_WORLD,
            &request
        );

       cout <<"Receiver: Message received: "<< message <<"with MPI_Irecv()"<< endl;
    MPI_Test(&request, &flag, &status);
    if(flag == 1)
    {
        cout << "Receiver: MPI_Irecv() operation completed." << endl;
    }
    else
    {
        cout << "Receiver: MPI_Irecv() operation not completed yet." << endl;
    }
    MPI_Wait(&request, &status);
    cout << "Receiver: MPI_Irecv() operation completed." << endl;

    //case 2 test after
    MPI_Test(&request, &flag, &status);
    if(flag == 1)
    {
        cout << "Receiver: MPI_Irecv() operation completed." << endl;
    }
    else
    {
        cout << "Receiver: MPI_Irecv() operation not completed yet." << endl;
    }

}
MPI_Finalize();
return 0;
}