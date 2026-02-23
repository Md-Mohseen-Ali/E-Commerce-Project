import { useEffect, useState } from "react";
import { getMyOrders } from "../services/api";
import axios from "axios";
import "../App.css";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
function MyOrders() {

  const [orders, setOrders] = useState([]);

  useEffect(() => {

    const userId = localStorage.getItem("userId");

    if (!userId) return;

    getMyOrders(userId).then(async res => {

      const ordersWithItems = await Promise.all(
        res.data.map(async order => {

          try {

            const itemsRes = await axios.get(
              `http://localhost:8080/api/orders/${order.id}/items`
            );

            console.log("REAL items:", itemsRes.data);

            return {
              ...order,
              items: itemsRes.data
            };

          } catch (err) {

            console.error("Error:", err);

            return {
              ...order,
              items: []
            };
          }

        })
      );

      setOrders(ordersWithItems);

    });

  }, []);

 return (
  <>
  <Navbar />
  <div className="orders-container">

    <h2>My Orders</h2>

    {orders.map(order => (

      <div className="order-card" key={order.id}>

        <p className="order-id">
          Order ID: {order.id}
        </p>

        {order.items.length > 0 ? (
          order.items.map(item => (
            <p className="order-product" key={item.id}>
              Product: {item.productName}
            </p>
          ))
        ) : (
          <p className="order-product">No items</p>
        )}

        <p className="order-total">
          Total: ₹{order.totalAmount}
        </p>

      </div>

    ))}

  </div>
  <Footer/>
  </>
);
}

export default MyOrders;