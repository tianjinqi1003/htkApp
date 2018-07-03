package com.htkapp.modules.API.dto;

import com.htkapp.modules.merchant.common.dto.ReturnCommentInfo;

import java.util.List;

public class CommentInfo {

    private List<ReturnCommentInfo> list;

    private int count;

    public CommentInfo(List<ReturnCommentInfo> list, int count) {
        this.list = list;
        this.count = count;
    }

    public List<ReturnCommentInfo> getList() {
        return list;
    }

    public void setList(List<ReturnCommentInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
