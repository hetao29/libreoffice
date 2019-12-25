package soffice

import (
	context "context"
	"google.golang.org/grpc"
	codes "google.golang.org/grpc/codes"
	status "google.golang.org/grpc/status"
	"path/filepath"
	"proto/libreoffice"
	//"bufio"
	"io/ioutil"
	"log"
	"os"
	"os/exec"
	"time"
)

func init() {
}

// Soffice ...
type Soffice struct {
	libreoffice.LibreofficeServer
}

// Register ...
func Register(s *grpc.Server) {
	libreoffice.RegisterLibreofficeServer(s, &Soffice{})
}

// Convert ...
func (i *Soffice) Convert(ctx context.Context, req *libreoffice.Request) (*libreoffice.Response, error) {
	//生成临时目录
	dir, err := ioutil.TempDir("/tmp", "libreoffice_")
	if err != nil {
		return nil, status.Errorf(codes.Internal, "gen tmp dir error!")
	}
	defer func() {
		err := os.RemoveAll(dir)
		if err != nil {
			log.Println("rm dir error:", dir, err)
		} else {
			log.Println("rm dir ok:", dir)
		}
	}()
	//保存文件
	baseName := ""
	if req.Name != "" {
		baseName = filepath.Base(req.Name)
		err := ioutil.WriteFile(dir+"/"+baseName, req.Content, 0644)
		if err != nil {
			log.Println("save file error", req.Name, baseName)
			return nil, status.Errorf(codes.Internal, "save file error!")
		}
		log.Println("save file ok", req.Name, baseName)

	} else {
		return nil, status.Errorf(codes.Internal, "name is empty!")
	}
	//转成pdf
	if convertToPdf(dir+"/"+baseName, dir) == false {
		return nil, status.Errorf(codes.Aborted, "convert to pdf!")
	}
	ext := filepath.Ext(baseName)
	basePdfName := baseName[0:len(baseName)-len(ext)] + ".pdf"
	log.Println("baseName,ext,basePdfName is ", baseName, ext, basePdfName)
	//转成jpg
	if convertToImage(dir+"/"+basePdfName, dir) == false {
		return nil, status.Errorf(codes.Aborted, "convert to pdf!")
	}
	response := &libreoffice.Response{}

	files, _ := filepath.Glob(dir + "/*.png")
	log.Println(files)
	for i, file := range files {
		content := libreoffice.FileContent{}
		log.Println(file, i)
		body, err := ioutil.ReadFile(file)
		if err != nil {
			log.Println(err)
			continue
		}

		content.Name = filepath.Base(file)
		content.Order = int32(i)
		content.Content = body
		response.Files = append(response.Files, &content)
	}
	return response, nil // status.Errorf(codes.Unimplemented, "Logined ")
}
func convertToPdf(file string, outdir string) bool {
	/**
	* 设置最长的超进100秒
	 */
	//check
	path, err := exec.LookPath("soffice")
	if err != nil {
		log.Println(err, "soffice is empty")
		return false
	}
	log.Println("find soffice ", path)
	log.Println("convertToPdf", path, file, outdir)
	ctx, cancel := context.WithTimeout(context.Background(), 100*1000*time.Millisecond)
	defer cancel()
	cmd := exec.CommandContext(ctx, path, "--headless", "--convert-to", "pdf", file, "--outdir", outdir)
	stdout, err := cmd.CombinedOutput()
	if err != nil {
		log.Println("cmd StdoutPipe error:", file, err, string(stdout))
		return false
	}
	log.Println(stdout)
	return true
}
func convertToImage(file string, outdir string) bool {
	/**
	* 设置最长的超进100秒
	 */
	//check
	path, err := exec.LookPath("gs")
	if err != nil {
		log.Println(err, "gs is empty")
		return false
	}
	log.Println("find gs", path)
	log.Println("convertToImage", path, outdir)
	ctx, cancel := context.WithTimeout(context.Background(), 100*1000*time.Millisecond)
	defer cancel()
	cmd := exec.CommandContext(ctx, path, "-sDEVICE=pngalpha", "-o", outdir+"/"+"%03d.png", "-r96", file) //outdir+"/*.pdf")
	stdout, err := cmd.CombinedOutput()
	if err != nil {
		log.Println("cmd StdoutPipe error:", err, string(stdout))
		return false
	}
	log.Println(stdout)
	return true
}
