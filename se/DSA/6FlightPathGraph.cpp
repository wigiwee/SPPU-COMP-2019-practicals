#include <iostream>
#include <string>
using namespace std;

struct node{
    string name;
    int time;
    struct node * next;
    int index;
};

class FlightPath{
    struct node * adjacencyList[20];
    string cityName[20];
    int no_of_cities;
    int visited[20] ;
    public:
        void getGraph();
        void displayAdjacencyList();
        void checkIfConnected();
        void DFS(int);
};

void FlightPath::getGraph(){
    cout<<"Enter the no. of cities: "<<endl;
    cin>>no_of_cities;
    cout<<"Enter the name of the cities: "<<endl;
    for(int i = 0 ; i < no_of_cities; i++){
        cin>>cityName[i];
    }
    char ch;
    for(int i = 0 ; i < no_of_cities ; i++){
        adjacencyList[i] = new struct node;
        adjacencyList[i]->index = i;
        adjacencyList[i]->next = nullptr;
        adjacencyList[i]->name = cityName[i];
        adjacencyList[i]->time = 0;
        for(int j = 0 ; j < no_of_cities; j++){
            if(i == j) continue;
            cout<<"Is there a path between "<<cityName[i]<<" and "<<cityName[j]<<" (y/n)"<<endl;
            cin>>ch;
            if( ch == 'y'){
                struct node* lastNode = adjacencyList[i];
                while(lastNode->next != nullptr){
                    lastNode = lastNode->next;
                }
                struct node *newNode =  new struct  node;
                newNode->index = j;
                newNode->name = cityName[j];
                newNode->next = nullptr;
                cout<<"Enter the time required :"<<endl;
                cin>>newNode->time ;
                lastNode->next = newNode;
            }
        }
    }
};

void FlightPath::displayAdjacencyList(){
    struct node* temp ;
    for(int i = 0 ; i < no_of_cities; i++){
        temp = adjacencyList[i];
        while(temp->next != nullptr){
            cout<<temp->name<<" =="<<temp->next->time<<"=>";
            temp = temp->next;
        }
        cout<<temp->name<<endl;
    }
};

void FlightPath::DFS(int i  ){
    visited[i] =1;
    struct node *temp = adjacencyList[i]->next;
    if(temp == nullptr) return;
    while( temp != nullptr) {
        if(visited[temp->index] == 1) {
            temp = temp->next;
            continue;
        }
        DFS(temp->index);
        temp = temp->next;
    }
};

void FlightPath::checkIfConnected(){
    for(int i = 0 ; i < no_of_cities; i++){
        visited[i] = 0;
    }
    DFS(0);
    for(int i = 0 ; i < no_of_cities; i++){
        if(visited[i] == 0){
            cout<<"Graph is not connected"<<endl;
            return;
        }
    }
    cout<<"Graph is connected"<<endl;
};

int main(){
    FlightPath flightpath ;
    while(true){
        
        cout<<"\n=========menu=========="<<endl;
        cout<<"1. Create flight path graph \n2. Display Adjacency list of graph \n3.Check if the graph is connected\n4. Exit"<<endl;
        cout<<"Enter your choice: "<<endl;
        int ch;
        cin>>ch;
        if(ch == 1) {
            cout<<"\n"<<endl;
            flightpath.getGraph();
        }else if (ch ==2)  {
            cout<<"\n"<<endl;
            cout<<"Adjacency List of the graph: "<<endl;
            flightpath.displayAdjacencyList();
        }else if(ch ==3)  {
            cout<<"\n"<<endl;
            flightpath.checkIfConnected();
        }else if(ch == 4) break;
        else {
            cout<<"Enter a valid choice!!!"<<endl;
        }
    }
    cout<<"Exiting program"<<endl;
    return 0;
}