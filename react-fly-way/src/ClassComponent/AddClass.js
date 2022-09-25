import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
 

const AddClass = (props) => {
  const [className, setClassName] = useState({
    className: "",
  });

  return (
    <div className="add-class">
      <input
        type={"text"}
        name={"className"}
        onChange={(e) => props.onChangeFunc(e)}
        placeholder={'Enter class name'}
        className="form-control"
      />
      <select className="form-control" name={'grade'} onChange={e => props.onChangeFunc(e)}>
        <option value={0} selected disabled>Select grade</option>
        <option value={10}>Grade 10</option>
        <option value={11}>Grade 11</option>
        <option value={12}>Grade 12</option>
      </select>
      <button onClick={props.addFunc} className={"btn btn-primary"}>
        Add class
      </button>
    </div>
  );
};
export default AddClass;
