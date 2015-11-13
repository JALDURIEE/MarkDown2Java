package com.pingan.vo;

import com.paic.hyperion.core.hfjson.annotation.HFJsonField;
import com.paic.hyperion.core.hfjson.annotation.HFJsonObject;
import com.paic.hyperion.model.BaseModel;


@HFJsonObject
public class ResponseData2 extends BaseModel {
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
        private String headUrl;
        @HFJsonField
        private String name;
        @HFJsonField
        private String totalAsset;
        @HFJsonField
        private String assetVisible;
        @HFJsonField
        private String wealthScore;
        @HFJsonField
        private String wealthBalance;
        @HFJsonField
        private String investment;
        @HFJsonField
        private String predictProfit;
        @HFJsonField
        private String yieldRate;
        @HFJsonField
        private String creditScore;
        @HFJsonField
        private String maxCreditScore;
        @HFJsonField
        private String creditDescribe;
        @HFJsonField
        private String rewardScore;

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getHeadUrl() {
            return this.headUrl;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setTotalAsset(String totalAsset) {
            this.totalAsset = totalAsset;
        }

        public String getTotalAsset() {
            return this.totalAsset;
        }

        public void setAssetVisible(String assetVisible) {
            this.assetVisible = assetVisible;
        }

        public String getAssetVisible() {
            return this.assetVisible;
        }

        public void setWealthScore(String wealthScore) {
            this.wealthScore = wealthScore;
        }

        public String getWealthScore() {
            return this.wealthScore;
        }

        public void setWealthBalance(String wealthBalance) {
            this.wealthBalance = wealthBalance;
        }

        public String getWealthBalance() {
            return this.wealthBalance;
        }

        public void setInvestment(String investment) {
            this.investment = investment;
        }

        public String getInvestment() {
            return this.investment;
        }

        public void setPredictProfit(String predictProfit) {
            this.predictProfit = predictProfit;
        }

        public String getPredictProfit() {
            return this.predictProfit;
        }

        public void setYieldRate(String yieldRate) {
            this.yieldRate = yieldRate;
        }

        public String getYieldRate() {
            return this.yieldRate;
        }

        public void setCreditScore(String creditScore) {
            this.creditScore = creditScore;
        }

        public String getCreditScore() {
            return this.creditScore;
        }

        public void setMaxCreditScore(String maxCreditScore) {
            this.maxCreditScore = maxCreditScore;
        }

        public String getMaxCreditScore() {
            return this.maxCreditScore;
        }

        public void setCreditDescribe(String creditDescribe) {
            this.creditDescribe = creditDescribe;
        }

        public String getCreditDescribe() {
            return this.creditDescribe;
        }

        public void setRewardScore(String rewardScore) {
            this.rewardScore = rewardScore;
        }

        public String getRewardScore() {
            return this.rewardScore;
        }
    }
}
