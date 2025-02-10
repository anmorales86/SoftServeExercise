## Instalation

Step 1: Clone the repository:
```
$ git clone https://github.com/anmorales86/SoftServeExercise
```

Step 2: cd into the cloned repository

step 3: Build the project using Maven:
```shell
  ./gradlew clean build
```
step 4: Before running the project it is necessary that the VPN is turned on. Run the application:
```shell
  ./gradlew bootRun
```

## Postman
```
curl --location 'http://localhost:8080/api/no-duplicate' \
--header 'Content-Type: application/json' \
--data '{
    "sentence": "AABBCCD112233",
    "level": "3"
}'
```
