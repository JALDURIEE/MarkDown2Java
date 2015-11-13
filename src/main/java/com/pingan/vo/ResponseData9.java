package com.pingan.vo;

import com.paic.hyperion.core.hfjson.annotation.HFJsonField;
import com.paic.hyperion.core.hfjson.annotation.HFJsonObject;
import com.paic.hyperion.model.BaseModel;

import java.util.List;


@HFJsonObject
public class ResponseData9 extends BaseModel {
    @HFJsonField
    private String code;
    @HFJsonField
    private String msg;
    @HFJsonField
    private Data data;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    @HFJsonObject
    public static class Data {
        @HFJsonField
        private List<RewardMallProduct> rewardMallProductList;
        @HFJsonField
        private List<LocalRecommendProduct> localRecommendProductList;

        public void setRewardMallProductList(
            List<RewardMallProduct> rewardMallProductList) {
            this.rewardMallProductList = rewardMallProductList;
        }

        public List<RewardMallProduct> getRewardMallProductList() {
            return this.rewardMallProductList;
        }

        public void setLocalRecommendProductList(
            List<LocalRecommendProduct> localRecommendProductList) {
            this.localRecommendProductList = localRecommendProductList;
        }

        public List<LocalRecommendProduct> getLocalRecommendProductList() {
            return this.localRecommendProductList;
        }

        @HFJsonObject
        public static class RewardMallProduct {
            @HFJsonField
            private String productId;
            @HFJsonField
            private String imageUrl;
            @HFJsonField
            private String rewardPrice;
            @HFJsonField
            private String title;
            @HFJsonField
            private String linkUrl;

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getProductId() {
                return this.productId;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getImageUrl() {
                return this.imageUrl;
            }

            public void setRewardPrice(String rewardPrice) {
                this.rewardPrice = rewardPrice;
            }

            public String getRewardPrice() {
                return this.rewardPrice;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitle() {
                return this.title;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }

            public String getLinkUrl() {
                return this.linkUrl;
            }
        }

        @HFJsonObject
        public static class LocalRecommendProduct {
            @HFJsonField
            private String productId;
            @HFJsonField
            private String imageUrl;
            @HFJsonField
            private String title;
            @HFJsonField
            private String originPrice;
            @HFJsonField
            private String nowPrice;
            @HFJsonField
            private String linkUrl;

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getProductId() {
                return this.productId;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getImageUrl() {
                return this.imageUrl;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitle() {
                return this.title;
            }

            public void setOriginPrice(String originPrice) {
                this.originPrice = originPrice;
            }

            public String getOriginPrice() {
                return this.originPrice;
            }

            public void setNowPrice(String nowPrice) {
                this.nowPrice = nowPrice;
            }

            public String getNowPrice() {
                return this.nowPrice;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }

            public String getLinkUrl() {
                return this.linkUrl;
            }
        }
    }
}
