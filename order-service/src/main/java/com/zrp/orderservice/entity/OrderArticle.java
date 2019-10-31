package com.zrp.orderservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "order_articles")
public class OrderArticle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "web_order_id")
    private String webOrderId;

    @Column(name = "line_item")
    private String lineItem;

    @Column(name = "article_id")
    private String articleId;

    @Column(name = "order_qty")
    private int orderQty;

    @Column(name = "qty_uom")
    private String qtyUom;

    @Column(name = "high_volume_flag")
    private Boolean highVolumeFlag;

    @Column(name = "console_flag")
    private Boolean consoleFlag;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "picked_qty")
    private int pickedQty;

    @Column(name = "article_status")
    private String articleStatus;

    @Transient
    private int articleStockQty = 0;

}
