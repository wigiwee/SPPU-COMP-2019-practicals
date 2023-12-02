"""


Experiment No. 1 : In a second year computer engineering class, group A students play cricket, group B students play
                   badminton and group C students play football.
                   Write a python program using functions to compute following:
                   a) List of students who play both cricket and badminton.
                   b) List of students who play either cricket or badminton but not both.
                   c) Number of students who play neither cricket nor badminton.
                   d) Number of students who play cricket and football but not badminton.
(NOTE : While realising the group, duplicate entries should be avoided. Do not use SET built-in functions)
"""


def removeDuplicate(d):
    lst = []
    for val in d:
        if val not in lst:
            lst.append(val)
    return lst


def intersection(lst1, lst2):
    lst = []
    for val in lst1:
        if val in lst2:
            lst.append(val)
    return lst


def union(lst1, lst2):
    lst = []
    for val in lst1:
        if val not in lst:
            lst.append(val)
    for val in lst2:
        if val not in lst:
            lst.append(val)
    return lst


def diff(lst1, lst2):
    lst = []
    for val in lst1:
        if val not in lst2:
            lst.append(val)
    return lst


def symmDiff(lst1, lst2):
    list_union = union(lst1, lst2)
    list_diff = diff(lst1, lst2)
    lst = diff(list_union, list_diff)
    return lst


SE_comp = []
n = int(input("Enter the number of students in second year computer class\n"))
print("Enter the names of the students, press Enter after the name of each student\n")
for i in range(0, n):
    name = input()
    SE_comp.append(name)
SE_comp = removeDuplicate(SE_comp)
print("The Final list (After removing duplicates)of students in second year computer class is \n", SE_comp, "\n")

Cricket = []
n = int(input("Enter the number of students who play cricket\n"))
print("Enter the names of the students, press Enter after the name of each student\n")
for i in range(0, n):
    name = input()
    Cricket.append(name)
Cricket = removeDuplicate(Cricket)
print("The Final list (After removing duplicates)of students who play Cricket \n", Cricket, "\n")

Football = []
n = int(input("Enter the number of students who play football\n"))
print("Enter the names of the students, press Enter after the name of each student\n")
for i in range(0, n):
    name = input()
    Football.append(name)
Football = removeDuplicate(Football)
print("The Final list (After removing duplicates)of students who play Football\n", Football, "\n")

Badminton = []
n = int(input("Enter the number of students who play Badminton\n"))
print("Enter the names of the students, press Enter after the name of each student\n")
for i in range(0, n):
    name = input()
    Badminton.append(name)
Badminton = removeDuplicate(Badminton)
print("The Final list (After removing duplicates)of students who play Badminton \n", Badminton, "\n")

while True:
    print("\n\n--------------------MENU--------------------\n")
    print("1. List of students who play both cricket and badminton")
    print("2. List of students who play either cricket or badminton but not both")
    print("3. List of students who play neither cricket nor badminton")
    print("4. Number of students who play cricket and football but not badminton")
    print("5. Exit\n")
    ch = int(input("Enter your Choice: "))
    print("\n")
    if ch == 1:
        print("Students who play both Cricket and Football are:\n")
        print(intersection(Cricket, Football))
    if ch == 2:
        print("Students who play either cricket or badminton but not both are:\n")
        print(diff(union(Cricket, Badminton), intersection(Cricket, Badminton)))
    if ch == 3:
        requiredList = diff(Football, Cricket)
        requiredList = diff(requiredList, Badminton)
        print("Students who play neither cricket nor badminton:\n")
        print(requiredList)
    if ch == 4:
        askedList = diff(intersection(Cricket, Football), Badminton)
        print("Students who play cricket and football but not badminton:\n")
        print(askedList)
    if ch == 5:
        break

    print("Do you wish to Continue:(y/n)\n")
    ans = input()
    if ans == "n":
        break
