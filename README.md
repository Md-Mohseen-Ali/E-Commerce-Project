Ecommerce Web Application
This is a Full Stack Ecommerce Web Application developed using Spring Boot (Backend) and React.js (Frontend). The project implements complete ecommerce functionality including product browsing, cart management, order placement, and order tracking.

Tech Stack Used
Frontend
React.js

JavaScript

HTML5

CSS3

Axios

React Router

Backend
Spring Boot

Spring Data JPA

Hibernate ORM

REST APIs

Maven

Database
Oracle Database

Tools
VS Code

IntelliJ IDEA / Spring Tool Suite

Git & GitHub

Postman
Features
User Features
User Login

View Products

Add Products to Cart

Remove Products from Cart

Place Order

View My Orders

System Features
REST API Integration

Database persistence using JPA

Automatic cart update after order placement

Proper frontend-backend communication

Project Structure
E-Commerce-Project
│
├── Frontend (React)
│   ├── Components
│   ├── Pages
│   ├── Services (API calls)
│   └── CSS
│
└── Backend (Spring Boot)
    ├── Controller
    ├── Service
    ├── Repository
    ├── Entity
    └── DTO
Project Flow
User logs in

Frontend requests products via REST API

Backend fetches products from database

User adds products to cart

Cart stored in database

User places order

Order and OrderItems saved in database

Cart cleared automatically

User can view order history

🔌 API Examples
GET /api/products
GET /api/cart/{userId}
POST /api/cart/add
POST /api/orders/place
GET /api/orders/{userId}
GET /api/orders/{orderId}/items

Learning Objectives
This project demonstrates:

Full Stack Development

REST API Development

Database Integration

Spring Boot Architecture

React Component Architecture

Frontend‑Backend Integration

Developed By
Md Mohseen Ali

Future Improvements
Payment Gateway Integration

JWT Authentication

Admin Panel

Cloud Deployment

