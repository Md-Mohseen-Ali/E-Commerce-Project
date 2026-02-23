import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../services/api";
import "../App.css";
import { Link } from "react-router-dom";
import Footer from "../components/Footer";
function Login() {

  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {

  const res = await login({ email, password });

  // NOW this works
  localStorage.setItem("userId", res.data.id);

  alert("Login successful");

  navigate("/home");

};

  return (
    <>
    <div className="login-container">
      <h2>Login</h2>

      <input
        type="email"
        placeholder="Enter email"
        onChange={(e) => setEmail(e.target.value)}
      />

      <input
        type="password"
        placeholder="Enter password"
        onChange={(e) => setPassword(e.target.value)}
      />

      <button onClick={handleLogin}>Login</button>
      <p>
  Not registered? <Link to="/register">Register here</Link>
</p>

    </div>
    <Footer />
</>
  );
}

export default Login;