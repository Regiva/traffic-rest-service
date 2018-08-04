# traffic-rest-service

REST-сервис по сбору статистики посещаемости WEB-сайта

Поддерживаются два метода:
* **Создать событие посещения сайта пользователем** 

  Параметры: *идентификатор пользователя, идентификатор страницы*
  
  Пример:
  ```http
  http://localhost:8080/visit?id=1&page_id=42
  ```
  Ответом будет JSON, содержащий количество посещений и количество уникальных пользователей за текущие сутки:
  ```json
  [ {
  "description" : "Today's visits",
  "count" : 2
  }, {
  "description" : "Today's unique visits",
  "count" : 2
  } ]
  ```
* **Получить статистику посещений за указанный период**

  Параметры: *период учёта (начало и конец в формате yyyy-mm-dd)*
  
  Пример: 
  ```http
  http://localhost:8080/traffic_rest_service-0.1/traffic?from=2018-08-01&to=2018-08-04
  ```
  
  Ответом будет JSON, содержащий количество посещений, количество уникальных пользователей и 
  количество постоянных пользователей (то есть тех, кто посетил больше 10 страниц) за период:
  ```json
  [ {
  "description" : "Count of visits in given period",
  "count" : 1
  }, {
  "description" : "Count of unique visits in given period",
  "count" : 1
  }, {
  "description" : "Count of regular users",
  "count" : 0
  } ]
  ```
## Сборка проекта

  ```
  mvn clean package
  ```
  
## Развёртка проекта
  Для GlassFish:
  ```
  as-install/bin/asadmin start-domain // например /opt/glassfish/bin/asadmin start-domain
  as-install/bin/asadmin deploy war-name // например /opt/glassfish5/bin/asadmin deploy ~/IdeaProjects/traffic_rest_service/target/traffic_rest_service-0.1.war
  ```
