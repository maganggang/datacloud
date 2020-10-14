package com.fei2e.getlost.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "pay_payment")
public class Payment {
    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 业务系统的用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 业务系统
     */
    private String source;

    /**
     * *支付号对应订单编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 提供给第三方的支付ID号
     */
    @Column(name = "payment_code")
    private String paymentCode;

    /**
     * 支付渠道
     */
    @Column(name = "pay_source")
    private String paySource;

    /**
     * 支付方式
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 支付状态0未回调,1已回调 2处理成功 3处理失败
     */
    @Column(name = "pay_status")
    private Byte payStatus;

    /**
     * 财务结算状态0未结算 1成功 2失败
     */
    @Column(name = "finance_status")
    private Byte financeStatus;

    /**
     * 支付金额
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 手续费
     */
    @Column(name = "pay_handle_fee")
    private BigDecimal payHandleFee;

    /**
     * 描述 微信为128字节 支付宝为256字节
     */
    private String subject;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 上次修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 用户支付时间(收到回调)
     */
    @Column(name = "pay_time")
    private Integer payTime;

    /**
     * 支付关闭时间
     */
    @Column(name = "finish_time")
    private Integer finishTime;

    /**
     * 成功后下一个状态
     */
    @Column(name = "next_state")
    private String nextState;

    /**
     * 是否删除
     */
    private Byte deleted;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取业务系统的用户ID
     *
     * @return user_id - 业务系统的用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置业务系统的用户ID
     *
     * @param userId 业务系统的用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取业务系统
     *
     * @return source - 业务系统
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置业务系统
     *
     * @param source 业务系统
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取*支付号对应订单编号
     *
     * @return order_code - *支付号对应订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置*支付号对应订单编号
     *
     * @param orderCode *支付号对应订单编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取提供给第三方的支付ID号
     *
     * @return payment_code - 提供给第三方的支付ID号
     */
    public String getPaymentCode() {
        return paymentCode;
    }

    /**
     * 设置提供给第三方的支付ID号
     *
     * @param paymentCode 提供给第三方的支付ID号
     */
    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    /**
     * 获取支付渠道
     *
     * @return pay_source - 支付渠道
     */
    public String getPaySource() {
        return paySource;
    }

    /**
     * 设置支付渠道
     *
     * @param paySource 支付渠道
     */
    public void setPaySource(String paySource) {
        this.paySource = paySource;
    }

    /**
     * 获取支付方式
     *
     * @return pay_type - 支付方式
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付方式
     *
     * @param payType 支付方式
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取支付状态0未回调,1已回调 2处理成功 3处理失败
     *
     * @return pay_status - 支付状态0未回调,1已回调 2处理成功 3处理失败
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态0未回调,1已回调 2处理成功 3处理失败
     *
     * @param payStatus 支付状态0未回调,1已回调 2处理成功 3处理失败
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取财务结算状态0未结算 1成功 2失败
     *
     * @return finance_status - 财务结算状态0未结算 1成功 2失败
     */
    public Byte getFinanceStatus() {
        return financeStatus;
    }

    /**
     * 设置财务结算状态0未结算 1成功 2失败
     *
     * @param financeStatus 财务结算状态0未结算 1成功 2失败
     */
    public void setFinanceStatus(Byte financeStatus) {
        this.financeStatus = financeStatus;
    }

    /**
     * 获取支付金额
     *
     * @return pay_amount - 支付金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置支付金额
     *
     * @param payAmount 支付金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取手续费
     *
     * @return pay_handle_fee - 手续费
     */
    public BigDecimal getPayHandleFee() {
        return payHandleFee;
    }

    /**
     * 设置手续费
     *
     * @param payHandleFee 手续费
     */
    public void setPayHandleFee(BigDecimal payHandleFee) {
        this.payHandleFee = payHandleFee;
    }

    /**
     * 获取描述 微信为128字节 支付宝为256字节
     *
     * @return subject - 描述 微信为128字节 支付宝为256字节
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置描述 微信为128字节 支付宝为256字节
     *
     * @param subject 描述 微信为128字节 支付宝为256字节
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取订单创建时间
     *
     * @return create_time - 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取上次修改时间
     *
     * @return modify_time - 上次修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置上次修改时间
     *
     * @param modifyTime 上次修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取用户支付时间(收到回调)
     *
     * @return pay_time - 用户支付时间(收到回调)
     */
    public Integer getPayTime() {
        return payTime;
    }

    /**
     * 设置用户支付时间(收到回调)
     *
     * @param payTime 用户支付时间(收到回调)
     */
    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取支付关闭时间
     *
     * @return finish_time - 支付关闭时间
     */
    public Integer getFinishTime() {
        return finishTime;
    }

    /**
     * 设置支付关闭时间
     *
     * @param finishTime 支付关闭时间
     */
    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取成功后下一个状态
     *
     * @return next_state - 成功后下一个状态
     */
    public String getNextState() {
        return nextState;
    }

    /**
     * 设置成功后下一个状态
     *
     * @param nextState 成功后下一个状态
     */
    public void setNextState(String nextState) {
        this.nextState = nextState;
    }

    /**
     * 获取是否删除
     *
     * @return deleted - 是否删除
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除
     *
     * @param deleted 是否删除
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}