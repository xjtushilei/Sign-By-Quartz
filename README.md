# Sign-By-Quartz
享受你的生活，从自动签到系统开始。使用Spring-boot，Spring-security，thymeleaf，quartz，AmazeUI，Mail


# 完成

目前本系统实现了基本的签到逻辑，用户登录系统，部分用户签到在线设置功能。

## 后记

并没有继续写下去，应为 thymeleaf 太难用了， 好多效果出不来，对 js 太不友好了。再结合 spring mvc 这种架构，感觉有点烦。但是目前自己还不会 Restful API 安全验证，准备之后入手学习后可以用微服务的架构来一套。

## 生产系统

目前生产系统使用的是 spring-boot 自带的` @Scheduled cron` ，需要我人工来维护大家的表。谁想自动签到和我说一下，我加进去数据里的用户表的一行信息就行了。自动签到和随机仿真签到的功能还是有的。

当然了，这和 quartz 就没有了关系，不过能灵活使用也算一个好点子吧。

想看代码可以迁移至 `pro` 分支 ，提供[传送门](https://github.com/xjtushilei/Sign-By-Quartz/tree/pro)，方便大家前去。