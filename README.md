# Developer Task Management System

## Proje Tanımı
`Developer Task Management System`, yazılım geliştiricilerin görevlerini yönetebileceği bir Java uygulamasıdır. Bu uygulama, kullanıcıların görev eklemesine, görüntülemesine ve düzenlemesine olanak tanırken, kullanıcı dostu bir arayüz sunar. Proje, geliştiricilerin görevlerini kategorilere ayırarak daha verimli bir şekilde takip etmelerine yardımcı olmayı amaçlamaktadır.

## Kullanılan Teknolojiler

**Frontend:**
- React.js
- React Router

**Backend:**
- Java
- Spring Framework
- Spring Security (JWT ile güvenlik)
- MySQL (Veritabanı)

## Kurulum

### Gereksinimler

- Java 11 veya daha yüksek
- Node.js (v14 veya daha yeni)
- MySQL


### Backend Kurulumu (Spring Boot)

1. Projeyi indirin:
    ```bash
    git clone <https://github.com/4-0-4-Brain-Not-Found/Task-Managment-System.git>
    ```

2. Proje dizinine gidin:
    ```bash
    cd  src/main/java/_4/example/taskManagement/

    ```

3. MySQL veritabanını oluşturun ve `application.properties` dosyasını aşağıdaki gibi yapılandırın:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/yourdbname
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    ```

4. Uygulamayı başlatın:
    ```bash
    ./mvnw spring-boot:run
    ```

### Frontend Kurulumu (React)

1. Frontend dizinine gidin:
    ```bash
    cd front-task-management
    ```

2. Gerekli bağımlılıkları yükleyin:
    ```bash
    npm install
    ```

3. Uygulamayı başlatın:
    ```bash
    npm start
    ```

Frontend uygulaması varsayılan olarak `http://localhost:3000` adresinde çalışacaktır.

## API Referansı

### Account Controller API'si

#### Login Endpoint

- **Endpoint**: `POST /login`
- **İstek Gövdesi**:
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```

#### Register Endpoint

- **Endpoint**: `POST /register`
- **İstek Gövdesi**:
    ```json
    {
      "username": "string",
      "email": "string",
      "phoneNo": "string",
      "password": "string"
    }
    ```
### Admin Account Controller API'si
#### Admin Login Endpoint

- **Endpoint**: `POST /admin/login`
- **İstek Gövdesi**:
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```
### Admin Task Controller API'si
#### Get All Task Endpoint
- **Endpoint**: `GET /admin/tasks`

#### Get Task By ID Endpoint
- **Endpoint**: `GET /admin/tasks/{id}`

#### Create Task Endpoint

- **Endpoint**: `POST /admin/tasks`
- **İstek Gövdesi**:
    ```json
    {
      "title": "string",
      "description": "string",
      "dueDate": "date",
      "taskStatus": "TaskStatus" 
    }
    ```
  #### Update Task Endpoint

- **Endpoint**: `PUT /admin/tasks/{id]`
- **İstek Gövdesi**:
    ```json
     {
      "title": "string",
      "description": "string",
      "dueDate": "date",
      "taskStatus": "TaskStatus" 
    }
    ```
  #### Delete Task Endpoint

- **Endpoint**: `DELETE /admin/tasks/{id]`

## Özellikler



## Testler
Bu projede `TaskController` sınıfının işlevselliği, **JUnit 5** ve **Mockito** kullanılarak test edilmiştir.
1. Test dosyası:
    ```bash
    .cd src/test/java/_4/example/taskManagement/controller/TaskControllerTest.java

    ```