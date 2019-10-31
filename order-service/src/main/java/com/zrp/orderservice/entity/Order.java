package com.zrp.orderservice.entity;

import lombok.Data;
import org.hibernate.type.NumericBooleanType;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sap_order_id")
    private String sapOrderId ;

    @Column(name = "web_order_id")
    private String webOrderId;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "sap_order_created_date")
    private Date sapOrderCreatedDate ;

    @Column(name = "sap_order_created_time")
    private String sapOrderCreatedTime;

    @Column(name = "black_list_time")
    private String blackListTime;

    @Column(name = "number_of_sku")
    private int numberOfSku;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "order_type")
    private String orderType ;

    @Column(name = "distributed_channel")
    private int distributedChannel;

    @Column(name = "lock_status")
    private NumericBooleanType lockStatus;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "payment_acknowledgement")
    private NumericBooleanType paymentAcknowledgement;

    @Column(name = "put_away_location")
    private String putAwayLocation;

    @Column(name = "return_flag")
    private NumericBooleanType returnFlag;

}
