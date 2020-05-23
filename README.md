# AUTO
> Automated deployment

## 初衷
利用 github 的 WebHooks 动态更新个人博客 :notebook:
这就涉及服务调用 Linux 脚本，调用通过 JSch 完成。
- 核心功能一个类就搞定了 :joy::joy:

## 如何具备拓展性呢？
这也正是本项目的价值。

## 技术栈
- WebFlux
- Docker :whale2:

## 直接使用
镜像已 push 到 docker 仓库 [amos0626/auto](https://hub.docker.com/repository/docker/amos0626/auto)

1. 创建一个 `docker-compose.yml`
    ```yaml
    version: '3.5'
    services:
      auto:
        image: amos0626/auto
        container_name: auto
        ports:
          - '8080:8080'
        volumes:
          - './logs:/root/logs'
        environment:
          - JSCH_HOST=127.0.0.1
          - JSCH_USERNAME=root
          - JSCH_PASSWORD=root
          - COMMAND=./update.sh
    ```

2. 自定义参数

    |名字|备注|默认值|
    |---|---|---|
    |JSCH_HOST|服务器 IP / 域名|127.0.0.1|
    |JSCH_PORT|SSH 端口|22|
    |JSCH_USERNAME|用户名|root|
    |JSCH_PASSWORD|密码|root|
    |COMMAND|要执行的命令|无|

3. 测试一哈

    ```http request
    POST http://localhost:8080/pull
    Content-Type: application/json
    
    {
      "ref": "1433233"
    }
    
    ```