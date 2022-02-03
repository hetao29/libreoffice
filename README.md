# 说明 

## @deprecated ,change to https://github.com/hetao29/url2image

把 office文件，转换成 png 的grpc 服务，具体的定义参考 proto_src 里的协议
默认 docker 服务的端口是10001

## 目录说明

1. proto_src目录，proto 的定义文件目录
2. client 目录，golang语言的客户端示例代码
2. client_xxx目录，是各种语言的客户端示例代码
3. proto目录，是自动生成的 proto 的目录，不需要手动修改
4. server/client目录，golang 版本的 server 和 client

## Makefile 说明

### 编译
```bash
make build
```

### 运行
```bash
make dockerpull
make dockerrun
```
