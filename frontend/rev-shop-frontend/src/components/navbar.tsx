import React from "react";
import {Link} from 'react-router-dom';

export function Navbar() {
    return (
        <div id = "navbar" className="navbar-container">
            <div className="HomeLogoContainer">
                <Link to ="/" className="home-nav-link">RevShop</Link>
            </div>
            <div id = "links" className="links-container">    
                <Link to="/Browse">Browse</Link>
                <Link to="/Profile">Profile</Link>
            </div>
            
        </div>
    );
};

export default Navbar;