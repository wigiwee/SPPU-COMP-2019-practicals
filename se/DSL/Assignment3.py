
def delete_duplicates(books):
    unique_books = []
    for book in books:
        if book not in unique_books:
            unique_books.append(book)
    return unique_books


def sort_by_cost(books):
    sorted_books = sorted(books, key=lambda book: book["cost"])
    return sorted_books


def count_expensive_books(books):
    expensive_books = 0
    for book in books:
        if book["cost"] > 500:
            expensive_books += 1
    return expensive_books


def copy_affordable_books(books):
    affordable_books = []
    for book in books:
        if book["cost"] <= 500:
            affordable_books.append(book)
    return affordable_books


# Example books data
books = [
    {"title": "The Lord of the Rings", "author": "J.R.R. Tolkien", "cost": 350},
    {"title": "The Hobbit", "author": "J.R.R. Tolkien", "cost": 250},
    {"title": "Harry Potter and the Sorcerer's Stone", "author": "J.K. Rowling", "cost": 400},
    {"title": "The Hitchhiker's Guide to the Galaxy", "author": "Douglas Adams", "cost": 300},
    {"title": "Pride and Prejudice", "author": "Jane Austen", "cost": 200},
    {"title": "To Kill a Mockingbird", "author": "Harper Lee", "cost": 250},
    {"title": "The Lord of the Rings", "author": "J.R.R. Tolkien", "cost": 350}
]

# Remove duplicate books
unique_books = delete_duplicates(books)
print("Unique books:")
for book in unique_books:
    print(book)

# Sort books by cost
sorted_books = sort_by_cost(books)
print("\nBooks sorted by cost:")
for book in sorted_books:
    print(book)

# Count expensive books
expensive_books = count_expensive_books(books)
print("\nNumber of books with cost more than 500:", expensive_books)

# Copy affordable books
affordable_books = copy_affordable_books(books)
print("\nBooks with cost less than 500:")
for book in affordable_books:
    print(book)