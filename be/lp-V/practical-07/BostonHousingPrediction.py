# Boston Housing Price Prediction using Deep Neural Network

import pandas as pd
import numpy as np
from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.optimizers import Adam

# Load dataset
boston = load_boston()

X = boston.data
y = boston.target

# Split dataset
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

# Feature scaling
scaler = StandardScaler()

X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

# Build Deep Neural Network
model = Sequential()

model.add(Dense(64, activation='relu', input_shape=(X_train.shape[1],)))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(1))  # Output layer

# Compile model
model.compile(
    optimizer=Adam(learning_rate=0.001),
    loss='mse',
    metrics=['mae']
)

# Train model
model.fit(
    X_train,
    y_train,
    epochs=100,
    batch_size=16,
    validation_split=0.1
)

# Evaluate model
loss, mae = model.evaluate(X_test, y_test)

print("\nTest Loss:", loss)
print("Mean Absolute Error:", mae)

# Predict house prices
predictions = model.predict(X_test)

print("\nSample Predictions:")
for i in range(5):
    print("Actual:", y_test[i], " Predicted:", predictions[i][0])
}
