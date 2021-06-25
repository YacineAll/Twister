# Derived from official mysql image (our base image)
FROM mysql:5.7.30
# Add a database

ENV MYSQL_ROOT_PASSWORD password
ENV MYSQL_DATABASE firstDataBase
ENV MYSQL_USER ghiles, MYSQL_PASSWORD password


# Add the content of the sql-scripts/ directory to your image
# All scripts in docker-entrypoint-initdb.d/ are automatically
# executed during container startup
COPY ./sql-scripts/ /docker-entrypoint-initdb.d/
