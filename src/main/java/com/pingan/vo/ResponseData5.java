package com.pingan.vo;

import com.paic.hyperion.core.hfjson.annotation.HFJsonField;
import com.paic.hyperion.core.hfjson.annotation.HFJsonObject;
import com.paic.hyperion.model.BaseModel;

import java.util.List;


@HFJsonObject
public class ResponseData5 extends BaseModel {
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
        private List<Expert> expertList;

        public void setExpertList(List<Expert> expertList) {
            this.expertList = expertList;
        }

        public List<Expert> getExpertList() {
            return this.expertList;
        }

        @HFJsonObject
        public static class Expert {
            @HFJsonField
            private String expertId;
            @HFJsonField
            private String expertName;
            @HFJsonField
            private String headUrl;
            @HFJsonField
            private String jobTitle;
            @HFJsonField
            private String resume;
            @HFJsonField
            private String newestInfo;

            public void setExpertId(String expertId) {
                this.expertId = expertId;
            }

            public String getExpertId() {
                return this.expertId;
            }

            public void setExpertName(String expertName) {
                this.expertName = expertName;
            }

            public String getExpertName() {
                return this.expertName;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getHeadUrl() {
                return this.headUrl;
            }

            public void setJobTitle(String jobTitle) {
                this.jobTitle = jobTitle;
            }

            public String getJobTitle() {
                return this.jobTitle;
            }

            public void setResume(String resume) {
                this.resume = resume;
            }

            public String getResume() {
                return this.resume;
            }

            public void setNewestInfo(String newestInfo) {
                this.newestInfo = newestInfo;
            }

            public String getNewestInfo() {
                return this.newestInfo;
            }
        }
    }
}
