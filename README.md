# 🚀 Mediatech Manager

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green) ![Angular](https://img.shields.io/badge/Angular-17-red) ![Bootstrap](https://img.shields.io/badge/Bootstrap-5-purple) ![MySQL](https://img.shields.io/badge/Database-MySQL-blue)

**Mediatech Manager** is a full-stack Inventory and Client Management System designed to streamline business operations. It features secure role-based access, real-time stock management, and automated PDF invoicing.

## ✨ Features

- **🔐 Secure Authentication:** Role-based access control (Admin vs. User) using Spring Security.
- **👥 Client Management:** CRUD operations for client data with admin-only deletion rights.
- **📦 Inventory System:** Real-time product tracking. Stock is automatically deducted when an invoice is generated.
- **📄 Smart Invoicing:** Create invoices linking Clients to Products with dynamic stock validation.
- **🖨️ PDF Export:** One-click generation of professional PDF invoices using iText.
- **⚡ Reactive UI:** Fast, responsive interface built with Angular 17 and Bootstrap.

## 🛠️ Technology Stack

### Backend (`mediatech`)
- **Language:** Java 21
- **Framework:** Spring Boot 3
- **Database:** MySQL (via XAMPP)
- **Security:** Spring Security (Basic Auth + Interceptors)
- **Tools:** Maven, Lombok, iText (PDF)

### Frontend (`mediatech-ui`)
- **Framework:** Angular 17
- **Styling:** Bootstrap 5, Custom CSS
- **Communication:** HTTP Client with Interceptors

## ⚙️ Prerequisites

Before running the project, ensure you have the following installed:
- Java JDK 21
- Node.js & npm (Latest LTS)
- Maven
- MySQL (XAMPP recommended)

---

## 🚀 How to Run

### 1. Database Setup
1. Open **XAMPP** and start **MySQL**.
2. The application is configured to automatically create the database `mediatech2` and tables upon the first run.

### 2. Backend Setup
1. Navigate to the backend folder:
   ```bash
   cd mediatech
2. Run the application:
   ```bash
   mvnw spring-boot:run
## The server will start on http://localhost:8080.
4. Frontend Setup:
    ## navigate to the frontend folder:
   ```bash
    cd mediatech-ui
## Install dependencies (First time only):    
    ```bash
    npm install
## Start the Angular server:
    ```bash
    ng serve
## Access the application at: http://localhost:4200

🔑 Login Credentials
The application initializes with two default users:
Admin : admin/1234
Client : user/1234
