package com.pingan.vo;

import com.paic.hyperion.core.hfjson.annotation.HFJsonField;
import com.paic.hyperion.core.hfjson.annotation.HFJsonObject;
import com.paic.hyperion.model.BaseModel;

import com.pingan.vo.ResponseData6.Data.Group;

import java.util.List;


@HFJsonObject
public class ResponseData6 extends BaseModel {
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
        private List<Group> defaultList;
        @HFJsonField
        private List<TypeGroup> unDefaultList;

        public void setDefaultList(List<Group> defaultList) {
            this.defaultList = defaultList;
        }

        public List<Group> getDefaultList() {
            return this.defaultList;
        }

        public void setUnDefaultList(List<TypeGroup> unDefaultList) {
            this.unDefaultList = unDefaultList;
        }

        public List<TypeGroup> getUnDefaultList() {
            return this.unDefaultList;
        }

        @HFJsonObject
        public static class Group {
            private String classname;
            @HFJsonField
            private String publisherId;
            @HFJsonField
            private String imageUrl;
            @HFJsonField
            private String publisher;
            @HFJsonField
            private String resume;
            @HFJsonField
            private String description;
            @HFJsonField
            private String isSubscribed;

            public void setPublisherId(String publisherId) {
                this.publisherId = publisherId;
            }

            public String getPublisherId() {
                return this.publisherId;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getImageUrl() {
                return this.imageUrl;
            }

            public void setPublisher(String publisher) {
                this.publisher = publisher;
            }

            public String getPublisher() {
                return this.publisher;
            }

            public void setResume(String resume) {
                this.resume = resume;
            }

            public String getResume() {
                return this.resume;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDescription() {
                return this.description;
            }

            public void setIsSubscribed(String isSubscribed) {
                this.isSubscribed = isSubscribed;
            }

            public String getIsSubscribed() {
                return this.isSubscribed;
            }
        }

        @HFJsonObject
        public static class TypeGroup {
            private String classname;
            @HFJsonField
            private String typeName;
            @HFJsonField
            private List<Group> groupList;

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getTypeName() {
                return this.typeName;
            }

            public void setGroupList(List<Group> groupList) {
                this.groupList = groupList;
            }

            public List<Group> getGroupList() {
                return this.groupList;
            }
        }
    }
}
