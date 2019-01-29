package com.example.admin.tomaszgurgul;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Array {
    @Expose
    private List<Array_> array = null;

    public List<Array_> getArray() {
        return array;
    }

    public void setArray(List<Array_> array) {
        this.array = array;
    }

    public class Array_ {
        @Expose
        private String title;
        @Expose
        private String desc;
        @Expose
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}