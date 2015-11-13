package com.pingan.vo;

import com.paic.hyperion.core.hfjson.annotation.HFJsonField;
import com.paic.hyperion.core.hfjson.annotation.HFJsonObject;
import com.paic.hyperion.model.BaseModel;

import java.util.List;


@HFJsonObject
public class ResponseData7 extends BaseModel {
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
        private List<Category> categoryList;

        public void setCategoryList(List<Category> categoryList) {
            this.categoryList = categoryList;
        }

        public List<Category> getCategoryList() {
            return this.categoryList;
        }

        @HFJsonObject
        public static class Category {
            @HFJsonField
            private String category;
            @HFJsonField
            private List<Product> productList;

            public void setCategory(String category) {
                this.category = category;
            }

            public String getCategory() {
                return this.category;
            }

            public void setProductList(List<Product> productList) {
                this.productList = productList;
            }

            public List<Product> getProductList() {
                return this.productList;
            }

            @HFJsonObject
            public static class Product {
                @HFJsonField
                private String title;
                @HFJsonField
                private String yieldRate;
                @HFJsonField
                private String rateDescribe;
                @HFJsonField
                private String limit;
                @HFJsonField
                private String productId;
                @HFJsonField
                private String productType;
                @HFJsonField
                private String premium;
                @HFJsonField
                private String coverage;
                @HFJsonField
                private String duration;

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getTitle() {
                    return this.title;
                }

                public void setYieldRate(String yieldRate) {
                    this.yieldRate = yieldRate;
                }

                public String getYieldRate() {
                    return this.yieldRate;
                }

                public void setRateDescribe(String rateDescribe) {
                    this.rateDescribe = rateDescribe;
                }

                public String getRateDescribe() {
                    return this.rateDescribe;
                }

                public void setLimit(String limit) {
                    this.limit = limit;
                }

                public String getLimit() {
                    return this.limit;
                }

                public void setProductId(String productId) {
                    this.productId = productId;
                }

                public String getProductId() {
                    return this.productId;
                }

                public void setProductType(String productType) {
                    this.productType = productType;
                }

                public String getProductType() {
                    return this.productType;
                }

                public void setPremium(String premium) {
                    this.premium = premium;
                }

                public String getPremium() {
                    return this.premium;
                }

                public void setCoverage(String coverage) {
                    this.coverage = coverage;
                }

                public String getCoverage() {
                    return this.coverage;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                public String getDuration() {
                    return this.duration;
                }
            }
        }
    }
}
