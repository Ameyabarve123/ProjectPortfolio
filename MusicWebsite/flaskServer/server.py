from flask import Flask, request, jsonify, session
from flask_cors import CORS

from googleapiclient.discovery import build
from google.oauth2.credentials import Credentials

from googleapiclient.http import MediaIoBaseDownload

import io
import re

app = Flask(__name__)
app.secret_key = "youtubeClientKey"
CORS(app)

def sanitizeFileName(filename):
  return re.sub(r'[^\w\-_\. ]', '', filename).replace(' ', '_')

@app.route("/setYtToken", methods=['POST'])
def setYtToken():
  try:
    data = request.get_json()
    session["yt_token"] = data['ytToken']
    return data
  except Exception as e:
    print(f"Client creation failed: {e}")
    return jsonify({'error': 'Couldnt create client'})


# Songs API Route

@app.route("/downloadSong", methods=['POST'])
def downloadSong():
  try:
    data = request.get_json()

    credentials = Credentials(session["yt_token"])
    youtubeClient = build('youtube', 'v3', credentials=credentials)

    captionsList = youtubeClient.captions().list(
      part="snippet",
      videoId= data["videoId"],
    )
    captionsList = captionsList.execute()

    captionId = captionsList['items'][0]['id']

    captionRequest = youtubeClient.captions().download(
      id=captionId
    )

    if captionRequest:
      sanitzedName = sanitizeFileName(data["videoName"])
      filePath = "C:\\Users\\barvea\\Documents\\" + sanitzedName + ".txt"
      fh = io.FileIO(filePath, "wb")

      download = MediaIoBaseDownload(fh, captionRequest)
      complete = False

      while not complete:
        status, complete = download.next_chunk()
      return data
    else:
      return jsonify({'error': 'Song has no captions'})
  except Exception as e:
    print(f"Download failed: {e}")
    return jsonify({'error': 'Couldnt download song captions'})

if __name__ == "__main__":
  app.run(debug=True)