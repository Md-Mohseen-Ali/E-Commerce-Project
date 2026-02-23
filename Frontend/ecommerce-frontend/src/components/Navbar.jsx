import { useNavigate } from "react-router-dom";
import { logout } from "../services/api";
import "./Navbar.css";
function Navbar() {

  const navigate = useNavigate();

  const handleLogout = async () => {
    await logout();
    navigate("/login");
  };

  return (
    <div className="navbar">

      <h2 style={{cursor:"pointer"}} onClick={() => navigate("/home")}>
        Ecommerce
      </h2>

      <div>
        <button onClick={() => navigate("/home")}>Home</button>
        <button onClick={() => navigate("/cart")}>Cart</button>
        <button onClick={() => navigate("/my-orders")}>My Orders</button>
        <button onClick={handleLogout}>Logout</button>
      </div>

    </div>
  );
}

export default Navbar;
