
class Node:
    def __init__(self, name, mobile_number):
        self.name = name
        self.mobile_number = mobile_number
        self.left = None
        self.right = None


def insert_friend(root, friend):
    if root is None:
        return Node(friend.name, friend.mobile_number)

    if friend.name < root.name:
        root.left = insert_friend(root.left, friend)
    elif friend.name > root.name:
        root.right = insert_friend(root.right, friend)
    else:
        print("Friend already exists in the phonebook")

    return root


def search_friend_recursive(root, friend_name):
    if root is None:
        return False

    if friend_name == root.name:
        print("Friend found:", root.mobile_number)
        return True
    elif friend_name < root.name:
        return search_friend_recursive(root.left, friend_name)
    else:
        return search_friend_recursive(root.right, friend_name)


def search_friend_non_recursive(root, friend_name):
    while root is not None:
        if friend_name == root.name:
            print("Friend found:", root.mobile_number)
            return True
        elif friend_name < root.name:
            root = root.left
        else:
            root = root.right

    return False


def main():
    root = None

    # Add some friends to the phonebook
    friend1 = Node("Alice", "1234567890")
    friend2 = Node("Bob", "9876543210")
    friend3 = Node("Charlie", "1122334455")

    root = insert_friend(root, friend1)
    root = insert_friend(root, friend2)
    root = insert_friend(root, friend3)

    # Search for a friend using recursive binary search
    friend_name = input("Enter the friend's name to search: ")
    if search_friend_recursive(root, friend_name):
        print("Friend found using recursive binary search")

    # Search for a friend using non-recursive binary search
    friend_name = input("\nEnter the friend's name to search: ")
    if search_friend_non_recursive(root, friend_name):
        print("Friend found using non-recursive binary search")


if __name__ == "__main__":
    main()



class Node:
    def __init__(self, name, mobile_number):
        self.name = name
        self.mobile_number = mobile_number
        self.left = None
        self.right = None


def insert_friend(root, friend):
    if root is None:
        return Node(friend.name, friend.mobile_number)

    if friend.name < root.name:
        root.left = insert_friend(root.left, friend)
    elif friend.name > root.name:
        root.right = insert_friend(root.right, friend)
    else:
        print("Friend already exists in the phonebook")

    return root


def search_friend_fibonacci(root, friend_name):
    if root is None:
        return False

    n = get_tree_size(root)
    f2 = 1
    f1 = 0
    f0 = 0

    while f2 <= n:
        f0 = f1
        f1 = f2
        f2 = f1 + f0

    offset = 0
    while f2 > 1:
        next = offset + f1
        if next <= n and root.left.name != friend_name:
            offset = next + 1
            f1 = f2
            f2 = f0 - f1
        else:
            f0 = f1
            f1 = f2
            f2 -= f0

    if f1 == 1 and root.right.name == friend_name:
        print("Friend found:", root.right.mobile_number)
        return True
    else:
        return False


