#include <iostream>
#include <string.h>
using namespace std;
struct node {
    int time ;
    string name;
    int index;
    struct node* next;
};

class FlightPath{
    public :
        int visited[20] ;
        struct node *adjacencyList[20];
        string names[20];
        int noOfCities;

        void getGraph();
        void displayGraph();
        void checkIfConnected();
        void DFS(int);
};

void FlightPath::getGraph(){
    char ch;
    cout<<"Enter the no of cities: "<<endl;
    cin>>noOfCities;
    cout<<"Enter the name of the cities: "<<endl;
    for(int i = 0 ; i < noOfCities; i++){
        cin>>names[i];
    }
    for(int i = 0 ; i < noOfCities; i++){
        adjacencyList[i] = new struct node;
        adjacencyList[i]->name = names[i];
        adjacencyList[i]->index = i;
        adjacencyList[i]->time = 0 ; 
        adjacencyList[i]->next = nullptr;
        for(int j = 0 ; j < noOfCities; j++){
            if (i == j) continue;
            struct node* lastNode = adjacencyList[i];
            while(lastNode->next != nullptr){
                lastNode= lastNode->next;
            }
           
            cout<<"is there a path between "<<names[i]<<" and "<<names[j]<<endl;
            cin>>ch;
            if(ch == 'y'){
                struct node* newNode = new node;
                newNode->index = j;
                newNode->name = names[j];
                newNode->next = nullptr;
                cout<<"Enter the time taken: "<<endl;   
                cin>>newNode->time;
                lastNode->next = newNode;
            }
                        
        }
    }
}
void FlightPath::displayGraph(){
    struct node* temp;
    for(int i = 0 ; i < noOfCities; i++){
        temp = adjacencyList[i];
        while(temp->next!= nullptr){
            cout<<temp->name<<"("<<temp->time<<") ==> ";
            temp = temp->next;
        }
        cout<<temp->name<<endl;
    }
}

void FlightPath::DFS(int i){
    visited[i] = 1;
    node* temp = adjacencyList[i]->next;
    while(temp != nullptr){
        if(visited[temp->index] == 1){
            temp = temp->next;
            continue;
        }      
        DFS(temp->index);
        temp = temp->next;
    }  
}
void FlightPath::checkIfConnected(){
    
    for(int  i = 0 ; i <noOfCities ; i++){
        visited[i] = 0;
    } 
    DFS(0);
    for(int i = 0 ;  i < noOfCities; i++){
        if(visited[i] == 0){
            cout<<"Graph is not connected"<<endl;
            return;
        }
    }
    cout<<"Graph is connected"<<endl;
    
} 
int main(){
    FlightPath FlightPath;
    FlightPath.getGraph();
    FlightPath.displayGraph();
    FlightPath.checkIfConnected();
    return 0;
}