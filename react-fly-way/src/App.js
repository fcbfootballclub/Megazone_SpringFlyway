import "./App.css";
import ClassList from "./ClassComponent/ClassList";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import StudentList from "./StudentComponent/StudentList";
import NoPage from "./Nopage/NoPage";
import Layout from "./Nopage/Layout";
import SignUp from "./Authen/Signup";
import Signin from "./Authen/Signin";
import { useState } from "react";
import Homepage from "./Nopage/Homepage";

function App() {
  const[isLogged, setIsLogged] = useState(false)
  const handleLogOut = () => {
    setIsLogged(false)
  }
  console.log("check parent log ", isLogged);

  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout props={isLogged} setProps={handleLogOut}/>}>
            <Route index element={<Homepage />} />
            <Route path="student" element={<StudentList />} />
            <Route path="class" element={<ClassList />} />
            <Route path="*" element={<NoPage />} />
            <Route path="signup" element={<SignUp />} />
            <Route path="signin" element={<Signin props={setIsLogged}/>} />
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
