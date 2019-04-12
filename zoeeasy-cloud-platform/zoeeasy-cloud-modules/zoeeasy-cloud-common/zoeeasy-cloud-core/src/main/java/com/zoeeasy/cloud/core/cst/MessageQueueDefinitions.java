package com.zoeeasy.cloud.core.cst;

/**
 * 消息队列常量定义
 *
 * @author walkman
 */
public class MessageQueueDefinitions {

    private MessageQueueDefinitions() {

    }

    public static class Topic {
        private Topic() {
        }

        /**
         * 过车消息Topic
         */
        public static final String PASSING_VEHICLE = "cloud_passing_vehicle";

        public static final String PASSING_VEHICLE_FAIL = "cloud_passing_vehicle_fail";
        /**
         * 富尚过车消息Topic
         */
        public static final String PASSING_VEHICLE_FUSHANG = "cloud_passing_vehicle_fushang";
        public static final String PASSING_VEHICLE_FUSHANG_FAIL = "cloud_passing_vehicle_fushang_fail";
        public static final String PASSING_VEHICLE_FUSHANG_IMAGE = "cloud_passing_vehicle_fushang_image";

        public static final String PASSING_VEHICLE_FUSHANG_IMAGE_FAIL = "cloud_passing_vehicle_fushang_image_fail";

        public static final String HIKVISION_PASSING_IMAGE = "cloud_passing_hikvision_image";
        public static final String HIKVISION_PASSING_IMAGE_FAIL = "cloud_passing_hikvision_image_fail";
        /**
         * 地磁过车消息
         */
        public static final String MAGNETIC_PASSING = "cloud_passing_magnetic";
        public static final String MAGNETIC_PASSING_FAIL = "cloud_passing_magnetic_fail";
        public static final String INSPECT_PASS_RECORD = "cloud_pass_record_to_inspect";
        public static final String INSPECT_PASS_RECORD_FAIL = "cloud_pass_record_to_inspect_fail";
        public static final String PUSH_PARKING_RECORD = "cloud_push_single_parking_record";
        public static final String PUSH_PARKING_RECORD_FAIL = "cloud_push_single_parking_record_fail";
        public static final String PUSH_PARKING_ORDER = "cloud_push_single_parking_order";
        public static final String INSPECT_PASS_RECORD_NOTIFY = "cloud_pass_record_to_inspect_notify";
        public static final String INSPECT_PASS_RECORD_NOTIFY_FAIL = "cloud_pass_record_to_inspect_notify_fail";
        public static final String APPOINT_ORDER_CLOSE = "cloud_appoint_order_close";
        /**
         * 三方账单支付回调
         */
        public static final String ORDER_CALL_BACK = "cloud_third_order_call_back";
        /**
         * 第三方支付订单延迟Topic
         */
        public static final String DELAY_TRADE_PAYMENT = "cloud_delay_payment_order";

        /**
         * 会员订单支付成功
         */
        public static final String CLOUD_TRADE_CUSTOMER_PAY_SUCCESS = "cloud_customer_pay_success";

        public static final String TRADE_PAYMENT_RECORD = "cloud_trade_payment_record";
        //任意停车APP消息队列
        public static final String RECHARGE_SUCCESS = "any_recharge_success_message";
        public static final String PAY_SUCCESS_NOTIFY = "any_pay_success_notification_message";
        public static final String USER_PAY_ORDER = "any_user_pay_order_message";
        //客户端系统过车消息
        public static final String ANY_PARKING_PASSING_RECORD = "any_parking_passing_record";
        public static final String ANY_PARKING_PASSING_RECORD_IMAGE = "any_parking_passing_record_image";
        public static final String ANY_PARKING_PASSING_RECORD_IMAGE_FAIL = "any_parking_passing_record_image_fail";
        //停车图片消息
        public static final String ANY_PARKING_RECORD_IMAGE = "any_parking_record_image";
        public static final String ANY_PARKING_RECORD_IMAGE_FAIL = "any_parking_record_image_fail";
        public static final String ANY_PARKING_PASSING_RECORD_FAIL = "any_parking_passing_record_fail";

        public static final String THIRD_PAYMENT_NOTIFY_CALL_BACK = "third_payment_notify_call_back";
        public static final String THIRD_QUERY_PRICE_CALL_BACK = "third_query_price_call_back";
    }

    public static class ConsumeGroup {
        private ConsumeGroup() {
        }

        public static final String HIKVISION_PASSING = "cloud_hikvision_passing_consumer";
        public static final String HIKVISION_PASSING_IMAGE = "cloud_hikvision_passing_image_consumer";
        public static final String MAGNETIC_PASSING = "cloud_magnetic_passing_consumer";
        public static final String INSPECT_PASS_RECORD = "cloud_pass_record_to_inspect_consumer";
        public static final String PUSH_PARKING_RECORD = "cloud_push_single_parking_record_consumer";
        public static final String INSPECT_PASS_RECORD_NOTIFY = "cloud_pass_record_to_inspect_notify_consumer";
        public static final String APPOINT_ORDER_CLOSE = "cloud_appoint_order_close_consumer";
        public static final String ANY_PARKING_PASSING_RECORD = "any_parking_passing_record_consumer";
        public static final String ANY_PARKING_PASSING_RECORD_IMAGE = "any_parking_passing_record_image_consumer";
        public static final String TRADE_PAYMENT_RECORD = "cloud_trade_payment_record_consumer";
        public static final String ORDER_CALL_BACK = "third_order_call_back_consumer";
        public static final String THIRD_PAYMENT_NOTIFY_CALL_BACK = "third_payment_notify_call_back_consumer";
        public static final String THIRD_QUERY_PRICE_CALL_BACK = "third_query_price_call_back_consumer";

        public static final String ANY_PARKING_RECORD_IMAGE_CONSUMER = "any_parking_record_image_consumer";

        /**
         * 富尚过车图片
         */
        public static final String PASSING_VEHICLE_FUSHANG_CONSUMEGROUP = "passing_vehicle_fushang_consumegroup";

        public static final String PASSING_VEHICLE_IMAGE_FUSHANG_CONSUMEGROUP = "passing_vehicle_image_fushang_consumegroup";
        /**
         * 会员订单支付成功
         */
        public static final String CLOUD_TRADE_CUSTOMER_PAY_SUCCESS = "cloud_customer_pay_success_consumer";

    }

    public static class Sender {
        private Sender() {
        }

        public static final String GATHER = "cloud_parking_gather";
        public static final String INTEGRATION = "cloud_parking_integration";
        public static final String ORDER = "cloud_parking_order";
        public static final String PAY = "cloud_parking_pay";
        public static final String COLLECT = "cloud_parking_collect";
    }

    /**
     * 事务消息监听消息分类
     */
    public static class TxProducerGroup {
        private TxProducerGroup() {
        }

        /**
         * 用户停车推送
         */
        public static final String PARKING_ENTER_PUSH = "cloud_parking_enter_push";
        /**
         * 巡检入车推送
         */
        public static final String INSPECT_ENTER_PUSH = "cloud_inspect_enter_push";
        /**
         * 海康过车图像
         */
        public static final String HIKVISION_PASSING_IMAGE = "cloud_hik_passing_image";
    }
}
