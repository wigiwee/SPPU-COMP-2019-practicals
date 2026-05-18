# Multiclass Classification using Deep Neural Network
# OCR Letter Recognition Dataset

import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder, StandardScaler
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical

# Load dataset
# Download letter-recognition.data from:
# https://archive.ics.uci.edu/ml/datasets/letter+recognition

data = pd.read_csv(
    "letter-recognition.data",
    header=None
)

# First column = target labels
X = data.iloc[:, 1:].values
y = data.iloc[:, 0].values

# Encode labels (A-Z -> 0-25)
encoder = LabelEncoder()
y = encoder.fit_transform(y)

# One-hot encoding
y = to_categorical(y)

# Feature scaling
scaler = StandardScaler()
X = scaler.fit_transform(X)

# Split dataset
X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.2,
    random_state=42
)

# Build Deep Neural Network
model = Sequential()

model.add(Dense(128, activation='relu', input_shape=(16,)))
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(26, activation='softmax'))

# Compile model
model.compile(
    optimizer='adam',
    loss='categorical_crossentropy',
    metrics=['accuracy']
)

# Train model
model.fit(
    X_train,
    y_train,
    epochs=20,
    batch_size=32,
    validation_split=0.1
)

# Evaluate model
loss, accuracy = model.evaluate(X_test, y_test)

print("\nTest Accuracy:", accuracy)

# Predictions
predictions = model.predict(X_test)

print("\nSample Predictions:")

for i in range(5):
    predicted = np.argmax(predictions[i])
    actual = np.argmax(y_test[i])

    print(
        "Actual:",
        encoder.inverse_transform([actual])[0],
        "Predicted:",
        encoder.inverse_transform([predicted])[0]
    )
