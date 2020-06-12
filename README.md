# ssh-shop

To start server:
  go to ssh-shop-server directory
  run: npm install
  setup your env variables, a template can be found at the root named .env.example
  the server uses mysql database also mongo db
  to setup your mysql server you must run:
  sequelize db:create  ; it will create the database
  sequelize db:migrate  ; it will create the necessary tables
  sequelize db:seed:all ; it will add some necessary data on the tabels such as categories, citiest etc.
  then run: npm start
 
To start client:
  go to ssh-shop-client directory
  you must have installed java on you system
  run the command mvn javafx:run 
  it should download all the necessary dependencies and then should start
  
