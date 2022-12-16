# Report It 
Full stack application built with Java utilizing Spring Boot. User authentication via Spring Security. SQL persistent. 

Report It empowers residents to report issues, identify repair needs, and share feedback. For local governments, it powers efficient, transparent workflows which aim to foster both accountability and trust. The resulting collaboration between governments and residents co-create clean, safe communities.



Clone the project

```
git clone https://github.com/Tfauves/ReportIt
```

Open project

### Application.Properties:

Spring Configs:
```
Spring.jpa.hibernate.ddl-auto=update
Spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

MySQL Configs: 
```
Spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
Spring.datasource.username=<username>
Spring.datasource.password=<password>
```

JWT Configs:
```
application properties
reportIt.app.jwtSecret=<secret_string>
```
##  Authentication

#### Sign Up

```
 POST /api/auth/signup
```
Logging in will grant access to the authenticated routes.

| Body      | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `signup_request` | `object` | **Required** {username: <username>, password: <password>} |


#### Sign in

```
 POST /api/auth/signin
```

Returns JWT Token used as a Bearer to access authenticated routes

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `signin_request` | `object` | **Required** {username: <username>, password: <password>} |

