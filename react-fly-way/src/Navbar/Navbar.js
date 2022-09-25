import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

function NavbarBoostrap(props) {
  console.log("check props ", props);
  return (
    <ul class="nav">
      <li class="nav-item">
        <Link to="/" className="nav-link">
          Home
        </Link>
      </li>

      {props.props === true && (
        <>
          <li class="nav-item">
            <Link to="student" className="nav-link">
              Student
            </Link>
          </li>
          <li class="nav-item">
            <Link to="class" className="nav-link">
              Class
            </Link>
          </li>
        </>
      )}

      {props.props === false ? (
        <>
          <div>
            <li class="nav-item">
              <Link to="signup" className="nav-link">
                Sing up
              </Link>
            </li>
            <li class="nav-item">
              <Link to="signin" className="nav-link">
                Sign in
              </Link>
            </li>
          </div>
        </>
      ) : (
        <>
          <div>
            <li class="nav-item">
              <button className="btn" onClick={props.setFunc}>
                Log out
              </button>
            </li>
          </div>
        </>
      )}
    </ul>
  );
}

export default NavbarBoostrap;
