# Author: Kaustubh kolhe

# Problem Statement: Consider a telephone book database of N clients. Make use of a hash table
# implementation to quickly look up a client's telephone number. Make use of two collision handling
# techniques and compare them using number of comparisons required to find a set of telephone
# numbers (Python)


TABLE_SIZE = 10

class Person:
	def __init__(self, name  , element2, next) :
		self.name = name
		self.element2 = element2
		self.next = next
	
	def print(self):
		print("Name: ", self.name, "  Telephone No : " , self.element2)

class HashTable:
	elements = [None] *TABLE_SIZE	

	def search(self, data):
		data = data.strip()
		index = self.hashFunction(data)
		if(self.elements[index] != None):
			person = self.elements[index]
			while(person != None):
				if(person.name == data):
					person.print()
					return
				person = person.next
		print("data not found")
				
	def insert(self, data, value):
		data = data.strip()
		index = self.hashFunction(data)
		if(self.elements[index] == None):
			self.elements[index] = Person(data,value, None)
		else:
			person = self.elements[index]
			while(person.next != None):
				person = person.next
			person.next = Person(data, value, None)

	def hashFunction(self , data):
		data = data.lower()
		index = 0
		for x in data:
			index +=( ord(x)- 96)
		return index % TABLE_SIZE

hashTable = HashTable()
while(True):
	print( """ 
1. Enter a Telephone
2. Search a Person (by name)
3. Exit   
	   """)
	choice= int(input("Enter your choice : "))
	print(choice)
	if(choice ==1):
		name = input("Enter the name: ")
		element2 = int(input("Enter the telephone no. :  "))
		hashTable.insert(name, element2)
		print("")
	elif(choice==2):
		name = input("Enter the name you want to search : ")
		hashTable.search(name)
		print("")
	else:
		break
