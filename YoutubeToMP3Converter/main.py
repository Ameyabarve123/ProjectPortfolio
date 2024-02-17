from pytube import YouTube
from moviepy.editor import *
from tkinter import filedialog
from tkinter import *
import os

yt = YouTube(input("Enter the URL: "))


#destination = r"C:\Users\barve\OneDrive\Desktop\SpotifyDownloads"
destination = filedialog.askdirectory()

#the r is neccessary, so this is a raw string... because its backslashes and not forward slashes, we need that r


video = yt.streams.get_highest_resolution()


out_file = video.download(output_path = destination)
#automatically downloads in ".mp4"

print(out_file)

mp3file= input('\nSong Title: ')


videoclip = VideoFileClip(out_file)
audioclip = videoclip.audio
audioclip.write_audiofile(destination + '\\' + mp3file + '.mp3')
#have to have the ".mp3" because it tells the write_audiofile what type of audio to be written as... ex: audio types could be WAV, M4A, MP3

audioclip.close()
videoclip.close()

os.remove(out_file)

print(yt.title + ' has been downloaded.')