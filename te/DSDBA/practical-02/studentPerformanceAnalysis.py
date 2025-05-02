import pandas as pd 
import numpy as np

data = pd.read_csv("./StudentPerformance.csv")

data 

# checking for null values
data.isnull().count()

data.notnull().count()

# using Label Encoding  
from sklearn.preprocessing import LabelEncoder
le = LabelEncoder()
data

data['placement_offer_count'] = le.fit_transform(data['placement_offer_count'])

data

# replacing null values with NaN
missing_values = ["Na", "na"]

df = pd.read_csv("StudentPerformance.csv", na_values =missing_values)

df

# filling null values with a single value

df = pd.read_csv("StudentPerformance.csv")
df
df = df.fillna(0)
df

df['math_score']= df['math_score'].fillna(df['math_score'].mean()) # filling missing values with mean
df['math_score']= df['math_score'].fillna(df['math_score'].median()) # filling missing values with median
df['math_score']= df['math_score'].fillna(df['math_score'].mode()) # filling missing values with mode
df['math_score']= df['math_score'].fillna(df['math_score'].min()) # filling missing values with min 
df['math_score']= df['math_score'].fillna(df['math_score'].max()) # filling missing values with max

# use inplace=True to avoid df['x'] = df['x'].something

# filling null values using replace() methond
df.replace(to_replace=np.NaN, value=-99)

# Deleting null values using dropna() method 
df = pd.read_csv("StudentPerformance.csv")
df
df.dropna() # to drop row with at least 1 null value
df.dropna(how='all') # to drop rows having all values null
df.dropna(axis=1) # to drop column with at least 1 null value
df.dropna(axis=0, how='any') # to drop rows with at least 1 null value in a row
df

# detecting outliers using boxplot
df = pd.read_csv("StudentPerformance.csv")
df
col=['math_score', 'reading_score', 'writing_score', 'placement_offer_count']
df.boxplot(col)
print(np.where(df['math_score']>90))
print(np.where(df['reading_score']<25))
print(np.where(df['writing_score']<30))

# detecting outlers using scatterplot
fig, ax = plt.subplots(figsize= (18,10))
ax.scatter(df['placement_score'], df['placement_offer_count'])
plt.show()

# detecting outlers using Z score
from scipy import stats
z = np.abs(stats.zscore(df['math_score']))
print(z)
threshold = 0.18

sample_outlers = np.where(z < threshold)
sample_outlers
df['math_score'].plot(kind='hist')
df['log_math'] = np.log10(df['math_score'])
df['log_math'].plot(kind='hist')