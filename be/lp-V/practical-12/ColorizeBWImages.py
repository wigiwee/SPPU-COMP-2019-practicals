# Mini Project: Colorizing Old Black & White Images

import cv2
import numpy as np

# Load pre-trained colorization model files
# Download these files:
# 1. colorization_deploy_v2.prototxt
# 2. colorization_release_v2.caffemodel
# 3. pts_in_hull.npy

# Load model
net = cv2.dnn.readNetFromCaffe(
    "colorization_deploy_v2.prototxt",
    "colorization_release_v2.caffemodel"
)

# Load cluster centers
pts = np.load("pts_in_hull.npy")

# Populate cluster centers
class8 = net.getLayerId("class8_ab")
conv8 = net.getLayerId("conv8_313_rh")

pts = pts.transpose().reshape(2, 313, 1, 1)

net.getLayer(class8).blobs = [pts.astype("float32")]
net.getLayer(conv8).blobs = [np.full([1, 313], 2.606,
                                     dtype="float32")]

# Load black & white image
image = cv2.imread("bw_image.jpg")

# Normalize image
scaled = image.astype("float32") / 255.0

# Convert to LAB color space
lab = cv2.cvtColor(scaled, cv2.COLOR_BGR2LAB)

# Extract L channel
L = lab[:, :, 0]

# Resize for network
resized = cv2.resize(L, (224, 224))
resized -= 50

# Predict AB channels
net.setInput(cv2.dnn.blobFromImage(resized))
ab = net.forward()[0, :, :, :].transpose((1, 2, 0))

# Resize output
ab = cv2.resize(ab, (image.shape[1], image.shape[0]))

# Combine L and AB channels
colorized = np.concatenate((L[:, :, np.newaxis], ab), axis=2)

# Convert LAB to BGR
colorized = cv2.cvtColor(colorized, cv2.COLOR_LAB2BGR)

# Clip values
colorized = np.clip(colorized, 0, 1)

# Save output image
cv2.imwrite("colorized_output.jpg",
            (255 * colorized).astype("uint8"))

print("Image colorization completed.")
print("Saved as: colorized_output.jpg")
