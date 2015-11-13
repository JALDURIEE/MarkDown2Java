package com.pingan.vo;

import com.paic.hyperion.core.hfjson.annotation.HFJsonField;
import com.paic.hyperion.core.hfjson.annotation.HFJsonObject;
import com.paic.hyperion.model.BaseModel;

import java.util.List;


@HFJsonObject
public class ResponseData10 extends BaseModel {
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
            private String categoryName;
            @HFJsonField
            private List<Type> typeList;

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public String getCategoryName() {
                return this.categoryName;
            }

            public void setTypeList(List<Type> typeList) {
                this.typeList = typeList;
            }

            public List<Type> getTypeList() {
                return this.typeList;
            }

            @HFJsonObject
            public static class Type {
                @HFJsonField
                private String typeId;
                @HFJsonField
                private String typeName;
                @HFJsonField
                private String isSelected;

                public void setTypeId(String typeId) {
                    this.typeId = typeId;
                }

                public String getTypeId() {
                    return this.typeId;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }

                public String getTypeName() {
                    return this.typeName;
                }

                public void setIsSelected(String isSelected) {
                    this.isSelected = isSelected;
                }

                public String getIsSelected() {
                    return this.isSelected;
                }
            }
        }
    }
}
