FROM node:14 as react-build
WORKDIR /builddir
COPY . ./
RUN yarn
RUN yarn build

# Stage 2 - the production environment
FROM nginx:alpine 
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=react-build /builddir/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]