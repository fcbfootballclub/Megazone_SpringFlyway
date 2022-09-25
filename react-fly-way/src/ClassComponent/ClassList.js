import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";
import AddClass from "./AddClass";
import ConfirmDelete from "./ConfirmDelete";
import Modal from "react-bootstrap/Modal";

const ClassList = () => {
  const [classList, setClassList] = useState([]);
  const [addClass, setAddClass] = useState({
    className: "",
    grade: "",
  });

  useEffect(() => {
    console.log("token >>>>>>>>>>>>>>>", {
      Authorization: localStorage.getItem("token"),
    });
    axios
      .get(`http://localhost:8080/class-name`, {
        headers: { Authorization: localStorage.getItem("token") },
      })
      .then((res) => {
        setClassList(res.data);
      })
      .catch((error) => console.log(error));
  }, []);

  //delete a class
  const deleteAClass = (classID) => {
    console.log("delete class id ", classID);
    axios
      .delete(`http://localhost:8080/class-name/` + classID)
      .then((res) => console.log("Response status ", res))
      .catch((error) => console.log("error log ", error));
  };

  //add class
  const handleOnNewClassChange = (e) => {
    setAddClass((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const addNewClass = () => {
    axios
      .post(`http://localhost:8080/class-name`, addClass)
      .then((res) => console.log(">>>>Check res post", res))
      .catch((error) => console.log("log error", error));
  };

  //update class
  const [edit, setEdit] = useState({
    idEdit: 0,
    status: false,
  });

  const [nameClassOnEdit, setNameClassOnEdit] = useState({
    className: "",
    grade: 10,
  });

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const handleOnchange = (e) => {
    setNameClassOnEdit((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };
  console.log(">>>>>check handle ", nameClassOnEdit);

  const saveEditClass = (editId) => {
    axios
      .put(`http://localhost:8080/class-name/` + editId, nameClassOnEdit)
      .then((res) => console.log("log response", res))
      .catch((error) => console.log("log error", error));
  };

  const handleOnEdit = (itemId) => {
    console.log("check item id ", itemId);
    setEdit((prev) => {
      if (prev.idEdit === 0) {
        return {
          idEdit: itemId,
          status: true,
        };
      } else {
        return {
          idEdit: 0,
          status: false,
        };
      }
    });
  };

  console.log("check data ", classList);

  const listBody = classList.map((item, index) => (
    <tr key={index}>
      {item.classId !== edit.idEdit ? (
        <>
          <td>{item.classId}</td>
          <td>{item.className}</td>
          <td>{item.grade}</td>
        </>
      ) : (
        <>
          <td>{item.classId}</td>
          <td>
            <input
              defaultValue={item.className}
              name={"className"}
              onChange={(e) => handleOnchange(e)}
            />
          </td>
          <td>
            <input
              defaultValue={item.grade}
              name={"grade"}
              onChange={(e) => handleOnchange(e)}
            />
          </td>
        </>
      )}

      <td className="d-flex">
        <button
          onClick={() => handleOnEdit(item.classId)}
          style={
            edit.idEdit !== item.classId
              ? { display: "block" }
              : { display: "none" }
          }
          className={"btn btn-primary"}
          disabled={edit.status === true ? true : false}
        >
          Edit
        </button>

        <button
          onClick={() => {
            handleOnEdit(item.classId);
            saveEditClass(item.classId);
          }}
          style={
            item.classId === edit.idEdit
              ? { display: "block" }
              : { display: "none" }
          }
          className={"btn btn-info"}
        >
          Save
        </button>

        <button
          style={
            edit.idEdit === item.classId
              ? { display: "block" }
              : { display: "none" }
          }
          onClick={() => handleOnEdit(item.classId)}
          className={"btn btn-warning"}
        >
          Cancel
        </button>

        <>
          <button
            onClick={() => handleShow()}
            style={
              edit.idEdit !== item.classId
                ? { display: "block" }
                : { display: "none" }
            }
            className={"btn btn-danger"}
            disabled={edit.status === true ? true : false}
          >
            Delete
          </button>
          <Modal show={show} onHide={handleClose} keyboard={false}>
            <Modal.Header closeButton>
              <Modal.Title>Are you sure?</Modal.Title>
            </Modal.Header>
            <Modal.Footer>
              <button
                className="btn btn-secondary"
                onClick={handleClose.bind(this)}
              >
                Close
              </button>
              <button
                className="btn btn-primary"
                onClick={() => {
                  deleteAClass(item.classId);
                  handleClose();
                }}
              >
                Yes
              </button>
            </Modal.Footer>
          </Modal>
        </>
      </td>
    </tr>
  ));

  return (
    <div className="container">
      <AddClass
        onChangeFunc={handleOnNewClassChange}
        addFunc={addNewClass}
        setFunc={setAddClass}
      />
      <ConfirmDelete />
      This is ClassList
      <table class="table">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Class Name</th>
            <th scope="col">Grade</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>{listBody}</tbody>
      </table>
    </div>
  );
};
export default ClassList;
