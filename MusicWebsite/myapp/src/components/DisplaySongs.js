import React, { useEffect, useReducer, useState } from "react";
import apiClient from "./LoginEndPoint";
import ytApiClient from "./YTLoginEndPoint";
import "./DisplaySongs.css";
import { RiDownload2Line } from "react-icons/ri";
import { TiCancel } from "react-icons/ti";
import axios from 'axios';


const DisplaySongs = (props) => {
  const [tracks, setTracks] = useState([]);
  const trackLimit = 1;

  const [downloading, setDownloading] = useState(
    false
  );

  function downloadOneSong(id, videoName){
    var myParams = {
      videoId: id,
      videoName: videoName,
    };

    if(videoName !== ""){
      alert("Downloading song caption");
      setDownloading(true);
      axios.post('/downloadSong', myParams).then(function(response){
        console.log(response);
        alert("Downloaded captions for: " + videoName);
        setDownloading(false);
      })
      .catch(function(error){
        console.log(error.response.data);
        alert("Couldn't download song caption");
        // alert("Song has no captions")
        setDownloading(false);
      });
    }
  }

  function pythonYtToken(){
    var myParams = {
      ytToken: window.localStorage.getItem("yt_token")
    }
    
    axios.post('/setYtToken', myParams).then(function(response){
      console.log(response);
    })
    .catch(function(error){
      console.log("YO");
      console.log(window.localStorage.getItem("yt_token"))
      console.log(error.response.data);
    });
  }
  
  function getSongs(element){
    const song = ytApiClient.get(`search?part=snippet&maxResults=1&type=video&videoCaption=closedCaption&q=${element.track.name}|${element.track.artists[0].name}`);
    return song;
  }

  useEffect(() => {
    apiClient.get("me/playlists").then(response => {
      const playlistId = response.data.items[props.playlistIndex].id;
      return apiClient.get(`playlists/${playlistId}/tracks?&limit=${trackLimit}`);
    }).then(response => {
      const limitedTracks = response.data.items;
      const trackPromises = limitedTracks.map(getSongs);
      return Promise.all(trackPromises);
    }).then(response => {
      const newTracks = response.map(currTrack => currTrack.data.items[0]);
      // console.log(newTracks);
      setTracks(newTracks);
    })
  }, [props.playlistIndex]);

  useEffect(() => {
    pythonYtToken();
  }, [])
  

  return (
    <div className="displaySongs">
      {tracks.map((track, index) => {
        return(
          <div key={index} className={index%2 === 0 ? "rowTextEven" : "rowTextOdd"}>
            <a href={`https://www.youtube.com/watch?v=${track.id.videoId}`} className="urlDecoration">
              <img src={track.snippet.thumbnails.high.url} alt={track.snippet.title}/>
            </a>

            <h1 className={!downloading ? "checkButtons notActive" : "checkButtons active"} 
                onClick={() => {if(!downloading){
                  downloadOneSong(
                    `${track.id.videoId}`,
                    `${track.snippet.title}`
                  )}
                }}>
              {!downloading ? (<RiDownload2Line/>) : (<TiCancel/>) }
            </h1>
            <p>Title: {track.snippet.title}</p>
            <p>Author: {track.snippet.channelTitle}</p>
          </div>
        );
      })}
    </div>
  )
}

export default DisplaySongs
