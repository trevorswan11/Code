import tkinter as tk
from datetime import datetime

def count_up():
    current_time = datetime.now().strftime("%I:%M:%S %p")
    label.config(text=current_time)
    root.after(1000, count_up)

def close_window():
    root.destroy()

root = tk.Tk()
root.geometry("200x100+500+500")
root.resizable(True, True)
root.title("Exam Clock")

label = tk.Label(root, font=("Arial", 24))
label.pack(pady=10)

count_up()

root.mainloop()