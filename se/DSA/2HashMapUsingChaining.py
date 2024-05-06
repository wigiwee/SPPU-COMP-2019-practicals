TABLE_SIZE = 20

elements = [None]*TABLE_SIZE
chain = [-1]*TABLE_SIZE    
    			
def hashFunction( data):
		return data % TABLE_SIZE

def insert(key):
    index =hashFunction(key)
    if(elements[index] == None ):
        elements[index] = key
    else:
        i = 0
        while(chain[index] !=-1):
            index = chain[index]
        j = index
        while(i < TABLE_SIZE-1):
            index = index% TABLE_SIZE
            if(elements[index] == None):
                elements[index] = key
                chain[j] = index
                return
            
            index = index +1
        print("[ERROR] Table is full")
    return

def search(key):
    index = hashFunction(key)
    while(elements[index] != key):
        index = chain[index]
        if(index == -1):
            print("key not found in the table")
            return
    print("Key Found ")

def delete(key):
    index = hashFunction(key)
    count = 0
    while(elements[index] != key):
        index  = chain[index]
        count = count+1
        if(index ==-1):
            print("[ERROR]key not present in the table ")
            return
    if(count == 0 and chain[index] != -1):
        next = elements[chain[index]]     
        nextChain = chain[index]   
        nextChain = chain[nextChain]
        delete(next)
        elements[index] = next
        chain[index] = nextChain
        return
    i = chain[index]
    m = count
    elements[index] = None
    chain[index] = -1
    if(count ==1 ):
        return
    index = hashFunction(key)
    count = count -1
    while(count != 0):
        index = chain[index]
        count = count -1
    
    chain[index] = i
    return

def display():
    i = 0
    while(i<TABLE_SIZE):
        if(elements[i] == None):
            element = "None"
            chainstr = str(chain[i])
        else:
            element = str(elements[i])
            chainstr = str(chain[i])
        print(element+"\t" +chainstr)
        i=i +1

while True:
    print("""
1. Insert in the hash table
2. Search in the hash table
3. Delete from the hash table
4. Display the hash table
5. Exit
    """)
    choice = int(input("Enter your choice: "))

    if(choice ==1):
        i = int(input("Enter the no. you want to insert: "))
        insert(i)
    elif(choice ==2):
        i = int(input("Enter the no. you want to search: "))
        search(i)
    elif(choice ==3):
        i = int(input("Enter the no. you want to delete: "))
        delete(i)
    elif(choice == 4):
        display()
    elif(choice == 5):
        exit()
