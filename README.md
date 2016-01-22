Pocket Money Tracker
====================

This is a simple web-based application to allow you to keep track of pocket money for any number of children. It works a bit like a bank, with individual accounts that are credited with a specified amount each week (on the day you specify). As an administrative user you can also add money as a reward for good behaviour, and subtract money for bad behaviour. If you give your child cash from their pocket money "account" you can record that as a "withdrawal", and their balance will be reduced accordingly.

The children can also be given logins, and they can use these to view their balance and transactions, and they can even update their own profile picture!

Installation
============

First, clone the repository to a local directory:

```
git clone https://github.com/ahatherly/pocketmoneytracker.git
```

Then, use maven to build a war file:

```
mvn install
```

Now, deploy the war file into a servlet container (e.g. tomcat).

Database Setup
==============

You will need to have mongodb installed to hold the data. By default it will assume mongodb is running on localhost, and the first time you access the application a new databse called "pocketmoneytracker" will be added. You can change the name of the database, as well as the connection host, port etc for mongodb by editing the config.properties file in the src/main/resources directory before running the maven build.