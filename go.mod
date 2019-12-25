module main

go 1.13

replace modules/soffice => ./modules/soffice

replace proto/libreoffice => ./proto/libreoffice

replace modules/user/info => ./modules/user/info

require (
	github.com/facebookgo/grace v0.0.0-20180706040059-75cf19382434
	github.com/google/logger v1.0.1
	github.com/gookit/config/v2 v2.0.13
	github.com/grpc-ecosystem/grpc-gateway v1.12.1
	golang.org/x/lint v0.0.0-20191125180803-fdd1cda4f05f // indirect
	golang.org/x/tools v0.0.0-20191224055732-dd894d0a8a40 // indirect
	google.golang.org/genproto v0.0.0-20191223191004-3caeed10a8bf // indirect
	google.golang.org/grpc v1.26.0
	gopkg.in/yaml.v2 v2.2.7 // indirect
	modules/soffice v0.0.0-00010101000000-000000000000
	proto/libreoffice v0.0.0-00010101000000-000000000000
)
