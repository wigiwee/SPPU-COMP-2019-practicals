# Recurrent Neural Network (RNN)
# Google Stock Price Prediction using LSTM

import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, LSTM, Dropout
import matplotlib.pyplot as plt

# Load dataset
# Dataset file: Google_Stock_Price_Train.csv

dataset = pd.read_csv("Google_Stock_Price_Train.csv")

# Use Open price column
training_set = dataset.iloc[:, 1:2].values

# Feature Scaling
scaler = MinMaxScaler(feature_range=(0, 1))
training_set_scaled = scaler.fit_transform(training_set)

# Create data with 60 time steps
X_train = []
y_train = []

for i in range(60, len(training_set_scaled)):
    X_train.append(training_set_scaled[i-60:i, 0])
    y_train.append(training_set_scaled[i, 0])

X_train = np.array(X_train)
y_train = np.array(y_train)

# Reshape for RNN
X_train = np.reshape(
    X_train,
    (X_train.shape[0], X_train.shape[1], 1)
)

# Build RNN Model
model = Sequential()

# First LSTM layer
model.add(LSTM(units=50, return_sequences=True,
               input_shape=(X_train.shape[1], 1)))
model.add(Dropout(0.2))

# Second LSTM layer
model.add(LSTM(units=50, return_sequences=True))
model.add(Dropout(0.2))

# Third LSTM layer
model.add(LSTM(units=50, return_sequences=True))
model.add(Dropout(0.2))

# Fourth LSTM layer
model.add(LSTM(units=50))
model.add(Dropout(0.2))

# Output layer
model.add(Dense(units=1))

# Compile model
model.compile(
    optimizer='adam',
    loss='mean_squared_error'
)

# Train model
model.fit(
    X_train,
    y_train,
    epochs=20,
    batch_size=32
)

# Predicting future values
predicted_stock_price = model.predict(X_train)

# Convert back to original values
predicted_stock_price = scaler.inverse_transform(
    predicted_stock_price
)

real_stock_price = scaler.inverse_transform(
    y_train.reshape(-1, 1)
)

# Plot results
plt.plot(real_stock_price, color='red',
         label='Real Google Stock Price')

plt.plot(predicted_stock_price, color='blue',
         label='Predicted Google Stock Price')

plt.title('Google Stock Price Prediction')
plt.xlabel('Time')
plt.ylabel('Google Stock Price')
plt.legend()

plt.show()
