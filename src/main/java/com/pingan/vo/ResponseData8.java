package com.pingan.vo;

import com.paic.hyperion.core.hfjson.annotation.HFJsonField;
import com.paic.hyperion.core.hfjson.annotation.HFJsonObject;
import com.paic.hyperion.model.BaseModel;

import java.util.List;


@HFJsonObject
public class ResponseData8 extends BaseModel {
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
        @HFJsonField
        private Article article;

        public void setExpertList(List<Expert> expertList) {
            this.expertList = expertList;
        }

        public List<Expert> getExpertList() {
            return this.expertList;
        }

        public void setArticle(Article article) {
            this.article = article;
        }

        public Article getArticle() {
            return this.article;
        }

        @HFJsonObject
        public static class Expert {
            @HFJsonField
            private String headUrl;
            @HFJsonField
            private String expertId;
            @HFJsonField
            private String expertName;
            @HFJsonField
            private String jobTitle;
            @HFJsonField
            private String resume;
            @HFJsonField
            private String isSubscribed;

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getHeadUrl() {
                return this.headUrl;
            }

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

            public void setIsSubscribed(String isSubscribed) {
                this.isSubscribed = isSubscribed;
            }

            public String getIsSubscribed() {
                return this.isSubscribed;
            }
        }

        @HFJsonObject
        public static class Article {
            @HFJsonField
            private String articleId;
            @HFJsonField
            private String publisherId;
            @HFJsonField
            private String title;
            @HFJsonField
            private String publisher;
            @HFJsonField
            private String linkUrl;
            @HFJsonField
            private String resume;
            @HFJsonField
            private String totalLike;
            @HFJsonField
            private String totalComment;

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

            public void setPublisher(String publisher) {
                this.publisher = publisher;
            }

            public String getPublisher() {
                return this.publisher;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }

            public String getLinkUrl() {
                return this.linkUrl;
            }

            public void setResume(String resume) {
                this.resume = resume;
            }

            public String getResume() {
                return this.resume;
            }

            public void setTotalLike(String totalLike) {
                this.totalLike = totalLike;
            }

            public String getTotalLike() {
                return this.totalLike;
            }

            public void setTotalComment(String totalComment) {
                this.totalComment = totalComment;
            }

            public String getTotalComment() {
                return this.totalComment;
            }
        }
    }
}
