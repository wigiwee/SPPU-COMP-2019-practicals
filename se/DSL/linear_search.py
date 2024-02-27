def linear_search(arr, target):
    for i in range(len(arr)):
        if arr[i] == target:
            return i  # Return the index if the target is found
    return -1  # Return -1 if the target is not found

# Take user input for the list of numbers
input_list = input("Enter a list of numbers separated by spaces: ").split()

# Convert the input values to integers
numbers = [int(num) for num in input_list]

# Take user input for the target number to search
target = int(input("Enter the number to search for: "))

# Perform the linear search
result = linear_search(numbers, target)

if result != -1:
    print(f"{target} found at index {result}.")
else:
    print(f"{target} not found in the list.")