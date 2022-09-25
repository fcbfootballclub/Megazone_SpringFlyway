import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Modal from "react-bootstrap/Modal";



const SignUp = () => {
  const [account, setAccount] = useState({
    useName: "",
    email: "",
    password: "",
  });

  let navigate = useNavigate();

  const handleOnchange = (e) => {
    setAccount((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  console.log(">>>>check account ", account);

  const signup = (e) => {
    e.preventDefault()
    axios.post(`http://localhost:8080/api/auth/signup`, account)
    .then(res => {
        if(res.status === 200) {
            console.log("response ", res)
            navigate('/signin');
        }
    })
    .catch(error => alert(error.response.data))
  }

  return (
    <div className="container signup">
      <div>
        <div>
          <form onSubmit={signup}>
            <div class="form-group">
              <label htmlFor="userName">User name</label>
              <input
                type="text"
                class="form-control"
                name={"userName"}
                onChange={handleOnchange}
                placeholder="User name"
              />
            </div>

            <div class="form-group">
              <label htmlFor="email">Email address</label>
              <input
                type="email"
                class="form-control"
                placeholder="Enter email"
                onChange={handleOnchange}
                name={"email"}
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
              Sign up
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default SignUp;
