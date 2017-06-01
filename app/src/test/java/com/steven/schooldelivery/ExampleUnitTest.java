package com.steven.schooldelivery;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.http.gson.OrderStateJson;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        // assertEquals(4, 2 + 2);
        Gson gson = new Gson();
        String s ="{\"status\":200,\"message\":\"ok\",\"data\":[{\"orderId\":\"20170520100000\",\"orderState\":1,\"changeTime\":1495275299000,\"id\":12,\"order\":null}]}";
        HttpResponse response = JSON.parseObject(s,HttpResponse.class);
        System.out.println(response.getData().toString());
        JSONArray array = (JSONArray) response.getData();
        // JSONArray objects = JSONArray.parseArray(response.getData().toString());
        OrderStateJson orderState = ((JSONObject)array.get(0)).toJavaObject(OrderStateJson.class);


        // HttpResponse response1= gson.fromJson(s,HttpResponse.class);
        // System.out.println(response1.getData().toString());
        // OrderStateJson orderState1 =gson.fromJson(response1.getData().toString(),OrderStateJson.class);

// String a = "{\"status\":200,\"message\":\"ok\",\"data\":{\"id\":\"20170521100000\",\"createTime\":1495363901000,\"finishTime\":null,\"grade\":null,\"price\":10.0,\"remark\":null,\"state\":1,\"recipientId\":\"1000000018\",\"replacementId\":null,\"recipient\":{\"uid\":\"1000000018\",\"name\":\"steven\",\"phone\":\"13052116492\",\"password\":\"123\",\"identity\":0,\"schoolCard\":\"140153018\",\"idCard\":\"1111\",\"photo\":\"test\",\"aliPay\":\"111111\",\"sex\":\"男\",\"schoolName\":\"上师大\",\"schoolAddress\":\"地\"},\"replacement\":null,\"expressName\":\"zhong\",\"expressCode\":\"123\",\"pickupTime\":1495350000000,\"pickupAddress\":\"di\",\"pickupCode\":\"f\",\"deliveryTime\":1495468800000,\"deliveryAddress\":\"f\"}}";
//         HttpResponse response2 = JSON.parseObject(a,HttpResponse.class);
//         System.out.println(response2.getData().toString());
//         DetailedOrder detailedOrder = gson.fromJson(response2.getData().toString(),DetailedOrder.class);
//         System.out.println(detailedOrder.getFinishtime());
    }

    class Us{
        @SerializedName("id")
        String uid;
        @SerializedName("n")
        String name;

        public void setUid(String id) {
            this.uid = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {

            return uid;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "id:"+uid+" name:"+name;
        }
    }
}