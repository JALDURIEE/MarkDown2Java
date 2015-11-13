package com.pingan.vo;

import com.paic.hyperion.core.hfjson.annotation.HFJsonField;
import com.paic.hyperion.core.hfjson.annotation.HFJsonObject;
import com.paic.hyperion.model.BaseModel;

import java.util.List;


@HFJsonObject
public class ResponseData4 extends BaseModel {
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
        private List<Recommend> recommendList;

        public void setRecommendList(List<Recommend> recommendList) {
            this.recommendList = recommendList;
        }

        public List<Recommend> getRecommendList() {
            return this.recommendList;
        }

        @HFJsonObject
        public static class Recommend {
            @HFJsonField
            private String articleId;
            @HFJsonField
            private String publisherId;
            @HFJsonField
            private String title;
            @HFJsonField
            private String resume;
            @HFJsonField
            private String publisher;
            @HFJsonField
            private String time;
            @HFJsonField
            private String imageUrl;
            @HFJsonField
            private String linkUrl;

            public void setArticleId(String articleId) {
                this.articleId = articleId;
            }

            public String getArticleId() {
                return this.articleId;
            }

            public void setPublisherId(String publisherId) {
                this.publisherId = publisherId;
            }

            public String getPublisherId() {
                return this.publisherId;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitle() {
                return this.title;
            }

            public void setResume(String resume) {
                this.resume = resume;
            }

            public String getResume() {
                return this.resume;
            }

            public void setPublisher(String publisher) {
                this.publisher = publisher;
            }

            public String getPublisher() {
                return this.publisher;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTime() {
                return this.time;
            }

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
