# changancentre
长安资源中心

>如果没有mongodb，先安装mongodb(Homebrew环境示例：)
>brew install mongodb


clone后直接运行
>mvn spring-boot:run

打开页面进行api测试  
```
http://localhost:8080/swagger-ui.html   
```
or

```
curl -X POST --header 'Content-Type: multipart/form-data' --header 'Accept: */*' -F "file=@temp.xls" 'http://localhost:8080/rest/upload'  
```


ctjsoft.com
