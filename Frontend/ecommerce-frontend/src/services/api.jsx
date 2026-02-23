import axios from "axios";

const API = "http://localhost:8080/api";

//  LOGIN (creates session)
export const login = (data) =>
  axios.post(`${API}/auth/login`, data, {
    withCredentials: true
  });

//  LOGOUT
export const logout = () =>
  axios.post(`${API}/auth/logout`, {}, {
    withCredentials: true
  });

//  GET PRODUCTS
export const getProducts = () =>
  axios.get(`${API}/products`, {
    withCredentials: true
  });

//  ADD TO CART
export const addToCart = (data) =>
  axios.post(`${API}/cart/add`, data, {
    withCredentials: true
  });

//  GET CART ITEMS
export const getCartItems = (cartId) =>
  axios.get(`${API}/cart/${cartId}`, {
    withCredentials: true
  });

//  PLACE ORDER
export const placeOrder = (data) =>
  axios.post(`${API}/orders/place`, data, {
    withCredentials: true
  });

//  GET LOGGED-IN USER ORDERS
export const getMyOrders = (userId) =>
  axios.get(`${API}/orders/my-orders/${userId}`, {
    withCredentials: true
  });

// GET LOGGED-IN USER INFO (optional)
export const getCurrentUser = () =>
  axios.get(`${API}/auth/me`, {
    withCredentials: true
  });

  export const removeFromCart = (cartItemId) =>
  axios.delete(`http://localhost:8080/api/cart/remove/${cartItemId}`, {
    withCredentials: true
  });

  export const getMyCart = (userId) =>
  axios.get(`http://localhost:8080/api/cart/my-cart/${userId}`, {
    withCredentials: true
  });

  export const getOrderItems = (orderId) =>
  axios.get(`/api/orders/${orderId}/items`);

  