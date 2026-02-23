import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../App.css";
import { Link } from "react-router-dom";
import Footer from "../components/Footer";
function Register() {

  const navigate = useNavigate();

  const [user, setUser] = useState({
    name: "",
    email: "",
    password: "",
    phone: "",
    address: ""
  });

  const handleRegister = async () => {

    await axios.post(
      "http://localhost:8080/api/auth/register",
      user,
      { withCredentials: true }
    );

    alert("Registration successful");
    navigate("/login");
  };

  return (
    <>
    <div className="login-container">

      <h2>Register</h2>

      <input placeholder="Name"
        onChange={e => setUser({...user, name: e.target.value})} />

      <input placeholder="Email"
        onChange={e => setUser({...user, email: e.target.value})} />

      <input type="password" placeholder="Password"
        onChange={e => setUser({...user, password: e.target.value})} />

      <input placeholder="Phone"
        onChange={e => setUser({...user, phone: e.target.value})} />

      <input placeholder="Address"
        onChange={e => setUser({...user, address: e.target.value})} />

      <button onClick={handleRegister}>
        Register
      </button>
      <p>
  Already registered? <Link to="/login">Login here</Link>
</p>

    </div>
    <Footer/>
    </>
  );
}

export default Register;