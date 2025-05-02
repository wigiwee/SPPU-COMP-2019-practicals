import pandas as pd  

# loading dataset
df = pd.read_csv("Social_Network_Ads.csv")
df

# preprocessing
df['Gender']
df.isnull().sum()
df.dtypes
df['Gender'] = df['Gender'].map({'Male':1, 'Female':0})
df['Gender']
df

# train test split
X = df.drop(['Purchased', 'User ID'], axis=1)
y = df['Purchased']

X
y
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y,test_size=0.25, random_state=0)

# scaling the features
from sklearn.preprocessing import StandardScaler
ss = StandardScaler()
X_train = ss.fit_transform(X_train)
X_test = ss.transform(X_test)

X_train
from sklearn.linear_model import LogisticRegression
classifier= LogisticRegression(random_state=0)
classifier.fit(X_train, y_train)

y_pred = classifier.predict(X_test)
classifier.score(X_test, y_test)

# evaluating the performance using confusion matrix
from sklearn.metrics import confusion_matrix
cm = confusion_matrix(y_test, y_pred)
cm
