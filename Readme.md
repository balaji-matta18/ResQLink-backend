# 🆘 ResQLink: Real-Time Ambulance Tracking & Blood Donor Network

**ResQLink** is a comprehensive backend service built with Spring Boot.  
It is designed to be a **one-stop solution for health, safety, and emergency needs**, combining the functionalities of a **blood donor network**, an **ambulance booking service**, and a **personal safety application**.  

This platform empowers individuals to take control of their health and safety while fostering a strong sense of community.

---

## ✨ Key Features

### 1. 🩸 Blood Donation and Requests — *A Digital Blood Bank*

This feature turns the app into a **community-powered blood bank**, connecting donors with those in need.

- **Become a Donor**: Register as a donor by creating a profile. Provide your blood group and last donation date, and the app will automatically determine your eligibility.  
- **Find Donors**: Search for eligible donors by blood group during emergencies.  
- **Request Blood**: Create a blood request specifying the required blood group and quantity. The request is then broadcast to nearby donors.  
- **Track Donations**: Maintain a history of your donations and earn reward points.

---

### 2. 🚑 Ambulance Services — *Emergency Response at Your Fingertips*

An Uber-like experience for booking and tracking emergency medical transport.

- **Book an Ambulance**: Enter pickup and drop-off locations to book an ambulance.  
- **Live Tracking**: Track the ambulance’s location in real-time with ETA.  
- **Booking History**: View your past ambulance bookings.

---

### 3. 🛡️ Disha — *Your Personal Safety Companion*

A personal safety feature designed to help users in distress.

- **Emergency Contacts**: Add and manage your trusted contacts.  
- **SOS Button**: Instantly send an alert with your location to your emergency contacts and, if configured, to the authorities.

---

### 4. 🤝 User Profile and Community Trust

The app fosters a trusted community ecosystem.

- **User Profiles**: Basic user information is available for transparency.  
- **Reviews & Ratings**: Users can review and rate each other, helping build a reliable community.

---

## 🛠️ Tech Stack

- **Backend**: Java 21, Spring Boot 3.5.0, Spring Data JPA, Spring Security  
- **Database**: PostgreSQL  
- **Build Tool**: Maven  
- **Containerization**: Docker

---

## 📋 Prerequisites

Before starting, ensure you have the following installed:

- [Java Development Kit (JDK) 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)  
- [Apache Maven](https://maven.apache.org/)  
- [PostgreSQL](https://www.postgresql.org/)  
- [Docker](https://www.docker.com/) *(optional)*

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/balaji-matta18/resqlink-backend.git
cd resqlink-backend
```

---

### 2. Configure the Database

1. Open `src/main/resources/application.properties`  
2. Update the following with your PostgreSQL credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

---

### 3. Build the Project

```bash
./mvnw clean install
```

---

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

- The application will start on port **`9000`**  
- Access it at: [http://localhost:9000](http://localhost:9000)

---

## 🐳 Run with Docker

You can also run the application in a containerized environment:

```bash
docker build -t resqlink-backend .
docker run -p 9000:9000 resqlink-backend
```

---

## 📜 API Endpoints

A full list of API endpoints is available in the [Postman Collection](./ResQLink_Postman_collection.json).

👉 [Download the Postman collection](./ResQLink_Postman_collection.json) and import it into [Postman](https://www.postman.com/) to explore and test the APIs easily.


---

## ✍️ Authors

- **[Balaji Matta](https://github.com/balaji-matta18)**  
- **[Varsha Bhogadi](https://github.com/varsha-bhogadi)**

---

## 🤝 How to Contribute

Contributions are welcome!

1. **Fork** the repository.  
2. **Create a new branch**:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes** and commit:
   ```bash
   git commit -m "Add your feature"
   ```
4. **Push** to your fork:
   ```bash
   git push origin feature/your-feature-name
   ```
5. **Create a Pull Request** to the `main` branch.

> Please make sure your code follows the existing style and includes tests for any new functionality.

---

## 🛡️ License

This project is licensed under the **MIT License**.  
You are free to use, modify, and distribute it.

---

## ⭐ Support

If you find this project helpful, don’t forget to **star ⭐ the repo**.  
Let’s build a safer and healthier community together.
