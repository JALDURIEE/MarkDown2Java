package com.pingan.vo;

import com.paic.hyperion.core.hfjson.annotation.HFJsonField;
import com.paic.hyperion.core.hfjson.annotation.HFJsonObject;
import com.paic.hyperion.model.BaseModel;

import java.util.List;


@HFJsonObject
public class ResponseData3 extends BaseModel {
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
        private List<Ad> adList;

        public void setAdList(List<Ad> adList) {
            this.adList = adList;
        }

        public List<Ad> getAdList() {
            return this.adList;
        }

        @HFJsonObject
        public static class Ad {
            @HFJsonField
            private String imageUrl;
            @HFJsonField
            private String linkUrl;

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getImageUrl() {
                return this.imageUrl;
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
