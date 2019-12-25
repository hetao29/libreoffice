all:
	@echo "Please specify the cmd!";
build:
	@#正常编译
	@export GOPROXY=https://goproxy.cn && cd server && go build -v -ldflags "-X main.version=1.0.0 -X main.build=`date -u +%Y-%m-%d%H:%M:%S`" -o ../bin/server .
	@export GOPROXY=https://goproxy.cn && cd client && go build -v -ldflags "-X main.version=1.0.0 -X main.build=`date -u +%Y-%m-%d%H:%M:%S`" -o ../bin/client .
start:	
	#./bin/server
test:
	./bin/client


###下面是和 protobuf 协议相关的操作，除非更新 proto，一般情况不需要安装
initprotoc:
	#可选!!!
	#安装protobuf 相关工具，用来生成 proto 目录里的代码，只有需要更新协议的才需要安装
	#protoc 的相关初始工作
	#https://github.com/grpc-ecosystem/grpc-gateway
	sudo apt-get install autoconf automake libtool curl
	export GOPROXY=https://goproxy.cn && go get -u github.com/grpc-ecosystem/grpc-gateway/protoc-gen-grpc-gateway
	export GOPROXY=https://goproxy.cn && go get -u github.com/grpc-ecosystem/grpc-gateway/protoc-gen-swagger
	export GOPROXY=https://goproxy.cn && go get -u github.com/golang/protobuf/protoc-gen-go
	#git submodule add https://github.com/grpc/grpc.git grpc/grpc
	#git submodule add https://github.com/protocolbuffers/protobuf.git grpc/protobuf
	git submodule init
	git submodule update grpc/grpc
	cd grpc/grpc && git submodule init && git submodule update && make
	git submodule update grpc/protobuf
	REALPREFIX=$(realpath .) && cd grpc/protobuf && git submodule init && git submodule update && ./autogen.sh && ./configure --prefix=$$REALPREFIX/grpc/tmp && make && make check && sudo make install && sudo ldconfig

initjava:
	#可选!!!
	# 要生成 protobuf 的java 语言才需要安装，执行前，需要先 make initprotoc，不然编译不通过
	#git submodule add https://github.com/grpc/grpc-java.git grpc/grpc-java
	#https://www.jianshu.com/p/7b3bc89d26e5
	git submodule init
	git submodule update grpc/grpc-java
	#https://github.com/grpc/grpc-java/tree/master/compiler
	#REALPREFIX=$(realpath .) && cd grpc/grpc-java/compiler && export CXXFLAGS="-I$$REALPREFIX/grpc/tmp/include/" LDFLAGS="-L$$REALPREFIX/grpc/tmp/lib/" && echo $$REALPREFIX && ../gradlew -DsocksProxyHost=192.168.8.108 -DsocksProxyPort=1080  java_pluginExecutable
	REALPREFIX=$(realpath .) && cd grpc/grpc-java/compiler && export CXXFLAGS="-I$$REALPREFIX/grpc/tmp/include/" LDFLAGS="-L$$REALPREFIX/grpc/tmp/lib/" && echo $$REALPREFIX && ../gradlew java_pluginExecutable

genproto:
	#golang
	find proto_src -name "*.proto" | xargs -I {} ./grpc/tmp/bin/protoc \
	       --proto_path=proto_src \
	       --grpc-gateway_out=logtostderr=true:proto \
	       --plugin=proto-google-common-protos --go_out=plugins=grpc:proto \
	       "{}"
gencppproto:
	#c++
	find proto_src -name "*.proto" | xargs -I {} ./grpc/tmp/bin/protoc \
	       --proto_path=proto_src \
	       --grpc-gateway_out=logtostderr=true:proto \
	       --plugin=protoc-gen-grpc=grpc/grpc/bins/opt/grpc_cpp_plugin \
	       --grpc_out=proto/cpp \
	       --cpp_out=proto/cpp \
	       "{}"
genjavaproto:
	#java
	find proto_src -name "*.proto" | xargs -I {} ./grpc/tmp/bin/protoc \
	       --proto_path=grpc/grpc/third_party/googleapis \
	       --proto_path=proto_src \
	       --plugin=proto-google-common-protos \
		   --java_out=proto/java \
		   --grpc-java_out=proto/java \
		   --plugin=protoc-gen-grpc-java=grpc/grpc-java/compiler/build/exe/java_plugin/protoc-gen-grpc-java \
	       "{}"
genphpproto:
	#php
	#https://github.com/grpc/grpc/tree/master/src/php
	#https://pecl.php.net/package/grpc
	find proto_src -name "*.proto" | xargs -I {} ./grpc/tmp/bin/protoc \
		--php_out=proto/php \
		--grpc_out=proto/php \
		--proto_path=grpc/grpc/third_party/googleapis \
		--proto_path=proto_src \
		--plugin=protoc-gen-grpc=grpc/grpc/bins/opt/grpc_php_plugin \
		"{}"

##docker 和 k8s 相关
dockerbuild:
	#可选!!!
	#生成 docker image
	docker build . -t tools/libreoffice:v1.0.0
dockertest:
	docker run -d -p 10001:50000 --name libreoffice tools/libreoffice:v1.0.0
dockerin:
	docker exec -it libreoffice /bin/ash
dockerrm:
	docker image rm tools/libreoffice:v1.0.0
dockerpull:
	docker login hub.docker.com
	docker pull hub.docker.com/tools/libreoffice:v1.0.0
dockerrun:
	docker run -d -p 10001:50000 --name libreoffice hub.docker.com/tools/libreoffice:v1.0.0
dockerpush:
	docker login hub.docker.com
	docker tag tools/libreoffice:v1.0.0 hub.docker.com/tools/libreoffice:v1.0.0
	docker push hub.docker.com/tools/libreoffice:v1.0.0
lint:
	#可选!!!
	export GOPROXY=https://goproxy.cn && go get -u golang.org/x/lint/golint
	find . -name "*go" | xargs -I {} golint "{}"
	#golint modules/...
	#golint server/*
	#golint client/*
fmt:
	#可选!!!
	find . -name "*go" | xargs -I {} go fmt "{}"
