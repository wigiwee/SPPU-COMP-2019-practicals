# Step 1: Import required libraries
import seaborn as sns
import matplotlib.pyplot as plt

# Step 2: Load Titanic dataset from seaborn
dataset = sns.load_dataset('titanic')

# Display the first 5 rows of the dataset
print(dataset.head())

# Step 3: Basic Boxplot of Age by Sex
plt.figure(figsize=(8, 5))
sns.boxplot(x='sex', y='age', data=dataset)
plt.title("Boxplot of Age by Sex")  
plt.show()

# Step 4: Boxplot of Age by Sex, Separated by Survival
plt.figure(figsize=(8, 5))
sns.boxplot(x='sex', y='age', data=dataset, hue='survived')
plt.title("Boxplot of Age by Sex and Survival")
plt.legend(title="Survived", labels=["No", "Yes"])
plt.show()