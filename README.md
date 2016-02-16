Upgrade latest version of Ubuntu
--------------------------------

    $ sudo apt-get update
    $ sudo apt-get dist-upgrade

Install Java
------------

    $ sudo apt-add-repository ppa:webupd8team/java
    $ sudo apt-get update
    $ sudo apt-get install oracle-java8-installer
    $ vi .profile
      # Java environment
      export JAVA_HOME=/usr/lib/jvm/java-8-oracle

Install Apache2
---------------

    $ sudo apt-get install apache2 libapache2-mod-jk
    $ cd /etc/apache2/sites-enabled
    $ sudo vi 000-default.conf
      JkMount         /*      tomcat
    $ cd /etc/libapache2-mod-jk/
    $ sudo vi workers.properties
      workers.tomcat_home=/home/vagrant/tomcat8

      workers.java_home=/usr/lib/jvm/java-8-oracle

      worker.list=tomcat

      worker.tomcat.port=8009
      worker.tomcat.host=localhost
      worker.tomcat.type=ajp13
    $ sudo service apache2 restart

Install Tomcat
--------------

    $ wget http://mirror.apache-kr.org/tomcat/tomcat-8/v8.0.23/bin/apache-tomcat-8.0.23.tar.gz
    $ tar zxvf apache-tomcat-8.0.23.tar.gz
    $ mv apache-tomcat-8.0.23 tomcat8
    $ mkdir -p tomcat8/conf/Catalina/localhost
    $ cd tomcat8/conf/Catalina/localhost
    $ vi seobaksa.xml
      <Context docBase="/vagrant/build/classes/artifacts/seobaksa-server/exploded/seobaksa-server-1.0.0.war"
               path=""
               reloadable="false"
               useHttpOnly="true"/>

Install MySQL
-------------

    $ sudo apt-get install mysql-server
    $ sudo vi /etc/mysql/my.cnf
      bind-address            = 127.0.0.1

      character-set-client-handshake = FALSE
      init_connect    = "SET collation_connection = utf8_general_ci"
      init_connect    = "SET NAMES utf8"
      character-set-server    = utf8
      collation-server        = utf8_general_ci

      default-character-set   = utf8
    $ sudo service mysql restart
    $ mysql -u root -p
      mysql> SET GLOBAL storage_engine = 'InnoDB';
      mysql> CREATE DATABASE seobaksa CHARACTER SET utf8 COLLATE utf8_bin;
      mysql> GRANT ALL PRIVILEGES ON seobaksa.* TO 'seobaksa'@'localhost' IDENTIFIED BY 'drseopw';
      mysql> GRANT ALL PRIVILEGES ON seobaksa.* TO 'seobaksa'@'%' IDENTIFIED BY 'drseopw';
      mysql> FLUSH PRIVILEGES;
      mysql> QUIT

Install MongoDB
---------------

    $ sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10
    $ echo "deb http://repo.mongodb.org/apt/ubuntu "$(lsb_release -sc)"/mongodb-org/3.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.0.list
    $ sudo apt-get update
    $ sudo apt-get install mongodb-org
    $ sudo vi /etc/mongod.conf
      bind_ip = 0.0.0.0
    $ sudo service mongod restart
