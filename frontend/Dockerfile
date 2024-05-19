FROM nginx:alpine

ENV TZ Asia/Seoul

COPY dist/ /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]