# Order and Billing Management System

Welcome to the Order and Billing Management System. This project is a comprehensive full-stack web application built to handle sales, inventory, customer tracking, and billing operations. 

## 🛠️ Technology Stack

- **Frontend:** React, Vite, CSS (Glassmorphism UI)
- **Backend:** Java, Spring Boot, Spring Data JPA, REST APIs
- **Database:** MySQL
- **Build Tool:** Maven (Backend), npm (Frontend)

## 📦 Project Structure

The repository is divided into two main applications:

### 1. `Order_and_Billing` (Frontend)
A modern, responsive React web application.
- **Pages / Modules:**
  - `Dashboard`: System overview, revenue summaries, and quick stats.
  - `CustomersManagement`: Add, edit, and view customer details.
  - `ProductManagement`: Manage inventory, prices, and stock levels.
  - `OrdersManagement`: Record sales and manage order statuses.
  - `InvoicesManagement`: Generate and manage billing and invoices.
  - `SettingsManagement`: Application preferences and user profiles.
- **Run Locally:**
  ```bash
  cd Order_and_Billing
  npm install
  npm run dev
  ```

### 2. `Order_and_Billing_Backend` (Backend)
A robust Spring Boot RESTful API handling business logic and data persistence.
- **Controllers:**
  - `CustomerController` - Endpoints for customer CRUD operations.
  - `ProductController` - Endpoints for product management.
  - `OrderController` - Endpoints for handling orders.
  - `InvoiceController` - Endpoints for invoice generation.
- **Run Locally:**
  ```bash
  cd Order_and_Billing_Backend
  mvn spring-boot:run
  ```
  *(Or execute using the provided `.bat`/`.cmd` scripts)*

## 👥 Team & Responsibilities

The system was developed collaboratively with responsibilities divided by modules:

- **Tharaka:** Dashboard and Core UI Layout (Navbar, Footer)
- **Ayanthi:** Customer Management Module
- **Yuthmi:** Orders Module
- **Gimhani:** Product/Inventory Module
- **Asel:** Invoice Module
- **Ithumathi:** Settings and Configuration Module

## 🚀 Getting Started

1. **Database Setup:** 
   - Ensure your MySQL server is running.
   - Run the provided `database.sql` script (located in the backend directory) to create the schema and necessary tables.
2. **Start the Backend:** Navigate to the `Order_and_Billing_Backend` directory and start the Spring Boot application.
3. **Start the Frontend:** Navigate to the `Order_and_Billing` directory, install node modules, and start the Vite development server.
4. **Access the Application:** Open `http://localhost:5173` in your web browser.

## 📌 Future Enhancements

- Implementing the Backend API for the `Settings` module.
- Adding a dedicated `DashboardController` on the backend to aggregate statistics efficiently.
- Advanced authentication and authorization (e.g., JWT).
- Dynamic PDF generation for invoices directly from the backend.
