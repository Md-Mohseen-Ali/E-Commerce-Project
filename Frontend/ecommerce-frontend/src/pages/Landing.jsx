import { useNavigate } from "react-router-dom";
import "../App.css";
import Footer from "../components/Footer";
function Landing() {

  const navigate = useNavigate();

  return (
    <>
    <div className="login-container">

      <h2>Ecommerce App</h2>

      <button onClick={() => navigate("/login")}>
        Login
      </button>

      <button
        onClick={() => navigate("/register")}
        style={{marginTop:"10px", background:"green"}}
      >
        Register
      </button>

    </div>
    <Footer />
    </>
  );
}

export default Landing;
