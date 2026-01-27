# 小区物业管理系统 - 客户端

基于 Spring Boot 3.2.4 + MyBatis 3.0.3 + Java 17 的小区物业管理系统客户端模块，
由成都工业职业技术学院开发，用于实现业主、楼栋、用户管理等功能。

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen)](https://spring.io/projects/spring-boot)
[![MyBatis](https://img.shields.io/badge/MyBatis-3.0.3-blue)](https://mybatis.org/mybatis-3/)
[![Java](https://img.shields.io/badge/Java-17-orange)](https://adoptium.net/)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

## 目录

- [项目简介](#项目简介)
- [功能特性](#功能特性)
- [技术架构](#技术架构)
- [快速开始](#快速开始)
- [API接口文档](#api接口文档)
- [安全配置](#安全配置)
- [开发规范](#开发规范)
- [贡献指南](#贡献指南)

## 项目简介

小区物业管理系统是一个现代化的物业管理后端服务系统，专为小区物业管理场景设计。该系统基于Spring Boot框架开发，提供RESTful
API接口，支持小区楼栋管理、房间管理、业主信息管理、费用管理等核心功能。

本项目采用前后端分离架构，后端专注于业务逻辑处理和数据接口提供，便于与各种前端框架集成。

## 功能特性

### 核心功能模块

- **楼栋管理**
  - 楼栋信息维护
  - 楼栋状态管理
  - 批量楼栋创建

- **房间管理**
  - 房间信息维护
  - 房间状态跟踪（自住、出租、空置等）
  - 房间分页查询

- **业主管理**
  - 业主信息维护
  - 业主状态管理
  - 未缴费业主查询

- **费用管理**
  - 水电费等费用标准维护
  - 缴费记录查询
  - 按条件筛选支付信息

### 系统特性

- 完整的RESTful API设计
- 基于JWT的权限验证（规划中）
- 数据分页查询支持
- 详细的接口文档
- 安全的配置管理

## 技术架构

### 核心技术栈

- **后端框架**: Spring Boot 3.2.4
- **持久层**: MyBatis 3.0.3 + PageHelper分页插件
- **API文档**: SpringDoc OpenAPI + Knife4j
- **数据库**: MySQL
- **开发语言**: Java 17
- **构建工具**: Maven

### 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── com/springboot/springboot2/
│   │       ├── config/              # 配置类
│   │       ├── constant/            # 常量定义
│   │       ├── controller/          # 控制器层
│   │       ├── mapper/              # 数据访问层
│   │       ├── pojo/                # 数据模型
│   │       ├── service/             # 业务逻辑层
│   │       ├── utils/               # 工具类
│   │       └── SpringBoot2Application.java  # 应用启动类
│   └── resources/
│       ├── application.yml          # 主配置文件
│       ├── application-private.yml  # 私有配置文件（示例）
│       └── mapper/                  # MyBatis映射文件
└── test/
    └── java/                        # 测试代码
```

## 快速开始

### 环境要求

- Java 17 或更高版本
- Maven 3.6+
- MySQL 5.7+

### 安装步骤

1. 克隆项目代码：
```bash
git clone <项目地址>
cd SpringBoot2
```

2. 创建数据库并导入表结构（数据库脚本需另外提供）

3. 配置数据库连接（参考[安全配置](#安全配置)部分）

4. 编译和运行：
```bash
mvn spring-boot:run
```

或者打包运行：
```bash
mvn clean package
java -jar target/小区物业管理系统-物业端.jar
```

## API接口文档

项目集成了Swagger UI和Knife4j增强文档界面，启动项目后可以通过以下地址访问API文档：

- Swagger UI: http://localhost:8081/swagger-ui.html
- Knife4j文档: http://localhost:8081/doc.html

## 安全配置

为了保护敏感信息（如数据库密码），本项目采用安全配置方案：

### 配置文件分离

项目使用两个配置文件：

- `application.yml` - 公共配置文件，包含占位符，可提交到版本控制系统
- `application-private.yml` - 私有配置文件，包含真实敏感信息，已被添加到`.gitignore`

### 配置方式

可以通过以下任一方式提供数据库凭证：

1. **环境变量（推荐）**

```bash
export DB_USERNAME=your_database_username
export DB_PASSWORD=your_database_password
```

2. **私有配置文件**
   创建`application-private.yml`文件并填写：

```yaml
DB_USERNAME: your_database_username
DB_PASSWORD: your_database_password
```

3. **命令行参数**

```bash
java -jar your-application.jar --DB_USERNAME=your_username --DB_PASSWORD=your_password
```

## 开发规范

### 配置管理规范

- 公共配置文件`application.yml`使用占位符，可以安全提交到版本控制系统
- 私有配置文件`application-private.yml`包含敏感信息，必须加入`.gitignore`避免泄露

### 代码规范

- 使用Lombok简化实体类开发
- 采用构造函数注入替代字段注入
- 统一使用ResponsePojo封装返回结果
- 使用PageResult封装分页查询结果
- 使用Swagger注解完善API文档

## 贡献指南

欢迎提交Issue和Pull Request来帮助改进项目。

### 开发流程

1. Fork项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启Pull Request

### 注意事项

- 请勿在代码中包含任何敏感信息
- 遵循项目现有的代码风格
- 添加适当的测试用例
- 更新相关文档