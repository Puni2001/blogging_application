
---

# 📝 Blogging Application API

A RESTful API for a full-featured blogging platform built with Spring Boot. This application supports user authentication, blog post management, category organization, image uploads, and commenting functionality.

---

## 🌐 Base URL

```
http://localhost:9192
```

---

## 📚 API Documentation

Explore the complete OpenAPI (OAS 3.1) spec at:

```
/v3/api-docs
```

---

## 🔐 Authentication

- **Register**: `POST /api/v1/auth/register`
- **Login**: `POST /api/v1/auth/login`
- **Current Logged-in User**: `GET /api/v1/auth/current-user/`

Uses JWT-based authentication for secure endpoints.

---

## 👤 User Management

- `GET /api/v1/users/{userId}` – Get user by ID  
- `PUT /api/v1/users/{userId}` – Update user  
- `DELETE /api/v1/users/{userId}` – Delete user  
- `GET /api/v1/users/` – Get all users  
- `POST /api/v1/users/` – Create a new user  

---

## 📝 Post Management

- `GET /api/v1/posts/{postId}` – Get post by ID  
- `PUT /api/v1/posts/{postId}` – Update post  
- `DELETE /api/v1/posts/{postId}` – Delete post  
- `GET /api/v1/posts/` – Get all posts  
- `GET /api/v1/posts/search/{keywords}` – Search posts  
- `POST /api/v1/user/{userId}/category/{categoryId}/posts` – Create post by user & category  
- `GET /api/v1/user/{userId}/posts` – Get all posts by user  
- `GET /api/v1/category/{categoryId}/posts` – Get all posts in category  
- `POST /api/v1/post/image/upload/{postId}` – Upload image for post  
- `GET /api/v1/post/image/{imageName}` – Download image  

---

## 🗂️ Category Management

- `GET /api/v1/categories/{catId}` – Get category by ID  
- `PUT /api/v1/categories/{catId}` – Update category  
- `DELETE /api/v1/categories/{catId}` – Delete category  
- `GET /api/v1/categories/` – Get all categories  
- `POST /api/v1/categories/` – Create new category  

---

## 💬 Comments

- `POST /api/v1/post/{postId}/comments` – Add comment to a post  
- `DELETE /api/v1/comments/{commentId}` – Delete comment  

---

## 📦 Schemas (DTOs)

- `RoleDto`
- `UserDto`
- `CategoryDto`
- `CommentDto`
- `PostDto`
- `JwtAuthRequest`
- `JwtAuthResponse`
- `PostResponse`
- `ApiResponse`

---

## ✅ Requirements

- Java 17+
- Spring Boot 3.x
- Maven or Gradle
- PostgreSQL or H2 (configurable)
- JWT (for authentication)

---

## 🚀 Running the App

1. Clone the repository  
2. Configure your `application.properties` or `application.yml` (DB, JWT, etc.)  
3. Run the application:

```bash
./mvnw spring-boot:run
```

4. Access the API via `http://localhost:9192`

---

## 📌 Notes

- Use tools like Postman or Swagger UI for easy testing.
- Ensure proper headers for JWT (`Authorization: Bearer <token>`).

---

Let me know if you’d like a version with usage examples or a Swagger-UI link!

## 🗃 ER Diagram

The ER diagram is available at:  
📁 `src/main/resources/docs/ER_Diagram.png`

---

## 🛠 Setup Instructions

1. Clone this repository:
   ```bash
   git clone https://github.com/Puni2001/blogging_application.git
