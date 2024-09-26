import React, {useEffect, useState} from 'react';
import * as FaIcons from "react-icons/fa";
import * as AiIcons from "react-icons/ai";
import { Link } from "react-router-dom";
import "./Sidebar.css";

import { IconContext } from 'react-icons';
import { ImFileMusic } from "react-icons/im";
import { MdOutlineLibraryMusic } from "react-icons/md";
import { IoLogOutOutline } from "react-icons/io5";

export const logUserOut = () => {
  window.localStorage.removeItem("token");
  window.localStorage.removeItem("token_expiration");
  window.localStorage.removeItem("yt_token");
  window.localStorage.removeItem("yt_token_expiration");
  window.location.reload();
}

const Sidebar = () => {
  const [sidebar, setSidebar] = useState(false);
  const showSidebar = () => setSidebar(!sidebar);

  const [activeTab, setActiveTab] = useState(0);
  const showActiveTab = (index) => setActiveTab(index);

  const token = window.localStorage.getItem("token");
  
  return (
    <>
      <IconContext.Provider
        value={{color:'undefined', size:'50px'}}
      >
        <div className="hamburgerSection">
          <Link to="#" className="menuBars">
            <FaIcons.FaBars onClick={showSidebar} />
          </Link>
        </div>
        <nav className={sidebar ? "sideBar active" : "sideBar"}>
          <ul className="navMenuItems" onClick={showSidebar}>
            <li className='toggleBar'>
              <Link to="#" className="menuBars">
                <AiIcons.AiOutlineClose />
              </Link>
            </li>
            <li className='sideBarTagContent'>
              <Link to="download" onClick={() => showActiveTab(0)} className={activeTab===0 ? 'sideBarTextActive' : 'sideBarText'}><ImFileMusic className='sideBarImg'/>Download</Link>
            </li>
            <li className='bottomSideBar'>
              <Link to="#" className='logoutText' onClick={logUserOut}><IoLogOutOutline className='sideBarImg'/>Logout</Link>
            </li>
          </ul>
        </nav>
      </IconContext.Provider>
    </>
  )
}

export default Sidebar
