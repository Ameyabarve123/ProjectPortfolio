## Purpose of Project
The purpose of this website is to provide a seamless way for users to obtain lyrics to their favorite songs. Although many mainstream streaming services display song lyrics to their users, most applications do not offer the ability to download lyrics. This project bridges that gap by offering an easy-to-use interface for downloading song captions.

# Technologies
## ReactJS Frontend
* ReactJS serves as the framework for the frontend, providing an interactive and responsive user interface.
* Users can log in to their Spotify accounts directly from the app to access their playlists and search for songs.

## Spotify API
* The Spotify API is used for user authentication and fetching song data such as playlists, saved tracks, and individual song details.

## YouTube API
* The YouTube API is utilized to search for corresponding videos or lyric videos based on the song selected by the user.

## Flask Server
* The Flask server handles backend operations, including file name sanitization, retrieving captions from YouTube videos, and serving them as downloadable files.

# Starting Server
## Starting Flask Server (IMPORTANT)
cd into flaskServer folder.
write venv/Scripts/activate into terminal.
write python server.py into terminal.

## Starting React Server (IMPORTANT)
If already running Flask Server, open a new terminal. 
cd into myapp folder.
write npm start in termal. 