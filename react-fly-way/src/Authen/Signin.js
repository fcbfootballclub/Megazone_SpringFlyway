import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import Modal from "react-bootstrap/Modal";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Signin = (props) => {
  let navigate = useNavigate();
  const [account, setAccount] = useState({
    userNameOrEmail: "",
    password: "",
  });

  const handleOnchange = (e) => {
    setAccount((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const login = (e) => {
    console.log("??/check account ", account);
    e.preventDefault();
    axios
      .post(`http://localhost:8080/api/auth/signin`, account)
      .then((res) => {
        console.log("res", res.data);
        props.props(true);
        const token = res.data.access_tocken;
        const role = res.data.role;
        localStorage.setItem("token", token);
        localStorage.setItem("login", true);
        localStorage.setItem("role", role);
        localStorage.setItem("role", res.data.username);
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;

        navigate("/class");
      })
      .catch((error) => console.log(error));
  };

  return (
    <div className="container signin">
      <div>
        <div>
          <form onSubmit={login}>
            <div class="form-group">
              <label htmlFor="userNameOrEmail">User name or email</label>
              <input
                type="text"
                class="form-control"
                name={"userNameOrEmail"}
                placeholder="User name"
                onChange={handleOnchange}
              />
            </div>

            <div class="form-group">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                class="form-control"
                placeholder="Password"
                name={"password"}
                onChange={handleOnchange}
              />
            </div>
            <button type="submit" class="btn btn-primary">
              Sign in
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Signin;
