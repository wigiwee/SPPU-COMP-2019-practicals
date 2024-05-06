/*
Author: Kaustubh Kolhe
Problem Statement: Beginning with an empty binary search tree, construct binary search tree by
inserting the values in the order given. After constructing a binary tree -
i. Insert new node
ii. Find number of nodes in longest path from root
iii. Minimum data value found in the tree
iv. Change a tree so that the roles of the left and right pointers are swapped at every node
v. Search a value
*/

#include <iostream>

using namespace std;

struct node {
    int data;
    struct node *left , *right;
};

struct node* newNode(int item)
{
	struct node* temp = new struct node;
	temp->data = item;
	temp->left = temp->right = NULL;
	return temp;
};

void insert(struct node* root , int data){
    while(true){
        if(data < root->data){
            if(root->left == NULL){
                root->left = newNode(data);
                return;
            }
            root = root->left;
        }else{
            if(root->right == NULL){
                root->right = newNode(data);
                return;
            }
            root = root->right;
        }
    }
}
bool search(struct node* root , int key){
    while(true){
        if(root == NULL){
            return false;
        }else if(root->data == key){
            return true;
        }else if(key < root->data){
            root= root->left;
        }else if(key >= root->data){
            root = root->right;
        }
    }
}
int minimum(struct node* root ){
    while(root->left != NULL){
        root = root->left;
    }
    return root->data;
}
int main (){
    struct node * root = newNode(4);
    insert(root, 2);
    insert(root, 9);
    insert(root, 3);
    insert(root, 5);
    insert(root , 32);
    insert(root , 23);
    insert(root, 21);
    cout<<search(root, 2)<<endl;
    cout<<search(root, 5)<<endl;
    cout<<search(root, 23)<<endl;
    cout<<search(root, 21)<<endl;
    cout<<search(root, 44)<<endl;
    insert(root, -99);
    cout<<minimum(root)<<endl;
    return 0;
}