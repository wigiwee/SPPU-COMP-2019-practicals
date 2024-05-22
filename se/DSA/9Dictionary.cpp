#include <iostream>    //Header Files
using namespace std;
struct HBTNode  //Structure for HBTre Node
{
    int Key;
    char Mean[10];
    HBTNode *left;
    HBTNode *right;
} *Root;

void create_HBT()  //Function to store keywords and meanings in HBT
{
    int i;
    int nodes;
    int done;
    struct HBTNode *Newnode, *current;
    cout << "\n\n Enter the no of nodes to insert in HBT.? : ";
    cin >> nodes;
    for (i = 0; i < nodes; i++)
    {
        Newnode = new struct HBTNode;   //memory allocation
        cout << "\n\t Enter Keyword: ";    //store keys
        cin >> Newnode->Key;
        cout << "\n\t Enter Meaning: ";   //store meanings
        cin >> Newnode->Mean;
        Newnode->left = NULL;   //left and right pointers initiall null

        Newnode->right = NULL;
        if (Root == NULL)
        {
            Root = Newnode;
        }
        else
        {
            done = 0;
            current = Root;
            while (!done)
            {
                if (Newnode->Key < current->Key)
                {
                    if (current->left == NULL)
                    {
                        current->left = Newnode;
                        done = 1;
                    }
                    else
                        current = current->left;
                }
                else
                {
                    if (current->right == NULL)
                    {
                        current->right = Newnode;
                        done = 1;
                    }
                    else
                        current = current->right;
                }
            } //while
        }     //else
    }         //for
} //function end
//Function to display keywords and meanings in HBT
void display_HBT(struct HBTNode *root)
{
    if (root)    //pre-order display
    {  
        cout << "\n\t" << root->Key << " - " << root->Mean; //...Data
        display_HBT(root->left);                            //...Left
        display_HBT(root->right);                           //...Right
    }
}


void Sorted_List(struct HBTNode *root)
{
    if (root)
    {
        Sorted_List(root->left);                            //...Left
        cout << "\n\t" << root->Key << " - " << root->Mean; //...Data
        Sorted_List(root->right);                           //...Right
    }
}

void Find_Keyword(int key)
{
    int comp = 0;
    int level = 0;
    int done;
    struct HBTNode *current;
    done = 0;
    current = Root;
    while (!done)
    {
        if (key < current->Key)
        {
            current = current->left;
            level++;
            comp++;
        }
        else if (key > current->Key)
        {
            current = current->right;
            level++;
            comp++;
        }
        else
        {
            done = 1;
            comp++;
            cout << "\n\t Key : " << key;
            cout << "\n\t Found at Level: " << level;
            cout << "\n\t No. of Comparisons: " << comp;
        }
    }
}

int main()
{
    cout << "\n -------***A C++ Program to Implement Dictionary using Height-Balanced Tree.* **-- -- -- -\n ";
    cout<< "\n 1. Store Keywords and Meanings in Height-Balanced Tree.";
    Root = NULL;
    create_HBT();
    cout << "\n 2. Display Keywords and Meanings in Height-Balanced Tree.";
    cout << "\n Keyword - Meaning";
    display_HBT(Root);
    cout << "\n 3. Display a Sorted List of Keywords and Meanings.";
    cout << "\n Keyword - Meaning";
    Sorted_List(Root);
    cout << "\n 4. Display Number of Comparisons required to find a keyword.";
    Find_Keyword(1);
    return 0;
}
