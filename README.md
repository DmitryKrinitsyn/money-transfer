# money-transfer

How to start the money-transfer application
---

1. Run `mvn clean verify` to build your application
2. Start application with `java -jar target/money-transfer-1.0.0.jar server`
3. To check your application:

`curl -X POST http://localhost:8080/new-account`

`curl -d '{"accountNumber":"1", "value":"20"}' -H "Content-Type: application/json" -X PUT http://localhost:8080/change`

`curl http://localhost:8080/balance?account=1`

` curl -d '{"sourceAccountNumber":"2", "destinationAccountNumber":"3", "value":"15"}' -H "Content-Type: application/json" -X PUT http://localhost:8080/transfer`