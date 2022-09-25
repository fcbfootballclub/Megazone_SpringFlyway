import { Outlet, Link } from "react-router-dom";
import NavbarBoostrap from "../Navbar/Navbar";

const Layout = (props) => {
  return (
    <>
      <NavbarBoostrap props={props.props} setFunc={props.setProps}/>
      <Outlet />
    </>
  );
};

export default Layout;
