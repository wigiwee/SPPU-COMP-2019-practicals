import tkinter as tk
from tkinter import ttk, messagebox
import pandas as pd
import pickle

# Load movie data and cosine similarity matrix
with open('movie_data.pkl', 'rb') as file:
    movies, cosine_sim = pickle.load(file)

movie_titles = sorted(movies['title'].tolist())

# Recommendation function
def get_recommendations(title, cosine_sim=cosine_sim):
    if title not in movies['title'].values:
        return None
    idx = movies[movies['title'] == title].index[0]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:11]
    movie_indices = [i[0] for i in sim_scores]
    return movies['title'].iloc[movie_indices].tolist()

# Autocomplete Combobox Class
class AutocompleteCombobox(ttk.Combobox):
    def set_completion_list(self, completion_list):
        self._completion_list = sorted(completion_list, key=str.lower)
        self['values'] = self._completion_list
        self.bind('<KeyRelease>', self._check_input)

    def _check_input(self, event):
        if event.keysym == "BackSpace" or len(self.get()) == 0:
            self['values'] = self._completion_list
        else:
            pattern = self.get().lower()
            filtered = [item for item in self._completion_list if pattern in item.lower()]
            self['values'] = filtered
        self.event_generate('<Down>')

# GUI logic
def recommend_movies():
    movie_name = combo.get().strip()
    if not movie_name:
        messagebox.showwarning("Input Error", "Please enter a movie name.")
        return
    recommendations = get_recommendations(movie_name)
    listbox.delete(0, tk.END)
    if recommendations:
        for movie in recommendations:
            listbox.insert(tk.END, movie)
    else:
        messagebox.showinfo("Not Found", f"No recommendations found for '{movie_name}'.")

# Tkinter window
root = tk.Tk()
root.title("Movie Recommendation System")

tk.Label(root, text="Enter Movie Name:").pack(pady=5)

combo = AutocompleteCombobox(root, width=40)
combo.set_completion_list(movie_titles)
combo.pack(pady=5)

tk.Button(root, text="Recommend", command=recommend_movies).pack(pady=10)

tk.Label(root, text="Recommended Movies:").pack(pady=5)
listbox = tk.Listbox(root, width=50, height=10)
listbox.pack(pady=5)

root.mainloop()
