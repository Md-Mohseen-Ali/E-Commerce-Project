import { useEffect, useState } from "react";
import { getProducts, addToCart, logout } from "../services/api";
import "../App.css";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

function Home() {

  const navigate = useNavigate();
  const [products, setProducts] = useState([]);

  // Load products
  useEffect(() => {
    getProducts()
      .then(res => setProducts(res.data))
      .catch(err => console.error(err));
  }, []);

  // Add to cart
  const handleAddToCart = async (productId) => {

    const userId = localStorage.getItem("userId");

    if (!userId) {
      alert("Please login first");
      navigate("/login");
      return;
    }

    try {
      await addToCart({
        userId: Number(userId),
        productId: productId,
        quantity: 1
      });

      alert("Added to cart");

    } catch (err) {
      console.error(err);
      alert("Failed to add to cart");
    }
  };

  // Logout
  const handleLogout = async () => {
    try {
      await logout();
    } catch(error) {
      console.log(error)
    }

    localStorage.removeItem("userId");

    alert("Logged out successfully");
    navigate("/login");
  };

  return (

    <div>

      {/* Navbar */}
      <Navbar onLogout={handleLogout} />

      {/* Products Section */}
      <div className="product-grid">

        {products.map(product => (

          <div className="product-card" key={product.id}>

            <img
              src={product.imageUrl}
              alt={product.name}
              className="product-image"
            />

            <h3>{product.name}</h3>

            <p className="product-price">
              ₹{product.price}
            </p>

            <button
              className="add-cart-btn"
              onClick={() => handleAddToCart(product.id)}
            >
              Add to Cart
            </button>

          </div>

        ))}

      </div>
<Footer />
    </div>

  );
}

export default Home;