package com.basic.rabbitmq.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  
 *  Auto Generate
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RabbitConf {
							   
    /** 自增长主键 */
    private Integer id;
    /** 实例主机 */
    private String host;
    /** 端口 */
    private Integer port;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 虚拟主机 */
    private String vHost;
    /** 交换机名称 */
    private String exchangeName;
    /** 交换机类型 */
    private String exchangeType;
    /** 消息队列 */
    private String queue;
    /** 路由关键字 */
    private String routingKey;
    /** 是否自动确认 */
    private boolean confirm;
    /** 消息处理器代码 */
    private String callback;
    /** 描述 */
    private String remark;
}