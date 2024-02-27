"""

Experiment Number 2 : Write a python program to store marks stored in subject "Fundamentals of Data Structure" by
                         N students in the class. Write functions to compute following:
                         1. The average score of the class.
                         2. Highest score and lowest score of the class.
                         3. Count of students who were absent for the test.
                         4. Display mark with the highest frequency.
"""


def average(list):
    sum = 0
    count = 0
    for marks in list:
        if marks != -1:
            sum = sum + marks
            count = count + 1
    return (sum, sum / count)


def countAbsent(list):
    count = 0
    for val in list:
        if val == -1:
            count = count + 1
    return count


def maximum(list):
    max = 0
    for marks in list:
        if marks > max:
            max = marks
    return max


def minimum(list):
    min = list[0]
    for marks in list:
        if marks < min and marks != -1:
            min = marks
    return min


def maxFrequency(list):
    i = 0
    maxFreq = 0
    maxFreqMarks = 0
    for j in list:
        if list.index(j) == i:
            if list.count(j) > maxFreq:
                maxFreq = list.count(j)
                maxFreqMarks = j
                i = i + 1
    return (maxFreq, maxFreqMarks)


# main method

FDSMarks = []
n = int(input("Enter the Number of students in the class\n"))
print("Enter the marks of the students NOTE: for absent students put -1 as marks\n")
for i in range(0, n):
    mark = int(input())
    FDSMarks.append(mark)
while True:
    print("\n\n--------------------MENU--------------------\n")
    print("1. Total and Average Marks of the Class")
    print("2. Highest and Lowest Marks in the Class")
    print("3. Number of Students absent for the test")
    print("4. Marks with Highest Frequency")
    print("5. Exit\n")

    ch = int(input("Enter your choice\n"))
    if ch == 1:
        total, avg = average(FDSMarks)
        print("Total marks of FDS are", total, " And average marks of FDS are ", avg, "\n")
    if ch == 2:
        print("For FDS exam Hightest score is ", maximum(FDSMarks), " and Minimum score is ", minimum(FDSMarks), "\n")
    if ch == 3:
        print("Absent count for FDS exam is: ", countAbsent(FDSMarks), "\n")
    if ch == 4:
        maxfreq, maxfrewmarks = maxFrequency(FDSMarks)
        print("The most frequently appeared marks are", maxfrewmarks, " and it occurred ", maxfreq, " times\n")
    if ch == 5:
        break
    ans = input("do you wish to continue(y/n)\n")
    if ans == "n":
        break
