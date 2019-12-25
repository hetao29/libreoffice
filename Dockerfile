FROM golang:1.13-alpine as builder
LABEL maintainer="hetao<hetao@hetao.name>"
LABEL version="1.0"

WORKDIR /data/libreoffice/

COPY . ./

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \ 
	&& apk update && apk add tree \
	&& tree -L 4 && export GOPROXY=https://goproxy.cn && cd server && go build -v -o ../bin/server . \
	&& rm -rf /var/lib/apk/*



FROM alpine:3.10 as prod

WORKDIR /data/libreoffice/

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \ 
	&& apk --no-cache add ca-certificates \
	&& apk update \
	&& apk add libreoffice openjdk8-jre-base rsyslog imagemagick \
	&& apk add msttcorefonts-installer mkfontscale ttf-dejavu fontconfig font-adobe-100dpi font-adobe-75dpi

RUN mkdir /usr/share/fonts/custom

COPY . ./

COPY fonts/* /usr/share/fonts/custom/

RUN update-ms-fonts \
	&& mkfontscale \
	&& fc-cache -f -v

COPY --from=builder /data/libreoffice/bin/* ./bin/

HEALTHCHECK CMD netstat -an | grep 50000 > /dev/null; if [ 0 != $? ]; then exit 1; fi;

EXPOSE 50000/tcp

CMD ["/data/libreoffice/bin/server"]
