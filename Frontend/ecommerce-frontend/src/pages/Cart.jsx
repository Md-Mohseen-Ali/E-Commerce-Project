import { useEffect, useState } from "react";
import { placeOrder, removeFromCart, getMyCart } from "../services/api";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
function Cart() {

  const [items, setItems] = useState([]);

  const userId = localStorage.getItem("userId");

  // Load cart
  useEffect(() => {

    const fetchCart = async () => {

      try {

        if (!userId) return;

        const res = await getMyCart(userId);

        setItems(res.data || []);

      } catch (error) {

        console.error("Error loading cart:", error);

        alert("Failed to load cart");

      }

    };

    fetchCart();

  }, [userId]);


  // Place order
  const handlePlaceOrder = async () => {

    try {

      const orderData = {
        userId: Number(userId),
        totalAmount: items.reduce(
          (total, item) => total + item.product.price * item.quantity,
          0
        ),
        items: items.map(item => ({
          productId: item.product.id,
          quantity: item.quantity
        }))
      };

      await placeOrder(orderData);

      alert("Order placed successfully");

      // Refresh cart
      const res = await getMyCart(userId);
      setItems(res.data || []);

    } catch (error) {

      console.error("Place order failed:", error);

      alert("Failed to place order");

    }

  };


  // Remove item
  const handleRemove = async (id) => {

    try {

      await removeFromCart(id);

      alert("Item removed");

      const res = await getMyCart(userId);

      setItems(res.data || []);

    } catch (error) {

      console.error("Remove failed:", error);

      alert("Failed to remove item");

    }

  };


  return (
    <>
    <div>

      <Navbar />

      <h2 style={{ textAlign: "center" }}>My Cart</h2>

      <div className="product-grid">

        {items.length === 0 ? (

          <p style={{ textAlign: "center" }}>Cart is empty</p>

        ) : (

          items.map(item => (

            <div className="product-card" key={item.id}>

              <h3>{item.product.name}</h3>

              <p>Quantity: {item.quantity}</p>

              <p>Price: ₹{item.product.price}</p>

              <button onClick={() => handleRemove(item.id)}>
                Remove
              </button>

            </div>

          ))

        )}

      </div>

      {items.length > 0 && (

        <div style={{ textAlign: "center", marginTop: "20px" }}>

          <button
            className="place-order-btn"
            onClick={handlePlaceOrder}
          >
            Place Order
          </button>

        </div>

      )}

    </div>
    <Footer />
    </>
  );

}

export default Cart;