package com.pro.foru.bean;

import java.util.List;

/**
 * Created by zuoyun on 2016/7/14.
 */

public class ShopList {
    public List<ShopData> shopData ;

    public class ShopData {
        public String shop_id;
        public String shop_name;
        public String shop_detail;
        public String shop_icon;
        public String shop_loc;
        public String shop_time;
    }

}
