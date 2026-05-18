# Binary Classification using Deep Neural Network
# IMDB Movie Review Sentiment Analysis

from tensorflow.keras.datasets import imdb
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Embedding, GlobalAveragePooling1D
from tensorflow.keras.preprocessing.sequence import pad_sequences

# Load IMDB dataset
# Keep only top 10000 most common words
vocab_size = 10000

(X_train, y_train), (X_test, y_test) = imdb.load_data(num_words=vocab_size)

# Pad sequences to same length
max_length = 200

X_train = pad_sequences(X_train, maxlen=max_length)
X_test = pad_sequences(X_test, maxlen=max_length)

# Build Deep Neural Network
model = Sequential()

# Embedding layer
model.add(Embedding(vocab_size, 32, input_length=max_length))

# Convert sequence to fixed-size vector
model.add(GlobalAveragePooling1D())

# Hidden layers
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))

# Output layer
model.add(Dense(1, activation='sigmoid'))

# Compile model
model.compile(
    optimizer='adam',
    loss='binary_crossentropy',
    metrics=['accuracy']
)

# Train model
model.fit(
    X_train,
    y_train,
    epochs=5,
    batch_size=32,
    validation_split=0.1
)

# Evaluate model
loss, accuracy = model.evaluate(X_test, y_test)

print("\nTest Accuracy:", accuracy)

# Predict reviews
predictions = model.predict(X_test)

print("\nSample Predictions:")

for i in range(5):

    sentiment = "Positive" if predictions[i][0] > 0.5 else "Negative"
    actual = "Positive" if y_test[i] == 1 else "Negative"

    print("Actual:", actual, "| Predicted:", sentiment)
}
